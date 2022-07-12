package com.patrick.springboot.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.entity.*;
import com.patrick.springboot.mapper.OrdersMapper;
import com.patrick.springboot.service.ICartService;
import com.patrick.springboot.service.IGoodsService;
import com.patrick.springboot.service.IOrdersGoodsService;
import com.patrick.springboot.service.IOrdersService;
import com.patrick.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Patrick
 * @since 2022-06-11
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private IOrdersService ordersService;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private IOrdersGoodsService ordersGoodsService;

    @Resource
    private ICartService cartService;

    @Resource
    private IGoodsService goodsService;

    //新增和修改
    @PostMapping
    public Result save(@RequestBody Orders orders){
        if(orders.getId() == null){
            ordersService.save(orders);
            Date date = new Date();
            orders.setTime(DateUtil.formatDateTime(date));
            orders.setNo(DateUtil.format(date,"yyyyMMdd") + System.currentTimeMillis());
            orders.setLoginId(TokenUtils.getCurrentUser().getId());

//            orders.setConsPoints(orders.getTotalPrice());


            List<Cart> carts = orders.getCarts();
            for(Cart cart : carts){
                OrdersGoods ordersGoods = new OrdersGoods();
                ordersGoods.setGoodsId(cart.getGoodsId());
                ordersGoods.setNum(cart.getNum());
                ordersGoods.setOrderId(orders.getId());

                orders.setUserId(cart.getUserId());

                ordersGoodsService.save(ordersGoods);

                //删除购物车数据
                cartService.removeById(cart.getId());
            }

            //创建订单
            ordersService.updateById(orders);
        }else {
            ordersService.updateById(orders);
        }
        return Result.success();
        }

    //支付订单成功
    @PostMapping("/paySuccess")
    public Result paySuccess(@RequestBody List<Orders> ordersList){

        for (Orders orders : ordersList) {
            Orders updateOrders = ordersService.getById(orders.getId());

            updateOrders.setState(orders.getState());
            updateOrders.setAddress(orders.getAddress());
            updateOrders.setTotalPrice(orders.getTotalPrice());
            updateOrders.setConsPoints(orders.getConsPoints());
            ordersService.updateById(updateOrders);
        }

        return Result.success();
    }

    @PostMapping("/paySuccess/updateState")
    public Result paySuccess(@RequestBody Orders orders
                             ) throws ParseException {
        Orders updateOrders = ordersService.getById(orders.getId());


        if("待发货".equals(orders.getState())) {
            Date date = new Date();
            updateOrders.setPayTime(DateUtil.formatDateTime(date));
        }
        if("已发货".equals(orders.getState())) {
            Date date = new Date();
            updateOrders.setDeliveryTime(DateUtil.formatDateTime(date));
        }
        if("待评价".equals(orders.getState())) {
            Date date = new Date();
            updateOrders.setDealTime(DateUtil.formatDateTime(date));
        }

        if("待退货".equals(orders.getState())) {
            DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Date dealTime = dft.parse(ordersService.getById(orders.getId()).getDealTime());
            Long starTime = dealTime.getTime();
            Long endTime  = date.getTime();
            Long num = (endTime - starTime)/24/60/60/1000;
//            System.out.println(num + "==================================================");
            if(num <= 1) {
//                updateOrders.setDealTime(DateUtil.formatDateTime(date));
                updateOrders.setState(orders.getState());
                ordersService.updateById(updateOrders);
                return Result.success();
            }else {
                return Result.error("400","售后时间已超过");
            }
        }

//        updateOrders.setPayTime(DateUtil.formatDateTime(date));
        updateOrders.setState(orders.getState());
        ordersService.updateById(updateOrders);

        return Result.success();
    }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        ordersService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        ordersService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(ordersService.list());
    }

    @GetMapping("/getGoodsById/{id}")
    public Result getGoodsById(@PathVariable Integer id) {
        QueryWrapper<OrdersGoods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("order_id",id);
        List<OrdersGoods> ordersGoodsList = ordersGoodsService.list(queryWrapper);
        ArrayList<Goods> goodsList = new ArrayList<>();
        for(OrdersGoods ordersGoods : ordersGoodsList){
            Integer goodsId = ordersGoods.getGoodsId();
            Goods goods = goodsService.getById(goodsId);
            goods.setNum(ordersGoods.getNum());
            goodsList.add(goods);
        }
        return Result.success(goodsList);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(ordersService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String no,
                           @RequestParam(defaultValue = "") String state) {
        Page<Orders> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
//        if(!"".equals(name)){
//            queryWrapper.like("name",name);
//        }

        User currentUser = TokenUtils.getCurrentUser();
        return Result.success(ordersMapper.page(page,no,currentUser.getRole(),currentUser.getId(),state));
    }


    @GetMapping("/frontPage")
    public Result findFrontPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String no,
                                @RequestParam(defaultValue = "") String state) {
        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
//        if(!"".equals(name)){
//            queryWrapper.like("name",name);
//        }

        User currentUser = TokenUtils.getCurrentUser();
        Integer loginId = currentUser.getId();
        return Result.success(ordersMapper.frontPage(new Page<>(pageNum, pageSize),no,currentUser.getRole(),loginId,state));
    }

    @GetMapping("/frontPageOrders")
    public Result findFrontPageOrders(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String no) {
//        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
////        if(!"".equals(name)){
////            queryWrapper.like("name",name);
////        }

        User currentUser = TokenUtils.getCurrentUser();
        Integer loginId = currentUser.getId();
        return Result.success(ordersMapper.frontPageOrders(new Page<>(pageNum, pageSize),no,currentUser.getRole(),loginId));
    }

    @GetMapping("/frontPageTransOrders")
    public Result findFrontPageTransOrders(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String no,
                                @RequestParam(defaultValue = "") String state) {
//        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
////        if(!"".equals(name)){
////            queryWrapper.like("name",name);
////        }

        User currentUser = TokenUtils.getCurrentUser();
        Integer sellerId = currentUser.getId();
        return Result.success(ordersMapper.frontPageTransOrders(new Page<>(pageNum, pageSize),no,currentUser.getRole(),sellerId,state));
    }

    @GetMapping("/frontPageAfterSales")
    public Result findFrontPageAfterSales(@RequestParam Integer pageNum,
                                           @RequestParam Integer pageSize,
                                           @RequestParam(defaultValue = "") String no,
                                           @RequestParam(defaultValue = "") String state) {
//        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
////        if(!"".equals(name)){
////            queryWrapper.like("name",name);
////        }

        User currentUser = TokenUtils.getCurrentUser();
        Integer loginId = currentUser.getId();
        return Result.success(ordersMapper.frontPageAfterSales(new Page<>(pageNum, pageSize),no,currentUser.getRole(),loginId,state));
    }





}

