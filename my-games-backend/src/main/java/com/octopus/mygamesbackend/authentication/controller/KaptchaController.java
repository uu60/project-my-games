package com.octopus.mygamesbackend.authentication.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {
    @Autowired
    private Producer kaptcha;

    @GetMapping("/kaptcha")
    public void getKaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 生成验证码内容
        String text = kaptcha.createText();
        // 生成验证码图片
        BufferedImage image = kaptcha.createImage(text);
        // 将文字保存到session
        session.setAttribute("code", text);
        // 将图片写回流中，输出给浏览器
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
    }
}
