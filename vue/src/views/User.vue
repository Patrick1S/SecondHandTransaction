<template>
  <div>


    <div style="padding: 10px 0">
      <el-input v-model="username" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称搜索"></el-input>
      <el-input v-model="email" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入邮箱搜索" class="ml-5"></el-input>
      <el-input v-model="address" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入地址搜索" class="ml-5"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="danger" @click="reset">重置</el-button>



      <div style="margin: 10px 0">

        <el-checkbox v-model="model" style="padding-right: 30px" @change="checkItems"></el-checkbox>


        <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
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
        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
        <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>

      </div>

    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
              @selection-change="handleSelectionChange"
    ref="tableNow">
      <el-table-column
          type="selection"
          width="55"
          :reserve-selection="true"
      >
      </el-table-column>
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="username" label="姓名" width="140">
      </el-table-column>
      <el-table-column prop="nickname" label="昵称" width="120">
      </el-table-column>
      <el-table-column prop="email" label="邮箱" >
      </el-table-column>
      <el-table-column prop="phone" label="电话" >
      </el-table-column>
      <el-table-column prop="address" label="地址">
      </el-table-column>
      <el-table-column prop="role" label="角色">
      </el-table-column>
      <el-table-column prop="grade" label="等级">
      </el-table-column>
      <el-table-column label="操作" width="210" align="center">
        <template slot-scope="scope">
          <el-button style="margin: 10px"
              type="primary" @click="handleExam(scope.row)">审核<i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="确定禁用用户吗？"
              @confirm="handleProhibit(scope.row)"
          >
            <el-button style="margin: 10px" slot="reference" type="danger" >禁用<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button style="margin: 10px" type="success" @click="handleEdit(scope.row)">编辑<i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="handleDel(scope.row.id)"
          >
            <el-button style="margin: 10px" slot="reference" type="danger" >删除<i class="el-icon-remove-outline"></i></el-button>
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


    <el-dialog :visible.sync="examFormVisible" width="60%" :show-close="false">
      <div style="font-size: 20px; margin-bottom: 40px">
        审核
      </div>
      <div style="text-align: right">
        <el-button size="medium" type="error" @click="failed">不通过</el-button>
        <el-button size="medium" type="primary" @click="success">通过</el-button>
      </div>
    </el-dialog>

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="120px" >
        <el-form-item label="用户名" size="small">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色">
          <el-select clearable v-model="form.role" placeholder="请选择角色" style="width: 100%">
            <el-option v-for="item in roles" :key="item.name" :label="item.name" :value="item.flag"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="昵称" >
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" >
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" >
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" >
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="等级" >
          <el-input v-model="form.grade" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import router, {resetRouter} from "@/router";

export default {
  name: "User",
  data(){
    return{
      tableData: [],//Array(10).fill(item),
      total:0,
      pageNum:1,
      pageSize:5,
      username:"",
      email:"",
      address:"",

      dialogFormVisible:false,
      form:{},

      multipleSelection: [],
      headerBg:'headerBg',

      roles:[],

      examination:[],
      examFormVisible:false,

      loginUser:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}

    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    handleProhibit(row){
      let form = {
        id:row.id,
        state:'已禁用',
      }
      this.request.post("/user/success/updateState",form).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("禁用用户成功")
          this.load()
        }else {
          this.$message.error("禁用失败")
        }
      })
    },
    handleExam(row) {
      this.examFormVisible = true
      this.examination = row
    },
    success(){

      let form = {
        id: this.examination.id,
        state:'审核通过',
      }
      this.request.post("/user/success/updateState",form).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("添加成功")
          this.load()
        }else {
          this.$message.error("添加失败")
        }
      })
      this.examFormVisible = false
    },

    failed(){
      let form = {
        id: this.examination.id,
        state:'通过失败',
      }
      this.request.post("/user/success/updateState",form).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("添加成功")
          this.load()
        }else {
          this.$message.error("添加失败")
        }
      })
      this.examFormVisible = false
    },

    checkItems(val){
      if(val === true) {
        this.$refs.tableNow.toggleRowSelection(this.tableData[0], true);
      }else{
        this.$refs.tableNow.toggleRowSelection(this.tableData[0], false);
      }
    },
    load(){
      //请求分页查询数据
      this.request.get("/user/page", {
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          username:this.username,
          email:this.email,
          address:this.address,
        }
      }).then(
          res => {
            // console.log(res)
            this.tableData=res.data.records
            this.total=res.data.total
          }
      )


      // //请求分页查询数据
      // fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username).then(res=> res.json()).then(res =>{
      //   console.log(res)
      //   this.tableData=res.data
      //   this.total=res.total
      // })

      this.request.get("/role").then(res =>{
        this.roles = res.data
      })

    },
    save(){
      this.request.post("/user",this.form).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("修改成功")
          if(this.form.id == this.loginUser.id){
            //清空当前缓存
            localStorage.removeItem("user")
            localStorage.removeItem("menus")
            router.push("/login")

            //重置路由
            resetRouter()
          }
          this.load()
        }else {
          this.$message.error("添加失败")
        }
      })
      this.dialogFormVisible = false
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible =true
    },
    handleDel(id){
      this.request.delete("/user/"+id).then(res =>{
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
      this.request.post("/user/del/batch",ids).then(res =>{
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
      this.username=""
      this.email=""
      this.address=""
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



  },


}
</script>

<style>
.headerBg{
  background-color: #afc9e5 !important;
}
</style>
