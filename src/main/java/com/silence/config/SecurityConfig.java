package com.silence.config;

import com.silence.util.CommunityConstant;
import com.silence.util.CommunityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements CommunityConstant {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/user/setting",
                        "/user/upload",
                        "/discuss/add",
                        "/comment/add/**",
                        "/letter/**",
                        "/notice/**",
                        "/like",
                        "/follow",
                        "/unfollow"
                )
                .hasAnyAuthority(
                        AUTHORITY_USER,
                        AUTHORITY_ADMIN,
                        AUTHORITY_MODERATOR
                )
                .anyRequest().permitAll()
                .and().csrf().disable();

        http.exceptionHandling()
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                    String xRequestedWith = httpServletRequest.getHeader("x-requested-with");
                    if ("XMLHttpRequest".equals(xRequestedWith)) {
                        httpServletResponse.setContentType("application/plain;charset=utf-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(CommunityUtil.getJSONString(403, "请先登录！"));
                    } else {
                        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
                    }
                })
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                    String xRequestedWith = httpServletRequest.getHeader("x-requested-with");
                    if ("XMLHttpRequest".equals(xRequestedWith)) {
                        httpServletResponse.setContentType("application/plain;charset=utf-8");
                        PrintWriter writer = httpServletResponse.getWriter();
                        writer.write(CommunityUtil.getJSONString(403, "您没有权限访问此功能！"));
                    } else {
                        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/denied");
                    }
                });
        http.logout().logoutUrl("/securitylogout");
    }
}
