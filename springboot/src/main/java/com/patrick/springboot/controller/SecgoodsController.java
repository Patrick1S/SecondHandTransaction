package com.patrick.springboot.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.config.AuthAccess;
import com.patrick.springboot.entity.Secgoods;
import com.patrick.springboot.service.ISecgoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Patrick
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/secgoods")
public class SecgoodsController {

    @Resource
    private ISecgoodsService secgoodsService;

    private final String now = DateUtil.now();

    //新增和修改
    @PostMapping
    public Result save(@RequestBody Secgoods secgoods){
        if(secgoods.getId() == null){
            secgoods.setTime(now);
        }
        //新增或更新都在这里
        secgoodsService.saveOrUpdate(secgoods);
        return Result.success();
        }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        secgoodsService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        secgoodsService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(secgoodsService.list());
    }

    @AuthAccess
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(secgoodsService.getById(id));
    }

    @AuthAccess
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String name) {
        Page<Secgoods> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Secgoods> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if(StrUtil.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        return Result.success(secgoodsService.page(page,queryWrapper));
    }
}

