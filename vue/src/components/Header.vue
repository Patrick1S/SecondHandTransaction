<template xmlns="http://www.w3.org/1999/html">
  <div style=" font-size: 12px; line-height: 60px; display: flex">
    <div style="flex: 1;font-size: 18px">
      <span :class="collapseBtnClass" style="font-size: 20px; width: 40px" @click="collapse"></span>

<!--      <el-breadcrumb separator-class="el-icon-arrow-right" style="display: inline-block">-->
<!--        <el-breadcrumb-item :to="'/'" >首页</el-breadcrumb-item>-->
<!--        <el-breadcrumb-item  >{{$route.name}}</el-breadcrumb-item>-->
<!--      </el-breadcrumb>-->

    </div>


    <el-dropdown style="width: 100%; text-align: right;cursor: pointer">
      <div style="display: inline-block">
        <img :src="user.avatarUrl" alt=""
             style="width: 30px;border-radius: 50%;position: relative;top: 10px; right: 5px">
      <span>{{user.nickname}}</span>
        <span style="padding-left: 15px">等级:</span>
        <span>{{user.grade}}</span>
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
</template>

<script>
export default {
  data(){
    return{
      // currentPathName:'',
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  name: "Header",
  props:{
    collapseBtnClass:String,
    collapse:Boolean,
  },
  methods:{
    logout(){
      this.$router.push("/login")
      localStorage.clear()
      // localStorage.removeItem("user")
      // this.$store.commit("logout")

      this.$message.success("退出成功")
      this.$router.go(0)
      location.reload()
    },
    personInfo(){
      this.$router.push("/person")
    }
  },
  // computed:{
  //   currentPathName(){
  //     return this.$store.state.currentPathName; //需要监听的数据
  //   }
  // },
  watch:{
    // 监听路由变化
    // '$route':function (){
    //   this.currentPathName = localStorage.getItem("currentPathName")//取出route里面设置的当前路由信息
    // }
    // currentPathName(newVal,oldVal){
    //   console.log(newVal)
    // }
  },
  created() {
    // console.log(this.$route)
  }
}
</script>

<style scoped>

</style>