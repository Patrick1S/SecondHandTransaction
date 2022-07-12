<template>

  <div>
    <div style="padding: 20px">
      <div style="font-size: 20px">平台总交易额</div>
      <div style="font-size: 30px;color: orangered">{{this.totalPrice}}</div>
    </div>
    <div style="padding: 20px">
      <div style="font-size: 20px">平台总收益</div>
      <div style="font-size: 30px; color: orangered">{{this.totalProfit}}</div>
    </div>
  </div>


</template>

<script>
export default {
  name: "Home",
  data(){
    return{
      tableData: [],
      goodsList:[],
      total:0,
      pageNum:1,
      pageSize:5,

      name:"",

      dialogFormVisible:false,
      form:{},

      multipleSelection: [],
      headerBg:'headerBg',

      totalPrice:0,
      totalProfit:0
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods: {
    load(){
      //请求分页查询数据
      this.request.get("/orders/page", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.name,
          state:'交易完成',
          // email:this.email,
          // address:this.address,
        }
      }).then(
          res => {
            // console.log(res)
            this.tableData=res.data.records
            this.total=res.data.total


            let ordersList =this.tableData
            if(ordersList && ordersList.length){
              ordersList.forEach(item => {

                this.totalPrice = this.totalPrice + item.totalPrice

                if(this.totalPrice >5000){
                  this.totalProfit = this.totalProfit + item.totalPrice *0.1
                }else{
                  this.totalProfit = this.totalProfit + item.totalPrice *0.05
                }

            })
              this.totalPrice = this.totalPrice.toFixed(2)
              this.totalProfit = this.totalProfit.toFixed(2)
              // console.log(this.totalPrice)
              // console.log(this.totalProfit)
            }
          })

      // console.log(this.tableData)
    },
  }
}
</script>

<style scoped>

</style>