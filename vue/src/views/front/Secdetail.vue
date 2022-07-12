<template>
  <div style="margin: 10px 0">
    <el-card>
      <div style="display: flex">
        <div style="flex: 1; padding-left: 50px">
          <div class="item1" style="font-size: 14px; padding-left: 5px">发布用户：{{goods.user}} {{goods.time}}</div>
          <div class="item1"><h3>{{goods.name}}</h3></div>
          <div class="item1" style="font-size: 14px; padding-left: 5px">{{goods.description}}</div>
          <div class="item1" style="color: orangered">价格 ¥{{goods.price}}</div>
<!--          <div class="item1" style="font-size: 14px; padding-left: 5px">剩余库存：{{goods.store}} {{goods.unit}}</div>-->
<!--          <div class="item1">-->
<!--            <el-input-number v-model="form.num" :min="1" :max="goods.store" label=""></el-input-number>-->
<!--            <span style="padding-left: 5px">{{goods.unit}}</span>-->
<!--          </div>-->
          <div style="width: 300px">
            <el-image :src="goods.img" :preview-src-list="[goods.img]" style="width: 100%"></el-image>
          </div>
          <div class="item1" style="margin-top: 40px">
            <el-button size="medium" style="background-color: red; color: white"><i class="el-icon-wallet"></i>我想要</el-button>
            <el-button size="medium" style="background-color: red; color: white"><i class="el-icon-shopping-cart-2"></i>直接购买</el-button>
          </div>

        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Detail",
  data(){
    return {
      id: this.$route.query.id,
      goods:{},
      form:{},
    }
  },
  created() {
    this.load()
  },
  methods:{
    load() {
      this.form.num='1'
    this.request.get("/secgoods/" + this.id).then(
        res => {
          this.goods = res.data
        }
    )
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