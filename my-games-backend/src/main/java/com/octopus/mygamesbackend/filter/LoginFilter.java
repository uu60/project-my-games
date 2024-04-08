package com.octopus.mygamesbackend.filter;

import com.google.gson.Gson;
import com.octopus.mygamesbackend.utils.properties.OProperties;
import com.octopus.mygamesbackend.service.LoginService;
import com.octopus.mygamesbackend.utils.http.HttpUtils;
import com.octopus.mygamesbackend.utils.http.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Deprecated
//@Component
@Order(1)
public class LoginFilter implements Filter {

    @Autowired
    private LoginService loginService;
    @Autowired
    private Gson gson;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 替换掉请求尾部的所有/
        boolean isAllowedPath = HttpUtils.isNoNeedLogin(request);
        if (isAllowedPath) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            String tokenHeader = request.getHeader("Login-Token");
            String token = tokenHeader != null ? tokenHeader : request.getParameter("login-token");
            if (token != null && loginService.isLogin(token)) {
                if (session.getAttribute(OProperties.SESSION_USERNAME_KEY) == null) {
                    String username = loginService.getToken(token).getUsername();
                    session.setAttribute(OProperties.SESSION_USERNAME_KEY, username);
                }
                filterChain.doFilter(request, response);
            } else {
                ServletOutputStream outputStream = response.getOutputStream();
                byte[] bytes = gson.toJson(R.error(OProperties.HTTP_LOGIN_EXPIRED, "")).getBytes();
                outputStream.write(bytes);
            }
        }
    }
}
