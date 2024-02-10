package com.octopus.mygamesbackend.service.impl;

import com.octopus.mygamesbackend.mapper.UserMapper;
import com.octopus.mygamesbackend.pojo.dao.User;
import com.octopus.mygamesbackend.utils.properties.OProperties;
import com.octopus.mygamesbackend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        if (ObjectUtils.isEmpty(user.getUsername()) || ObjectUtils.isEmpty(user.getPassword())) {
            return OProperties.HTTP_ILLEGAL_PARAMS;
        }
        String pwdByUsername = userMapper.getPasswordByUsername(user.getUsername());
        if (pwdByUsername != null) {
            return OProperties.HTTP_REPEAT_USERNAME;
        }
        user.setCreateTime(new Date());
        userMapper.addUser(user);
        return OProperties.HTTP_SUCCESS;
    }
}
