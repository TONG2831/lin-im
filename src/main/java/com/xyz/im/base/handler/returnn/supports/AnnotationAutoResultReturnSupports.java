package com.xyz.im.base.handler.returnn.supports;

import com.xyz.im.base.handler.returnn.AutoResult;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author xyz
 * @date 2020/7/17
 */
public class AnnotationAutoResultReturnSupports implements AutoResultReturnSupports {

    @Override
    public boolean supportsReturnType(@NonNull MethodParameter methodParameter, @Nullable String param) {
        return methodParameter.getMethodAnnotation(AutoResult.class) != null
                || methodParameter.getDeclaringClass().getAnnotation(AutoResult.class) != null;
    }

}
