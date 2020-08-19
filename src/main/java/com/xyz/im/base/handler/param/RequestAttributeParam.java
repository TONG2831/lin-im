package com.xyz.im.base.handler.param;

import java.lang.annotation.*;

/**
 * @author xyz
 * @date 2020/7/2
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestAttributeParam {

    String name() default "";

    boolean required() default true;

    String defaultValue() default "";
}
