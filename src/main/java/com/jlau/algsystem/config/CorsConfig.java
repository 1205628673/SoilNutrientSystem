package com.jlau.algsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by cxr1205628673 on 2020/5/3.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowCredentials(true)
                .allowedOrigins("*")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
