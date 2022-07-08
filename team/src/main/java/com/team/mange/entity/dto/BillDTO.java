package com.team.mange.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BillDTO {

    private Integer id;
    private String teamName;
    private String realName;

    /**
     * 费用
     */
    @TableField("cost")
    private BigDecimal cost;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
