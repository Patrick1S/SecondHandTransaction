package com.patrick.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.entity.Evaluate;
import com.patrick.springboot.entity.SellerEvaluate;
import com.patrick.springboot.mapper.SellerEvaluateMapper;
import com.patrick.springboot.service.ISellerEvaluateService;
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
 * @since 2022-06-17
 */
@RestController
@RequestMapping("/seller-evaluate")
public class SellerEvaluateController {

    @Resource
    private SellerEvaluateMapper sellerEvaluateMapper;

    @Resource
    private ISellerEvaluateService sellerEvaluateService;

    //新增和修改
    @PostMapping
    public Result save(@RequestBody SellerEvaluate sellerEvaluate){
        //新增或更新都在这里
        sellerEvaluateService.saveOrUpdate(sellerEvaluate);
        return Result.success();
        }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        sellerEvaluateService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        sellerEvaluateService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(sellerEvaluateService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(sellerEvaluateService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        Page<SellerEvaluate> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SellerEvaluate> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(sellerEvaluateService.page(page,queryWrapper));
    }

    @GetMapping("/frontPageSeller")
    public Result findFrontPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        Integer userId = TokenUtils.getCurrentUser().getId();

        Page<Evaluate> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Evaluate> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

//        queryWrapper.like("login_id", TokenUtils.getCurrentUser().getId());
//        queryWrapper.like("user_id", TokenUtils.getCurrentUser().getId());
        return Result.success(sellerEvaluateMapper.frontPageSeller(page,userId));

//        return Result.success(evaluateService.page(page,queryWrapper));
    }

    @GetMapping("/frontPageBuyer")
    public Result findFrontPageBuyer(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize) {

        Integer userId = TokenUtils.getCurrentUser().getId();

        Page<Evaluate> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Evaluate> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

//        queryWrapper.like("login_id", TokenUtils.getCurrentUser().getId());
//        queryWrapper.like("user_id", TokenUtils.getCurrentUser().getId());
        return Result.success(sellerEvaluateMapper.frontPageBuyer(page,userId));

//        return Result.success(evaluateService.page(page,queryWrapper));
    }

}

