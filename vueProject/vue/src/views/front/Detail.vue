<template>
  <div style="margin: 10px 0">
    <el-card>
      <div @click="$router.push('/front/goodsWindow?id=' + goods.userId)">{{goods.nickname}}</div>
      <div style="display: flex">
        <div style="width: 300px">
          <el-image :src="goods.img" :preview-src-list="[goods.img]" style="width: 100%"></el-image>
        </div>
        <div style="flex: 1; padding-left: 50px">
          <div class="item1"><h3>{{goods.name}}</h3></div>
          <div class="item1" style="font-size: 14px; padding-left: 5px">{{goods.description}}</div>
          <div class="item1" style="color: orangered">价格 ¥{{goods.price}}</div>
          <div class="item1" style="font-size: 14px; padding-left: 5px">剩余库存：{{goods.store}} {{goods.unit}}</div>
          <div class="item1">
            <el-input-number v-model="goodsForm.num" :min="1" :max="goods.store" label=""></el-input-number>
            <span style="padding-left: 5px">{{goods.unit}}</span>
          </div>
          <div class="item1" >交易方式：{{goods.transport}}</div>
          <div class="item1" style="font-size: 14px;" >上架时间：{{goods.time}}</div>
<!--          <div class="item1" style="color: orangered">价格 ¥{{goods.price}}</div>-->
          <div class="item1" style="margin-top: 40px">
            <el-button size="medium" style="background-color: red; color: white"><i class="el-icon-wallet"></i>直接购买</el-button>
            <el-button size="medium" style="background-color: red; color: white" v-on:click="addCart" ><i class="el-icon-shopping-cart-2"></i>加入购物车</el-button>
          </div>

        </div>
      </div>
    </el-card>

    <div style="margin:30px 0">
      <div style="margin: 10px 0;">
        <div style="border-bottom: 1px solid orangered; padding: 10px 0; font-size: 20px">评论</div>
        <div style="padding: 10px 0">
          <el-input type="textarea" v-model="commentText"></el-input>
        </div>
        <div class="=pd-10" style="text-align: right">
          <el-button type="primary" @click="saveCommentText">评论</el-button>
        </div>
      </div>
    </div>

<!--    评论列表-->
    <div>
      <div v-for="item in comments" :key="item.id" style="border-bottom: 1px solid #ccc; padding: 10px;">

        <div style="display: flex">
          <!--        头像-->
          <div style="width: 100px; text-align: center">
            <el-image :src="item.avatarUrl" style="width: 50px; height: 50px;border-radius: 50%"></el-image>
          </div>

          <!--        内容-->
          <div style="flex: 1; font-size: 14px; padding-left: 5px; line-height: 25px">
            <b>{{item.nickname}}: </b>
            <span>{{item.content}}</span>
            <div style="display: flex; line-height: 20px;margin-top: 5px">
              <div style="width: 200px">
                <i class="el-icon-time"></i><span style="margin-left: 5px">{{item.time}}</span>
              </div>
              <div style="text-align: right; flex: 1">
                <el-button style="margin-left: 5px" type="text" @click="handleReply(item.id,item.originId)">回复</el-button>
                <el-button type="text" style="color: red" @click="handleDel(item.id)"  v-if="item.userId == loginUser.id">删除</el-button>
              </div>
            </div>
          </div>
        </div>


        <div v-if="item.children.length" style="padding-left: 85px;">
          <!--          回复列表-->
          <div  v-for="subItem in item.children" :key="subItem.id" style="display:flex; background-color: #f3efef; padding: 10px 20px">
            <div style="width: 70px; text-align: center;">
              <el-image :src="subItem.avatarUrl" style="width: 35px; height: 35px;border-radius: 50%"></el-image>
            </div>

            <div style="font-size: 14px; padding:5px 0;line-height: 25px">
              <div style="display: flex">
                <b>{{subItem.nickname}}   </b>
              </div>
              <b v-if="subItem.pnickName">回复给  </b>
              <b style="color: #409EFF" v-if="subItem.pnickName">@{{subItem.pnickName}}:   </b>
              <span>{{subItem.content}}</span>


              <div style="display: flex; line-height: 20px;margin-top: 5px">
                <div style="width: 200px">
                  <i class="el-icon-time"></i><span style="margin-left: 5px">{{subItem .time}}</span>
                </div>
                <div style="text-align: right; flex: 1">
                  <el-button style="margin-left: 5px" type="text" @click="handleReply(subItem.id,subItem.originId)">回复</el-button>
                  <el-button type="text" style="color: red" @click="handleDel(subItem.id)"  v-if="subItem.userId == loginUser.id">删除</el-button>
                </div>
              </div>
            </div>
          </div>
        </div>


      </div>
    </div>

    <el-dialog title="回复" :visible.sync="dialogFormVisible" width="50%">
      <el-form label-width="100px" size="small">
        <el-form-item label="回复内容">
          <el-input type="textarea" v-model="contentReply" autocomplete="off"></el-input>
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
  name: "Detail",
  data() {
    return {
      id: this.$route.query.id,
      goods: {},
      goodsForm:{num:0},//商品表单
      form: {},//评论表单
      commentText:'',
      comments: [],
      loginUser:localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      dialogFormVisible:false,
      contentReply:'',

    }
  },
  created() {
    this.load()
    this.loadComment()
  },
  methods: {
    load() {
      this.request.get("/goods/" + this.id).then(
          res => {
            this.goods = res.data
          })

      this.request.get("/goodscomment/tree/" + this.id).then(
          res => {
            this.comments = res.data
          })


    },

    loadComment(){
      this.request.get("/goodscomment/tree/" + this.id).then(
          res => {
            this.comments = res.data
          })
    },

    save(){
      if(!this.loginUser.id){
        this.$message.warning("请登录后操作")
        return
      }

      if(this.contentReply.length != 0){
        this.form.content = this.contentReply
      }else {
        this.$message.warning("输入值不能为空")
        return;
      }

      this.form.goodsId = this.id
      this.request.post("/goodscomment",this.form).then(res =>{
        if(res.code === '200'){
          this.$message.success("评论成功")
          this.loadComment()
        }else {
          this.$message.warning("请登录后操作")
        }
      })
      this.form = {}

      this.dialogFormVisible = false
    },

    saveCommentText(){
      if(!this.loginUser.id){
        this.$message.warning("请登录后操作")
        return
      }

      this.form.originId = null;

      if(this.commentText.length != 0){
        this.form.content = this.commentText
      }else {
        this.$message.warning("输入值不能为空")
        return;
      }


      this.form.goodsId = this.id
      this.request.post("/goodscomment",this.form).then(res =>{
        if(res.code === '200'){
          this.$message.success("评论成功")
          this.loadComment()
        }else {
          this.$message.warning("请登录后操作")
        }
      })
      this.form = {}

      this.commentText = ''
    },

    handleDel(id){
      this.request.delete("/goodscomment/"+id).then(res =>{
        // console.log(res)
        if(res.code === '200'){
          this.$message.success("删除成功")
          this.loadComment()
        }else {
          this.$message.error("删除失败")
        }
      })
    },

    handleReply(pid,originId){
      if(!this.loginUser.id){
        this.$message.warning("请登录后操作")
        return
      }

      if(originId == null) {
        this.form = {pid: pid, originId: pid}
      // console.log(originId)
      }else{
        this.form = {pid: pid, originId: originId}
      }
      this.contentReply=''
      this.dialogFormVisible = true
    },

    //加入购物车
    addCart(){
      if(!this.loginUser.id){
        this.$message.warning("请登录后操作")
        return
      }

      this.goodsForm.goodsId = this.goods.id //商品id
      this.goodsForm.userId = this.goods.userId //商品id

      this.request.post("/cart",this.goodsForm).then(res =>{
        if(res.code === '200'){
          this.$message.success("加入购物车成功")
        }else {
          this.$message.error(res.msg)
        }
      })

    },

  }
}
</script>

<style scoped>
.item1{
  padding: 5px;
  color: #666666;
}
</style>