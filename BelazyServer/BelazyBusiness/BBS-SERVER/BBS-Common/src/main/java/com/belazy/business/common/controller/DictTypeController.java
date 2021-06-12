package com.belazy.business.common.controller;

import com.belazy.business.common.entity.DictTypeEntity;
import com.belazy.business.common.service.IDictTypeService;
import com.belazy.business.api.dto.DictTypeDTO;
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
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "系统-字典类型 控制类")
@RequestMapping("/common/dictType")
public class DictTypeController extends BasicController {
    @Autowired
    private IDictTypeService iDictTypeService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-字典类型列表(分页)", notes = "查询系统-字典类型列表(分页)")
    public Result<PageVO<DictTypeEntity>>page(DictTypeDTO dto){
        Page<DictTypeEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        DictTypeEntity entity = new DictTypeEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<DictTypeEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iDictTypeService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-字典类型列表(不分页)", notes = "查询系统-字典类型列表(不分页)")
    public Result<List<DictTypeEntity>> list(DictTypeDTO dto) {
        DictTypeEntity entity = new DictTypeEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<DictTypeEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iDictTypeService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-字典类型信息", notes = "获取系统-字典类型信息")
    public Result<DictTypeEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iDictTypeService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-字典类型", notes = "新增系统-字典类型")
    public Result add(@RequestBody DictTypeEntity dictType) {
        boolean result = iDictTypeService.save(dictType);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-字典类型", notes = "修改系统-字典类型")
    public Result edit(@RequestBody DictTypeEntity dictType) {
        boolean result = iDictTypeService.updateById(dictType);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-字典类型(新增/修改)", notes = "保存系统-字典类型(新增/修改)")
    public Result save(@RequestBody DictTypeEntity dictType) {
        boolean result = iDictTypeService.saveOrUpdate(dictType);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-字典类型", notes = "删除系统-字典类型")
    public Result remove(@PathVariable String id) {
        boolean result =  iDictTypeService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
