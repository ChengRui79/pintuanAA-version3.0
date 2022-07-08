package com.team.mange.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.mange.entity.CostHis;
import com.team.mange.service.CostHisService;
import com.team.mange.utils.DataResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * 团费历史表
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:46
 */
@Controller
public class CostHisController {
    @Autowired
    private CostHisService costHisService;


    /**
    *
    */
    @GetMapping("/costHis/index")
    public String costHis() {
        return "costhis/list";
        }

    @ApiOperation(value = "新增")
    @PostMapping("costHis/add")

    @ResponseBody
    public DataResult add(@RequestBody CostHis costHis){
        costHisService.save(costHis);
        return DataResult.success();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("costHis/delete")

    @ResponseBody
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids){
        costHisService.removeByIds(ids);
        return DataResult.success();
    }

    @ApiOperation(value = "更新")
    @PutMapping("costHis/update")

    @ResponseBody
    public DataResult update(@RequestBody CostHis costHis){
        costHisService.updateById(costHis);
        return DataResult.success();
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("costHis/listByPage")

    @ResponseBody
    public DataResult findListByPage(@RequestBody CostHis costHis){
        Page page = new Page(costHis.getPage(), costHis.getLimit());
        LambdaQueryWrapper<CostHis> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        //queryWrapper.eq(CostHis::getId, costHis.getId());
        IPage<CostHis> iPage = costHisService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

}
