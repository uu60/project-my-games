package com.octopus.mygamesbackend.mapper;

import com.octopus.mygamesbackend.pojo.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    String getPasswordByUsername(@Param("username") String username);

    void addUser(User user);
}
