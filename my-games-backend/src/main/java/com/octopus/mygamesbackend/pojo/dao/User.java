package com.octopus.mygamesbackend.pojo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 对应数据库的t_user
 */
@Deprecated
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Date createTime;

    public User(String password) {

    }
}
