package com.patrick.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.entity.Evaluate;
import com.patrick.springboot.mapper.EvaluateMapper;
import com.patrick.springboot.service.IEvaluateService;
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
@RequestMapping("/evaluate")
public class EvaluateController {

    @Resource
    private EvaluateMapper evaluateMapper;

    @Resource
    private IEvaluateService evaluateService;

    //新增和修改
    @PostMapping
    public Result save(@RequestBody Evaluate evaluate){
        //新增或更新都在这里
        evaluateService.saveOrUpdate(evaluate);
        return Result.success();
        }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        evaluateService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        evaluateService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(evaluateService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(evaluateService.getById(id));
    }

    @GetMapping("/frontPageSeller")
    public Result findFrontPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        Integer userId = TokenUtils.getCurrentUser().getId();

        Page<Evaluate> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Evaluate> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        queryWrapper.like("reply_comment","");

//        queryWrapper.like("login_id", TokenUtils.getCurrentUser().getId());
//        queryWrapper.like("user_id", TokenUtils.getCurrentUser().getId());
        return Result.success(evaluateMapper.frontPageSeller(page,userId));

//        return Result.success(evaluateService.page(page,queryWrapper));
    }

    @GetMapping("/frontPageBuyer")
    public Result findFrontPageBuyer(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {

        Integer userId = TokenUtils.getCurrentUser().getId();

        Page<Evaluate> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Evaluate> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");

        queryWrapper.like("reply_comment","");

//        queryWrapper.like("login_id", TokenUtils.getCurrentUser().getId());
//        queryWrapper.like("user_id", TokenUtils.getCurrentUser().getId());
        return Result.success(evaluateMapper.frontPageBuyer(page,userId));

//        return Result.success(evaluateService.page(page,queryWrapper));
    }
}

