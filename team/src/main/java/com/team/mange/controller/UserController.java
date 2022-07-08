package com.team.mange.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.mange.entity.User;
import com.team.mange.service.UserService;
import com.team.mange.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author
 * @email
 * @date 2021-03-22 16:31:32
 */
@RestController
@RequestMapping("/user")
@Api(value="用户接口", tags={"用户接口"})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增")
    @PostMapping("user/add")

    @ResponseBody
    public DataResult add(@RequestBody User user){
        userService.save(user);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("user/delete")

    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        userService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("user/update")

    @ResponseBody
    public DataResult update(@RequestBody User user){
        userService.updateById(user);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("user/listByPage")

    @ResponseBody
    public DataResult findListByPage(@RequestBody User user){
        Page page = new Page(user.getPage(), user.getLimit());
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(User::getId, user.getId());
        IPage<User> iPage = userService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }


}
