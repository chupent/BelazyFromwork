package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.MenuEntity;
import com.belazy.business.common.service.IMenuService;
import com.belazy.business.common.mapper.IMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-菜单表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class MenuService  extends ServiceImpl<IMenuMapper, MenuEntity> implements IMenuService {

}
