package com.patrick.springboot.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.patrick.springboot.common.Result;

import com.patrick.springboot.service.IOrdersGoodsService;
import com.patrick.springboot.entity.OrdersGoods;


import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Patrick
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/orders-goods")
public class OrdersGoodsController {

    @Resource
    private IOrdersGoodsService ordersGoodsService;

    //新增和修改
    @PostMapping
    public Result save(@RequestBody OrdersGoods ordersGoods){
        //新增或更新都在这里
        ordersGoodsService.saveOrUpdate(ordersGoods);
        return Result.success();
        }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        ordersGoodsService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        ordersGoodsService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(ordersGoodsService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(ordersGoodsService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        Page<OrdersGoods> page = new Page<>(pageNum,pageSize);
        QueryWrapper<OrdersGoods> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(ordersGoodsService.page(page,queryWrapper));
    }
}

