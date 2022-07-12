package com.patrick.springboot.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.entity.ConsumRecords;
import com.patrick.springboot.entity.User;
import com.patrick.springboot.mapper.ConsumRecordsMapper;
import com.patrick.springboot.service.IConsumRecordsService;
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
 * @since 2022-06-18
 */
@RestController
@RequestMapping("/consum-records")
public class ConsumRecordsController {

    @Resource
    private IConsumRecordsService consumRecordsService;

    @Resource
    private ConsumRecordsMapper consumRecordsMapper;

    //新增和修改
    @PostMapping
    public Result save(@RequestBody ConsumRecords consumRecords){
        //新增或更新都在这里
        consumRecords.setTime(DateUtil.now());
        consumRecordsService.saveOrUpdate(consumRecords);
        return Result.success();
        }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        consumRecordsService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        consumRecordsService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(consumRecordsService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(consumRecordsService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        Page<ConsumRecords> page = new Page<>(pageNum,pageSize);
        QueryWrapper<ConsumRecords> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(consumRecordsService.page(page,queryWrapper));
    }

    @GetMapping("/frontPageBills")
    public Result findFrontPageBills(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize) {
        QueryWrapper<ConsumRecords> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
////        if(!"".equals(name)){
////            queryWrapper.like("name",name);
////        }

        User currentUser = TokenUtils.getCurrentUser();
        Integer loginId = currentUser.getId();

//        Page<ConsumRecords> consumRecordsPage = consumRecordsMapper.frontPageBills(new Page<>(pageNum, pageSize),currentUser.getRole(),loginId);
//
        return Result.success(consumRecordsMapper.frontPageBills(new Page<>(pageNum, pageSize),currentUser.getRole(),loginId));
//        return   Result.success(consumRecordsService.page(consumRecordsPage,queryWrapper));
    }
}

