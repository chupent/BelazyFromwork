package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysDictTypeEntity;
import com.belazy.business.common.service.ISysDictTypeService;
import com.belazy.business.api.dto.SysDictTypeDTO;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.belazy.library.core.basics.BasicController;
import com.belazy.library.model.Result;
import com.belazy.library.model.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;


/**
 * 系统-字典类型Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "系统-字典类型 控制类")
@RequestMapping("/bbs-common/sysDictType")
public class SysDictTypeController extends BasicController {
    @Autowired
    private ISysDictTypeService iSysDictTypeService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-字典类型列表(分页)", notes = "查询系统-字典类型列表(分页)")
    public Result<PageVO<SysDictTypeEntity>>page(SysDictTypeDTO dto){
        Page<SysDictTypeEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysDictTypeEntity entity = new SysDictTypeEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDictTypeEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysDictTypeService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-字典类型列表(不分页)", notes = "查询系统-字典类型列表(不分页)")
    public Result<List<SysDictTypeEntity>> list(SysDictTypeDTO dto) {
        SysDictTypeEntity entity = new SysDictTypeEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDictTypeEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysDictTypeService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-字典类型信息", notes = "获取系统-字典类型信息")
    public Result<SysDictTypeEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysDictTypeService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-字典类型", notes = "新增系统-字典类型")
    public Result add(@RequestBody SysDictTypeEntity sysDictType) {
        boolean result = iSysDictTypeService.save(sysDictType);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-字典类型", notes = "修改系统-字典类型")
    public Result edit(@RequestBody SysDictTypeEntity sysDictType) {
        boolean result = iSysDictTypeService.updateById(sysDictType);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-字典类型(新增/修改)", notes = "保存系统-字典类型(新增/修改)")
    public Result save(@RequestBody SysDictTypeEntity sysDictType) {
        boolean result = iSysDictTypeService.saveOrUpdate(sysDictType);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-字典类型", notes = "删除系统-字典类型")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysDictTypeService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
