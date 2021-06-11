package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysUserInfoEntity;
import com.belazy.business.common.service.ISysUserInfoService;
import com.belazy.business.api.dto.SysUserInfoDTO;
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
 * 用户信息表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "用户信息表 控制类")
@RequestMapping("/bbs-common/sysUserInfo")
public class SysUserInfoController extends BasicController {
    @Autowired
    private ISysUserInfoService iSysUserInfoService;

    @GetMapping("/page")
    @ApiOperation(value = "查询用户信息表列表(分页)", notes = "查询用户信息表列表(分页)")
    public Result<PageVO<SysUserInfoEntity>>page(SysUserInfoDTO dto){
        Page<SysUserInfoEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysUserInfoEntity entity = new SysUserInfoEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysUserInfoEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysUserInfoService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询用户信息表列表(不分页)", notes = "查询用户信息表列表(不分页)")
    public Result<List<SysUserInfoEntity>> list(SysUserInfoDTO dto) {
        SysUserInfoEntity entity = new SysUserInfoEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysUserInfoEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysUserInfoService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取用户信息表信息", notes = "获取用户信息表信息")
    public Result<SysUserInfoEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysUserInfoService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增用户信息表", notes = "新增用户信息表")
    public Result add(@RequestBody SysUserInfoEntity sysUserInfo) {
        boolean result = iSysUserInfoService.save(sysUserInfo);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改用户信息表", notes = "修改用户信息表")
    public Result edit(@RequestBody SysUserInfoEntity sysUserInfo) {
        boolean result = iSysUserInfoService.updateById(sysUserInfo);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存用户信息表(新增/修改)", notes = "保存用户信息表(新增/修改)")
    public Result save(@RequestBody SysUserInfoEntity sysUserInfo) {
        boolean result = iSysUserInfoService.saveOrUpdate(sysUserInfo);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户信息表", notes = "删除用户信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysUserInfoService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
