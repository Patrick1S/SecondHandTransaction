package com.patrick.springboot.service;

import com.patrick.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Patrick
 * @since 2022-05-14
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
