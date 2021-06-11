package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysPostEntity;
import com.belazy.business.common.service.ISysPostService;
import com.belazy.business.api.dto.SysPostDTO;
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
 * 岗位信息表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "岗位信息表 控制类")
@RequestMapping("/bbs-common/sysPost")
public class SysPostController extends BasicController {
    @Autowired
    private ISysPostService iSysPostService;

    @GetMapping("/page")
    @ApiOperation(value = "查询岗位信息表列表(分页)", notes = "查询岗位信息表列表(分页)")
    public Result<PageVO<SysPostEntity>>page(SysPostDTO dto){
        Page<SysPostEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysPostEntity entity = new SysPostEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysPostEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysPostService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询岗位信息表列表(不分页)", notes = "查询岗位信息表列表(不分页)")
    public Result<List<SysPostEntity>> list(SysPostDTO dto) {
        SysPostEntity entity = new SysPostEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysPostEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysPostService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取岗位信息表信息", notes = "获取岗位信息表信息")
    public Result<SysPostEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysPostService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增岗位信息表", notes = "新增岗位信息表")
    public Result add(@RequestBody SysPostEntity sysPost) {
        boolean result = iSysPostService.save(sysPost);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改岗位信息表", notes = "修改岗位信息表")
    public Result edit(@RequestBody SysPostEntity sysPost) {
        boolean result = iSysPostService.updateById(sysPost);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存岗位信息表(新增/修改)", notes = "保存岗位信息表(新增/修改)")
    public Result save(@RequestBody SysPostEntity sysPost) {
        boolean result = iSysPostService.saveOrUpdate(sysPost);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除岗位信息表", notes = "删除岗位信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysPostService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
