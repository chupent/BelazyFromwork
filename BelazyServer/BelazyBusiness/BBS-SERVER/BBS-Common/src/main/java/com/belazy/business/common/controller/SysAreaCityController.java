package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysAreaCityEntity;
import com.belazy.business.common.service.ISysAreaCityService;
import com.belazy.business.api.dto.SysAreaCityDTO;
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
 * 系统-省份城市区域表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:27:25
 */
@RestController
@Api(description = "系统-省份城市区域表 控制类")
@RequestMapping("/bbs-common/sysAreaCity")
public class SysAreaCityController extends BasicController {
    @Autowired
    private ISysAreaCityService iSysAreaCityService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-省份城市区域表列表(分页)", notes = "查询系统-省份城市区域表列表(分页)")
    public Result<PageVO<SysAreaCityEntity>>page(SysAreaCityDTO dto){
        Page<SysAreaCityEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysAreaCityEntity entity = new SysAreaCityEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysAreaCityEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysAreaCityService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-省份城市区域表列表(不分页)", notes = "查询系统-省份城市区域表列表(不分页)")
    public Result<List<SysAreaCityEntity>> list(SysAreaCityDTO dto) {
        SysAreaCityEntity entity = new SysAreaCityEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysAreaCityEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysAreaCityService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-省份城市区域表信息", notes = "获取系统-省份城市区域表信息")
    public Result<SysAreaCityEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysAreaCityService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-省份城市区域表", notes = "新增系统-省份城市区域表")
    public Result add(@RequestBody SysAreaCityEntity sysAreaCity) {
        boolean result = iSysAreaCityService.save(sysAreaCity);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-省份城市区域表", notes = "修改系统-省份城市区域表")
    public Result edit(@RequestBody SysAreaCityEntity sysAreaCity) {
        boolean result = iSysAreaCityService.updateById(sysAreaCity);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-省份城市区域表(新增/修改)", notes = "保存系统-省份城市区域表(新增/修改)")
    public Result save(@RequestBody SysAreaCityEntity sysAreaCity) {
        boolean result = iSysAreaCityService.saveOrUpdate(sysAreaCity);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-省份城市区域表", notes = "删除系统-省份城市区域表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysAreaCityService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
