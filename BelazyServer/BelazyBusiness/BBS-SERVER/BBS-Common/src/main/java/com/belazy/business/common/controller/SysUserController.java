package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysUserEntity;
import com.belazy.business.common.service.ISysUserService;
import com.belazy.business.api.dto.SysUserDTO;

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
 * 用户登录账号Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 13:04:11
 */
@RestController
@Api(description = "用户登录账号 控制类")
@RequestMapping("/bbs-common/sysUser")
public class SysUserController extends BasicController {
    @Autowired
    private ISysUserService iSysUserService;

    @GetMapping("/page")
    @ApiOperation(value = "查询用户登录账号列表(分页)", notes = "查询用户登录账号列表(分页)")
    public Result<PageVO<SysUserEntity>>list(SysUserDTO dto){
        Page<SysUserEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysUserEntity entity = new SysUserEntity ();
        QueryWrapper<SysUserEntity> query = Wrappers.query (entity);
        return Result.success (toPage (iSysUserService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询用户登录账号列表(不分页)", notes = "查询用户登录账号列表(不分页)")
    public Result<List<SysUserEntity>> listall(SysUserDTO dto) {
        SysUserEntity entity = new SysUserEntity ();
        QueryWrapper<SysUserEntity> query = Wrappers.query(entity);
        return Result.success(iSysUserService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取用户登录账号信息", notes = "获取用户登录账号信息")
    public Result<SysUserEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysUserService.getById(id));
    }
    @PostMapping
    @ApiOperation(value = "新增用户登录账号", notes = "新增用户登录账号")
    public Result add(@RequestBody SysUserEntity sysUser) {
        boolean result = iSysUserService.save(sysUser);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping
    @ApiOperation(value = "修改用户登录账号", notes = "修改用户登录账号")
    public Result edit(@RequestBody SysUserEntity sysUser) {
        boolean result = iSysUserService.updateById(sysUser);
		return result?Result.success() : Result.fail();
    }    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户登录账号", notes = "删除用户登录账号")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysUserService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
