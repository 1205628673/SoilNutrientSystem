package com.jlau.algsystem.config;

import com.jlau.algsystem.fliter.EncodeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by cxr1205628673 on 2020/5/3.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport{

    @Bean
    public EncodeInterceptor getEncodeInterceptor(){
        return new EncodeInterceptor();
    }
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(getEncodeInterceptor()).addPathPatterns("/**");
    }

}
