<template>
  <div>

    <div style="font-size: 20px; margin-bottom: 15px;text-align: left;margin-top: 40px">
      <span>账户余额：</span><span style="color: orangered;padding: 10px">¥ {{this.loginUser.account}}</span>
      <div style="padding-top: 10px">
        <span >账户积分：</span><span style="color: orangered">{{this.loginUser.points}}</span>
      </div>
    </div>

    <div style="padding: 10px 0">

      <el-button class="ml-5" size="large" type="primary" >提现</el-button>
      <el-button class="ml-5" size="large" type="danger" @click="recharge">充值</el-button>

    </div>

    <el-dialog :visible.sync="rechargeDialogFormVisible" width="30%" :show-close="false">
      <div style="font-size: 20px; margin-bottom: 40px">
        请输入充值金额¥
      </div>
      <Div>
        <el-input v-model="money" style="width: 300px; padding-bottom: 40px"></el-input>
      </Div>
      <div style="text-align: right">
        <el-button size="medium" type="error" @click="cancel">取消</el-button>
        <el-button size="medium" type="primary" @click="confirm">确定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "User",
  data(){
    return{

      rechargeDialogFormVisible:false,

      form:{},

      money:'',

      loginUser:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    recharge(){
      this.money = ''
      this.rechargeDialogFormVisible = true
    },
    cancel(){
      this.rechargeDialogFormVisible = false

    },
    confirm(){
      let user = {
        id:this.loginUser.id,
        account: this.money,
      }
      this.request.post("/user/rechargeAccount", user).then(res => {
        if(res.code === '200'){
          this.$message.success("支付成功")
        }else {
          this.$message.error(res.msg)
        }
      })

      this.form ={
        loginId:this.loginUser.id,
        money:this.money,
        type:"充值",
      }
    this.request.post("/consum-records", this.form).then(res => {
      if(res.code === '200'){
        // this.$message.success("支付成功")
      }else {
        this.$message.error(res.msg)
      }
    })

      for (let i = 0 ; i<= 10 ;i++){
        this.load()
      }


      this.rechargeDialogFormVisible = false
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
