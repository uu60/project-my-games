package com.octopus.mygamesbackend.pojo.vo;

import lombok.Data;

@Data
public class RegisterUser {
    private String username;
    private String password;
    private String code;
}
