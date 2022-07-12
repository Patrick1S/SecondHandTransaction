package com.patrick.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.entity.UserAddress;
import com.patrick.springboot.service.IUserAddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Patrick
 * @since 2022-06-13
 */
@RestController
@RequestMapping("/user-address")
public class UserAddressController {

    @Resource
    private IUserAddressService userAddressService;

    //新增和修改
    @PostMapping
    public Result save(@RequestBody UserAddress userAddress){
        //新增或更新都在这里
        userAddressService.saveOrUpdate(userAddress);
        return Result.success();
        }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userAddressService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userAddressService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(userAddressService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(userAddressService.getById(id));
    }

//    @GetMapping("/page")
//    public Result findPage(@RequestParam Integer pageNum,
//    @RequestParam Integer pageSize) {
//        Page<UserAddress> page = new Page<>(pageNum,pageSize);
//        QueryWrapper<UserAddress> queryWrapper=new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        return Result.success(userAddressService.page(page,queryWrapper));
//    }

    @GetMapping("/userAddressPage")
    public Result findPage(@RequestParam Integer userId) {
        QueryWrapper<UserAddress> queryWrapper=new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
        if(userId != null ){
            queryWrapper.eq("user_id",userId);
        }
        return Result.success(userAddressService.list(queryWrapper));
    }


}

