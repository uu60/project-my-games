package com.octopus.mygamesbackend.authentication.vo;

import lombok.Data;

/**
 * @author dekopon
 * @since 2023/6/18 18:19
 */
@Data
public class UsernamePasswordVO {
    String username;
    String password;
    // 验证码
    String code;
}
