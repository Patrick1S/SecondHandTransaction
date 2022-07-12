<template>
  <div>


    <div style="padding: 10px 0">
      <el-input v-model="name" style="width: 300px"  placeholder="请输入名称搜索" size="medium" clearable></el-input>
<!--      <el-input v-model="email" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入邮箱搜索" class="ml-5"></el-input>-->
<!--      <el-input v-model="address" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入地址搜索" class="ml-5"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load" size="medium"><i class="el-icon-search"></i>搜索</el-button>
      <el-button class="ml-5" type="danger" @click="reset" size="medium">重置</el-button>
    </div>


    <div style="margin: 10px 0">
      <el-row :gutter="10">
<!--        :span="50"-->
        <el-col  v-for="item in cartList" :key="item.id" style="margin-bottom: 10px; border-radius: 15px; overflow: hidden">
          <div style="border: 1px solid #ccc; padding-bottom: 5px; cursor: pointer" >
            <div @click="$router.push('/front/goodsWindow?id=' + item.id)"><span>店铺：</span>{{item.sellerNickame}}</div>
            <el-table :data="item.data" border stripe :header-cell-class-name="headerBg" size="medium"
                      @selection-change="handleSelectionChange($event,item.id)"
                      :ref="`tableNow${item.id}`"
            >
              <el-table-column
                  type="selection"
                  width="55"
                  :reserve-selection = "true"
              >
              </el-table-column>
              <!--      <el-table-column prop="id" label="ID" width="80"></el-table-column>-->
              <!--      <el-table-column prop="goodsId" label="商品id" ></el-table-column>-->

              <el-table-column prop="goodsName" label="商品名称" >
              </el-table-column>
              <el-table-column prop="goodsImg" label="商品图片" width="120px">
                <template slot-scope="scope">
                  <div>
                    <img :src="scope.row.goodsImg" alt="" style="width: 100%;">
                  </div>
                </template>
              </el-table-column>
              <el-table-column prop="price" label="商品价格"width="100px" >
                <template slot-scope="scope">
                  <span style="padding-left: 5px; color: orangered">¥ {{scope.row.price}}</span>
                </template>
              </el-table-column>
              <el-table-column  prop="num" label="商品数量" width="170px" >
                <template slot-scope="scope">
                  <el-input-number size="small" v-model="scope.row.num" :min="0" :max="scope.row.store" label="" @change="changeNum(scope.row)"></el-input-number>
                  <span style="padding-left: 5px">{{scope.row.unit}}</span>
                </template>
              </el-table-column>
              <!--      <el-table-column prop="userId" label="用户id" ></el-table-column>-->
              <!--      <el-table-column prop="username" label="用户名称" ></el-table-column>-->

              <el-table-column prop="transport" label="交易方式"></el-table-column>
              <el-table-column prop="time" label="加入购物车时间" ></el-table-column>
              <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                  <!--          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>-->
                  <el-popconfirm
                      class="ml-5"
                      confirm-button-text='确定'
                      cancel-button-text='取消'
                      icon="el-icon-info"
                      icon-color="red"
                      title="您确定删除吗？"
                      @confirm="handleDel(scope.row.id)"
                  >
                    <el-button slot="reference" type="danger" >删除<i class="el-icon-remove-outline"></i></el-button>
                  </el-popconfirm>
                </template>
              </el-table-column>
            </el-table>

<!--            <div style="padding: 10px 0">-->
<!--              <div class="block">-->

<!--                <el-pagination-->
<!--                    @size-change="handleSizeChange"-->
<!--                    @current-change="handleCurrentChange"-->
<!--                    :current-page="pageNum"-->
<!--                    :page-sizes="[2, 5, 10, 20]"-->
<!--                    :page-size="pageSize"-->
<!--                    layout="total, sizes, prev, pager, next, jumper"-->
<!--                    :total="total">-->
<!--                </el-pagination>-->
<!--              </div>-->

<!--            </div>-->

          </div>
        </el-col>
      </el-row>
    </div>





    <div style="margin:10px 0;">
      <el-checkbox v-model="model" style="padding-right: 30px" @change="selectPoints">全选</el-checkbox>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定删除吗？"
          @confirm="delBatch"
      >
        <el-button size="medium" slot="reference" type="danger">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>

      <div style="text-align: right">
        <div>当前已选商品<span style="color: orangered">{{totalGoodsNum}}</span>件</div>
        <div style="font-size: 20px">当前已选商品总价：<span style="color: orangered">¥ {{totalPrice}}</span></div>

        <el-button size="medium" type="danger" @click="settleAccount">
          <i class="el-icon-coin"></i>结 算</el-button>
      </div>

    </div>





    <el-dialog title="商品信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="120px" >
        <el-form-item label="商品名称" size="small">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="商品描述" >
          <el-input v-model="form.description" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="单位" >
          <el-input v-model="form.unit" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="库存" >
          <el-input v-model="form.store" autocomplete="off"></el-input>
        </el-form-item>
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
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
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
      total:0,
      pageNum:1,
      pageSize:1000,

      name:"",

      dialogFormVisible:false,
      form:{},

      multipleSelections: [],
      headerBg:'headerBg',

      totalPrice:0,
      totalGoodsNum:0,

      cartList:[],



    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    load(){
      //请求分页查询数据
      this.request.get("/cart/frontPage", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.name,
        }
      }).then(
          res => {
            this.tableData=res.data.records
            this.total=res.data.total

            let sellerIds =this.tableData.map(value => value.userId)
            sellerIds = Array.from((new Set(sellerIds)))

            if(sellerIds && sellerIds.length){
              sellerIds.forEach(item =>{
                this.request.get("/cart/frontPage", {
                  params:{
                    pageNum:this.pageNum,
                    pageSize:this.pageSize,
                    sellerId:item,
                  }
                }).then(res =>{
                  if(res.code === '200'){
                    let id = item
                    let data = res.data.records

                    this.request.get("/user/" + id).then(
                        res => {
                          let seller = res.data
                          let sellerNickame = seller.nickname
                          let cart = {id,sellerNickame, data}
                          this.cartList.push(cart)
                          // console.log(this.cartList)
                        })

                  }else {
                    this.$message.error("添加失败")
                  }
                })
              })
            }
          }
      )

    },
    save(){
      this.request.post("/cart",this.form).then(res =>{
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
      this.request.delete("/cart/"+id).then(res =>{
        if(res.code === '200'){
          this.$message.success("删除成功")

          for(let i =0; i<200;i++) {
            this.$router.replace("/front/cart")
            this.$router.go(0)
          }
          this.load()


        }else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val,id){
      this.multipleSelections[id] = val
      this.totalPrice = 0
      this.totalGoodsNum=0
      //进行计算操作
      if(this.multipleSelections && this.multipleSelections.length){
        this.multipleSelections.forEach(item =>{

          this.totalGoodsNum = this.totalGoodsNum+item.length
          if(item && item.length){
            item.forEach(itemChild =>{
              this.totalPrice = this.totalPrice + (itemChild.num * itemChild.price)
            })
          }
        })
      }
      //价格取两位小数
      this.totalPrice = this.totalPrice.toFixed(2)


    },

    changeNum(row){
      this.request.post("/cart",JSON.parse(JSON.stringify(row))).then(res =>{
        this.totalPrice = 0
        //计算总价格
        this.multipleSelections.forEach(multipleSelection => {
          multipleSelection.forEach(item => {
            this.totalPrice += item.price * item.num
          })
        })
        //价格取两位小数
        this.totalPrice = this.totalPrice.toFixed(2)
      })
    },

    selectPoints(val){
      if(val === true) {
        if(this.cartList && this.cartList.length){
          this.cartList.forEach(cart =>{

            let array = cart.data
            if(array && array.length){
              array.forEach(item =>{
                this.$refs[`tableNow${cart.id}`][0].toggleRowSelection(item,true);
              })
            }

          })
        }
      }else{
        if(this.cartList && this.cartList.length){
          this.cartList.forEach(cart =>{

            let array = cart.data
            if(array && array.length){
              array.forEach(item =>{
                this.$refs[`tableNow${cart.id}`][0].toggleRowSelection(item,false);
              })
            }

          })
        }
      }
    },



    delBatch(){
      if(!this.multipleSelections || !this.multipleSelections.length){
        this.$message.error("请选择需要清理的商品")
        return
      }

      if(this.multipleSelections && this.multipleSelections.length){
        this.multipleSelections.forEach(item =>{
          let ids = item.map(value => value.id) //[{},{},{}] =>[1,2,3]
          this.request.post("/cart/del/batch",ids).then(res =>{
            if(res.code === '200'){

              for(let i =0; i<200;i++) {
                this.$router.replace("/front/cart")
                this.$router.go(0)
              }
              this.load()
            }else {
              this.$message.error("批量删除失败")
            }
          })
        })
      }

      this.$message.success("批量删除成功")

    },



    reset(){
      this.name=""
      this.load()
    },
    handleSizeChange(pageSize){
      this.pageSize=pageSize
      this.load()
    },

    handleCurrentChange(pageNum){
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

    settleAccount(){
      if(!this.multipleSelections || !this.multipleSelections.length){
        this.$message.error("请选择需要结算的商品")
        return;
      }
      // console.log(this.multipleSelections)


      if(this.multipleSelections && this.multipleSelections.length){
        this.multipleSelections.forEach(multipleSelection=>{

          let sumPrice = 0
          // console.log(multipleSelection)
          for(let i=0;i<multipleSelection.length;i++){
            if(multipleSelection[i]) {
              sumPrice += multipleSelection[i].price * multipleSelection[i].num
            }
          }
          //价格取两位小数
          sumPrice = sumPrice.toFixed(2)
          console.log(sumPrice)

          let data = {totalPrice:sumPrice, carts:multipleSelection,userId:multipleSelection[0].userId}

          data.goods = []
          multipleSelection.forEach(v => {
            data.goods.push({ goodsId: v.goodsId,num: v.num,})
          })


          console.log(data)
          // data.goods = []
          // for(let i=0;i<multipleSelection.length;i++){
          //   if(multipleSelection[i]) {
          //     data.goods.push({ goodsId: multipleSelection[i].goodsId,num: multipleSelection[i].num,})
          //   }
          // }

          this.request.post("/orders", data).then(res =>{
            if(res.code === '200'){
              this.$message.success("结算成功")
              this.load()
              this.$router.push("/front/order")
            }else{
              this.$message.error(res.msg)
            }
          })

        })
      }

      if(this.multipleSelections && this.multipleSelections.length){
        this.multipleSelections.forEach(multipleSelection=>{
          // console.log(multipleSelection)
          if(multipleSelection && multipleSelection.length){
            multipleSelection.forEach(item =>{
              // console.log(item)
              this.request.post("/goods/updateNum/" + item.id).then(res =>{
                if(res.code === '200'){
                  this.$message.success("结算成功")
                  // this.load()
                }else{
                  this.$message.error(res.msg)
                }
              })
            })

          }
        })
      }


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
