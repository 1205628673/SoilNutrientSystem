package com.jlau.algsystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlau.algsystem.entity.User;
import com.jlau.algsystem.service.UserService;
import com.jlau.algsystem.utils.CodeUtil;
import com.jlau.algsystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxr1205628673 on 2020/5/1.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/static/**")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/user/**")
                .hasAnyRole("USER","ADMIN")
                //.access("hasAnyRole('ADMIN','USER')")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json");
                        Map map = new HashMap();
                        map.put("name",authentication.getName());
                        map.put("auth",authentication.getAuthorities());
                        User user = (User) authentication.getPrincipal();
                        user.setPassword("");
                        map.put("user",user);
                        ObjectMapper objectMapper = new ObjectMapper();
                        Result result = new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),map);
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(objectMapper.writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        ObjectMapper objectMapper = new ObjectMapper();
                        Result result = new Result(CodeUtil.LOGIN_FAIL.getCode(), e.getMessage(),"login fail");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(objectMapper.writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Result result = new Result(CodeUtil.SUCESS.getCode(),CodeUtil.SUCESS.getMessage(),"ok");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(objectMapper.writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                /*
                .sessionManagement()
                .invalidSessionStrategy(new InvalidSessionStrategy() {

                    @Override
                    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
                        ObjectMapper objectMapper = new ObjectMapper();
                        Result result = new Result(CodeUtil.INVALID_SESSION.getCode(),CodeUtil.INVALID_SESSION.getMessage(),"invalid");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(objectMapper.writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                */
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        ObjectMapper objectMapper = new ObjectMapper();
                        Result result = new Result(CodeUtil.ACCESS_DENY.getCode(),CodeUtil.ACCESS_DENY.getMessage(),"fail");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(objectMapper.writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                })
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        ObjectMapper objectMapper = new ObjectMapper();
                        Result result = new Result(CodeUtil.NOT_LOGIN.getCode(),CodeUtil.NOT_LOGIN.getMessage(),"fail");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(objectMapper.writeValueAsString(result));
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .csrf()
                .disable()
                .cors();

    }
}
