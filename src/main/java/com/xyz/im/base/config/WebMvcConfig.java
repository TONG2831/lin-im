package com.xyz.im.base.config;

import com.google.common.collect.Lists;
import com.xyz.im.base.handler.returnn.AutoResultReturnHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实现InitializingBean的bean在启动时都会被执行afterPropertiesSet
 * 源码 {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#invokeInitMethods}
 */
@Configuration
@Slf4j
public class WebMvcConfig implements InitializingBean {

    @Resource
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void afterPropertiesSet() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter
                .getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> list = Lists.newArrayList();
        // first add owner handler
        // 封装结果处理
        list.add(new AutoResultReturnHandler());
        if (returnValueHandlers != null) {
            list.addAll(returnValueHandlers);
        }
        requestMappingHandlerAdapter.setReturnValueHandlers(list);
        log.info("add owner return value handlers");
    }

}
