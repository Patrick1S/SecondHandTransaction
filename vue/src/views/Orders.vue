<template>
  <div>


    <div style="padding: 10px 0">
      <el-input v-model="name" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称搜索"></el-input>
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
<!--        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>-->
<!--        <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>-->

      </div>

    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg" size="medium"
              @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="name" label="订单名称(商品)"></el-table-column>
      <el-table-column prop="no" label="商品id" ></el-table-column>
      <el-table-column prop="totalPrice" label="总金额" ></el-table-column>
      <el-table-column prop="state" label="订单状态" ></el-table-column>
      <el-table-column prop="time" label="下单时间" ></el-table-column>
      <el-table-column prop="payTime" label="付款时间" ></el-table-column>
      <el-table-column prop="username" label="用户账号" ></el-table-column>
      <el-table-column prop="nickname" label="用户昵称" ></el-table-column>
      <el-table-column label="查看商品" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="viewGoods(scope.row.id)">查看商品<i class="el-icon-edit"></i></el-button>
        </template>
      </el-table-column>
<!--      <el-table-column prop="goodsImg" label="商品图片" width="120px">-->
<!--        <template slot-scope="scope">-->
<!--          <div>-->
<!--            <img :src="scope.row.goodsImg" alt="" style="width: 100%;">-->
<!--          </div>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
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


    <el-dialog title="商品信息" width="30%">
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
        <el-table-column prop="time" label="上架时间">
        </el-table-column>

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

  </div>
</template>

<script>
export default {
  name: "User",
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

    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    load(){
      //请求分页查询数据
      this.request.get("/orders/page", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.name,
          // email:this.email,
          // address:this.address,
        }
      }).then(
          res => {
            // console.log(res)
            this.tableData=res.data.records
            this.total=res.data.total


          }
      )
    },
    save(){
      this.request.post("/cart",this.form).then(res =>{
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
      this.request.delete("/cart/"+id).then(res =>{
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
      // console.log(val)
      this.multipleSelection=val
    },
    delBatch(){
      let ids =this.multipleSelection.map(value => value.id) //[{},{},{}] =>[1,2,3]
      this.request.post("/cart/del/batch",ids).then(res =>{
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

    viewGoods(orderId){
      this.request.get("/orders/getGoodsById/" + orderId).then(res =>{
        this.goodsList = res.data
       this.dialogFormVisible = true

      })
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
