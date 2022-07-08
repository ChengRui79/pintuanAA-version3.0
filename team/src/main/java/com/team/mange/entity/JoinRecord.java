package com.team.mange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 参团记录
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:46
 */
@Data
@TableName("join_record")
public class JoinRecord extends BaseEntity implements Serializable {
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
	 * 团员ID
	 */
	@TableField("user_id")
	private Integer userId;


}
