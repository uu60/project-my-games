package com.octopus.mygamesbackend.controller;


import com.octopus.mygamesbackend.pojo.dao.User;
import com.octopus.mygamesbackend.pojo.vo.RegisterUser;
import com.octopus.mygamesbackend.service.RegisterService;
import com.octopus.mygamesbackend.utils.http.R;
import com.octopus.mygamesbackend.utils.properties.OProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Deprecated
//@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/do_register")
    public R doRegister(@RequestBody RegisterUser registerUser, HttpSession session) {
        String code = (String) session.getAttribute("code");
        if (code == null) {
            return R.error();
        }
        // 验证码不正确
        if (!code.equalsIgnoreCase(registerUser.getCode())) {
            return R.error(OProperties.HTTP_WRONG_CODE);
        }
        session.removeAttribute("code");
        int res = registerService.addUser(new User(null, registerUser.getUsername(),
                registerUser.getPassword(), new Date()));
        if (res != OProperties.HTTP_SUCCESS) {
            return R.error(res);
        }
        return R.ok();
    }
}
