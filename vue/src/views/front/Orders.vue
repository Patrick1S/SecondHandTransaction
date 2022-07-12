<template>
  <div>


    <div style="padding: 10px 0">
<!--      <el-input v-model="no" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称搜索"></el-input>-->
<!--&lt;!&ndash;      <el-input v-model="email" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入邮箱搜索" class="ml-5"></el-input>&ndash;&gt;-->
<!--&lt;!&ndash;      <el-input v-model="address" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入地址搜索" class="ml-5"></el-input>&ndash;&gt;-->
<!--      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>-->
<!--      <el-button class="ml-5" type="danger" @click="reset">重置</el-button>-->



      <span style="text-align: right;"><el-button type="primary" @click="addAddress">新增地址</el-button></span>
      <div style="margin: 10px 0; padding-top: 20px;">
        <div style="padding-bottom:15px;">确认收货地址</div>
            <div style="border: 1px solid #ccc; padding-bottom: 10px; cursor: pointer; " >
              <el-radio-group v-model="defaultAddress" @change="changeAddress"  >
                <div v-for="item in addressList" :key="item.id" style="width: 100%; padding: 10px;">
                  <el-radio :label="item.id"  border style="width: 100%; height: 100%; ">
                    <span style="font-size: 15px">{{item.address}}</span>
                    <div style="text-align: right;padding: 10px">
                      <span style="color: darkorange">修改本地址</span>
                      <span style="color: orangered; padding-left: 50px">设为默认收货地址</span>
                    </div>
                  </el-radio>
                </div>
              </el-radio-group>
            </div>
      </div>


<!--      <div style="margin: 10px 0">-->
<!--        <el-popconfirm-->
<!--            class="ml-5"-->
<!--            confirm-button-text='确定'-->
<!--            cancel-button-text='取消'-->
<!--            icon="el-icon-info"-->
<!--            icon-color="red"-->
<!--            title="您确定删除吗？"-->
<!--            @confirm="delBatch"-->
<!--        >-->
<!--          <el-button slot="reference" type="danger" >批量删除 <i class="el-icon-remove-outline"></i></el-button>-->
<!--        </el-popconfirm>-->
<!--&lt;!&ndash;        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>&ndash;&gt;-->
<!--&lt;!&ndash;        <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>&ndash;&gt;-->

<!--      </div>-->

    </div>


    <div style="padding-top:40px; padding-bottom:15px;">确认订单信息</div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" size="medium">
<!--              @selection-change="handleSelectionChange">-->
<!--      <el-table-column-->
<!--          type="selection"-->
<!--          width="55">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="id" label="ID"></el-table-column>-->


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
<!--                <el-descriptions-item label="备注">-->
<!--                  <el-tag size="small">学校</el-tag>-->
<!--                </el-descriptions-item>-->
<!--                <el-descriptions-item abel="居住地"></el-descriptions-item>-->
<!--                <el-descriptions-item llabel="联系地址">江苏省苏州市吴中区吴中大道 1188 号</el-descriptions-item>-->
              </el-descriptions>
            </div>
          </div>



        </template>
      </el-table-column>

<!--      <el-table-column prop="name" label="订单名称(商品)"></el-table-column>-->
      <el-table-column prop="totalPrice" label="总金额" ></el-table-column>
<!--      <el-table-column prop="state" label="订单状态" ></el-table-column>-->
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

<!--      <el-table-column label="操作" width="200" align="center">-->
<!--        <template slot-scope="scope">-->
<!--&lt;!&ndash;          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>&ndash;&gt;-->
<!--          <el-popconfirm-->
<!--              class="ml-5"-->
<!--              confirm-button-text='确定'-->
<!--              cancel-button-text='取消'-->
<!--              icon="el-icon-info"-->
<!--              icon-color="red"-->
<!--              title="您确定删除吗？"-->
<!--              @confirm="handleDel(scope.row.id)"-->
<!--          >-->
<!--            <el-button slot="reference" type="danger" >删除<i class="el-icon-remove-outline"></i></el-button>-->
<!--          </el-popconfirm>-->
<!--        </template>-->
<!--      </el-table-column>-->
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



    <div style="text-align: right">
      <div style="font-size: 14px; margin-bottom: 15px">
        <span>账户余额：</span><span style="color: orangered">¥ {{this.loginUser.account}}</span>
        <span style="padding-left: 30px">账户积分：</span><span style="color: orangered">{{this.loginUser.points}}</span>
      </div>

      <div style="font-size: 18px; margin-bottom: 10px">应付金额：<span style="color: orangered">¥ {{this.totalPrice}}</span></div>

      <div style="padding-bottom: 5px">
        <div v-if="this.loginUser.points != 0 ">
          <!--      <div>-->
          <el-checkbox v-model="model" style="padding-right: 90px" @change="selectPoints">可使用积分{{this.pointsAble}}</el-checkbox>
          <span style="margin-bottom: 15px; color: orangered">¥  -{{this.pointsAble / 100}}</span>
        </div>
        <div v-else>
          <span style="font-size: 14px;">无可用积分兑换</span>
        </div>
      </div>

<!--      <div>已选择订单数量：<span style="color: orangered">{{totalGoodsNum}}</span></div>-->
      <div style="font-size: 20px; margin-bottom: 15px">实付款：<span style="color: orangered">¥ {{this.settlePrice}}</span></div>
      <div style="font-size: 14px; margin-bottom: 15px">寄送至：<span>{{this.selectAddress.address}}</span></div>


      <el-button size="medium" type="danger" @click="settleAccount">
        <i class="el-icon-coin"></i>提交订单</el-button>
    </div>




    <el-dialog title="商品信息" :visible.sync="dialogFormVisible" width="60%">
      <el-table :data="goodsList" border stripe :header-cell-class-name="headerBg">
<!--                @selection-change="handleSelectionChange">-->
<!--        <el-table-column-->
<!--            type="selection"-->
<!--            width="55">-->
<!--        </el-table-column>-->
        <el-table-column prop="id" label="ID" width="80">
        </el-table-column>
        <el-table-column prop="name" label="商品名称" width="140">
        </el-table-column>
        <el-table-column prop="price" label="价格" width="120">
        </el-table-column>
        <el-table-column prop="description" label="商品描述" >
        </el-table-column>
        <el-table-column  prop="num" label="购买数量"  >
          <template slot-scope="scope">
            <span style="padding-left: 5px">{{scope.row.num}}{{scope.row.unit}}</span>
          </template>
        </el-table-column>
        <el-table-column  prop="store" label="商品库存" >
          <template slot-scope="scope">
            <span style="padding-left: 5px">{{scope.row.num}}{{scope.row.unit}}</span>
          </template>
        </el-table-column>
        <el-table-column prop="img" label="图片">
          <template slot-scope="scope">
            <div>
              <img :src="scope.row.img" alt="" style="width: 100%;">
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="transport" label="交易方式"></el-table-column>
        <el-table-column prop="time" label="上架时间"></el-table-column>

<!--        <el-table-column label="操作" width="200" align="center">-->
<!--          <template slot-scope="scope">-->
<!--            <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>-->
<!--            <el-popconfirm-->
<!--                class="ml-5"-->
<!--                confirm-button-text='确定'-->
<!--                cancel-button-text='取消'-->
<!--                icon="el-icon-info"-->
<!--                icon-color="red"-->
<!--                title="您确定删除吗？"-->
<!--                @confirm="handleDel(scope.row.id)"-->
<!--            >-->
<!--              <el-button slot="reference" type="danger" >删除<i class="el-icon-remove-outline"></i></el-button>-->
<!--            </el-popconfirm>-->
<!--          </template>-->
<!--        </el-table-column>-->

      </el-table>
    </el-dialog>


    <el-dialog title="商品信息" :visible.sync="payDialogFormVisible" width="60%" :show-close="false">
      <div style="font-size: 20px; margin-bottom: 40px">
        确认支付
        <span style="color: red">{{this.settlePrice}}</span>
        元？
      </div>
      <div style="text-align: right">
        <el-button size="medium" type="error" @click="cancelOrder">取消</el-button>
        <el-button size="medium" type="primary" @click="confirmOrder">确定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="请输入地址" :visible.sync="addressDialogFormVisible" width="40%" :show-close="false">
      <div><el-input style="width: 500px;margin-bottom: 20px;" v-model="address"></el-input></div>
      <div style="text-align: right">
        <el-button size="medium" type="error" @click="addressDialogFormVisible=false">取消</el-button>
        <el-button style="margin-left: 20px" size="medium" type="primary" @click="confirmAddress">确定</el-button>
      </div>
    </el-dialog>

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
      address:'',

      selectAddress:'',
      settlePrice:0,

      payDialogFormVisible:false,
      addressDialogFormVisible:false,

      consPoints:0,
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    addAddress(){
      this.address=''
      this.addressDialogFormVisible = true
    },
    confirmAddress(){
      let form ={
        userId:this.loginUser.id,
        address:this.address,
      }
      this.request.post("/user-address", form).then(res => {
        if(res.code === '200'){
          this.$message.success("添加地址成功")
          this.load()
        }else {
          this.$message.error(res.msg)
        }
      })
      this.addressDialogFormVisible = false
    },
    confirmOrder(){

      let user = {
        id:this.loginUser.id,
        account: this.settlePrice,
        points:this.consPoints
      }
      this.request.post("/user/updateAccount", user).then(res => {
        if(res.code === '200'){
          this.$message.success("支付成功")


          let ordersList =this.tableData
          if(ordersList && ordersList.length){
            ordersList.forEach(item =>{
              item.state = "待发货"
              let itemPrice = item.totalPrice
              item.totalPrice = this.settlePrice * (itemPrice / this.totalPrice)
              item.consPoints = this.consPoints * (itemPrice / this.totalPrice)
              item.address =  this.selectAddress.address


              let form2 ={
                loginId:this.loginUser.id,
                money:item.totalPrice,
                type:"消费",
                sellerId: item.userId,
                buyerId:this.loginUser.id,
              }
              this.request.post("/consum-records", form2).then(res => {
                if(res.code === '200'){
                  // this.$message.success("支付成功")
                }else {
                  this.$message.error(res.msg)
                }
              })

            })
          }
          // console.log(ordersList)
          // console.log(this.settlePrice)
          this.request.post("/orders/paySuccess",ordersList).then(res =>{
            // console.log(res)
            if(res.code === '200'){
              // this.$message.success("添加成功")
              this.load()
            }else {
              // this.$message.error("添加失败")
            }
          })

        }else {
          this.$message.error(res.msg)
        }
      })

      this.payDialogFormVisible = false
    },
    cancelOrder(){
      let ordersList =this.tableData
      if(ordersList && ordersList.length){
        ordersList.forEach(item =>{
          item.state = "待付款"
          let itemPrice = item.totalPrice
          item.totalPrice = this.settlePrice * (itemPrice / this.totalPrice)
          item.consPoints = this.consPoints * (itemPrice / this.totalPrice)
          item.address =  this.selectAddress.address
        })
      }
      this.request.post("/orders/paySuccess",ordersList).then(res =>{
        if(res.code === '200'){
          this.$message.success("添加成功")
          this.$router.push("/front/waitPaymentOrder")
          // this.load()
        }else {
          this.$message.error("添加失败")
        }
      })
    },
    // viewGoods(orderId){
    //   // console.log(orderId)
    //   this.request.get("/orders/getGoodsById/" + orderId).then(res =>{
    //     this.goodsList = res.data
    //     })
    //
    // },
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


      this.request.get("/orders/frontPage", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          no:this.no,
          // name:this.name,
          state:""
          // email:this.email,
          // address:this.address,
        }
      }).then(
          res => {


            // console.log(res)
            this.tableData=res.data.records
            this.total=res.data.total

            // console.log(this.tableData)

            if(this.tableData && this.tableData.length){
              this.tableData.forEach(item =>{
                this.totalPrice += item.totalPrice
              })
            }

            let loginUsePoints = this.totalPrice
            //价格取两位小数
            this.totalPrice = this.totalPrice.toFixed(2)

            // console.log(this.totalPrice)
            // console.log(usePoints)


            if(this.loginUser.points >= loginUsePoints){
              this.pointsAble = loginUsePoints
            }else {
              this.pointsAble = this.loginUser.points
            }

            

            this.settlePrice = this.totalPrice


            console.log(this.tableData)

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

      // this.request.get("/user-address/" + this.defaultAddress).then(
      //     res => {
      //       this.selectAddress = res.data
      //       // console.log(this.selectAddress)
      //     }
      // )

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


    // handleSelectionChange(val){
    //
    //   this.totalPrice = 0
    //   this.totalGoodsNum = val.length
    //
    //   //进行计算操作
    //   if(val && val.length){
    //     val.forEach(item =>{
    //       this.totalPrice += item.totalPrice
    //     })
    //   }
    //   //价格取两位小数
    //   this.totalPrice = this.totalPrice.toFixed(2)
    //
    //   let usePoints = this.totalPrice
    //
    //   if(this.user.points > usePoints){
    //     this.pointsAble = usePoints
    //   }
    // },
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
    }

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
