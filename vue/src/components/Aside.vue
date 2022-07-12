<template>
  <el-menu :default-openeds="opens" style="height: 100%; min-height: 100%; overflow-x: hidden;"
           background-color="rgb(48,65,86)"
           text-color="#EEF1F6FF" active-text-color="#E8B729FF" :collapse-transition="false" :collapse="isCollapse"
           router
           @select="handleSelect"
  >
    <div style="height: 60px;line-height: 60px;text-align: center">
      <img src="../assets/logo.png" style="width: 25px; position: relative; top: 7px; margin-right: 9px" >
      <b style="color: white" v-show="logoTextShow">后台管理系统</b>
    </div>

    <div v-for="item in menus" :key="item.id">
      <div v-if="item.path">
        <el-menu-item :index="item.path"  >
          <i :class="item.icon"></i>
          <span slot="title">{{item.name}}</span>
        </el-menu-item>
      </div>
      <div v-else>
        <el-submenu :index="item.id + ''">
          <template slot="title">
            <i :class="item.icon"></i>
            <span slot="title">{{item.name}}</span>
          </template>
          <div v-for="subItem in item.children" :key="subItem.id">
            <el-menu-item :index="subItem.path"  >
              <i :class="subItem.icon"></i>
              <span slot="title">{{subItem.name}}</span>
            </el-menu-item>
          </div>
        </el-submenu>
      </div>
    </div>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapse:Boolean,
    logoTextShow:Boolean,

  },
  data(){
    return{
      menus:localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")) : [],
      opens:localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")).map(v => v.id + '') : [],
    }
  },
  methods:{
    handleSelect(){
      console.log(this.$route)
    }
  }
}
</script>

<style scoped>

</style>