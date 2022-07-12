<template>
  <div style="margin-bottom: 100px">
    <el-input v-model="name" style="width: 300px; height: 50px;margin-top: 20px" suffix-icon="el-icon-search" placeholder="请输入名称搜索"></el-input>
    <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
    <el-button class="ml-5" type="danger" @click="reset">重置</el-button>

<!--    <div style="margin: 10px 0">-->
<!--      <el-carousel height="450px" :interval="10000">-->
<!--        <el-carousel-item v-for="item in imgs" :key="item">-->
<!--          <img :src="item" alt="" style="width: 100%;">-->
<!--        </el-carousel-item>-->
<!--      </el-carousel>-->
<!--    </div>-->


    <div style="margin: 10px 0">
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in tableData" :key="item.id" style="margin-bottom: 10px; border-radius: 10px; overflow: hidden">
          <div style="border: 1px solid #ccc; padding-bottom: 10px; cursor: pointer"  @click="$router.push('/front/secdetail?id=' + item.id)">
            <img :src="item.img" alt="" style="width: 100% ;height: 290.5px">
            <div style="color: #666; padding: 5px; height: 50px; font-size: 18px"><b>{{item.name}}</b></div>
            <div style="color: #666; padding-left: 5px; height: 50px; font-size: 14px">{{item.description}}</div>
            <div style="color: orangered; padding-left: 5px; height: 30px; font-size: 14px">¥{{item.price}}</div>
            <div style="padding-left: 5px; height: 30px; font-size: 14px">发布用户：{{item.user}}</div>
            <div style="padding-left: 5px; height: 30px; font-size: 14px">发布时间：{{item.time}}</div>
<!--            <div style="padding: 10px"><el-button>购买</el-button></div>-->
          </div>
        </el-col>
      </el-row>
    </div>
  </div>

</template>

<script>
export default {
  name: "FrontHome",
  data(){
    return{
      tableData: [],//Array(10).fill(item),
      total:0,
      pageNum:1,
      pageSize:5,
      name:"",

      // imgs:[
      //   'https://img12.360buyimg.com/pop/s1180x940_jfs/t1/181448/7/24696/95508/629f3993Ea3999cd6/5fd507b91e22e632.jpg.webp',
      //   'https://img30.360buyimg.com/pop/s1180x940_jfs/t1/82989/7/19593/92190/629735abE6595c645/d666507760677ce3.jpg.webp',
      // ],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      //请求分页查询数据
      this.request.get("/secgoods/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          // email:this.email,
          // address:this.address,
        }
      }).then(
          res => {
            // console.log(res)
            this.tableData = res.data.records
            this.total = res.data.total
          }
      )
    },
    reset(){
      this.name=""
      this.load()
    },

    handleSizeChange(pageSize){
      // console.log(pageSize)
      this.pageSize=pageSize
      this.load()
    },

    handleCurrentChange(pageNum){
      // console.log(pageNum)
      this.pageNum=pageNum
      this.load()
    },
  }
}
</script>

<style scoped>

</style>