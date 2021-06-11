package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysUserPostEntity;
import com.belazy.business.common.service.ISysUserPostService;
import com.belazy.business.api.dto.SysUserPostDTO;
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
 * 用户与岗位关联表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:58:12
 */
@RestController
@Api(description = "用户与岗位关联表 控制类")
@RequestMapping("/bbs-common/sysUserPost")
public class SysUserPostController extends BasicController {
    @Autowired
    private ISysUserPostService iSysUserPostService;

    @GetMapping("/page")
    @ApiOperation(value = "查询用户与岗位关联表列表(分页)", notes = "查询用户与岗位关联表列表(分页)")
    public Result<PageVO<SysUserPostEntity>>page(SysUserPostDTO dto){
        Page<SysUserPostEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysUserPostEntity entity = new SysUserPostEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysUserPostEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysUserPostService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询用户与岗位关联表列表(不分页)", notes = "查询用户与岗位关联表列表(不分页)")
    public Result<List<SysUserPostEntity>> list(SysUserPostDTO dto) {
        SysUserPostEntity entity = new SysUserPostEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysUserPostEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysUserPostService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取用户与岗位关联表信息", notes = "获取用户与岗位关联表信息")
    public Result<SysUserPostEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysUserPostService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增用户与岗位关联表", notes = "新增用户与岗位关联表")
    public Result add(@RequestBody SysUserPostEntity sysUserPost) {
        boolean result = iSysUserPostService.save(sysUserPost);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改用户与岗位关联表", notes = "修改用户与岗位关联表")
    public Result edit(@RequestBody SysUserPostEntity sysUserPost) {
        boolean result = iSysUserPostService.updateById(sysUserPost);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存用户与岗位关联表(新增/修改)", notes = "保存用户与岗位关联表(新增/修改)")
    public Result save(@RequestBody SysUserPostEntity sysUserPost) {
        boolean result = iSysUserPostService.saveOrUpdate(sysUserPost);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户与岗位关联表", notes = "删除用户与岗位关联表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysUserPostService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
