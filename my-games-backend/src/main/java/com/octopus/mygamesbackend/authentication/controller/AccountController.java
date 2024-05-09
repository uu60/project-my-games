package com.octopus.mygamesbackend.authentication.controller;

import com.octopus.mygamesbackend.authentication.entity.AccountEntity;
import com.octopus.mygamesbackend.authentication.service.AccountService;
import com.octopus.mygamesbackend.authentication.vo.UsernamePasswordVO;
import com.octopus.mygamesbackend.utils.http.Resp;
import com.octopus.mygamesbackend.utils.properties.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author dekopon
 * @since 2023/6/18 16:52
 */
@RestController
@RequestMapping("/api/v1")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public Resp register(@RequestBody UsernamePasswordVO vo, HttpSession session) {
        String code = (String) session.getAttribute("code");
        // 验证码不正确
        if (code == null || !code.equalsIgnoreCase(vo.getCode())) {
            return Resp.error(MyConstants.HTTP_WRONG_CODE);
        }
        session.removeAttribute("code");
        AccountEntity accountEntity = accountService.getAccountEntity(vo.getUsername());
        if (accountEntity != null) {
            return Resp.error(MyConstants.HTTP_REPEAT_USERNAME, "Username already exists.");
        }
        try {
            boolean res = accountService.addAccount(vo);
            return res ? Resp.ok() : Resp.error(MyConstants.HTTP_WRONG_PWD, "Invalid username or password.");
        } catch (Exception e) {
            e.printStackTrace();
            return Resp.error(MyConstants.HTTP_INTERNAL_SERVER_ERROR, "Server internal error.");
        }
    }
}
