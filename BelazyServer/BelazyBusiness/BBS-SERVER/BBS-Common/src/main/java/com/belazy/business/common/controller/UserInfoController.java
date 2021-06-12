package com.belazy.business.common.controller;

import com.belazy.business.common.entity.UserInfoEntity;
import com.belazy.business.common.service.IUserInfoService;
import com.belazy.business.api.dto.UserInfoDTO;
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
 * 用户信息表Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "用户信息表 控制类")
@RequestMapping("/common/userInfo")
public class UserInfoController extends BasicController {
    @Autowired
    private IUserInfoService iUserInfoService;

    @GetMapping("/page")
    @ApiOperation(value = "查询用户信息表列表(分页)", notes = "查询用户信息表列表(分页)")
    public Result<PageVO<UserInfoEntity>>page(UserInfoDTO dto){
        Page<UserInfoEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        UserInfoEntity entity = new UserInfoEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<UserInfoEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iUserInfoService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询用户信息表列表(不分页)", notes = "查询用户信息表列表(不分页)")
    public Result<List<UserInfoEntity>> list(UserInfoDTO dto) {
        UserInfoEntity entity = new UserInfoEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<UserInfoEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iUserInfoService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取用户信息表信息", notes = "获取用户信息表信息")
    public Result<UserInfoEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iUserInfoService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增用户信息表", notes = "新增用户信息表")
    public Result add(@RequestBody UserInfoEntity userInfo) {
        boolean result = iUserInfoService.save(userInfo);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改用户信息表", notes = "修改用户信息表")
    public Result edit(@RequestBody UserInfoEntity userInfo) {
        boolean result = iUserInfoService.updateById(userInfo);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存用户信息表(新增/修改)", notes = "保存用户信息表(新增/修改)")
    public Result save(@RequestBody UserInfoEntity userInfo) {
        boolean result = iUserInfoService.saveOrUpdate(userInfo);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户信息表", notes = "删除用户信息表")
    public Result remove(@PathVariable String id) {
        boolean result =  iUserInfoService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
