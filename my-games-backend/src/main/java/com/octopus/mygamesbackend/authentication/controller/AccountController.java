package com.octopus.mygamesbackend.authentication.controller;

import com.octopus.mygamesbackend.authentication.entity.AccountEntity;
import com.octopus.mygamesbackend.authentication.service.AccountService;
import com.octopus.mygamesbackend.authentication.vo.UsernamePasswordVO;
import com.octopus.mygamesbackend.utils.http.R;
import com.octopus.mygamesbackend.utils.properties.OProperties;
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
    public R register(@RequestBody UsernamePasswordVO vo, HttpSession session) {
        String code = (String) session.getAttribute("code");
        // 验证码不正确
        if (code == null || !code.equalsIgnoreCase(vo.getCode())) {
            return R.error(OProperties.HTTP_WRONG_CODE);
        }
        session.removeAttribute("code");
        AccountEntity accountEntity = accountService.getAccountEntity(vo.getUsername());
        if (accountEntity != null) {
            return R.error(OProperties.HTTP_REPEAT_USERNAME, "Username already exists.");
        }
        try {
            boolean res = accountService.addAccount(vo);
            return res ? R.ok() : R.error(OProperties.HTTP_WRONG_PWD, "Invalid username or password.");
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(OProperties.HTTP_INTERNAL_SERVER_ERROR, "Server internal error.");
        }
    }
}
