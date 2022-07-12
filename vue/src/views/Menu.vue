<template>
  <div>


    <div style="padding: 10px 0">
      <el-input v-model="menuName" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入名称搜索"></el-input>
<!--      <el-input v-model="email" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入邮箱搜索" class="ml-5"></el-input>-->
<!--      <el-input v-model="address" style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入地址搜索" class="ml-5"></el-input>-->
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="danger" @click="reset">重置</el-button>


      <div style="margin: 10px 0">
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
<!--        <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>-->
<!--        <el-button type="primary">导出 <i class="el-icon-top"></i></el-button>-->

      </div>

    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg"
              row-key="id" default-expand-all
              @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="name" label="名称" >
      </el-table-column>
      <el-table-column prop="path" label="路径" >
      </el-table-column>
      <el-table-column prop="pagePath" label="页面路径" >
      </el-table-column>
      <el-table-column  label="图标"  class-name="fontSize18" align="center" label-class-name="fontSize12">
        <template slot-scope="scope">
          <i :class="scope.row.icon" ></i>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述" >
      </el-table-column>
      <el-table-column label="操作" width="300" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="addChildren(scope.row.id)" v-if="!scope.row.pid && !scope.row.path">添加子菜单<i class="el-icon-menu"></i></el-button>
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



    <el-dialog title="菜单信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form label-width="120px" >
        <el-form-item label="名称" size="small">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="路径" size="small">
          <el-input v-model="form.path" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="页面路径" size="small">
          <el-input v-model="form.pagePath" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标" size="small">
          <template slot-scope="scope">
            <el-select clearable v-model="form.icon" placeholder="请选择" style="width: 100%">
              <el-option v-for="item in options" :key="item.name" :lable="item.name" :value="item.value">
                <i :class="item.value" />{{item.name}}
              </el-option>
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="描述" >
          <el-input v-model="form.description" autocomplete="off"></el-input>
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
export default {
  name: "Role",
  data(){
    return{
      tableData: [],//Array(10).fill(item),
      total:0,
      pageNum:1,
      pageSize:5,
      menuName:"",
      // email:"",
      // address:"",

      dialogFormVisible:false,

      form:{},

      multipleSelection: [],
      headerBg:'headerBg',

      options:[],

    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods:{
    load(){
      //请求分页查询数据
      this.request.get("/menu", {
        params:{
          // pageNum:this.pageNum,
          // pageSize:this.pageSize,
          name:this.menuName,
          // email:this.email,
          // address:this.address,
        }
      }).then(
          res => {
            // console.log(res)
            this.tableData=res.data
            // this.total=res.data.total
          }
      )


      // //请求分页查询数据
      // fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username).then(res=> res.json()).then(res =>{
      //   console.log(res)
      //   this.tableData=res.data
      //   this.total=res.total
      // })
    },
    save(){
      this.request.post("/menu",this.form).then(res =>{
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
      this.dialogFormVisible=true
      this.form={}
    },
    handleEdit(row){
      this.form=row
      this.dialogFormVisible =true

      //请求图标的数据
      this.request.get("/menu/icons").then(
          res => {
            this.options = res.data
          }
      )

    },
    handleDel(id){
      this.request.delete("/menu/"+id).then(res =>{
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
      this.request.post("/menu/del/batch",ids).then(res =>{
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
      this.menuName=""
      // this.email=""
      // this.address=""
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


    addChildren(pid){
      this.dialogFormVisible = true
      this.form = {}
      if(pid) {
        this.form.pid = pid
      }
    },

  },


}
</script>

<style>
.headerBg{
  background-color: #afc9e5 !important;
}

.fontSize18{
  font-size: 18px;
}

.fontSize12{
  font-size: 12px;
}

</style>
