package com.team.mange.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.mange.entity.Bill;
import com.team.mange.entity.dto.BillDTO;
import com.team.mange.service.BillService;
import com.team.mange.service.TeamService;
import com.team.mange.service.UserService;
import com.team.mange.utils.ExcelUtil;
import com.team.mange.utils.PageResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 账单表
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:46
 */
@Controller
public class BillController extends BaseController {

    @Autowired
    private BillService billService;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;

    /**
     *
     */
    @GetMapping("/bill/index")
    public String bill(Integer teamId, Model model) {
        model.addAttribute("teamId", teamId);
        return "bill/list";
    }

    @GetMapping("/bill/toPay")
    public String toPay() {
        return "bill/pay";
    }


    @ApiOperation(value = "查询分页数据")
    @GetMapping("bill/listByPage")
    @ResponseBody
    public PageResult findListByPage(Bill bill) {
        Page page = new Page(bill.getPage(), bill.getLimit());
        LambdaQueryWrapper<Bill> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        queryWrapper.eq(Bill::getTeamId, bill.getTeamId());
        IPage<Bill> iPage = billService.page(page, queryWrapper);
        ArrayList<BillDTO> dtos = new ArrayList<>();
        for (Bill b : iPage.getRecords()) {
            BillDTO dto = new BillDTO();
            BeanUtils.copyProperties(b, dto);
            dto.setTeamName(teamService.getById(b.getTeamId()).getTeamName());
            dto.setRealName(userService.getById(b.getUserId()).getRealName());
            dtos.add(dto);
        }
        PageResult pageResult = new PageResult();
        pageResult.setRows(dtos);
        pageResult.setTotal((int) iPage.getTotal());
        return pageResult;
    }

    /**
     * 导出excel
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/bill/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, Integer teamId) {
        QueryWrapper<Bill> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("team_id", teamId);
        List<Bill> billList = billService.list(queryWrapper);
        String[] keys = {"teamName", "realName", "cost", "remark"};
        String[] columnNames = {"团队活动", "姓名", "费用", "备注"};
        String fileName = "账单";
        List<Map<String, Object>> list = new ArrayList<>();
        for (Bill bill : billList) {
            Map<String, Object> map = new HashMap<>(16);
            map.put("teamName", teamService.getById(bill.getTeamId()).getTeamName());
            map.put("realName", userService.getById(bill.getUserId()).getRealName());
            map.put("cost", bill.getCost());
            map.put("remark", bill.getRemark());
            list.add(map);
        }
        try {
            ExcelUtil.downloadExcel(response, fileName, list, keys, columnNames, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
