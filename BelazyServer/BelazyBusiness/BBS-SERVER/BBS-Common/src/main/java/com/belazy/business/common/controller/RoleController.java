package com.belazy.business.common.controller;

import com.belazy.business.common.entity.RoleEntity;
import com.belazy.business.common.service.IRoleService;
import com.belazy.business.api.dto.RoleDTO;
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
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "角色信息表 控制类")
@RequestMapping("/common/role")
public class RoleController extends BasicController {
    @Autowired
    private IRoleService iRoleService;

    @GetMapping("/page")
    @ApiOperation(value = "查询角色信息表列表(分页)", notes = "查询角色信息表列表(分页)")
    public Result<PageVO<RoleEntity>>page(RoleDTO dto){
        Page<RoleEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        RoleEntity entity = new RoleEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<RoleEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iRoleService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询角色信息表列表(不分页)", notes = "查询角色信息表列表(不分页)")
    public Result<List<RoleEntity>> list(RoleDTO dto) {
        RoleEntity entity = new RoleEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<RoleEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iRoleService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取角色信息表信息", notes = "获取角色信息表信息")
    public Result<RoleEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iRoleService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增角色信息表", notes = "新增角色信息表")
    public Result add(@RequestBody RoleEntity role) {
        boolean result = iRoleService.save(role);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改角色信息表", notes = "修改角色信息表")
    public Result edit(@RequestBody RoleEntity role) {
        boolean result = iRoleService.updateById(role);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存角色信息表(新增/修改)", notes = "保存角色信息表(新增/修改)")
    public Result save(@RequestBody RoleEntity role) {
        boolean result = iRoleService.saveOrUpdate(role);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色信息表", notes = "删除角色信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iRoleService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
