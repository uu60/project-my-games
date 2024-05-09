package com.octopus.mygamesbackend.handler.http.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index{
    @RequestMapping("/")
    public String getIndex(){
        return "index.html";    //首页全名称(.html不能省略)
    }
}
