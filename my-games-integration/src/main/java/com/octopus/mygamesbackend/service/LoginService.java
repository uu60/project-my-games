package com.octopus.mygamesbackend.service;


import com.octopus.mygamesbackend.pojo.dao.LoginToken;

public interface LoginService {
    Boolean validatePassword(String username, String password);

    boolean isLogin(String tokenValue);

    LoginToken getToken(String tokenValue);

    void addLoginToken(LoginToken token);
}
