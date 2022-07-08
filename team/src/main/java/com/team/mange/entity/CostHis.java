package com.team.mange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 团费历史表
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:46
 */
@Data
@TableName("cost_his")
public class CostHis extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 团队ID
	 */
	@TableField("team_id")
	private Integer teamId;

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
