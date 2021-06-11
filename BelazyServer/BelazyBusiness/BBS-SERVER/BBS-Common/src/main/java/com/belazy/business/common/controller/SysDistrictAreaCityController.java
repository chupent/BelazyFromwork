package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysDistrictAreaCityEntity;
import com.belazy.business.common.service.ISysDistrictAreaCityService;
import com.belazy.business.api.dto.SysDistrictAreaCityDTO;
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
 * 系统-大区-省份关联表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@RestController
@Api(description = "系统-大区-省份关联表 控制类")
@RequestMapping("/bbs-common/sysDistrictAreaCity")
public class SysDistrictAreaCityController extends BasicController {
    @Autowired
    private ISysDistrictAreaCityService iSysDistrictAreaCityService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-大区-省份关联表列表(分页)", notes = "查询系统-大区-省份关联表列表(分页)")
    public Result<PageVO<SysDistrictAreaCityEntity>>page(SysDistrictAreaCityDTO dto){
        Page<SysDistrictAreaCityEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysDistrictAreaCityEntity entity = new SysDistrictAreaCityEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDistrictAreaCityEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysDistrictAreaCityService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-大区-省份关联表列表(不分页)", notes = "查询系统-大区-省份关联表列表(不分页)")
    public Result<List<SysDistrictAreaCityEntity>> list(SysDistrictAreaCityDTO dto) {
        SysDistrictAreaCityEntity entity = new SysDistrictAreaCityEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDistrictAreaCityEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysDistrictAreaCityService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-大区-省份关联表信息", notes = "获取系统-大区-省份关联表信息")
    public Result<SysDistrictAreaCityEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysDistrictAreaCityService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-大区-省份关联表", notes = "新增系统-大区-省份关联表")
    public Result add(@RequestBody SysDistrictAreaCityEntity sysDistrictAreaCity) {
        boolean result = iSysDistrictAreaCityService.save(sysDistrictAreaCity);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-大区-省份关联表", notes = "修改系统-大区-省份关联表")
    public Result edit(@RequestBody SysDistrictAreaCityEntity sysDistrictAreaCity) {
        boolean result = iSysDistrictAreaCityService.updateById(sysDistrictAreaCity);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-大区-省份关联表(新增/修改)", notes = "保存系统-大区-省份关联表(新增/修改)")
    public Result save(@RequestBody SysDistrictAreaCityEntity sysDistrictAreaCity) {
        boolean result = iSysDistrictAreaCityService.saveOrUpdate(sysDistrictAreaCity);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-大区-省份关联表", notes = "删除系统-大区-省份关联表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysDistrictAreaCityService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
