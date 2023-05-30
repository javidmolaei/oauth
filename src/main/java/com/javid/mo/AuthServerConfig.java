package com.javid.mo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
//@EnableAuthorizationServer یک انوتیشن در Spring Security است که برای فعال‌سازی و پیکربندی سرور احراز هویت و مجوزدهی OAuth2 استفاده می‌شود.
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    /**
     * در Spring Security، برخی از پیاده‌سازی‌های معروف PasswordEncoder عبارتند از:

    BCryptPasswordEncoder: بر اساس الگوریتم BCrypt از Blowfish.
    NoOpPasswordEncoder: یک پیاده‌سازی خالی که هیچگونه رمزنگاری را انجام نمی‌دهد و برای موارد آزمایشی یا توسعه مورد استفاده قرار می‌گیرد.
    StandardPasswordEncoder: بر اساس الگوریتم SHA-256.
    MessageDigestPasswordEncoder: بر اساس MessageDigest و الگوریتم های مختلفی مانند MD5 یا SHA-256.
    استفاده از PasswordEncoder به شما کمک می‌کند تا در برنامه خود امنیت رمزنگاری را بهبود دهید و رمز عبورها را به صورت امن ذخیره کنید.*/
    @Value("${user.oauth.clientId}")
    private String ClientID;
    @Value("${user.oauth.clientSecret}")
    private String ClientSecret;
    @Value("${user.oauth.redirectUris}")
    private String RedirectURLs;

    public AuthServerConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(
            AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
    //این متد برای تنظیمات امنیتی مربوط به سرور احراز هویت و مجوزدهی استفاده می‌شود. می‌توانید مواردی مانند تنظیمات دسترسی (access) به مسیرهای احراز هویت و مجوزدهی را تعیین کنید.

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(ClientID)
                .secret(passwordEncoder.encode(ClientSecret))
                .authorizedGrantTypes("authorization_code")
                .scopes("user_info")
                .autoApprove(true)
                .redirectUris(RedirectURLs);
    }
    // این متد برای پیکربندی اطلاعات مشتریان (Clients) استفاده می‌شود. شما می‌توانید اطلاعات مشتریان را تعریف کنید، مانند شناسه (client ID) و رمزعبور (client secret) و نوع مجوزهایی که مشتری می‌تواند درخواست کند (authorization grant types).

}
