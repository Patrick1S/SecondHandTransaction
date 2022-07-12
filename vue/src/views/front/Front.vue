<template>
  <div>
<!--    头部-->
    <div style="display:flex; height: 60px; line-height: 60px; border-bottom: 1px solid #ccc">
      <div style="width: 300px; display:flex;padding-left: 30px">
        <div style="width: 60px;">
          <img src="../../assets/logo.png" alt="" style="display: inline-block;  width: 50px; position: relative; top: 5px; right: 0;">
        </div>
        <div>欢迎来到xx系统</div>
      </div>
      <!--        导航菜单-->
      <div style="flex: 1">
        <el-menu :default-active="1" class="el-menu-demo" mode="horizontal" router >
          <el-menu-item index="/front/home">首页</el-menu-item>
          <el-menu-item index="/front/sechand">二手交易</el-menu-item>
          <el-menu-item index="/front/cart">我的购物车</el-menu-item>


          <el-submenu index="2">
            <template slot="title">我的订单</template>
            <el-menu-item index="/front/order">订单界面</el-menu-item>
            <el-menu-item index="/front/allOrder">所有订单</el-menu-item>
            <el-menu-item index="/front/waitPaymentOrder">待付款</el-menu-item>
            <el-menu-item index="/front/waitTransportOrder">待发货</el-menu-item>
            <el-menu-item index="/front/waitReceiveOrder">已发货</el-menu-item>
            <el-menu-item index="/front/confirmReceiveOrder">待收货</el-menu-item>
            <el-menu-item index="/front/waitCommentOrder">待评价</el-menu-item>
            <el-menu-item index="/front/buyerEvaluate">我的评价</el-menu-item>
            <el-menu-item index="/front/buyerRciEvaluate">我收到的评价</el-menu-item>
            <el-menu-item index="/front/resultAfterSales">售后结果</el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">商品处理</template>
            <el-menu-item index="/front/sellerSendGoods">订单发货</el-menu-item>
            <el-menu-item index="/front/sellerUpdateTransInfo">物流更新</el-menu-item>
            <el-menu-item index="/front/sellerCommentOrder">订单评价</el-menu-item>
            <el-menu-item index="/front/sellerEvaluate">对用户的评价</el-menu-item>
            <el-menu-item index="/front/sellerRciEvaluate">用户评价反馈</el-menu-item>
            <el-menu-item index="/front/waitAfterSales">退货审核</el-menu-item>
            <el-menu-item index="/front/sellerGoods">下架商品</el-menu-item>
            <el-menu-item index="/front/sellerGoodsDisable">已下架商品</el-menu-item>
<!--            <el-menu-item index="/front/waitReceiveOrder">已发货</el-menu-item>-->
          </el-submenu>

          <el-menu-item index="/front/goodsWindow" @click="$router.push('/front/goodsWindow?id=' + loginUser.id)">商品橱窗</el-menu-item>

<!--          <el-submenu index="2">-->
<!--            <template slot="title">我的订单</template>-->
<!--            <el-menu-item index="/front/order">待付款</el-menu-item>-->
<!--            <el-menu-item index="2-2">选项2</el-menu-item>-->
<!--            <el-menu-item index="2-3">选项3</el-menu-item>-->
<!--&lt;!&ndash;            <el-submenu index="2-4">&ndash;&gt;-->
<!--&lt;!&ndash;              <template slot="title">选项4</template>&ndash;&gt;-->
<!--&lt;!&ndash;              <el-menu-item index="2-4-1">选项1</el-menu-item>&ndash;&gt;-->
<!--&lt;!&ndash;              <el-menu-item index="2-4-2">选项2</el-menu-item>&ndash;&gt;-->
<!--&lt;!&ndash;              <el-menu-item index="2-4-3">选项3</el-menu-item>&ndash;&gt;-->
<!--&lt;!&ndash;            </el-submenu>&ndash;&gt;-->
<!--          </el-submenu>-->

          <el-submenu index="4">
            <template slot="title">个人中心</template>
            <el-menu-item index="/front/buyerAccount">我的钱包</el-menu-item>
            <el-menu-item index="/front/buyerBills">账单</el-menu-item>
<!--            <el-menu-item index="/front/sellerEvaluate">支付记录</el-menu-item>-->
            <!--            <el-menu-item index="/front/waitReceiveOrder">已发货</el-menu-item>-->
          </el-submenu>
<!--          <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>-->
        </el-menu>
      </div>
      <div style="width: 200px">
        <div v-if="!loginUser.username" style="text-align: right; padding-right: 30px">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown style="width: 150px; text-align: right;cursor: pointer">
            <div style="display: inline-block">
              <img :src="loginUser.avatarUrl" alt=""
                   style="width: 30px;border-radius: 50%;position: relative;top: 10px; right: 5px">
              <span>{{loginUser.nickname}}</span>
              <span style="padding-left: 15px">等级:</span>
              <span>{{loginUser.grade}}</span>
              <i class="el-icon-arrow-down" style="margin-left: 5px;"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
              <el-dropdown-item style="font-size: 14px;padding: 5px 0" >
                <span style="text-decoration: none" @click="personInfo">个人信息</span>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px;padding: 5px 0">
                <span style="text-decoration: none" @click="logout">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <div style="width: 1200px; margin: 0 auto;">
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  name: "Front",
  data() {
    return{
      loginUser:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods:{
    logout(){
      // this.$router.push("/login")
      localStorage.clear()
      localStorage.removeItem("user")
      this.$message.success("退出成功")
      this.$router.go(0)
      location.reload()
    },
    personInfo(){
      this.$router.push("/person")
    },

  }
}
</script>

<style scoped>
  .item{
    display: inline-block;
    width: 100px;
    text-align: center;
    cursor: pointer
  }
  .item a{
    color: #1E90FF;

  }
  .item:hover{
    background-color: LightPink;
  }
</style>