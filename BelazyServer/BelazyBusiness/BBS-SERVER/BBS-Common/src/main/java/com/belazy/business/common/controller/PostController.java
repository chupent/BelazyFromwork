package com.belazy.business.common.controller;

import com.belazy.business.common.entity.PostEntity;
import com.belazy.business.common.service.IPostService;
import com.belazy.business.api.dto.PostDTO;
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
 * 岗位信息表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "岗位信息表 控制类")
@RequestMapping("/common/post")
public class PostController extends BasicController {
    @Autowired
    private IPostService iPostService;

    @GetMapping("/page")
    @ApiOperation(value = "查询岗位信息表列表(分页)", notes = "查询岗位信息表列表(分页)")
    public Result<PageVO<PostEntity>>page(PostDTO dto){
        Page<PostEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        PostEntity entity = new PostEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<PostEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iPostService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询岗位信息表列表(不分页)", notes = "查询岗位信息表列表(不分页)")
    public Result<List<PostEntity>> list(PostDTO dto) {
        PostEntity entity = new PostEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<PostEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iPostService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取岗位信息表信息", notes = "获取岗位信息表信息")
    public Result<PostEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iPostService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增岗位信息表", notes = "新增岗位信息表")
    public Result add(@RequestBody PostEntity post) {
        boolean result = iPostService.save(post);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改岗位信息表", notes = "修改岗位信息表")
    public Result edit(@RequestBody PostEntity post) {
        boolean result = iPostService.updateById(post);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存岗位信息表(新增/修改)", notes = "保存岗位信息表(新增/修改)")
    public Result save(@RequestBody PostEntity post) {
        boolean result = iPostService.saveOrUpdate(post);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除岗位信息表", notes = "删除岗位信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iPostService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
