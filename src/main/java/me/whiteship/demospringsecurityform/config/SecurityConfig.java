package me.whiteship.demospringsecurityform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Security Filter Chain
     *
     * @param http
     *              HttpSecurity
     * @return Security Filter Chain
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /**
         * 접근 권한 설정
         */
        http.authorizeHttpRequests(authorize  -> {
            authorize
                    // 인증 없이 접근 허용
                    .requestMatchers("/","/info", "/account/**").permitAll()
                    // ADMIN 권한이 있어야 허용
                    .requestMatchers("/admin").hasRole("ADMIN")
                    // '인증'만 되면 허용
                    .anyRequest().authenticated();
        });

        /**
         * 로그인
         */
        http.formLogin(Customizer.withDefaults());

        return http.build();
    }


}
