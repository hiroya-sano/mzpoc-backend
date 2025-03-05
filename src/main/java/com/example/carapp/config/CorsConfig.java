package com.example.carapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*")  // 必要に応じて許可するオリジンを制限してください
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
