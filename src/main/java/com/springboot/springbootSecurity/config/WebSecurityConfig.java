package com.springboot.springbootSecurity.config;
import com.springboot.springbootSecurity.filters.JwtAuthFilter;
import com.springboot.springbootSecurity.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/posts", "/error","/public","/login","/logout","/auth/**","/home.html").permitAll()
                //        .requestMatchers("/posts/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .csrf(csrf->csrf.disable())
                .sessionManagement(session->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2Config -> oauth2Config
                        .failureUrl("/login?error=true")
                        .successHandler(oAuth2SuccessHandler)
                );
               // .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

 //    @Bean
//    UserDetailsService myInMemoryUserDetailsService() {
//        UserDetails normalUser = User.withUsername("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//        UserDetails adminUser = User.withUsername("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }


}
