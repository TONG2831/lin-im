package com.xyz.im;

import com.xyz.bu.handler.param.RequestAttributeParamResolver;
import com.xyz.im.base.log.SysLogUtils;
import com.xyz.im.web.interceptor.AuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ResourceUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;
import java.util.List;

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
public class Application extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * 这里配置静态资源文件的路径导包都是默认的直接导入就可以
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        SysLogUtils.info("config static resource");

        // http://{ip}:{port}/swagger-ui.html
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/");

        registry.addResourceHandler("/webjars/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/");
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }

    @Resource
    private AuthInterceptor authInterceptor;

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(
                        "/user/**");
    }

    /**
     * 注册自定义参数解析器
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        SysLogUtils.info("add owner argument resolvers");
        argumentResolvers.add(new RequestAttributeParamResolver());
    }

    /**
     * 注册自定义结果处理器
     * 该方式只能注册自定义处理器到处理器列表的尾部（也不准确，反正到不了头部，意思就是基本就派不上用处）
     * 通过最后获取处理器列表再设置即可 {@link WebMvcConfig#afterPropertiesSet()}
     * 源码注册处理器的代码 {@link RequestMappingHandlerAdapter#getDefaultReturnValueHandlers()}
     *
     * @param returnValueHandlers
     */
    @Override
    protected void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
//        SysLogUtils.info("add owner return value handlers");
//        // first add owner handler
//        returnValueHandlers.add(0, new AutoResultReturnHandler());
    }

}
