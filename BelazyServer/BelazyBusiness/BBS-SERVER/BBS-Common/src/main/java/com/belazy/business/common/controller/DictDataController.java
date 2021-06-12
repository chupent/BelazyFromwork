package com.belazy.business.common.controller;

import com.belazy.business.common.entity.DictDataEntity;
import com.belazy.business.common.service.IDictDataService;
import com.belazy.business.api.dto.DictDataDTO;
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
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "系统-通用字典表 控制类")
@RequestMapping("/common/dictData")
public class DictDataController extends BasicController {
    @Autowired
    private IDictDataService iDictDataService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-通用字典表列表(分页)", notes = "查询系统-通用字典表列表(分页)")
    public Result<PageVO<DictDataEntity>>page(DictDataDTO dto){
        Page<DictDataEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        DictDataEntity entity = new DictDataEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<DictDataEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iDictDataService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-通用字典表列表(不分页)", notes = "查询系统-通用字典表列表(不分页)")
    public Result<List<DictDataEntity>> list(DictDataDTO dto) {
        DictDataEntity entity = new DictDataEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<DictDataEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iDictDataService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-通用字典表信息", notes = "获取系统-通用字典表信息")
    public Result<DictDataEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iDictDataService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-通用字典表", notes = "新增系统-通用字典表")
    public Result add(@RequestBody DictDataEntity dictData) {
        boolean result = iDictDataService.save(dictData);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-通用字典表", notes = "修改系统-通用字典表")
    public Result edit(@RequestBody DictDataEntity dictData) {
        boolean result = iDictDataService.updateById(dictData);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-通用字典表(新增/修改)", notes = "保存系统-通用字典表(新增/修改)")
    public Result save(@RequestBody DictDataEntity dictData) {
        boolean result = iDictDataService.saveOrUpdate(dictData);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-通用字典表", notes = "删除系统-通用字典表")
    public Result remove(@PathVariable String id) {
        boolean result =  iDictDataService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
