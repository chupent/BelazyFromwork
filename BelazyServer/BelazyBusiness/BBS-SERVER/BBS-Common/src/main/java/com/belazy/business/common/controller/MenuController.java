package com.belazy.business.common.controller;

import com.belazy.business.common.entity.MenuEntity;
import com.belazy.business.common.service.IMenuService;
import com.belazy.business.api.dto.MenuDTO;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.belazy.library.core.basics.BasicController;
import com.belazy.library.model.Result;
import com.belazy.library.model.vo.PageVO;
import com.belazy.library.web.util.TokenUtils;
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
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "系统-菜单表 控制类")
@RequestMapping("/common/menu")
public class MenuController extends BasicController {
    @Autowired
    private IMenuService iMenuService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-菜单表列表(分页)", notes = "查询系统-菜单表列表(分页)")
    public Result<PageVO<MenuEntity>>page(MenuDTO dto){
        Page<MenuEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        MenuEntity entity = new MenuEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<MenuEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iMenuService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-菜单表列表(不分页)", notes = "查询系统-菜单表列表(不分页)")
    public Result<List<MenuEntity>> list(MenuDTO dto) {
        MenuEntity entity = new MenuEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<MenuEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iMenuService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-菜单表信息", notes = "获取系统-菜单表信息")
    public Result<MenuEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iMenuService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-菜单表", notes = "新增系统-菜单表")
    public Result add(@RequestBody MenuEntity menu) {
        boolean result = iMenuService.save(menu);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-菜单表", notes = "修改系统-菜单表")
    public Result edit(@RequestBody MenuEntity menu) {
        boolean result = iMenuService.updateById(menu);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-菜单表(新增/修改)", notes = "保存系统-菜单表(新增/修改)")
    public Result save(@RequestBody MenuEntity menu) {
        boolean result = iMenuService.saveOrUpdate(menu);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-菜单表", notes = "删除系统-菜单表")
    public Result remove(@PathVariable String id) {
        boolean result =  iMenuService.removeById(id);
		return result?Result.success() : Result.fail();
    }
    @GetMapping("/userMenuList")
    @ApiOperation(value = "查询系统-用户菜单表列表(不分页)", notes = "查询系统-用户菜单表列表(不分页)")
    public Result<List<MenuEntity>> userMenuList() {
        List<MenuEntity> list = iMenuService.userMenuList();
        return Result.success(list);
    }
}
