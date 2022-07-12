<template>
  <div>

    <div style="font-size: 14px; margin-bottom: 15px;text-align: right">
      <span>账户余额：</span><span style="color: orangered;padding: 10px">¥ {{this.loginUser.account}}</span>
      <div style="padding: 10px">
        <span style="padding-left: 30px">账户积分：</span><span style="color: orangered">{{this.loginUser.points}}</span>
      </div>
    </div>

    <div style="padding: 10px 0">
      <el-input v-model="no" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入订单号搜索"></el-input>
<!--      <el-input v-model="email" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入邮箱搜索" class="ml-5"></el-input>-->
<!--      <el-input v-model="address" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入地址搜索" class="ml-5"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="danger" @click="reset">重置</el-button>



      <div style="margin: 10px 0">
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除吗？"
            @confirm="delBatch"
        >
          <el-button slot="reference" type="danger" >批量删除 <i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>
      </div>

    </div>


    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" size="medium"
              @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>

      <el-table-column prop="no" label="订单编号" ></el-table-column>

      <el-table-column label="商品" align="center" width="500px">
        <template slot-scope="scope">
          <div  v-for="item in scope.row.goods" :key="item.id" style="height: 130px;text-align: left">
            <div >
              <el-descriptions class="margin-top"  :column="4" size="medium" :colon="false">
                <el-descriptions-item >
                  <img :src="item.img" alt="" style="width: 100px; height: 100px;">
<!--                  <span style="color: #666; height: 50px; font-size: 10px;text-align: center ">{{item.name}}</span>-->
                </el-descriptions-item>
                <el-descriptions-item >
<!--                  <img :src="item.img" alt="" style="width: 100px; height: 100px;">-->
                  <span style="color: #666; height: 50px; font-size: 10px;text-align: center ">{{item.name}}</span>
                </el-descriptions-item>
                <el-descriptions-item >
                  <span style="color: orangered; padding-left: 5px; height: 30px; font-size: 14px">¥{{item.price}}</span>
                </el-descriptions-item>
                <el-descriptions-item label="数量：" >
                  <span style="padding-left: 5px; height: 30px; font-size: 14px">{{item.num}}</span>
                </el-descriptions-item>
              </el-descriptions>
            </div>
          </div>



        </template>
      </el-table-column>

<!--      <el-table-column prop="name" label="订单名称(商品)"></el-table-column>-->
      <el-table-column prop="totalPrice" label="总金额" ></el-table-column>
      <el-table-column prop="state" label="订单状态" ></el-table-column>
      <el-table-column prop="time" label="下单时间" ></el-table-column>
<!--      <el-table-column prop="userId" label="商家id" ></el-table-column>-->
      <el-table-column prop="username" label="商家名称" >
        <template slot-scope="scope" style="text-align: center">
          <div>点击前往</div>
          <span style="color: orangered" @click="$router.push('/front/goodsWindow?id=' + scope.row.userId)">
            {{scope.row.username}}
          </span>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="180px" align="center">
        <template slot-scope="scope">
<!--          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>-->
          <div v-if="scope.row.state === '待付款'">
            <el-popconfirm
                class="ml-5"
                confirm-button-text='确定'
                cancel-button-text='取消'
                icon="el-icon-info"
                icon-color="red"
                title="您确定付款吗？"
                @confirm="handleApply(scope.row)"
            >
              <el-button slot="reference" type="danger" >付款<i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
          </div>

          <div v-else>
            <el-popconfirm
                class="ml-5"
                confirm-button-text='确定'
                cancel-button-text='取消'
                icon="el-icon-info"
                icon-color="red"
                title="您确定删除吗？"
                @confirm="handleDel(scope.row.id)"
            >
              <el-button slot="reference" type="danger" >删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
          </div>

        </template>
      </el-table-column>
    </el-table>

    <div style="padding: 10px 0">
      <div class="block">

        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[2, 5, 10, 20]"
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
      pageSize:5,

      no:"",

      dialogFormVisible:false,
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

      payDialogFormVisible:false,

      consPoints:0,
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
    handleApply(row){

      let user = {
        id:this.loginUser.id,
        account: row.totalPrice,
        points:row.consPoints
      }
      this.request.post("/user/updateAccount", user).then(res => {
        if(res.code === '200'){
          this.$message.success("支付成功")

          let form = {
            id: row.id,
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
        }else {
          this.$message.error(res.msg)
        }
      })

    },
    load(){
      // this.viewGoods(70)
      // console.log(this.viewGoods(70))
      this.request.get("/user/" + this.loginUser.id).then(res => {
            this.loginUser = res.data
          })

      //查询用户地址
      this.request.get("/user-address/userAddressPage", {
        params: {
          userId: this.loginUser.id,
        }
      }).then(
          res => {
            this.addressList = res.data
          }
      )


      this.request.get("/orders/frontPageOrders", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          no:this.no,
          // state:"待付款"
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

            if(this.tableData && this.tableData){
              this.tableData.forEach(item =>{
                this.request.get("/orders/getGoodsById/" + item.id).then(res =>{
                  item.goods = res.data
                })
              })
            }

            console.log(this.tableData)

          }
      )

      this.request.get("/user-address/" + this.defaultAddress).then(
          res => {
            this.selectAddress = res.data
            // console.log(this.selectAddress)
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
