package com.belazy.business.common.mapper;
import com.belazy.business.common.entity.MenuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 系统-菜单表
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
public interface IMenuMapper extends BaseMapper<MenuEntity> {
    List<MenuEntity> userMenuList(String userId);
}
