<template>
  <div class="login_container">
    <div class="register_box">
      <!-- 头像区域 -->
      <div class="avatar_box">
        <img src="../assets/logo.png" />
      </div>


      <!-- 登录表单区域 -->
      <el-form
        ref="userLoginFormRef"
        :model="user"
        :rules="userLoginFormRules"
        label-width="0px"
        class="register_form"
      >
        <!-- 用户名 -->
        <el-form-item label="" prop="username" >
          <el-input placeholder="请输入用户名"
                    style="width: 400px"
                    size="medium"
            v-model="user.username"
            prefix-icon="el-icon-user"
            ref="username"
          ></el-input>
        </el-form-item>


        <el-form-item label="" prop="nickname" >
          <el-input placeholder="请输入昵称"
                    style="width: 400px"
                    size="medium"
                    v-model="user.nickname"
                    prefix-icon="el-icon-user"
                    ref="username"
          ></el-input>
        </el-form-item>
        <el-form-item label="" prop="sex" >
          <el-input placeholder="请输入性别"
                    style="width: 400px"
                    size="medium"
                    v-model="user.sex"
                    prefix-icon="el-icon-user"
                    ref="username"
          ></el-input>
        </el-form-item>



        <el-form-item label="" >
          <el-select clearable v-model="user.role" filterable placeholder="请选择角色"  style="width: 400px">
            <el-option v-for="item in roles" :key="item.name" :label="item.name" :value="item.flag"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="" prop="email" >
          <el-input placeholder="请输入邮箱"
                    style="width: 400px"
                    size="medium"
                    v-model="user.email"
                    prefix-icon="el-icon-user"
                    ref="username"
          ></el-input>
        </el-form-item>
        <el-form-item label="" prop="phone" >
          <el-input placeholder="请输入电话号"
                    style="width: 400px"
                    size="medium"
                    v-model="user.phone"
                    prefix-icon="el-icon-user"
                    ref="username"
          ></el-input>
        </el-form-item>
        <el-form-item label="" prop="address" >
          <el-input placeholder="请输入地址"
                    style="width: 400px"
                    size="medium"
                    v-model="user.address"
                    prefix-icon="el-icon-user"
                    ref="username"
          ></el-input>
        </el-form-item>

<!--        <el-form-item label="" prop="bankAccount" >-->
<!--          <el-input placeholder="请输入银行账户"-->
<!--                    style="width: 400px"-->
<!--                    size="medium"-->
<!--                    v-model="user.bankAccount"-->
<!--                    prefix-icon="el-icon-user"-->
<!--                    ref="username"-->
<!--          ></el-input>-->
<!--        </el-form-item>-->




        <!-- 密码 -->
        <el-form-item label="" prop="password" style="margin: 20px 0" >
          <el-input placeholder="请输入密码"
                    style="width: 400px"
                    size="medium"
            v-model="user.password"
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
              v-model="user.confirmPassword"
              prefix-icon="el-icon-lock"
              ref="confirmPassword"
              show-password
          ></el-input>
        </el-form-item>
        <!-- 按钮区域 -->
        <div style="margin: 10px 0; text-align: center">
          <el-button type="primary"  style="margin-right: 50px" @click="login" >注册</el-button>
          <el-button type="warning" @click="$router.push('/login')">返回登录</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name:"Login",
  data() {
    return {
      //这是登录表单的数据绑定对象
      user: {
        username: '',
        password: '',
        nickname:'',
        sex:'',
        email:'',
        phone:'',
        address:'',
        role:'',
        avatarUrl:'',
      },
      //这是表单的验证规则对象
      userLoginFormRules: {
        //验证用户名是否合法
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          {
            min: 3,
            max: 10,
            message: '长度在 3 到 10 个字符',
            trigger: 'blur',
          },
        ],
        //验证密码是否合法
        password: [
          { required: true, message: '请输入登录密码', trigger: 'blur' },
          {
            min: 5,
            max: 15,
            message: '长度在 5 到 15 个字符',
            trigger: 'blur',
          },
        ],
        confirmPassword: [
          { required: true, message: '请输入确认密码', trigger: 'blur' },
          {
            min: 5,
            max: 15,
            message: '长度在 5 到 15 个字符',
            trigger: 'blur',
          },
        ],
        confirmRole: [
          { required: true, message: '请选择角色', trigger: ['blur', 'change'] },
        ],
      },

      username: '',
      password: '',
      roles:[],
    }
  },
  created() {
    this.request.get("/role").then(res =>{
      this.roles = res.data
    })
  },
  methods: {
    //点击重置按钮，重置登录表单
    resetLoginForm() {
      //   console.log(this)
      this.$refs.userLoginFormRef.resetFields()
    },

    login() {
      this.$refs.userLoginFormRef.validate(async (valid) => {
        // console.log(valid)
        if (!valid) return
        // const { data: res } = await this.$http.post("login", this.loginForm);
        // if (res.meta.status !== 200) return console.log("登陆失败");
        // console.log("登陆成功");
        // console.log(res);
        if(this.user.role == null || this.user.role == ""){
          this.$message.error("请选择角色")
          return
        }

        if(this.user.password !== this.user.confirmPassword){
          this.$message.error("两次输入的密码不一致")
          return
        }

        this.request.post("/user/register",this.user).then(res =>{
          if(res.code==='200'){


            this.$message.success("注册成功,请等待管理员审核")
            this.$router.push('/login')
          }else{
            // this.$message.error("用户名或密码错误")
            this.$message.error(res.msg)
          }
        })

        // let username = this.$refs.username.value
        // let password = this.$refs.password.value
        // // console.log(username + password);
        // if (username == 'zjx' && password == '123456') {
        //   this.$message.success('登录成功')
        //   //this.$router.push('/home');
        //   //1.将登陆成功之后的token保存到客户端的 sessionStorage中
        //   //1.1项目中除了登录之外的其它API接口，必须在登录之后才能访问
        //   //1.2token 只应在当前网站打开期间生效，所以将token保存在sessionStorage中
        //   //console.log(res);
        //   sessionStorage.setItem('token', username)
        //   // tokenStr = Window.sessionStorage.getItem('token');
        //   //alert(tokenStr);
        //   //2.通过编程式导航跳转到后台主页，路由地址是 /home
        //   this.$router.push('/home')
        // } else {
        //   return this.$message.error('登录失败!')
        // }
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


<style >
.login_container {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #154d90, #809c65);
  height: 100%;
  overflow: hidden;
}

.register_box {
  width:500px;
  height: 650px;
  background-color: #fff;
  border-radius: 15px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}


.avatar_box {
  height: 130px;
  width: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;

}

img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.register_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 20px 20px;
  box-sizing: border-box;

}

</style>