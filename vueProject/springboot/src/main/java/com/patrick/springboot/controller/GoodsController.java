package com.patrick.springboot.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.config.AuthAccess;
import com.patrick.springboot.entity.Cart;
import com.patrick.springboot.entity.Goods;
import com.patrick.springboot.mapper.GoodsMapper;
import com.patrick.springboot.service.ICartService;
import com.patrick.springboot.service.IGoodsService;
import com.patrick.springboot.service.IUserService;
import com.patrick.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Patrick
 * @since 2022-06-08
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private IGoodsService goodsService;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private IUserService userService;

    @Resource
    private ICartService cartService;

    private final String now = DateUtil.now();

    //新增和修改
    @PostMapping
    public Result save(@RequestBody Goods goods){
        //新增或更新都在这里
        if(goods.getId() == null){
            goods.setTime(now);
//            User user = userService.getById(TokenUtils.getCurrentUser());
            goods.setUserId(TokenUtils.getCurrentUser().getId());
//            goods.setUsername(user.getUsername());
//            System.out.println(user.getUsername());
            goodsService.save(goods);
            return Result.success();
        }
        goodsService.saveOrUpdate(goods);
        return Result.success();
        }

    @PostMapping("/updateNum/{id}")
    public Result save(@PathVariable Integer id){
        Cart cart = cartService.getById(id);
        Goods goods = goodsService.getById(cart.getGoodsId());
        int store = goods.getStore() - cart.getNum();
        if(store >= 0) {
            goods.setStore(store);
        }else {
            return Result.error("400","购买商品超过库存数");
        }

        goodsService.saveOrUpdate(goods);
        return Result.success();
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        goodsService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        goodsService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(goodsService.list());
    }


    @AuthAccess
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        Goods goods = goodsService.getById(id);
        goods.setNickname(userService.getById(goods.getUserId()).getNickname());
        return Result.success(goods);
    }

    @AuthAccess
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
//        Page<Goods> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();

        if(!"".equals(name)) {
            queryWrapper.like("name", name);
        }

//        String state = "审核通过";
//        queryWrapper.like("state",state);
        queryWrapper.orderByDesc("id");
//        //获取当前用户信息
//        User currentUser = TokenUtils.getCurrentUser();
//        System.out.println("当前管理员========》"+currentUser.getNickname());
//        User currentUser = TokenUtils.getCurrentUser();
        return Result.success(goodsMapper.page(new Page<>(pageNum, pageSize), name));
    }

    @AuthAccess
    @GetMapping("/frontPage")
    public Result findFrontPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
//        Page<Goods> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();

        if(!"".equals(name)) {
            queryWrapper.like("name", name);
        }

        String state = "审核通过";
        queryWrapper.like("state",state);
        queryWrapper.orderByDesc("id");
//        //获取当前用户信息
//        User currentUser = TokenUtils.getCurrentUser();
//        System.out.println("当前管理员========》"+currentUser.getNickname());
//        User currentUser = TokenUtils.getCurrentUser();
        return Result.success(goodsMapper.frontPage(new Page<>(pageNum, pageSize), name,state));
    }

    @GetMapping("/shopWindowPage")
    public Result findShopWindowPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam(defaultValue = "") String name,
                                     @RequestParam(defaultValue = "") Integer userId,
                                     @RequestParam(defaultValue = "") String state) {

        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();

        if(!"".equals(name)) {
            queryWrapper.like("name", name);
        }

        queryWrapper.orderByDesc("id");
        return Result.success(goodsMapper.shopWindowPage(new Page<>(pageNum, pageSize), name, userId,state));
    }

    @PostMapping("/success/updateState")
    public Result paySuccess(@RequestBody Goods goods
    ){
        Goods update = goodsService.getById(goods.getId());


//        if("待发货".equals(orders.getState())) {
//            Date date = new Date();
//            updateOrders.setPayTime(DateUtil.formatDateTime(date));
//        }
//        if("已发货".equals(orders.getState())) {
//            Date date = new Date();
//            updateOrders.setDeliveryTime(DateUtil.formatDateTime(date));
//        }
//        if("待评价".equals(orders.getState())) {
//            Date date = new Date();
//            updateOrders.setDealTime(DateUtil.formatDateTime(date));
//        }

//        updateOrders.setPayTime(DateUtil.formatDateTime(date));
        update.setState(goods.getState());
        goodsService.updateById(update);

        return Result.success();
    }

}

