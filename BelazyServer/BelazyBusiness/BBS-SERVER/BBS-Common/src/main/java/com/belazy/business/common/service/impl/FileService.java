package com.belazy.business.common.service.impl;

import com.belazy.business.common.entity.FileEntity;
import com.belazy.business.common.service.IFileService;
import com.belazy.business.common.mapper.IFileMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 系统-文件信息表 Service业务层处理
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@Service
@Transactional
public class FileService  extends ServiceImpl<IFileMapper, FileEntity> implements IFileService {

}
