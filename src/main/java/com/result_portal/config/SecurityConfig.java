package com.result_portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf-> csrf.disable());

        http.authorizeHttpRequests(httpRequest->
                httpRequest.requestMatchers("/admin/**").authenticated()
                        .anyRequest().permitAll()
                ).formLogin(login->
                login.loginPage("/admin-login")
                        .loginProcessingUrl("/do-login")
                        .defaultSuccessUrl("/admin/dashboard", true)
                        .permitAll()
                ).logout(logout->
                logout.logoutUrl("/admin-logout")
                        .logoutSuccessUrl("/admin-login?logout")
                        .permitAll()
                );
        DefaultSecurityFilterChain build = http.build();
        return build;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.builder()
                .username("admin")
                .password("{noop}admin@10")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
