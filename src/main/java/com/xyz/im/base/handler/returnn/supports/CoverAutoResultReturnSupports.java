package com.xyz.im.base.handler.returnn.supports;

import com.xyz.im.base.handler.returnn.NotAutoResult;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * @author xyz
 * @date 2020/7/17
 */
public class CoverAutoResultReturnSupports implements AutoResultReturnSupports {

    @Override
    public boolean supportsReturnType(@NonNull MethodParameter methodParameter, @Nullable String param) {
        if (StringUtils.isEmpty(param)) {
            return false;
        }
        if (methodParameter.getMethodAnnotation(NotAutoResult.class) != null
                || methodParameter.getDeclaringClass().getAnnotation(NotAutoResult.class) != null) {
            return false;
        }
        String clazzName = methodParameter.getDeclaringClass().getName();
        return clazzName.matches(param);
    }
}
