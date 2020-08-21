package com.xyz.im.base.config;

import com.xyz.im.web.interceptor.OriginCrossInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description TODO
 * @Author liutong
 * @Date 2020/8/21
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(originCrossInterceptor()).order(0);
    }

    @Bean
    public OriginCrossInterceptor originCrossInterceptor() {
        return new OriginCrossInterceptor();
    }
}
