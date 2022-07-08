package com.team.mange.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.mange.entity.Message;
import com.team.mange.service.MessageService;
import com.team.mange.utils.DataResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * 消息表
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:45
 */
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;


    /**
    *
    */
    @GetMapping("/message/index")
    public String message() {
        return "message/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("message/add")

    @ResponseBody
    public DataResult add(@RequestBody Message message){
        messageService.save(message);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("message/delete")

    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        messageService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("message/update")

    @ResponseBody
    public DataResult update(@RequestBody Message message){
        messageService.updateById(message);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("message/listByPage")

    @ResponseBody
    public DataResult findListByPage(@RequestBody Message message){
        Page page = new Page(message.getPage(), message.getLimit());
        LambdaQueryWrapper<Message> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(Message::getId, message.getId());
        IPage<Message> iPage = messageService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
