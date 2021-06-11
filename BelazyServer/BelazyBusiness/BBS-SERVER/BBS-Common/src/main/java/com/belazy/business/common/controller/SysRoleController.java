package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysRoleEntity;
import com.belazy.business.common.service.ISysRoleService;
import com.belazy.business.api.dto.SysRoleDTO;
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
 * 角色信息表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "角色信息表 控制类")
@RequestMapping("/bbs-common/sysRole")
public class SysRoleController extends BasicController {
    @Autowired
    private ISysRoleService iSysRoleService;

    @GetMapping("/page")
    @ApiOperation(value = "查询角色信息表列表(分页)", notes = "查询角色信息表列表(分页)")
    public Result<PageVO<SysRoleEntity>>page(SysRoleDTO dto){
        Page<SysRoleEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysRoleEntity entity = new SysRoleEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysRoleEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysRoleService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询角色信息表列表(不分页)", notes = "查询角色信息表列表(不分页)")
    public Result<List<SysRoleEntity>> list(SysRoleDTO dto) {
        SysRoleEntity entity = new SysRoleEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysRoleEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysRoleService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取角色信息表信息", notes = "获取角色信息表信息")
    public Result<SysRoleEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysRoleService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增角色信息表", notes = "新增角色信息表")
    public Result add(@RequestBody SysRoleEntity sysRole) {
        boolean result = iSysRoleService.save(sysRole);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改角色信息表", notes = "修改角色信息表")
    public Result edit(@RequestBody SysRoleEntity sysRole) {
        boolean result = iSysRoleService.updateById(sysRole);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存角色信息表(新增/修改)", notes = "保存角色信息表(新增/修改)")
    public Result save(@RequestBody SysRoleEntity sysRole) {
        boolean result = iSysRoleService.saveOrUpdate(sysRole);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色信息表", notes = "删除角色信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysRoleService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
