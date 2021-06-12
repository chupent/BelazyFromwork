package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.DistrictEntity;
import com.belazy.business.common.service.IDistrictService;
import com.belazy.business.common.mapper.IDistrictMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-大区 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class DistrictService  extends ServiceImpl<IDistrictMapper, DistrictEntity> implements IDistrictService {

}
