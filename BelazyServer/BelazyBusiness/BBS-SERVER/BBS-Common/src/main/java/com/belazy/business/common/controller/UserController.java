package com.belazy.business.common.controller;

import com.belazy.business.common.entity.UserEntity;
import com.belazy.business.common.service.IUserService;
import com.belazy.business.api.dto.UserDTO;
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
 * 用户登录账号Controller
 *
 * @author tchupeng
 * @email 923574674@qq.com
 * @date 2021年06月12日 10:18:58
 */
@RestController
@Api(tags = "用户登录账号 控制类")
@RequestMapping("/common/user")
public class UserController extends BasicController {
    @Autowired
    private IUserService iUserService;

    @GetMapping("/page")
    @ApiOperation(value = "查询用户登录账号列表(分页)", notes = "查询用户登录账号列表(分页)")
    public Result<PageVO<UserEntity>>page(UserDTO dto){
        Page<UserEntity> page = new Page<> (dto.getOffset (),dto.getSize ());
        UserEntity entity = new UserEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<UserEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success (toPage (iUserService.page (page, query)));
    }
    @GetMapping("/list")
    @ApiOperation(value = "查询用户登录账号列表(不分页)", notes = "查询用户登录账号列表(不分页)")
    public Result<List<UserEntity>> list(UserDTO dto) {
        UserEntity entity = new UserEntity ();
        BeanUtil.copyProperties(dto,entity);
        QueryWrapper<UserEntity> query = orderBy (Wrappers.query (entity),dto);
        return Result.success(iUserService.list(query));
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "获取用户登录账号信息", notes = "获取用户登录账号信息")
    public Result<UserEntity> getInfo(@PathVariable("id") String id) {
        return Result.success(iUserService.getById(id));
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增用户登录账号", notes = "新增用户登录账号")
    public Result add(@RequestBody UserEntity user) {
        boolean result = iUserService.save(user);
		return result?Result.success() : Result.fail();
    } 
    @PutMapping("/edit")
    @ApiOperation(value = "修改用户登录账号", notes = "修改用户登录账号")
    public Result edit(@RequestBody UserEntity user) {
        boolean result = iUserService.updateById(user);
		return result?Result.success() : Result.fail();
    }   
    @PostMapping("/save")
    @ApiOperation(value = "保存用户登录账号(新增/修改)", notes = "保存用户登录账号(新增/修改)")
    public Result save(@RequestBody UserEntity user) {
        boolean result = iUserService.saveOrUpdate(user);
        return result?Result.success() : Result.fail();
    }  
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户登录账号", notes = "删除用户登录账号")
    public Result remove(@PathVariable String id) {
        boolean result =  iUserService.removeById(id);
		return result?Result.success() : Result.fail();
    }
}
