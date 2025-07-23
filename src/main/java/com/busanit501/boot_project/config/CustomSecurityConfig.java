package com.busanit501.boot_project.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity()
public class CustomSecurityConfig {

    // 순서1,
    // 실제 접근 하는 ACL, 접근 제어 목록,
    // 서버에 접근하는 명단 작성.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Security Filter Chain--------------설정--------------");
        return  http.build();
    }

    // 순서2
    // 정적 자원 , 검사에서 제외.
    // resources -> static 하위 폴더들.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("======webSecurityCustomizer: 정적자원 검사 제외 ========================");
        return (web)
                -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
