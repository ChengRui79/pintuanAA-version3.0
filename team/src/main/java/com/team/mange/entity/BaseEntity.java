package com.team.mange.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * BaseEntity
 *
 * @author dell
 * @version V1.0
 * @date 2022年4月1日
 */
@Data
public class BaseEntity {
    @JSONField(serialize = false)
    @TableField(exist = false)
    private int page = 1;

    @JSONField(serialize = false)
    @TableField(exist = false)
    private int limit = 10;

    /**
     * 数据权限：用户id
     */
    @TableField(exist = false)
    private List<String> createIds;
}
