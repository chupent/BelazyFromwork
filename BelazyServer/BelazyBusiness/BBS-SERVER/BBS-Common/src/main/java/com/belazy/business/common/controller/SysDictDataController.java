package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysDictDataEntity;
import com.belazy.business.common.service.ISysDictDataService;
import com.belazy.business.api.dto.SysDictDataDTO;
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
 * 系统-通用字典表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "系统-通用字典表 控制类")
@RequestMapping("/bbs-common/sysDictData")
public class SysDictDataController extends BasicController {
    @Autowired
    private ISysDictDataService iSysDictDataService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-通用字典表列表(分页)", notes = "查询系统-通用字典表列表(分页)")
    public Result<PageVO<SysDictDataEntity>>page(SysDictDataDTO dto){
        Page<SysDictDataEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysDictDataEntity entity = new SysDictDataEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDictDataEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysDictDataService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-通用字典表列表(不分页)", notes = "查询系统-通用字典表列表(不分页)")
    public Result<List<SysDictDataEntity>> list(SysDictDataDTO dto) {
        SysDictDataEntity entity = new SysDictDataEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDictDataEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysDictDataService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-通用字典表信息", notes = "获取系统-通用字典表信息")
    public Result<SysDictDataEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysDictDataService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-通用字典表", notes = "新增系统-通用字典表")
    public Result add(@RequestBody SysDictDataEntity sysDictData) {
        boolean result = iSysDictDataService.save(sysDictData);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-通用字典表", notes = "修改系统-通用字典表")
    public Result edit(@RequestBody SysDictDataEntity sysDictData) {
        boolean result = iSysDictDataService.updateById(sysDictData);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-通用字典表(新增/修改)", notes = "保存系统-通用字典表(新增/修改)")
    public Result save(@RequestBody SysDictDataEntity sysDictData) {
        boolean result = iSysDictDataService.saveOrUpdate(sysDictData);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-通用字典表", notes = "删除系统-通用字典表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysDictDataService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
