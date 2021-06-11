package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysFileEntity;
import com.belazy.business.common.service.ISysFileService;
import com.belazy.business.api.dto.SysFileDTO;
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
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "系统-文件信息表 控制类")
@RequestMapping("/bbs-common/sysFile")
public class SysFileController extends BasicController {
    @Autowired
    private ISysFileService iSysFileService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-文件信息表列表(分页)", notes = "查询系统-文件信息表列表(分页)")
    public Result<PageVO<SysFileEntity>>page(SysFileDTO dto){
        Page<SysFileEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysFileEntity entity = new SysFileEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysFileEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysFileService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-文件信息表列表(不分页)", notes = "查询系统-文件信息表列表(不分页)")
    public Result<List<SysFileEntity>> list(SysFileDTO dto) {
        SysFileEntity entity = new SysFileEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysFileEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysFileService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-文件信息表信息", notes = "获取系统-文件信息表信息")
    public Result<SysFileEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysFileService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-文件信息表", notes = "新增系统-文件信息表")
    public Result add(@RequestBody SysFileEntity sysFile) {
        boolean result = iSysFileService.save(sysFile);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-文件信息表", notes = "修改系统-文件信息表")
    public Result edit(@RequestBody SysFileEntity sysFile) {
        boolean result = iSysFileService.updateById(sysFile);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-文件信息表(新增/修改)", notes = "保存系统-文件信息表(新增/修改)")
    public Result save(@RequestBody SysFileEntity sysFile) {
        boolean result = iSysFileService.saveOrUpdate(sysFile);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-文件信息表", notes = "删除系统-文件信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysFileService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
