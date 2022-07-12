<template>
  <div class="login_container">
    <div class="login_box">
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
        class="login_form"
      >
        <!-- 用户名 -->
        <el-form-item label="" prop="username" >
          <el-input placeholder="请输入用户名"
              style="font-size: 20px"
              size="large"
              v-model="user.username"
              prefix-icon="el-icon-user"
              ref="username"
          ></el-input>
        </el-form-item>

        <!-- 密码 -->
        <el-form-item label="" prop="password" style="margin: 20px 0" >
          <el-input placeholder="请输入密码"
              style="font-size: 20px"
              size="large"
            v-model="user.password"
            prefix-icon="el-icon-lock"
            ref="password"
            show-password
          ></el-input>
        </el-form-item>

        <el-form-item label="" prop="identifyCode">
          <div style="display: flex" >
            <el-input ref="identifyCode" placeholder="请输入验证码" prefix-icon="el-icon-key" v-model="user.identifyCode" size="medium" style="width: 50%" ></el-input>
            <s-identify @input="createIdentify" style="padding-left: 30px"></s-identify>
          </div>
        </el-form-item>

        <!-- 按钮区域 -->
        <div style="margin: 10px 0; text-align: center">
          <el-button type="primary"  style="margin-right: 90px" @click="login" >登录</el-button>
          <el-button type="primary" @click="$router.push('/register')">注册</el-button>
          <el-button type="warning" @click="resetLoginForm">重置</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import {setRoutes} from "@/router";
import SIdentify from "@/components/identify";

export default {
  name:"Login",
  components: {SIdentify},
  data() {

    return {
      //这是登录表单的数据绑定对象
      // user.identifyCode = ''
      user: {
        username: '买家111',
        password: 'zzzzzz',
        identifyCode:''
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
        identifyCode: [
          { required: true, message: '请输入验证码', trigger: 'blur' },
          {
            min: 4,
            max: 4,
            message: '验证码长度为4',
            trigger: 'blur',
          },
        ],
      },

      username: '',
      password: '',
      identifyCode:'',
    }
  },
  created() {
    // if (location.href.indexOf("#reloaded")<=0) {
    //   location.href = location.href + "#reloaded"+"#reloaded";
    //   location.reload();
    // }
    this.createIdentify()
    this.$router.replace('/login')
  },
  methods: {
    createIdentify(data){
      // console.log(data)
      this.identifyCode = data
    },

    //点击重置按钮，重置登录表单
    resetLoginForm() {
      // console.log(this.user)
      this.$refs.userLoginFormRef.resetFields()
    },

    login() {
      this.$refs.userLoginFormRef.validate(async (valid) => {
        // console.log(valid)
        if (!valid) return

        if(this.identifyCode != this.user.identifyCode){
          this.$message.error("验证码错误")
          return
        }
        // const { data: res } = await this.$http.post("login", this.loginForm);
        // if (res.meta.status !== 200) return console.log("登陆失败");
        // console.log("登陆成功");
        // console.log(res);

        this.request.post("/user/login",this.user).then(res =>{
          if(res.code==='200'){
            localStorage.setItem("user",JSON.stringify(res.data))//存储用户信息到浏览器
            localStorage.setItem("menus",JSON.stringify(res.data.menus))//存储用户菜单信息到浏览器

            //动态设置当前用户的路由
            setRoutes()

            if( res.data.state === "审核通过") {
            this.$message.success("登陆成功")
              if (res.data.role !== 'ROLE_ADMIN') {
                this.$router.push("/front/home")
              } else {
                this.$router.push("/")
              }
            }else{
              this.$message.error("用户未通过审核")
            }
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

.login_box {
  width:370px;
  height: 335px;
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

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 20px 20px;
  box-sizing: border-box;

}

</style>