package com.team.mange.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.mange.entity.dto.JoinRecordDTO;
import com.team.mange.entity.JoinRecord;
import com.team.mange.service.JoinRecordService;
import com.team.mange.service.TeamService;
import com.team.mange.service.UserService;
import com.team.mange.utils.DataResult;
import com.team.mange.utils.PageResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



/**
 * 参团记录
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:46
 */
@Controller
public class JoinRecordController extends BaseController{
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private JoinRecordService joinRecordService;


    /**
    *
    */
    @GetMapping("/joinRecord/index")
    public String joinRecord() {
        return "joinRecord/list";
    }

    @ApiOperation(value = "团员申请加入团队")
    @PostMapping("joinRecord/add")
    @ResponseBody
    public DataResult add(@RequestBody JoinRecord joinRecord){
        QueryWrapper<JoinRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("team_id", joinRecord.getTeamId());
        wrapper.eq("user_id", super.getUserId());
        JoinRecord one = joinRecordService.getOne(wrapper);
        if (one != null) {
            return DataResult.fail("您已经参加过了");
        }
        joinRecord.setUserId(super.getUserId());
        joinRecordService.save(joinRecord);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("joinRecord/delete")

    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        joinRecordService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("joinRecord/update")

    @ResponseBody
    public DataResult update(@RequestBody JoinRecord joinRecord){
        joinRecordService.updateById(joinRecord);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @GetMapping("joinRecord/listByPage")
    @ResponseBody
    public PageResult findListByPage(JoinRecord joinRecord){
        Page page = new Page(joinRecord.getPage(), joinRecord.getLimit());
        LambdaQueryWrapper<JoinRecord> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(JoinRecord::getId, joinRecord.getId());
        IPage<JoinRecord> iPage = joinRecordService.page(page, queryWrapper);
        ArrayList<JoinRecordDTO> dtos = new ArrayList<>();
        for(JoinRecord jr : iPage.getRecords()){
            JoinRecordDTO dto = new JoinRecordDTO();
            dto.setId(jr.getId());
            dto.setTeamName(teamService.getById(jr.getTeamId()).getTeamName());
            dto.setRealName(userService.getById(jr.getUserId()).getRealName());
            dtos.add(dto);
        }

        PageResult pageResult = new PageResult();
        pageResult.setRows(dtos);
        pageResult.setTotal((int)iPage.getTotal());
        return pageResult;
    }

}
