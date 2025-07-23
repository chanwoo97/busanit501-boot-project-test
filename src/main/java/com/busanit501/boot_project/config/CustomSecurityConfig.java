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

        // 내부에서 순서1
        // 폼방식 : 로그인창으로, 유저, 패스워드 인증하는 방식.
        // 시큐리티에서 제공해주는 기본 인증 폼을 사용안하고,
        // 우리가 만든 로그인창을 이용함.
        http.formLogin(form -> form.loginPage("/member/login"));

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
