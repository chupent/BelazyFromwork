package com.belazy.business.common.controller;

import com.belazy.business.common.entity.ConfigEntity;
import com.belazy.business.common.service.IConfigService;
import com.belazy.business.api.dto.ConfigDTO;
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
 * 系统-参数配置表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "系统-参数配置表 控制类")
@RequestMapping("/common/config")
public class ConfigController extends BasicController {
    @Autowired
    private IConfigService iConfigService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-参数配置表列表(分页)", notes = "查询系统-参数配置表列表(分页)")
    public Result<PageVO<ConfigEntity>>page(ConfigDTO dto){
        Page<ConfigEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        ConfigEntity entity = new ConfigEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<ConfigEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iConfigService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-参数配置表列表(不分页)", notes = "查询系统-参数配置表列表(不分页)")
    public Result<List<ConfigEntity>> list(ConfigDTO dto) {
        ConfigEntity entity = new ConfigEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<ConfigEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iConfigService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-参数配置表信息", notes = "获取系统-参数配置表信息")
    public Result<ConfigEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iConfigService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-参数配置表", notes = "新增系统-参数配置表")
    public Result add(@RequestBody ConfigEntity config) {
        boolean result = iConfigService.save(config);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-参数配置表", notes = "修改系统-参数配置表")
    public Result edit(@RequestBody ConfigEntity config) {
        boolean result = iConfigService.updateById(config);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-参数配置表(新增/修改)", notes = "保存系统-参数配置表(新增/修改)")
    public Result save(@RequestBody ConfigEntity config) {
        boolean result = iConfigService.saveOrUpdate(config);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-参数配置表", notes = "删除系统-参数配置表")
    public Result remove(@PathVariable String id) {
        boolean result =  iConfigService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
