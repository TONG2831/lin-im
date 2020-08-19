package com.xyz.im;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;

/**
 * 启动时的一些配置
 *
 * @author xyz
 * @date 2020-03-25
 */
@Slf4j
@Configuration
public class BizApplicationConfig implements ApplicationRunner {

    @Resource
    private ApplicationContext applicationContext;

    @Value("${spring.profiles.active}")
    private String profiles;

    @Override
    public void run(ApplicationArguments args) {
        // 启动时需要干啥
    }

    /**
     * 添加404和500的定向请求
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
//            ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
            factory.addErrorPages(errorPage404);
//            factory.addErrorPages(errorPage500);
        });
    }


}
