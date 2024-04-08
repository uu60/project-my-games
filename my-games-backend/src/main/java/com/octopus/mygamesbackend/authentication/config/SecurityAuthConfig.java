package com.octopus.mygamesbackend.authentication.config;

import com.google.gson.Gson;
import com.octopus.mygamesbackend.authentication.filter.JwtValidatorFilter;
import com.octopus.mygamesbackend.authentication.entity.AccountEntity;
import com.octopus.mygamesbackend.authentication.filter.JwtGeneratorAuthenticationFilter;
import com.octopus.mygamesbackend.authentication.service.AccountService;
import com.octopus.mygamesbackend.authentication.utils.Constants;
import com.octopus.mygamesbackend.authentication.vo.AccountUserDetails;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author dekopon
 * @since 2023/6/17 19:02
 */
@Configuration
@EnableWebSecurity
public class SecurityAuthConfig {

    @Autowired
    AccountService accountService;
    @Autowired
    Gson gson;
    @Autowired
    AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public JwtParser jwtParser() {
        return Jwts.parser().setSigningKey(Constants.JWT_SECRET);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        String[] allowed = {"/", "/static/**", "/ws/**", "/api/v1/register", "/kaptcha", "/error/**"};
        AntPathRequestMatcher[] allowedMatchers = new AntPathRequestMatcher[allowed.length];
        for (int i = 0; i < allowed.length; i++) {
            allowedMatchers[i] = new AntPathRequestMatcher(allowed[i]);
        }
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(allowedMatchers).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtGeneratorAuthenticationFilter(authenticationManager(), gson))
                .addFilterBefore(new JwtValidatorFilter(jwtParser(), gson), UsernamePasswordAuthenticationFilter.class);;
        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            AccountEntity accountEntity = accountService.getAccountEntity(username);
            if (accountEntity == null) {
                throw new UsernameNotFoundException("User not found");
            }
            AccountUserDetails accountUserDetails = new AccountUserDetails();
            BeanUtils.copyProperties(accountEntity, accountUserDetails);
            return accountUserDetails;
        };
    }
}
