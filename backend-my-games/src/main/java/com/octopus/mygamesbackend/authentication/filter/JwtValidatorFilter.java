package com.octopus.mygamesbackend.authentication.filter;

import com.google.gson.Gson;
import com.octopus.mygamesbackend.utils.http.Resp;
import com.octopus.mygamesbackend.utils.properties.MyConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author dekopon
 * @since 2023/6/18 16:27
 */
@AllArgsConstructor
public class JwtValidatorFilter extends OncePerRequestFilter {

    JwtParser jwtParser;
    Gson gson;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.hasText(response.getHeader("Authorization"))) {
            return;
        }
        String header = request.getHeader("Authorization");
        header = StringUtils.hasText(header) ? header : request.getParameter("login-token");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Jws<Claims> claims = jwtParser.parseClaimsJws(token);
                String username = claims.getBody().getSubject();
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_TEST"), new SimpleGrantedAuthority("TEST"))));
                request.getSession().setAttribute(MyConstants.SESSION_USERNAME_KEY, username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException e) {
                String json = gson.toJson(Resp.error(MyConstants.HTTP_ILLEGAL_ACCESS, "Invalid token."));
                response.setContentType("application/json");
                response.getWriter().write(json);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}