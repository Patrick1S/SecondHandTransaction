<template>
  <div class="home" style="height:100%">
    <el-container style="height: 100%; border: 0px solid #eee ; min-height: 100%">
      <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238,241,246);height: 100%; box-shadow: 2px 0 4px rgb(0 21 41)">
        <Aside  :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
      </el-aside>

      <el-container style="height:100%">
        <el-header style="border-bottom: 1px solid #ccc; ">
          <Header :collapseBtnClass="collapseBtnClass"  :collapse="collapse"/>
        </el-header>
        <el-main>
<!--          表示当前页面的子路由会在<router-view /> 里面展示-->
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>

// import request from "@/utils/request";
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',
  components:{
    Aside,
    Header,
  },
  data() {
    // const item = {
    //   date: '2016-05-02',
    //   name: '王小虎',
    //   address: '上海市普陀区金沙江路 1518 弄'
    // };
    return {

      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWidth:200,
      logoTextShow:true,

      // user:{},

    }
  },

  created() {
    //从后台获取数据
    this.getUser()
  },
  methods:{
    collapse(){ ///点击收缩按钮触发
      this.isCollapse= !this.isCollapse
      if(this.isCollapse){
        this.sideWidth=64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow=false
      }else {
        this.sideWidth=200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow=true
      }

    },
    // getUser() {
    //   let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
    //   if(username){
    //     //从后台获取User数据
    //     this.request.get("/user/username" + username).then(res =>{
    //       //从新赋值后台最新的User数据
    //       this.user = res.data
    //       console.log(res.data)
    //     })
    //   }
    // }

  }
};
</script>

<style>

</style>
