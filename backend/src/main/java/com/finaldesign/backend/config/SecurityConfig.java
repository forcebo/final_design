package com.finaldesign.backend.config;

import com.finaldesign.backend.service.impl.StudentDetailsServiceImpl;
import com.finaldesign.backend.service.impl.TeacherDetailsServiceImpl;
import com.finaldesign.backend.utils.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    @Qualifier("studentDetailsServiceImpl")
    private UserDetailsService studentDetailsService;

    @Autowired
    @Qualifier("teacherDetailsServiceImpl")
    private UserDetailsService teacherDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 学生登录路径
                .antMatchers("/student/account/token/", "/student/account/register/", "/img/**").permitAll()
                // 教师登录路径
                .antMatchers("/teacher/account/token/", "/teacher/account/register/").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 将两个 AuthenticationProvider 添加到 AuthenticationManagerBuilder
        auth.authenticationProvider(studentAuthenticationProvider())
                .authenticationProvider(teacherAuthenticationProvider());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider studentAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(studentDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder()); // 设置密码编码器
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider teacherAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(teacherDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder()); // 设置密码编码器
        return authProvider;
    }
}


