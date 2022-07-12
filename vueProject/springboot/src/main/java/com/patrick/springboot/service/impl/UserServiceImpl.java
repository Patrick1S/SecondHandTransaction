package com.patrick.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.patrick.springboot.common.Constants;
import com.patrick.springboot.controller.dto.UserDto;
import com.patrick.springboot.entity.Menu;
import com.patrick.springboot.entity.User;
import com.patrick.springboot.exception.ServiceException;
import com.patrick.springboot.mapper.RoleMapper;
import com.patrick.springboot.mapper.RoleMenuMapper;
import com.patrick.springboot.mapper.UserMapper;
import com.patrick.springboot.service.IMenuService;
import com.patrick.springboot.service.IUserService;
import com.patrick.springboot.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Patrick
 * @since 2022-04-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log LOG = Log.get();

    @Autowired
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;


    @Override
    public UserDto login(UserDto userDto) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username",userDto.getUsername());
//        queryWrapper.eq("password",userDto.getPassword());
////        User one =getOne(queryWrapper);//只能读取一个数据，不能同时读取数据库中用户名和密码重复的数据
////        return one !=null;
////        List<User> list = list(queryWrapper);//允许查询多个重复用户，只要有一个就允许通过登陆界面
////        return list.size() != 0;
//
//        //采用抛出异常方法处理，使重复用户无法正常登陆
//        User one;
//        try {
//            one = getOne(queryWrapper);//从数据库查询用户信息
//        } catch (Exception e){
//            LOG.error(e);
//            throw new ServiceException(Constants.CODE_500,"系统错误");
//        }
        User one = getUserInfo(userDto);

        if(one != null) {
            BeanUtil.copyProperties(one,userDto,true );
            //设置token
            String token = TokenUtils.getToken(one.getId().toString(),one.getPassword());
            userDto.setToken(token);


            String role = one.getRole();//ROLE_ADMIN

            //设置用户的菜单列表
            List<Menu> roleMenus = getRoleMenu(role);
            userDto.setMenus(roleMenus);

            return userDto;
        }else {
            throw new  ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public User register(UserDto userDto) {
        User one = getUserInfo(userDto);
        if(one == null){
            one = new User();
            BeanUtil.copyProperties(userDto,one,true);
            save(one);//把copy完之后的用户对象存储到数据库
        }else {
            throw new  ServiceException(Constants.CODE_600,"用户已存在");
        }

        return one;
    }

    private User getUserInfo(UserDto userDto){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDto.getUsername());
        queryWrapper.eq("password",userDto.getPassword());
        User one;
        try {
            one = getOne(queryWrapper);//从数据库查询用户信息
        } catch (Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }

        return one;
    }


    //获取当前角色的菜单列表
    private List<Menu> getRoleMenu(String roleFlag){

        Integer roleId = roleMapper.selectByFlag(roleFlag);

        //当前角色的所有菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);

        //查出系统的所有菜单
        List<Menu> menus = menuService.findMenus("");

        //new一个最后筛选完成之后的List
        List<Menu>  roleMenus = new ArrayList<>();

        //筛选当前用户角色的菜单
        for(Menu menu : menus){
            if(menuIds.contains(menu.getId())){
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            //removeIf() children 里面不在 menuIds 集合中的元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }

        return roleMenus;
    }
}
