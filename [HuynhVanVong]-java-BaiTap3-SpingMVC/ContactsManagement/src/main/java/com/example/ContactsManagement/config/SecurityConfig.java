package com.example.ContactsManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CSRF, CORS
        http.csrf().disable().cors().disable();

        // Phân quyền sử dụng
        http.authorizeRequests()
                .anyRequest().permitAll();

        // Điều khiển lỗi truy cập không đúng vai trò
        http.exceptionHandling()
                .accessDeniedPage("/auth/access/denied"); // [/error]

        // Giao diện đăng nhập
        http.formLogin()
                .loginPage("/users/login")
                        .usernameParameter("username")
                                .passwordParameter("password")
                .permitAll();

        // Đăng xuất
        http.logout()
                .logoutUrl("/auth/logoff") // [/logout]
                .logoutSuccessUrl("/auth/logoff/success");
    }

    /*--Cho phép request đến REST API từ browser--*/
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
