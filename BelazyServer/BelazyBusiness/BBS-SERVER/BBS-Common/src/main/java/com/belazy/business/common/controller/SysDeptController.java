package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysDeptEntity;
import com.belazy.business.common.service.ISysDeptService;
import com.belazy.business.api.dto.SysDeptDTO;
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
 * 部门信息表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "部门信息表 控制类")
@RequestMapping("/bbs-common/sysDept")
public class SysDeptController extends BasicController {
    @Autowired
    private ISysDeptService iSysDeptService;

    @GetMapping("/page")
    @ApiOperation(value = "查询部门信息表列表(分页)", notes = "查询部门信息表列表(分页)")
    public Result<PageVO<SysDeptEntity>>page(SysDeptDTO dto){
        Page<SysDeptEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysDeptEntity entity = new SysDeptEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDeptEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysDeptService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询部门信息表列表(不分页)", notes = "查询部门信息表列表(不分页)")
    public Result<List<SysDeptEntity>> list(SysDeptDTO dto) {
        SysDeptEntity entity = new SysDeptEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysDeptEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysDeptService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取部门信息表信息", notes = "获取部门信息表信息")
    public Result<SysDeptEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysDeptService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增部门信息表", notes = "新增部门信息表")
    public Result add(@RequestBody SysDeptEntity sysDept) {
        boolean result = iSysDeptService.save(sysDept);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改部门信息表", notes = "修改部门信息表")
    public Result edit(@RequestBody SysDeptEntity sysDept) {
        boolean result = iSysDeptService.updateById(sysDept);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存部门信息表(新增/修改)", notes = "保存部门信息表(新增/修改)")
    public Result save(@RequestBody SysDeptEntity sysDept) {
        boolean result = iSysDeptService.saveOrUpdate(sysDept);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门信息表", notes = "删除部门信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysDeptService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
