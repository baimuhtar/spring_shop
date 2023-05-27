package baimuhtar.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeHttpRequests(authorizationConfigurer -> {
            authorizationConfigurer
                    .requestMatchers("/product/list")
                    .authenticated();
            authorizationConfigurer.anyRequest().permitAll();
        });
        // login
        http.formLogin().defaultSuccessUrl("/product/list");
        return http.build();
    }

}
