package com.belazy.business.common.controller;

import com.belazy.business.common.entity.FileEntity;
import com.belazy.business.common.service.IFileService;
import com.belazy.business.api.dto.FileDTO;
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
 * 系统-文件信息表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "系统-文件信息表 控制类")
@RequestMapping("/common/file")
public class FileController extends BasicController {
    @Autowired
    private IFileService iFileService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-文件信息表列表(分页)", notes = "查询系统-文件信息表列表(分页)")
    public Result<PageVO<FileEntity>>page(FileDTO dto){
        Page<FileEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        FileEntity entity = new FileEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<FileEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iFileService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-文件信息表列表(不分页)", notes = "查询系统-文件信息表列表(不分页)")
    public Result<List<FileEntity>> list(FileDTO dto) {
        FileEntity entity = new FileEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<FileEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iFileService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-文件信息表信息", notes = "获取系统-文件信息表信息")
    public Result<FileEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iFileService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-文件信息表", notes = "新增系统-文件信息表")
    public Result add(@RequestBody FileEntity file) {
        boolean result = iFileService.save(file);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-文件信息表", notes = "修改系统-文件信息表")
    public Result edit(@RequestBody FileEntity file) {
        boolean result = iFileService.updateById(file);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-文件信息表(新增/修改)", notes = "保存系统-文件信息表(新增/修改)")
    public Result save(@RequestBody FileEntity file) {
        boolean result = iFileService.saveOrUpdate(file);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-文件信息表", notes = "删除系统-文件信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iFileService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
