<template>
  <div style="margin-bottom: 100px">
<!--    <div style="margin: 10px 0">-->
<!--      <el-carousel height="450px" :interval="10000">-->
<!--        <el-carousel-item v-for="item in imgs" :key="item">-->
<!--          <img :src="item" alt="" style="width: 100%;">-->
<!--        </el-carousel-item>-->
<!--      </el-carousel>-->
<!--    </div>-->



    <div>{{this.user.nickname}}</div>


    <div style="margin: 20px 20px">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    </div>


    <div style="margin: 10px 0">
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in tableData" :key="item.id" style="margin-bottom: 10px; border-radius: 10px; overflow: hidden">
          <div style="border: 1px solid #ccc; padding-bottom: 10px; cursor: pointer"  @click="$router.push('/front/detail?id=' + item.id)">
            <img :src="item.img" alt="" style="width: 100%; height: 290.5px;">
            <div style="color: #666; padding: 5px; height: 50px; font-size: 18px"><b>{{item.name}}</b></div>
            <div style="color: #666; padding-left: 5px; height: 50px; font-size: 14px">{{item.description}}</div>
            <div style="color: orangered; padding-left: 5px; height: 30px; font-size: 14px">¥{{item.price}}</div>
<!--            <div style="padding: 10px"><el-button>购买</el-button></div>-->
          </div>
        </el-col>
      </el-row>
    </div>

    <div style="padding: 10px 0">
      <div class="block">

        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[8, 16, 24, 32]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>
      </div>

    </div>


    <el-dialog title="商品信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="120px" >
        <el-form-item label="商品名称" size="small">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" autocomplete="off"></el-input>
          <!--          <el-select clearable v-model="form.role" placeholder="请选择角色" style="width: 100%">-->
          <!--            <el-option v-for="item in roles" :key="item.name" :label="item.name" :value="item.flag"></el-option>-->
          <!--          </el-select>-->
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
        <el-form-item label="图片" >
          <div>
            <el-upload
                class="avatar-uploader"
                action="http://localhost:9090/file/upload"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
              <img v-if="form.img" :src="form.img" class="avatar">
              <i v-else>
                <el-button size="small" type="primary">点击上传</el-button>
              </i>
            </el-upload>
          </div>
        </el-form-item>

        <!--        <el-form-item label="上架时间" >-->
        <!--          <el-input v-model="form.time" autocomplete="off"></el-input>-->
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
  name: "FrontHome",
  data(){
    return{
      userId: this.$route.query.id,
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      tableData: [],//Array(10).fill(item),
      total:0,
      pageNum:1,
      pageSize:8,
      name:"",

      dialogFormVisible:false,
      form:{},
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
    save(){
      this.request.post("/goods",this.form).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success(" 请等待管理员审核")
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
    load() {
      //请求分页查询数据
      this.request.get("/goods/shopWindowPage", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
          userId: this.userId,
          state:"审核通过"
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

      this.request.get("/user/" + this.userId).then(
          res => {
            this.user = res.data
          }
      )
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
  }
}
</script>

<style scoped>

</style>