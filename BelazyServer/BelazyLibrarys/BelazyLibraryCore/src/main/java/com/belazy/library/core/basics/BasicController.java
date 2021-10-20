package com.belazy.library.core.basics;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.belazy.library.model.dto.ListDTO;
import com.belazy.library.model.vo.PageVO;
import org.springframework.util.StringUtils;

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
    /**
     * 排序
     * @param query
     * @param dto
     */
    public <T>QueryWrapper<T> orderBy(QueryWrapper<T> query, ListDTO dto){
        String field = dto.getField ();
        String sort = dto.getSort ();
        if(!StringUtils.isEmpty (field)){
            if(sort.equalsIgnoreCase ("DECS")){
                String[] str = field.split (",");
                for(String f:str){
                    query.orderByDesc (f);
                }
            }else {
                String[] str = field.split (",");
                for(String f:str){
                    query.orderByAsc (f);
                }
            }
        }
        return query;
    }
}
