package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysLoginLogEntity;
import com.belazy.business.common.service.ISysLoginLogService;
import com.belazy.business.api.dto.SysLoginLogDTO;
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
 * 系统-访问记录Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "系统-访问记录 控制类")
@RequestMapping("/bbs-common/sysLoginLog")
public class SysLoginLogController extends BasicController {
    @Autowired
    private ISysLoginLogService iSysLoginLogService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-访问记录列表(分页)", notes = "查询系统-访问记录列表(分页)")
    public Result<PageVO<SysLoginLogEntity>>page(SysLoginLogDTO dto){
        Page<SysLoginLogEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysLoginLogEntity entity = new SysLoginLogEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysLoginLogEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysLoginLogService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-访问记录列表(不分页)", notes = "查询系统-访问记录列表(不分页)")
    public Result<List<SysLoginLogEntity>> list(SysLoginLogDTO dto) {
        SysLoginLogEntity entity = new SysLoginLogEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysLoginLogEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysLoginLogService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-访问记录信息", notes = "获取系统-访问记录信息")
    public Result<SysLoginLogEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysLoginLogService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-访问记录", notes = "新增系统-访问记录")
    public Result add(@RequestBody SysLoginLogEntity sysLoginLog) {
        boolean result = iSysLoginLogService.save(sysLoginLog);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-访问记录", notes = "修改系统-访问记录")
    public Result edit(@RequestBody SysLoginLogEntity sysLoginLog) {
        boolean result = iSysLoginLogService.updateById(sysLoginLog);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-访问记录(新增/修改)", notes = "保存系统-访问记录(新增/修改)")
    public Result save(@RequestBody SysLoginLogEntity sysLoginLog) {
        boolean result = iSysLoginLogService.saveOrUpdate(sysLoginLog);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-访问记录", notes = "删除系统-访问记录")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysLoginLogService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
