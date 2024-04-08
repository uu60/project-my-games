package com.octopus.mygamesbackend.authentication.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author dekopon
 * @since 2023/6/18 01:57
 */
@Data
@TableName("t_account")
public class AccountEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @TableField("create_time")
    private Date createTime;
}
