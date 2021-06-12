package com.belazy.business.common.controller;

import com.belazy.business.common.entity.AreaCityEntity;
import com.belazy.business.common.service.IAreaCityService;
import com.belazy.business.api.dto.AreaCityDTO;
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
 * 系统-省份城市区域表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "系统-省份城市区域表 控制类")
@RequestMapping("/common/areaCity")
public class AreaCityController extends BasicController {
    @Autowired
    private IAreaCityService iAreaCityService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-省份城市区域表列表(分页)", notes = "查询系统-省份城市区域表列表(分页)")
    public Result<PageVO<AreaCityEntity>>page(AreaCityDTO dto){
        Page<AreaCityEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        AreaCityEntity entity = new AreaCityEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<AreaCityEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iAreaCityService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-省份城市区域表列表(不分页)", notes = "查询系统-省份城市区域表列表(不分页)")
    public Result<List<AreaCityEntity>> list(AreaCityDTO dto) {
        AreaCityEntity entity = new AreaCityEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<AreaCityEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iAreaCityService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-省份城市区域表信息", notes = "获取系统-省份城市区域表信息")
    public Result<AreaCityEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iAreaCityService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-省份城市区域表", notes = "新增系统-省份城市区域表")
    public Result add(@RequestBody AreaCityEntity areaCity) {
        boolean result = iAreaCityService.save(areaCity);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-省份城市区域表", notes = "修改系统-省份城市区域表")
    public Result edit(@RequestBody AreaCityEntity areaCity) {
        boolean result = iAreaCityService.updateById(areaCity);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-省份城市区域表(新增/修改)", notes = "保存系统-省份城市区域表(新增/修改)")
    public Result save(@RequestBody AreaCityEntity areaCity) {
        boolean result = iAreaCityService.saveOrUpdate(areaCity);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-省份城市区域表", notes = "删除系统-省份城市区域表")
    public Result remove(@PathVariable String id) {
        boolean result =  iAreaCityService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
