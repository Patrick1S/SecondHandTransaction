package com.patrick.springboot.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.config.AuthAccess;
import com.patrick.springboot.entity.Goodscomment;
import com.patrick.springboot.entity.User;
import com.patrick.springboot.service.IGoodscommentService;
import com.patrick.springboot.service.IUserService;
import com.patrick.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Patrick
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/goodscomment")
public class GoodscommentController {

    @Resource
    private IGoodscommentService goodscommentService;

    @Resource
    private IUserService userService;

    //新增和修改
    @PostMapping
    public Result save(@RequestBody Goodscomment goodscomment){
        if(goodscomment.getUserId() == null){
            goodscomment.setUserId(TokenUtils.getCurrentUser().getId());
            goodscomment.setTime(DateUtil.now());




//            if(goodscomment.getPid() != null) {//判断如果是回复，进行处理
//                Integer pid = goodscomment.getPid();
//                Goodscomment pGoodscomment = goodscommentService.getById(pid);
//                if (pGoodscomment.getOriginId() != null) {
//                    //如果当前回复的父级有祖宗，那么就设置相同的祖宗
//                    goodscomment.setOriginId(pGoodscomment.getOriginId());
//                } else {
//                    //否则就设置当前回复的父级id为祖宗id
//                    goodscomment.setOriginId(goodscomment.getPid());
//                }
//            }

        }
        //新增或更新都在这里
        goodscommentService.saveOrUpdate(goodscomment);
        return Result.success();
        }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        goodscommentService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        goodscommentService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(goodscommentService.list());
    }

    @AuthAccess
    @GetMapping("/tree/{goodsId}")
    public Result findTree(@PathVariable Integer goodsId) {
//        List<Goodscomment> goodscomments = goodscommentService.findGoodsCommentDetail(goodsId);

        QueryWrapper<Goodscomment> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_id",goodsId);
        queryWrapper.orderByDesc("id");

        goodscommentService.list(queryWrapper);


        //查询所有的评论数据
        List<Goodscomment> goodscomments = goodscommentService.list(queryWrapper);

        for(Goodscomment goodscomment : goodscomments){
            User user = userService.getById(goodscomment.getUserId());
                if(user != null){
                goodscomment.setAvatarUrl(user.getAvatarUrl());
                goodscomment.setNickname(user.getNickname());
            }
        }

//        for(Goodscomment goodscomment : goodscomments){
//            User user = userService.getById(goodscomment.getPid());
//            if(user != null){
//                goodscomment.setPUserName(user.getNickname());
//            }
//        }

        //查询评论数据（不包括回复）
        List<Goodscomment> originList = goodscomments.stream().filter(comment -> comment.getOriginId() == null).collect(Collectors.toList());

        //设置评论数据的子节点，即回复内容
        for(Goodscomment origin: originList){

            List<Goodscomment> comments = goodscomments.stream().filter(comment -> origin.getId().equals(comment.getOriginId())).collect(Collectors.toList());

            comments.forEach( comment ->{
                goodscomments.stream().filter(c -> c.getId().equals(comment.getPid())).findFirst().ifPresent((//找到父级评论的用户id和用户昵称，并设置给当前的回复对象
                        v -> {
                            comment.setPUserId(v.getUserId());
                            comment.setPNickName(v.getNickname());
                        }
                        ));
            });

            origin.setChildren(comments);
        }

        return Result.success(originList);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(goodscommentService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize) {
        Page<Goodscomment> page = new Page<>(pageNum,pageSize);
        QueryWrapper<Goodscomment> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(goodscommentService.page(page,queryWrapper));
    }
}

