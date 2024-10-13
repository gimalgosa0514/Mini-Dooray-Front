package com.nhnacademy.mini_dooray.gateway.config;

import com.nhnacademy.mini_dooray.gateway.server.account.filter.UserAuthenticationFilter;
import com.nhnacademy.mini_dooray.gateway.server.account.handler.CustomLoginSuccessHandler;
import com.nhnacademy.mini_dooray.gateway.server.account.handler.CustomLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

    private final RedisTemplate<String, Object> redisTemplate;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests.anyRequest().permitAll()
                )

                .formLogin(formLogin ->
                        formLogin.loginPage("/login")
                                .usernameParameter("id")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/project/")
                                .failureUrl("/login?error")
                                .successHandler(customLoginSuccessHandler())
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(customLogoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .deleteCookies("SESSION")
                        .permitAll()
                )

                .csrf(csrf -> csrf.disable()
                );

        http.addFilterBefore(userAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler(){
        return new CustomLogoutSuccessHandler(redisTemplate);
    }
    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler(){
        return new CustomLoginSuccessHandler(redisTemplate);
    }
    @Bean
    public UserAuthenticationFilter userAuthenticationFilter() {
        return new UserAuthenticationFilter(redisTemplate);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
