package com.octopus.mygamesbackend.authentication.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.octopus.mygamesbackend.authentication.utils.Constants;
import com.octopus.mygamesbackend.authentication.vo.UsernamePasswordVO;
import com.octopus.mygamesbackend.utils.http.Resp;
import com.octopus.mygamesbackend.utils.properties.MyConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author dekopon
 * @since 2023/6/18 21:00
 */
public class JwtGeneratorAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    Gson gson;

    public JwtGeneratorAuthenticationFilter(AuthenticationManager authenticationManager, Gson gson) {
        super.setAuthenticationManager(authenticationManager);
        this.gson = gson;
        setFilterProcessesUrl("/api/v1/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UsernamePasswordVO usernamePasswordVO = objectMapper.readValue(request.getInputStream(),
                    UsernamePasswordVO.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(usernamePasswordVO.getUsername(),
                    usernamePasswordVO.getPassword());
            return super.getAuthenticationManager().authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities",
                authResult.getAuthorities()).setIssuedAt(new Date()).setExpiration(Date.from(LocalDate.now().plusMonths(2).atStartOfDay(ZoneId.systemDefault()).toInstant())).signWith(SignatureAlgorithm.HS256, Constants.JWT_SECRET).compact();
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("Access-Control-Expose-Headers","Authorization");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.getOutputStream().write(gson.toJson(Resp.error(MyConstants.HTTP_WRONG_PWD, "Wrong " +
                "username or password.")).getBytes(StandardCharsets.UTF_8));
    }
}
