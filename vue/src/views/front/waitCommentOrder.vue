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
<!--      <el-table-column-->
<!--          type="selection"-->
<!--          width="55">-->
<!--      </el-table-column>-->

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
      <el-table-column prop="state" label="订单状态"></el-table-column>
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

      <el-table-column label="操作"  align="center" width="170px">
        <template slot-scope="scope">
<!--          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>-->

          <el-button slot="reference" type="danger" @click="handleAfterSales(scope.row)">售后<i class="el-icon-remove-outline"></i></el-button>
          <el-button slot="reference" type="danger" @click="handleEvaluate(scope.row)" >评价<i class="el-icon-remove-outline"></i></el-button>
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


    <el-dialog title="商品信息" :visible.sync="dialogFormVisible" width="60%">
      <el-table :data="goodsEvaluate.goods" border stripe :header-cell-class-name="headerBg" size="medium">
<!--                @selection-change="handleSelectionChange($event,item.id)"-->
<!--                :ref="`tableNow${item.id}`"-->
<!--        <el-table-column-->
<!--            type="selection"-->
<!--            width="55"-->
<!--            :reserve-selection = "true"-->
<!--        >-->
<!--        </el-table-column>-->
        <!--      <el-table-column prop="id" label="ID" width="80"></el-table-column>-->
        <!--      <el-table-column prop="goodsId" label="商品id" ></el-table-column>-->

        <el-table-column prop="img" label="商品图片" width="120px">
          <template slot-scope="scope">
            <div>
              <img :src="scope.row.img" alt="" style="width: 100%;">
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="商品名称" width="200px" >
        </el-table-column>
        <el-table-column prop="price" label="商品价格"width="100px" >
          <template slot-scope="scope">
            <span style="padding-left: 5px; color: orangered">¥ {{scope.row.price}}</span>
          </template>
        </el-table-column>
        <el-table-column  prop="num" label="购买数量" >
          <template slot-scope="scope">
<!--            <el-input-number size="small" v-model="scope.row.num" :min="0" :max="scope.row.store" label="" @change="changeNum(scope.row)"></el-input-number>-->
            <span style="padding-left: 5px">{{scope.row.num}}</span>
            <span style="padding-left: 5px">{{scope.row.unit}}</span>
          </template>
        </el-table-column>
        <!--      <el-table-column prop="userId" label="用户id" ></el-table-column>-->
        <!--      <el-table-column prop="username" label="用户名称" ></el-table-column>-->

<!--        <el-table-column prop="transport" label="交易方式"></el-table-column>-->
<!--        <el-table-column prop="time" label="加入购物车时间" ></el-table-column>-->
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button slot="reference" type="danger" @click="handleGoodsEvaluate(scope.row)">评价<i class="el-icon-remove-outline"></i></el-button>

          </template>
        </el-table-column>
      </el-table>

      <el-button style="text-align: right; margin-top: 30px" type="danger" @click=" finishEvaluate()">完成交易<i class="el-icon-remove-outline"></i></el-button>


    </el-dialog>



    <el-dialog title="对当前已购买商品进行评价" :visible.sync="evaluateDialogFormVisible" width="30%">
      <el-form label-width="120px" >
        <el-form-item label="商品评价" size="small">
          <el-input v-model="form.comment" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商家打分">
          <el-input v-model="form.goodsPoints" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商家服务打分" >
          <el-input v-model="form.sellerPoints" autocomplete="off"></el-input>
        </el-form-item>
<!--        <el-form-item label="单位" >-->
<!--          <el-input v-model="form.unit" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="库存" >-->
<!--          <el-input v-model="form.store" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
        <!--        <el-form-item label="图片" >-->
        <!--          <div>-->
        <!--            <el-upload-->
        <!--                class="avatar-uploader"-->
        <!--                action="http://localhost:9090/file/upload"-->
        <!--                :show-file-list="false"-->
        <!--                :on-success="handleAvatarSuccess"-->
        <!--                :before-upload="beforeAvatarUpload">-->
        <!--              <img v-if="form.img" :src="form.img" class="avatar">-->
        <!--              <i v-else>-->
        <!--                <el-button size="small" type="primary">点击上传</el-button>-->
        <!--              </i>-->
        <!--            </el-upload>-->
        <!--          </div>-->
        <!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="evaluateDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveEvaluate">确 定</el-button>
      </div>
    </el-dialog>



    <el-dialog :visible.sync="afterSalesFormVisible" width="60%" :show-close="false">
      <div style="font-size: 20px; margin-bottom: 40px">
        确认退货申请吗？
      </div>
      <div style="text-align: right">
        <el-button size="medium" type="error" @click="cancelOrder">取消</el-button>
        <el-button size="medium" type="primary" @click="confirmOrder">确定</el-button>
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
      evaluateDialogFormVisible:false,
      afterSalesFormVisible:false,

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

      waitCancelSales:[],
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    handleAfterSales(row){
      this.waitCancelSales = row
      this. afterSalesFormVisible = true
    },
    confirmOrder(){

      let form = {
        id: this.waitCancelSales.id,
        state:'待退货',
      }
      this.request.post("/orders/paySuccess/updateState",form).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("已提交申请，等待退货结果")
          this.load()
        }else {
          this.$message.error(res.msg)
        }
      })
      this.afterSalesFormVisible  = false
    },

    cancelOrder(){
      this. afterSalesFormVisible = false

    },
    finishEvaluate(){

      let form = {
        id: this.goodsEvaluate.id,
        state:'交易完成',
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

      let userPoints = {
        id:this.loginUser.id,
        points:this.goodsEvaluate.totalPrice
      }
      this.request.post("/user/updateUserPoints", userPoints).then(res => {
        if(res.code === '200'){
          // this.$message.success("支付成功")
        }else {
          // this.$message.error(res.msg)
          return
        }
      })

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
      this.form.userId = row.userId
      this.form.loginId = this.loginUser.id
      this.evaluateDialogFormVisible = true
    },

    saveEvaluate(){
      this.request.post("/evaluate",this.form).then(res =>{
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
          state:"待评价"
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
