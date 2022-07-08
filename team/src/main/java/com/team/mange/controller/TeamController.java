package com.team.mange.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.mange.common.Constant;
import com.team.mange.entity.*;
import com.team.mange.service.*;
import com.team.mange.utils.BigDecimalUtil;
import com.team.mange.utils.DataResult;
import com.team.mange.utils.PageResult;
import com.team.mange.entity.vo.TeamVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 团队活动
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:46
 */
@Controller
public class TeamController extends BaseController{
    @Autowired
    private TeamService teamService;
    @Autowired
    private CostHisService costHisService;
    @Autowired
    private JoinRecordService joinRecordService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;
    /**
    *
    */
    @GetMapping("/team/index")
    public String team() {
        return "team/list";
    }

    @GetMapping("/team/manage")
    public String manage() {
        return "team/manage";
    }

    @GetMapping("/team/toAdd")
    public String toEdit(){
        return "team/teamAdd";
    }

    @GetMapping("/team/toAppendCost")
    public String toAppendCost(Integer teamId, Model model){
        model.addAttribute("teamId", teamId);
        return "team/appendCost";
    }

    @GetMapping("/team/toEdit")
    public String toEdit(Integer id, Model model) {
        Team team = teamService.getById(id);
        if(team!=null){
            model.addAttribute("data", team);
        }
        return "team/teamEdit";
    }

    @ApiOperation(value = "创建团队")
    @PostMapping("team/add")
    @ResponseBody
    public DataResult add(@RequestBody Team team){
        team.setTeamLeader(super.getUserId());
        team.setState(Constant.NEW);
        teamService.save(team);

        JoinRecord joinRecord = new JoinRecord();
        joinRecord.setTeamId(team.getId());
        joinRecord.setUserId(super.getUserId());
        joinRecordService.save(joinRecord);

        CostHis costHis = new CostHis();
        costHis.setCost(team.getCost());
        costHis.setTeamId(team.getId());
        costHis.setRemark("初始团费");
        costHisService.save(costHis);

        Message message = new Message();
        message.setContent("团队活动\""+team.getTeamName()+"\"创建成功");
        message.setSendTime(new Date());
        messageService.save(message);

        return DataResult.success();
    }

    @PostMapping("team/update")
    @ResponseBody
    public DataResult update(@RequestBody Team team){
        Team team1 = teamService.getById(team.getId());
        if(!team1.getTeamLeader().equals(super.getUserId())){
            return DataResult.fail("您无权操作");
        }
        teamService.updateById(team);
        return DataResult.success();
    }

    @PostMapping("team/updateState")
    @ResponseBody
    public DataResult updateState(@RequestBody TeamVO vo){
        Team team = teamService.getById(vo.getTeamId());
        if(!team.getTeamLeader().equals(super.getUserId())){
            return DataResult.fail("您无权操作");
        }
        team.setState(vo.getState());
        teamService.updateById(team);
        //团长结束活动，输出账单
        if(vo.getState()==Constant.FINISH){
            QueryWrapper<JoinRecord> wrapper = new QueryWrapper<>();
            wrapper.eq("team_id", vo.getTeamId());
            List<JoinRecord> list = joinRecordService.list(wrapper);
            for(JoinRecord jr : list) {
                Bill bill = new Bill();
                bill.setUserId(jr.getUserId());
                bill.setTeamId(vo.getTeamId());
                bill.setRemark("AA制团费");
                double divide = BigDecimalUtil.divide(team.getCost().doubleValue(), (double) list.size(), 2);
                bill.setCost(new BigDecimal(divide));
                billService.save(bill);
            }
        }
        return DataResult.success();
    }

    @ApiOperation(value = "追加团费")
    @PostMapping("team/appendCost")
    @ResponseBody
    public DataResult appendCost(@RequestBody CostHis costHis){
        Team team = teamService.getById(costHis.getTeamId());
        if(!team.getTeamLeader().equals(super.getUserId())){
            return DataResult.fail("您无权操作");
        }
        costHisService.save(costHis);

        double sum = BigDecimalUtil.add(team.getCost().doubleValue(), costHis.getCost().doubleValue());
        team.setCost(new BigDecimal(sum));
        teamService.updateById(team);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @GetMapping("team/listByPage")
    @ResponseBody
    public PageResult findListByPage(Team team){
        Page page = new Page(team.getPage(), team.getLimit());
        LambdaQueryWrapper<Team> queryWrapper = Wrappers.lambdaQuery();
        IPage<Team> iPage = teamService.page(page, queryWrapper);
        for(Team t : iPage.getRecords()){
            if(t.getState()==1) {
                t.setStateStr("新建");
            }
            if(t.getState()==2) {
                t.setStateStr("成立");
            }
            if(t.getState()==3) {
                t.setStateStr("结束");
            }
        }

        PageResult pageResult = new PageResult();
        pageResult.setRows(iPage.getRecords());
        pageResult.setTotal((int)iPage.getTotal());
        return pageResult;
    }

    //生成报告
    @GetMapping("/team/genReport")
    public String genReport(Integer id, Model model) {
        Team team = teamService.getById(id);
        if(team!=null){
            model.addAttribute("data", team);
        }
        QueryWrapper<JoinRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("team_id", id);
        List<JoinRecord> list = joinRecordService.list(wrapper);
        model.addAttribute("personNum", list.size());
        return "team/teamReport";
    }
}
