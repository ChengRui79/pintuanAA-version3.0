package com.team.mange.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 消息表
 *
 * @author dell
 * @email *****@mail.com
 * @date 2022-07-04 23:35:45
 */
@Data
@TableName("message")
public class Message extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 内容
	 */
	@TableField("content")
	private String content;

	/**
	 * 发送时间
	 */
	@TableField("send_time")
	private Date sendTime;


}
