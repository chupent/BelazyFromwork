package com.belazy.library.core.basics;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.belazy.library.model.vo.PageVO;
/**
 * @author tangcp
 */
public abstract class BasicController {
    /**
     * 构建分页数据模型
     * @param page
     * @param <T>
     * @return
     */
    public <T>PageVO<T> toPage(Page<T> page){
        PageVO<T> vo = new PageVO<> ();
        vo.setRecords (page.getRecords ());
        vo.setSize (page.getSize ());
        vo.setOffset (page.getCurrent ());
        vo.setTotal (page.getTotal ());
        return vo;
    }
}
