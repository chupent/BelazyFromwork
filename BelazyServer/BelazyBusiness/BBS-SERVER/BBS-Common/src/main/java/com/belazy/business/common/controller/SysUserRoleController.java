package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysUserRoleEntity;
import com.belazy.business.common.service.ISysUserRoleService;
import com.belazy.business.api.dto.SysUserRoleDTO;
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
 * 用户角色关联表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@RestController
@Api(description = "用户角色关联表 控制类")
@RequestMapping("/bbs-common/sysUserRole")
public class SysUserRoleController extends BasicController {
    @Autowired
    private ISysUserRoleService iSysUserRoleService;

    @GetMapping("/page")
    @ApiOperation(value = "查询用户角色关联表列表(分页)", notes = "查询用户角色关联表列表(分页)")
    public Result<PageVO<SysUserRoleEntity>>page(SysUserRoleDTO dto){
        Page<SysUserRoleEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysUserRoleEntity entity = new SysUserRoleEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysUserRoleEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysUserRoleService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询用户角色关联表列表(不分页)", notes = "查询用户角色关联表列表(不分页)")
    public Result<List<SysUserRoleEntity>> list(SysUserRoleDTO dto) {
        SysUserRoleEntity entity = new SysUserRoleEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysUserRoleEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysUserRoleService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取用户角色关联表信息", notes = "获取用户角色关联表信息")
    public Result<SysUserRoleEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysUserRoleService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增用户角色关联表", notes = "新增用户角色关联表")
    public Result add(@RequestBody SysUserRoleEntity sysUserRole) {
        boolean result = iSysUserRoleService.save(sysUserRole);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改用户角色关联表", notes = "修改用户角色关联表")
    public Result edit(@RequestBody SysUserRoleEntity sysUserRole) {
        boolean result = iSysUserRoleService.updateById(sysUserRole);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存用户角色关联表(新增/修改)", notes = "保存用户角色关联表(新增/修改)")
    public Result save(@RequestBody SysUserRoleEntity sysUserRole) {
        boolean result = iSysUserRoleService.saveOrUpdate(sysUserRole);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户角色关联表", notes = "删除用户角色关联表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysUserRoleService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
