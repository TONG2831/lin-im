package com.xyz.im.web.aspect;

import com.xyz.bu.common.BaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Controller方法的Aspect
 *
 * @author xyz
 * @date 2020.06.28
 */
@Aspect
@Component
@Slf4j
public class ControllerAspect {

    /**
     * 拦截所有Controller方法<p>
     * 在所有方法开始前设置开始时间戳，结果统一处理取出计算
     *
     * @param joinPoint
     */
    @Before("execution(* com.xyz.im.web.controller..*Controller.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        BaseConstant.START_TIME.set(System.currentTimeMillis());
    }

}
