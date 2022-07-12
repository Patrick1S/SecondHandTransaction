<template>
  <el-card style="width: 700px;">
    <el-upload
        class="avatar-uploader"
        action="http://localhost:9090/file/upload"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
      <img v-if="form.avatarUrl" :src="form.avatarUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>



    <el-form
        ref="userLoginFormRef"
        :model="form"
        :rules="userLoginFormRules"
        label-width="120px" >
      <el-form-item label="用户名" size="small"  prop="username">
        <el-input v-model="form.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称" >
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别" >
        <el-input v-model="form.sex" autocomplete="off"></el-input>
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
      <el-form-item label="银行账户" >
        <el-input v-model="form.bankAccount" autocomplete="off"></el-input>
      </el-form-item>
      <!-- 密码 -->
      <el-form-item label="" prop="password" style="margin: 20px 0" >
        <el-input placeholder="请输入密码"
                  style="width: 400px"
                  size="medium"
                  v-model="form.password"
                  prefix-icon="el-icon-lock"
                  ref="password"
                  show-password
        ></el-input>
      </el-form-item>
      <!-- 确认密码 -->
      <el-form-item label="" prop="confirmPassword" style="margin: 20px 0" >
        <el-input placeholder="请确认密码"
                  style="width: 400px"
                  size="medium"
                  v-model="form.confirmPassword"
                  prefix-icon="el-icon-lock"
                  ref="confirmPassword"
                  show-password
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="save">保存</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>


<script>
export default {
  name: "Person",
  data(){
    return {
      form:{},
      user:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      userLoginFormRules: {
        //验证用户名是否合法
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {
            min: 3,
            max: 10,
            message: '长度在 3 到 10 个字符',
            trigger: 'blur',
          },
        ],
        //验证密码是否合法
        password: [
          {required: true, message: '请输入登录密码', trigger: 'blur'},
          {
            min: 5,
            max: 15,
            message: '长度在 5 到 15 个字符',
            trigger: 'blur',
          },
        ],
        confirmPassword: [
          {required: true, message: '请输入确认密码', trigger: 'blur'},
          {
            min: 5,
            max: 15,
            message: '长度在 5 到 15 个字符',
            trigger: 'blur',
          },
        ],
      }

    }
  },
  created() {
    this.getUser().then(res => {
      this.form = res.data
    })
  },
  methods:{
    async getUser(){
       let  res = await this.request.get("/user/username/"+this.user.username)
        // console.log(res)
        if(res.code === '200'){
          return res
        }
    },
    save(){
      this.$refs.userLoginFormRef.validate(async (valid) => {
        // console.log(valid)
        if (!valid) return
        this.request.post("/user", this.form).then(res => {
          // console.log(res)
          if (res.code === '200') {

            //更新浏览器存储的用户信息
            this.getUser().then(res => {
              let user = res.data
              console.log(user)
              user.token = JSON.parse(localStorage.getItem("user")).token
              localStorage.setItem("user", JSON.stringify(user))
            })
            this.$router.replace('/person')
            this.$router.go(0)
            location.reload()
            this.$router.go(0)
            location.reload()
            this.$router.go(0)
            location.reload()
            this.$router.go(0)
            location.reload()
            this.$router.go(0)
            location.reload()
            this.$router.go(0)
            location.reload()

            this.$message.success("保存成功")
            // this.$router.go(0)
            // this.load()
          } else {
            this.$message.error("保存失败")
          }
        })
      })
    },
    handleAvatarSuccess(res) {
      this.form.avatarUrl = res
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
  },
}
</script>



<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader{
  text-align: center;
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
