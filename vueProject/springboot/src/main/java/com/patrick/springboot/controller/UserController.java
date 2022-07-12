package com.patrick.springboot.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Constants;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.controller.dto.UserDto;
import com.patrick.springboot.entity.User;
import com.patrick.springboot.service.IUserService;
import com.patrick.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Patrick
 * @since 2022-04-08
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


    //登陆
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto){
        String username =userDto.getUsername();
        String password =userDto.getPassword();
        if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDto dto = userService.login(userDto);
        return Result.success(dto);
    }

    //注册
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto){
        String username =userDto.getUsername();
        String password =userDto.getPassword();
        if(StrUtil.isBlank(username)||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(userService.register(userDto));
    }

    //新增和修改
    @PostMapping
    public Result save(@RequestBody User user){
        //新增或更新都在这里
        return Result.success(userService.saveOrUpdate(user));
    }

    @PostMapping("/updateAccount")
    public Result updateAccount(@RequestBody User user){
        User currentUser = userService.getById(user.getId());


        BigDecimal account = currentUser.getAccount().subtract(user.getAccount());
        if(account.compareTo(BigDecimal.ZERO) != -1){
            currentUser.setAccount(account);
            currentUser.setPoints(currentUser.getPoints().subtract(user.getPoints()));

            return Result.success(userService.saveOrUpdate(currentUser));
        }
        return Result.error("400","账户余额不足,请充值");
    }

    @PostMapping("/updateSellerAfterSalesAccount")
    public Result updateSellerAfterSalesAccount(@RequestBody User user){
        User currentUser = userService.getById(user.getId());


        BigDecimal account = currentUser.getAccount().subtract(user.getAccount());
        if(account.compareTo(BigDecimal.ZERO) != -1){
            currentUser.setAccount(account);
//            currentUser.setPoints(currentUser.getPoints().subtract(user.getPoints()));

            return Result.success(userService.saveOrUpdate(currentUser));
        }
        return Result.error("400","账户余额不足,退款失败");
    }

    @PostMapping("/updateUserPoints")
    public Result updateUserPoints(@RequestBody User user){
        User currentUser = userService.getById(user.getId());


        BigDecimal points = currentUser.getPoints().add(user.getPoints());

        currentUser.setPoints(points);

        return Result.success(userService.saveOrUpdate(currentUser));

//        return Result.error("400","账户余额不足,请充值");
    }

    @PostMapping("/updateSellerAccount")
    public Result updateSellerAccount(@RequestBody User user){
        User seller = userService.getById(user.getId());


        BigDecimal account = seller.getAccount().add(user.getAccount());

        seller.setAccount(account);

        return Result.success(userService.saveOrUpdate(seller));

//        return Result.error("400","账户余额不足,请充值");
    }

    @PostMapping("/rechargeAccount")
    public Result rechargeAccount(@RequestBody User user){
        User currentUser = userService.getById(user.getId());


        BigDecimal account = currentUser.getAccount().add(user.getAccount());
        currentUser.setAccount(account);

        return Result.success(userService.saveOrUpdate(currentUser));

    }

//    //支付订单成功
//    @PostMapping("/updateAccount")
//    public Result paySuccess(@RequestBody Integer id){
//
//        User user = userService.getById(id);
//        return Result.success();
//    }


    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
            return Result.success(userService.removeById(id));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {

            return Result.success(userService.removeByIds(ids));
            }

    @GetMapping
    public Result findAll() {
            return Result.success(userService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
            return Result.success(userService.getById(id));
    }

    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success(userService.getOne(queryWrapper));
    }



    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                               @RequestParam(defaultValue = "")  String email,
                               @RequestParam(defaultValue = "")  String address){

        Page<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();

        if(!"".equals(username)) {
            queryWrapper.like("username", username);
        }
        if(!"".equals(email)) {
            queryWrapper.like("email", email);
        }
        if(!"".equals(address)) {
//        queryWrapper.or().like("address",address);
            queryWrapper.like("address",address);
        }

        queryWrapper.orderByDesc("id");
        //获取当前用户信息
        User currentUser = TokenUtils.getCurrentUser();
        System.out.println("当前管理员========》"+currentUser.getNickname());

        return Result.success(userService.page(page,queryWrapper));
    }


    @PostMapping("/success/updateState")
    public Result paySuccess(@RequestBody User user
    ){
        User update = userService.getById(user.getId());


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
        update.setState(user.getState());
        userService.updateById(update);

        return Result.success();
    }

}

