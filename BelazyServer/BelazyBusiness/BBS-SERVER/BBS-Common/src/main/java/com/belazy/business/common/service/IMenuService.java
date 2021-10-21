package com.belazy.business.common.service;

import com.belazy.business.common.entity.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 系统-菜单表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
public interface IMenuService extends IService<MenuEntity> {
    List<MenuEntity> userMenuList();
}
