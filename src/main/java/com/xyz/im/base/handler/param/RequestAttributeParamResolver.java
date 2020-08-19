package com.xyz.im.base.handler.param;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xyz
 * @date 2020/7/2
 */
@Configuration
public class RequestAttributeParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestAttributeParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Annotation[] paramAnnotations = methodParameter.getParameterAnnotations();
        Class paramType = methodParameter.getParameterType();
        for (Annotation annotation : paramAnnotations) {
            if (annotation instanceof RequestAttributeParam) {
                RequestAttributeParam attributeParam = (RequestAttributeParam) annotation;
                HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
                Object result = request.getAttribute(attributeParam.name());
                if (result != null) {
                    return result;
                } else {
                    String defaultValue = attributeParam.defaultValue();
                    String type = paramType.getName();
                    result = convert2Default(type, defaultValue);
                }
                return result;
            }
        }

        return new Object();
    }

    private static Object convert2Default(String type, String defaultValue) {
        ClassTypes classTypes = ClassTypes.resolve(type);
        if (classTypes == null) {
            return null;
        }

        Object result;
        switch (classTypes) {
            case INT:
            case INTEGER:
                result = NumberUtils.toInt(defaultValue);
                break;
            case LONG:
            case LONG_PAC:
                result = NumberUtils.toLong(defaultValue);
                break;
            case FLOAT:
            case FLOAT_PAC:
                result = NumberUtils.toFloat(defaultValue);
                break;
            case SHORT:
            case SHORT_PAC:
                result = NumberUtils.toShort(defaultValue);
                break;
            case DOUBLE:
            case DOUBLE_PAC:
                result = NumberUtils.toDouble(defaultValue);
                break;
            default:
                result = null;
        }

        return result;
    }

    private enum ClassTypes {

        /**
         * 数据类型
         */
        INT("int"),
        INTEGER("Integer"),
        LONG("long"),
        LONG_PAC("Long"),
        DOUBLE("double"),
        DOUBLE_PAC("Double"),
        FLOAT("float"),
        FLOAT_PAC("Float"),
        SHORT("short"),
        SHORT_PAC("Short"),

        ;

        private String type;

        ClassTypes(String type) {
            this.type = type;
        }

        private static final Map<String, ClassTypes> DATA_MAP = Arrays.stream(ClassTypes.values()).collect(Collectors.toMap(ClassTypes::getType, item -> item));

        public String getType() {
            return type;
        }

        public static ClassTypes resolve(String type) {
            return DATA_MAP.get(type);
        }
    }

}
