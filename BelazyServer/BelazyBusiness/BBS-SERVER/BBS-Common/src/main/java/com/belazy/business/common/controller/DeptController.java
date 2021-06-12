package com.belazy.business.common.controller;

import com.belazy.business.common.entity.DeptEntity;
import com.belazy.business.common.service.IDeptService;
import com.belazy.business.api.dto.DeptDTO;
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
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "部门信息表 控制类")
@RequestMapping("/common/dept")
public class DeptController extends BasicController {
    @Autowired
    private IDeptService iDeptService;

    @GetMapping("/page")
    @ApiOperation(value = "查询部门信息表列表(分页)", notes = "查询部门信息表列表(分页)")
    public Result<PageVO<DeptEntity>>page(DeptDTO dto){
        Page<DeptEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        DeptEntity entity = new DeptEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<DeptEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iDeptService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询部门信息表列表(不分页)", notes = "查询部门信息表列表(不分页)")
    public Result<List<DeptEntity>> list(DeptDTO dto) {
        DeptEntity entity = new DeptEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<DeptEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iDeptService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取部门信息表信息", notes = "获取部门信息表信息")
    public Result<DeptEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iDeptService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增部门信息表", notes = "新增部门信息表")
    public Result add(@RequestBody DeptEntity dept) {
        boolean result = iDeptService.save(dept);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改部门信息表", notes = "修改部门信息表")
    public Result edit(@RequestBody DeptEntity dept) {
        boolean result = iDeptService.updateById(dept);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存部门信息表(新增/修改)", notes = "保存部门信息表(新增/修改)")
    public Result save(@RequestBody DeptEntity dept) {
        boolean result = iDeptService.saveOrUpdate(dept);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除部门信息表", notes = "删除部门信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iDeptService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
