package com.belazy.business.common.controller;

import com.belazy.business.common.entity.SysMenuEntity;
import com.belazy.business.common.service.ISysMenuService;
import com.belazy.business.api.dto.SysMenuDTO;
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
 * 系统-菜单表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月11日 15:34:44
 */
@RestController
@Api(description = "系统-菜单表 控制类")
@RequestMapping("/bbs-common/sysMenu")
public class SysMenuController extends BasicController {
    @Autowired
    private ISysMenuService iSysMenuService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-菜单表列表(分页)", notes = "查询系统-菜单表列表(分页)")
    public Result<PageVO<SysMenuEntity>>page(SysMenuDTO dto){
        Page<SysMenuEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        SysMenuEntity entity = new SysMenuEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysMenuEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iSysMenuService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-菜单表列表(不分页)", notes = "查询系统-菜单表列表(不分页)")
    public Result<List<SysMenuEntity>> list(SysMenuDTO dto) {
        SysMenuEntity entity = new SysMenuEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<SysMenuEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iSysMenuService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-菜单表信息", notes = "获取系统-菜单表信息")
    public Result<SysMenuEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iSysMenuService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-菜单表", notes = "新增系统-菜单表")
    public Result add(@RequestBody SysMenuEntity sysMenu) {
        boolean result = iSysMenuService.save(sysMenu);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-菜单表", notes = "修改系统-菜单表")
    public Result edit(@RequestBody SysMenuEntity sysMenu) {
        boolean result = iSysMenuService.updateById(sysMenu);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-菜单表(新增/修改)", notes = "保存系统-菜单表(新增/修改)")
    public Result save(@RequestBody SysMenuEntity sysMenu) {
        boolean result = iSysMenuService.saveOrUpdate(sysMenu);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-菜单表", notes = "删除系统-菜单表")
    public Result remove(@PathVariable String id) {
        boolean result =  iSysMenuService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
