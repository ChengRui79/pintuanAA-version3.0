package com.team.mange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 团队
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:46
 */
@Data
@TableName("team")
public class Team extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 团队ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 团队名称
	 */
	@TableField("team_name")
	private String teamName;

	/**
	 * 团队名称
	 */
	@TableField("team_leader")
	private Integer teamLeader;

	/**
	 * 开始时间
	 */
	@TableField("start_time")
	private String startTime;

	/**
	 * 结束时间
	 */
	@TableField("end_time")
	private String endTime;

	/**
	 * 团费总额
	 */
	@TableField("cost")
	private BigDecimal cost;

	/**
	 * 状态
	 */
	@TableField("state")
	private Integer state;

	@TableField(exist = false)
	private String stateStr;

}
