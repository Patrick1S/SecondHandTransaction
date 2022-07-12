import Vue from 'vue'
import VueRouter from 'vue-router'
import de from "element-ui/src/locale/lang/de";
// import store from "@/store";

Vue.use(VueRouter)

const routes = [
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component:() => import('../views/Login.vue'),
    },
    {
        path: '/register',
        name: 'Register',
        component:() => import('../views/Register.vue'),
    },
    {
        path: '/404',
        name: '404',
        component:() => import('../views/404.vue'),
    },
    {
        path: '/front',
        name: 'Front',
        component:() => import('../views/front/Front.vue'),
        children: [
            {
                path: 'home',
                name: 'FrontHome',
                component:() => import('../views/front/Home.vue'),
            },
            {
                path: 'detail',
                name: 'Detail',
                component:() => import('../views/front/Detail.vue'),
            },
            {
                path: 'sechand',
                name: 'Sechand',
                component:() => import('../views/front/Sechand.vue'),
            },
            {
                path: 'secdetail',
                name: 'Secdetail',
                component:() => import('../views/front/Secdetail.vue'),
            },
            {
                path: 'cart',
                name: 'Cart',
                component:() => import('../views/front/Cart.vue'),
            },
            {
                path: 'order',
                name: 'Order',
                component:() => import('../views/front/Orders.vue'),
            },
            {
                path: 'allOrder',
                name: 'AllOrder',
                component:() => import('../views/front/AllOrder.vue'),
            },
            {
                path: 'waitPaymentOrder',
                name: 'WaitPaymentOrder',
                component:() => import('../views/front/waitPaymentOrder.vue'),
            },
            {
                path: 'waitTransportOrder',
                name: 'WaitTransportOrder',
                component:() => import('../views/front/waitTransportOrder.vue'),
            },
            {
                path: 'waitReceiveOrder',
                name: 'WaitReceiveOrder',
                component:() => import('../views/front/waitReceiveOrder.vue'),
            },
            {
                path: 'confirmReceiveOrder',
                name: 'ConfirmReceiveOrder.',
                component:() => import('../views/front/confirmReceiveOrder.vue'),
            },
            {
                path: 'waitCommentOrder',
                name: 'WaitCommentOrder.',
                component:() => import('../views/front/waitCommentOrder.vue'),
            },
            {
                path: 'goodsWindow',
                name: 'GoodsWindow',
                component:() => import('../views/front/GoodsWindow.vue'),
            },
            {
                path: 'sellerSendGoods',
                name: 'SellerSendGoods',
                component:() => import('../views/front/sellerSendGoods.vue'),
            },
            {
                path: 'sellerUpdateTransInfo',
                name: 'SellerUpdateTransInfo',
                component:() => import('../views/front/sellerUpdateTransInfo.vue'),
            },
            {
                path: 'sellerCommentOrder',
                name: 'SellerCommentOrder',
                component:() => import('../views/front/sellerCommentOrder.vue'),
            },
            {
                path: 'sellerRciEvaluate',
                name: 'SellerRciEvaluate',
                component:() => import('../views/front/sellerRciEvaluate.vue'),
            },
            {
                path: 'sellerEvaluate',
                name: 'SellerEvaluate',
                component:() => import('../views/front/sellerEvaluate.vue'),
            },
            {
                path: 'buyerEvaluate',
                name: 'BuyerEvaluate',
                component:() => import('../views/front/buyerEvaluate.vue'),
            },
            {
                path: 'buyerRciEvaluate',
                name: 'BuyerRciEvaluate',
                component:() => import('../views/front/buyerRciEvaluate.vue'),
            },
            {
                path: 'waitAfterSales',
                name: 'WaitAfterSales',
                component:() => import('../views/front/waitAfterSales.vue'),
            },
            {
                path: 'resultAfterSales',
                name: 'ResultAfterSales',
                component:() => import('../views/front/resultAfterSales.vue'),
            },
            {
                path: 'sellerGoods',
                name: 'SellerGoods',
                component:() => import('../views/front/sellerGoods.vue'),
            },
            {
                path: 'sellerGoodsDisable',
                name: 'SellerGoodsDisable',
                component:() => import('../views/front/sellerGoodsDisable.vue'),
            },
            {
                path: 'buyerAccount',
                name: 'BuyerAccount',
                component:() => import('../views/front/buyerAccount.vue'),
            },
            {
                path: 'buyerBills',
                name: 'BuyerBills',
                component:() => import('../views/front/buyerBills.vue'),
            },

        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})


//提供一个重置路由的方法
export const resetRouter = () => {
    router.matcher = new VueRouter({
        mode:'history',
        base:process.env.BASE_URL,
        routes
    })
}


//注意：刷新页面会导致页面路由重置
export const setRoutes = () => {
    const storeMenu = localStorage.getItem("menus");
    if(storeMenu){
        //拼装动态路由
        const manageRoute = {
            path: '/',
            name: 'Manage',
            component: () => import('../views/Manage.vue'),
            redirect:"/home",
            children:[
                {
                    path: 'person',
                    name: '个人信息',
                    component: () => import('../views/Person.vue'),
                }
            ]
        }
        const menus = JSON.parse(storeMenu)
        menus.forEach(item =>{
            if(item.path) {//当且仅当path不为空的时候才设置路由
                let itemMenu = {
                    path: item.path.replace("/", ""),
                    name: item.name,
                    component: () => import('../views/' + item.pagePath + '.vue')
                }
                manageRoute.children.push(itemMenu)
            }else if(item.children.length){
                item.children.forEach( item => {
                    if(item.path){
                        let itemMenu = {
                            path: item.path.replace("/", ""),
                            name: item.name,
                            component: () => import('../views/' + item.pagePath + '.vue')
                        }
                        manageRoute.children.push(itemMenu)
                    }
                    }
                )
            }
        })

        //获取当前的路由对象名称数组
        const currentRouteNames = router.getRoutes().map(v => v.name)
        if(!currentRouteNames.includes('Manage')){
            //动态添加到现在的路由对象中去
            router.addRoute(manageRoute)
        }

    }
}

//重置我就再set一下路由
setRoutes()

router.beforeEach((to ,from,next)=>{
    //未找到路由的情况
    if(!to.matched.length) {
        const storeMenus = localStorage.getItem("menus")
        if (storeMenus) {
            next("/404")
        } else {
            //跳回登陆界面
            next("/login")
        }
    }
    //其他情况有路由都放行
    next()
})

export default router
