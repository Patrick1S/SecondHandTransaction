<template>
  <div>

    <div style="font-size: 14px; margin-bottom: 15px;text-align: right">
      <span>账户余额：</span><span style="color: orangered;padding: 10px">¥ {{this.loginUser.account}}</span>
      <div style="padding: 10px">
        <span style="padding-left: 30px">账户积分：</span><span style="color: orangered">{{this.loginUser.points}}</span>
      </div>
    </div>



    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" size="medium"
              @selection-change="handleSelectionChange">
<!--      <el-table-column-->
<!--          type="selection"-->
<!--          width="55">-->
<!--      </el-table-column>-->
      <el-table-column prop="currentName" label="当前用户"></el-table-column>
      <el-table-column prop="buyerName" label="消费者"></el-table-column>
      <el-table-column prop="money" label="金额" ></el-table-column>
<!--      <el-table-column prop="name" label="订单名称(商品)"></el-table-column>-->
      <el-table-column prop="type" label="类型" ></el-table-column>
      <el-table-column prop="sellerName" label="消费商家"></el-table-column>
      <el-table-column prop="time" label="时间"></el-table-column>
<!--      <el-table-column prop="time" label="下单时间" ></el-table-column>-->
<!--      <el-table-column prop="userId" label="商家id" ></el-table-column>-->
    </el-table>

    <div style="padding: 10px 0">
      <div class="block">

        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>

    </div>



  </div>
</template>

<script>
export default {
  name: "User",
  data(){
    return{
      tableData: [],
      // goodsList:[],
      total:0,
      pageNum:1,
      pageSize:10,

      no:"",

      dialogFormVisible:false,
      evaluateDialogFormVisible:false,

      form:{},

      multipleSelection: [],
      headerBg:'headerBg',

      totalPrice:0,
      pointsAble:0,

      totalGoodsNum:0,
      val:[],

      loginUser:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},

      defaultAddress:1,
      addressList:[],

      selectAddress:'',
      settlePrice:0,

      consPoints:0,

      goodsEvaluate:[],
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    confirmOrder(){
      let ordersList =this.multipleSelection
      let user = {
        id:this.loginUser.id,
        account: this.totalPrice,
        points:this.consPoints
      }
      this.request.post("/user/updateAccount", user).then(res => {
        if(res.code === '200'){
          this.$message.success("支付成功")

          if(ordersList && ordersList.length){
            ordersList.forEach(item =>{

              let form = {
                id: item.id,
                state:'待发货',
              }
              this.request.post("/orders/paySuccess/updateState",form).then(res =>{
                // console.log(res)
                if(res.code === '200'){
                  this.$message.success("添加成功")
                  this.load()
                }else {
                  this.$message.error("添加失败")
                }
              })

            })
          }



          //
          // let ordersList =this.tableData

          // console.log(ids)
          // this.request.post("/orders/paySuccess",ordersList).then(res =>{
          //   // console.log(res)
          //   if(res.code === '200'){
          //     // this.$message.success("添加成功")
          //     this.load()
          //   }else {
          //     // this.$message.error("添加失败")
          //   }
          // })

        }else {
          this.$message.error(res.msg)
        }
      })




      this.payDialogFormVisible = false
    },

    cancelOrder(){
      this.payDialogFormVisible = false

    },
    finishEvaluate(){
      // let form = {
      //   id: this.goodsEvaluate.id,
      //   state:'交易完成',
      // }
      // this.request.post("/orders/paySuccess/updateState",form).then(res =>{
      //   // console.log(res)
      //   if(res.code === '200'){
      //     this.$message.success("添加成功")
      //     this.load()
      //   }else {
      //     this.$message.error("添加失败")
      //   }
      // })

      this.dialogFormVisible = false

    },
    handleEvaluate(row){

      this.dialogFormVisible = true

      this.goodsEvaluate = row


      // let user = {
      //   id:this.loginUser.id,
      //   account: row.totalPrice,
      //   points:row.consPoints
      // }
      // this.request.post("/user/updateAccount", user).then(res => {
      //   if(res.code === '200'){
      //     this.$message.success("支付成功")
      //
      //     let form = {
      //       id: row.id,
      //       state:'待发货',
      //     }
      //     this.request.post("/orders/paySuccess/updateState",form).then(res =>{
      //       // console.log(res)
      //       if(res.code === '200'){
      //         this.$message.success("添加成功")
      //         this.load()
      //       }else {
      //         this.$message.error("添加失败")
      //       }
      //     })
      //   }else {
      //     this.$message.error(res.msg)
      //   }
      // })

    },
    handleGoodsEvaluate(row){
      this.form = {}
      this.form.goodsId= row.id
      // this.form.userId = row.userId
      this.form.sellerId = this.loginUser.id
      this.form.buyerId = this.goodsEvaluate.loginId
      this.evaluateDialogFormVisible = true
    },

    saveEvaluate(){
      this.request.post("/seller-evaluate",this.form).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("添加成功")
          this.load()
        }else {
          this.$message.error("添加失败")
        }
      })

      this.evaluateDialogFormVisible = false
    },

    load(){
      // this.viewGoods(70)
      // console.log(this.viewGoods(70))
      this.request.get("/user/" + this.loginUser.id).then(res => {
            this.loginUser = res.data
          })


      this.request.get("/consum-records/frontPageBills", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          // name:this.name,
          // state:"交易完成"
          // email:this.email,
          // address:this.address,
        }
      }).then(
          res => {


            // console.log(res)
            this.tableData=res.data.records
            this.total=res.data.total

            // console.log(this.tableData)

            // if(this.tableData && this.tableData.length){
            //   this.tableData.forEach(item =>{
            //     this.totalPrice += item.totalPrice
            //   })
            // }
            //
            // // let loginUsePoints = this.totalPrice
            // //价格取两位小数
            // this.totalPrice = this.totalPrice.toFixed(2)

            // console.log(this.totalPrice)
            // console.log(usePoints)


            // if(this.loginUser.points >= loginUsePoints){
            //   this.pointsAble = loginUsePoints
            // }else {
            //   this.pointsAble = this.user.points
            // }

            

            // this.settlePrice = this.totalPrice

            // if(this.tableData && this.tableData){
            //   this.tableData.forEach(item =>{
            //     this.request.get("/orders/getGoodsById/" + item.id).then(res =>{
            //       item.goods = res.data
            //     })
            //   })
            // }

            console.log(this.tableData)

          }
      )

    },

    save(){
      this.request.post("/orders",this.form).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("添加成功")
          this.load()
        }else {
          this.$message.error("添加失败")
        }
      })
      this.dialogFormVisible = false
    },
    handleAdd(){
      this.form={}
      this.dialogFormVisible=true
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible =true
    },
    handleDel(id){
      this.request.delete("/orders/"+id).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("删除成功")
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },




    handleSelectionChange(val){

      this.multipleSelection = val
      this.totalPrice = 0
      this.consPoints = 0
      // this.totalGoodsNum = val.length

      //进行计算操作
      if(val && val.length){
        val.forEach(item =>{
          this.totalPrice += item.totalPrice
          this.consPoints += item.consPoints
        })
      }
      //价格取两位小数
      this.totalPrice = this.totalPrice.toFixed(2)

      // let usePoints = this.totalPrice

      // if(this.user.points > usePoints){
      //   this.pointsAble = usePoints
      // }
    },



    delBatch(){
      let ids =this.multipleSelection.map(value => value.id) //[{},{},{}] =>[1,2,3]
      this.request.post("/orders/del/batch",ids).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },



    reset(){
      this.no=""
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


    handleAvatarSuccess(res) {
      this.form.img = res
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 50;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 50MB!');
      }
      return isJPG && isLt2M;
    },

    changeAddress(val){
      // console.log(val)
      this.request.get("/user-address/" + val).then(
          res => {
            this.selectAddress = res.data
            // console.log(this.selectAddress)
          }
      )
    },

    selectPoints(val){
      if(val === true){
        this.settlePrice = this.totalPrice - (this.pointsAble / 100)
        this.consPoints = this.pointsAble
      }
      if(val === false){
        this.settlePrice = this.totalPrice
      }

      console.log(this.settlePrice)
    },

    settleAccount(){
      this.payDialogFormVisible = true
    },

  },


}
</script>

<style>
.headerBg{
  background-color: #afc9e5 !important;
}

 .avatar-uploader .el-upload {
   border: 1px dashed #d9d9d9;
   border-radius: 6px;
   cursor: pointer;
   position: relative;
   overflow: hidden;
 }
.avatar-uploader{
  /*text-align: center;*/
  padding-bottom: 20px;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}

</style>
