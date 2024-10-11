package com.Movies.On_Screen.Archive.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import services.CustomUserDetailService;

public class SecurityConfig {
    @Autowired
    CustomUserDetailService userDetailService;

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
//        auth.setPasswordEncoder();
        auth.setUserDetailsService(userDetailService);
        return auth;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->{
                    auth.anyRequest().authenticated();
                }).csrf(AbstractHttpConfigurer:: disable )
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.defaultSuccessUrl("/swagger-ui.html");
                });
        return http.build();
    }
}
