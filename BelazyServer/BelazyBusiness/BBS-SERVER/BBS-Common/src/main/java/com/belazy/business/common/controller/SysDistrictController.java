package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysDistrictEntity;
import com.belazy.business.common.service.ISysDistrictService;
import com.belazy.business.api.dto.SysDistrictDTO;
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
 * 系统-大区Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "系统-大区 控制类")
@RequestMapping("/bbs-common/sysDistrict")
public class SysDistrictController extends BasicController {
    @Autowired
    private ISysDistrictService iSysDistrictService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-大区列表(分页)", notes = "查询系统-大区列表(分页)")
    public Result<PageVO<SysDistrictEntity>>page(SysDistrictDTO dto){
        Page<SysDistrictEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysDistrictEntity entity = new SysDistrictEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDistrictEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysDistrictService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-大区列表(不分页)", notes = "查询系统-大区列表(不分页)")
    public Result<List<SysDistrictEntity>> list(SysDistrictDTO dto) {
        SysDistrictEntity entity = new SysDistrictEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDistrictEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysDistrictService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-大区信息", notes = "获取系统-大区信息")
    public Result<SysDistrictEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysDistrictService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-大区", notes = "新增系统-大区")
    public Result add(@RequestBody SysDistrictEntity sysDistrict) {
        boolean result = iSysDistrictService.save(sysDistrict);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-大区", notes = "修改系统-大区")
    public Result edit(@RequestBody SysDistrictEntity sysDistrict) {
        boolean result = iSysDistrictService.updateById(sysDistrict);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-大区(新增/修改)", notes = "保存系统-大区(新增/修改)")
    public Result save(@RequestBody SysDistrictEntity sysDistrict) {
        boolean result = iSysDistrictService.saveOrUpdate(sysDistrict);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-大区", notes = "删除系统-大区")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysDistrictService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
