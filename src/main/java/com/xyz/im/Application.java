package com.xyz.im;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动入口
 *
 * @author xyz
 */
@SpringBootApplication
@PropertySource("classpath:config.properties")
@ImportResource("classpath:config/${spring.profiles.active}/applicationContext-quartz.xml")
@EnableTransactionManagement
@ComponentScan({"com.xyz"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


}
