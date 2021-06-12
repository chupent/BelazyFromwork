package com.belazy.business.common.controller;

import com.belazy.business.common.entity.DistrictEntity;
import com.belazy.business.common.service.IDistrictService;
import com.belazy.business.api.dto.DistrictDTO;
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
 * 系统-大区Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "系统-大区 控制类")
@RequestMapping("/common/district")
public class DistrictController extends BasicController {
    @Autowired
    private IDistrictService iDistrictService;

    @GetMapping("/page")
    @ApiOperation(value = "查询系统-大区列表(分页)", notes = "查询系统-大区列表(分页)")
    public Result<PageVO<DistrictEntity>>page(DistrictDTO dto){
        Page<DistrictEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        DistrictEntity entity = new DistrictEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<DistrictEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iDistrictService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询系统-大区列表(不分页)", notes = "查询系统-大区列表(不分页)")
    public Result<List<DistrictEntity>> list(DistrictDTO dto) {
        DistrictEntity entity = new DistrictEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<DistrictEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iDistrictService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取系统-大区信息", notes = "获取系统-大区信息")
    public Result<DistrictEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iDistrictService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增系统-大区", notes = "新增系统-大区")
    public Result add(@RequestBody DistrictEntity district) {
        boolean result = iDistrictService.save(district);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改系统-大区", notes = "修改系统-大区")
    public Result edit(@RequestBody DistrictEntity district) {
        boolean result = iDistrictService.updateById(district);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存系统-大区(新增/修改)", notes = "保存系统-大区(新增/修改)")
    public Result save(@RequestBody DistrictEntity district) {
        boolean result = iDistrictService.saveOrUpdate(district);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除系统-大区", notes = "删除系统-大区")
    public Result remove(@PathVariable String id) {
        boolean result =  iDistrictService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
