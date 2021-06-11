package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.SysMenuEntity;
import com.belazy.business.common.service.ISysMenuService;
import com.belazy.business.common.mapper.ISysMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-菜单表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@Service
@Transactional
public class SysMenuService  extends ServiceImpl<ISysMenuMapper, SysMenuEntity> implements ISysMenuService {

}
