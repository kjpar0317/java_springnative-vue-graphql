package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
    private TokenUtils tokenUtils;
	
    @Bean
    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
   
    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    protected WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*");
            }
        };
    }
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
        	.and()
        	.cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers("/graphql").permitAll()
            .antMatchers("/graphiql", "/vendor/graphiql/*").permitAll()
            .antMatchers("/h2-console/*").permitAll()
            .anyRequest().denyAll()
            .and().apply(com.example.demo.security.MyCustomDsl.customDsl(tokenUtils))
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       
        return http.build();
    }
}