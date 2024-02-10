package com.octopus.mygamesbackend.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
    @Bean
    public Producer kaptchaProducer(){
        //用于设置验证码具体细节
        Properties properties = new Properties();
        //设置验证码图片宽度
        properties.setProperty("kaptcha.image.width", "100");
        //设置验证码图片高度
        properties.setProperty("kaptcha.image.height", "40");
        //设置验证码文字大小
        properties.setProperty("kaptcha.textproducer.font.size", "32");
        //设置验证码图片颜色
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,0");
        //设置验证码文字内容
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYAZ");
        //设置生成随机字符数量
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //图片干扰设置
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");

        //Kaptcha实现类
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        //将properties配置封装到Config对象中
        Config config = new Config(properties);
        //将config传入实现类
        kaptcha.setConfig(config);
        return kaptcha;
    }
}

