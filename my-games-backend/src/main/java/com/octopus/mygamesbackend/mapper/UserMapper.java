package com.octopus.mygamesbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.octopus.mygamesbackend.pojo.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Deprecated
//@Mapper
public interface UserMapper {
    String getPasswordByUsername(@Param("username") String username);

    void addUser(User user);
}
