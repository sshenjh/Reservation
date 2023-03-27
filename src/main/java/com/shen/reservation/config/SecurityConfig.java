package com.shen.reservation.config;

import com.shen.reservation.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserServiceImpl userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf->csrf.ignoringRequestMatchers("/home").disable())
                        .authorizeHttpRequests(auth->auth
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/registration").permitAll()
                                .anyRequest().authenticated()
                        )
                .userDetailsService(userDetailsService)
                .headers(headers->headers.frameOptions().sameOrigin())
//                .formLogin(Customizer.withDefaults())
                .formLogin()
                .loginPage("/showLoginForm")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .loginProcessingUrl("/authenticateTheUser")
                .defaultSuccessUrl("/",true)
                .failureUrl("/?error=true")
                .and()
                .logout(Customizer.withDefaults())
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and().build();
    }

}
