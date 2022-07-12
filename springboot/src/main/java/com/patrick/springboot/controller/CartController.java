package com.patrick.springboot.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.patrick.springboot.common.Result;
import com.patrick.springboot.entity.Cart;
import com.patrick.springboot.entity.Goods;
import com.patrick.springboot.entity.User;
import com.patrick.springboot.mapper.CartMapper;
import com.patrick.springboot.service.ICartService;
import com.patrick.springboot.service.IGoodsService;
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
 * @since 2022-06-11
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private ICartService cartService;

    @Resource
    private CartMapper cartMapper;


    @Resource
    private IGoodsService goodsService;



    //新增和修改
    @PostMapping
    public Result save(@RequestBody Cart cart){
        User loginUser = TokenUtils.getCurrentUser();

        //更新购物车里相同的商品数量
        Integer goodsId = cart.getGoodsId();
        Integer userId  = cart.getUserId();
        Integer loginId = loginUser.getId();

        Goods goods = goodsService.getById(goodsId);
        Integer goodsStore = goods.getStore();


        //新增或更新
        if(cart.getId()==null){

            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("login_id", loginId);
            queryWrapper.eq("goods_id",goodsId);
            Cart db = cartService.getOne(queryWrapper);

            if(db != null){
                Integer cartNum = db.getNum() + cart.getNum();
                if(cartNum <= goodsStore) {
                    db.setNum(cartNum);
                }else {
                    return Result.error("400","商品加购件数超过库存");
                }
                cartService.updateById(db);
                return Result.success();
            }

            cart.setGoodsId(goodsId);
            cart.setUserId(userId);
            cart.setTime(DateUtil.now());
            cart.setLoginId(TokenUtils.getCurrentUser().getId());
        }

        //新增或更新都在这里
        cartService.saveOrUpdate(cart);
        return Result.success();
        }

    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        cartService.removeById(id);
        return Result.success();
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        cartService.removeByIds(ids);
        return Result.success();
    }

    @GetMapping
    public Result findAll() {
        return Result.success(cartService.list());
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(cartService.getById(id));
    }

    @GetMapping("/frontPage")
    public Result findFrontPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String name,
                                @RequestParam(defaultValue = "") Integer sellerId
    ) {
        User loginUser = TokenUtils.getCurrentUser();
        Integer loginId = loginUser.getId();
        String role = loginUser.getRole();
        Page<Cart> page = new Page<>(pageNum,pageSize);
        //name是商品的
        return Result.success(cartMapper.frontPage(page,loginId,role,name,sellerId));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name
//                           @RequestParam(defaultValue = "") Integer sellerId
    ) {
        User loginUser = TokenUtils.getCurrentUser();
        Integer loginId = loginUser.getId();
        Page<Cart> page = new Page<>(pageNum,pageSize);

        //name是商品的
        return Result.success(cartMapper.Page(page,loginId,name));
    }

//    @GetMapping("/sellersGoods/{sellerId}")
//    public Result findSellerList(@PathVariable Integer sellerId) {
////        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
////        queryWrapper.eq("user_id", sellerId);
////        List<Cart> cartList = cartService.list(queryWrapper);
//        User loginUser = TokenUtils.getCurrentUser();
//        Integer loginId = loginUser.getId();
//        String role = loginUser.getRole();
//        return Result.success(cartMapper.sellersGoods(loginId,role,sellerId));
//    }
}

