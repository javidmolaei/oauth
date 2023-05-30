package com.javid.mo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
//Anotation @Order(1) در Spring Security برای تعیین ترتیب اعمال کانفیگوراسیون‌های مختلف استفاده می‌شود. هنگامی که در برنامه شما چندین کلاس WebSecurityConfigurerAdapter یا SecurityConfigurer وجود دارد، ترتیب اعمال آنها از اهمیت برخوردار است.
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${user.oauth.user.username}")
    private String username;
    @Value("${user.oauth.user.password}")
    private String password;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }
    //این متد برای پیکربندی قوانین امنیتی در سطح وب استفاده می‌شود. شما می‌توانید قوانین مربوط به دسترسی به مسیرها و منابع، محدودیت‌های دسترسی و تنظیمات امنیتی دیگر را تعیین کنید.

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(username)
                .password(passwordEncoder().encode(password))
                .roles("USER");
    }
    //این متد برای پیکربندی مدیر احراز هویت استفاده می‌شود. شما می‌توانید تنظیمات مربوط به احراز هویت کاربران را تعیین کنید، مانند سیستم مدیریت کاربران و روش‌های احراز هویت مورد استفاده.

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //configure(WebSecurity web): این متد برای پیکربندی امنیت بر روی منابع استاتیک (مانند CSS، JavaScript، تصاویر و ...) استفاده می‌شود. شما می‌توانید تنظیمات مربوط به مسیره
}
