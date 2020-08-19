package com.xyz.im.base.handler.returnn;

import com.xyz.im.base.handler.returnn.supports.AnnotationAutoResultReturnSupports;
import com.xyz.im.base.handler.returnn.supports.AutoResultReturnSupports;
import com.xyz.im.base.handler.returnn.supports.CoverAutoResultReturnSupports;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class AutoResultReturnHandler implements HandlerMethodReturnValueHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoResultReturnHandler.class);

    private static final String PROP_FILE_NAME = "lin-config.properties";
    private static final String COVER_REGEX_KEY = "auto.result.cover.regex";
    private static final String TYPE_KEY = "auto.result.type";

    private String param;
    private AutoResultReturnSupports supports;

    public AutoResultReturnHandler() {
        initProps();
    }

    /**
     * init handler's setting, if prop file not exists or read prop file error,
     * use default setting {@link HandlerType#ANNOTATION}
     */
    private void initProps() {
        Properties props = new Properties();
        try (
                InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_FILE_NAME)
        ) {
            if (is != null) {
                props.load(is);
            }
        } catch (Exception e) {
            LOGGER.error("#AutoResultReturnHandler load config error", e);
        }

        String tmpType = props.getProperty(TYPE_KEY);
        HandlerType type = HandlerType.resolve(tmpType, HandlerType.ANNOTATION);

        switch (type) {
            case ANNOTATION:
                supports = new AnnotationAutoResultReturnSupports();
                break;
            case COVER:
                param = props.getProperty(COVER_REGEX_KEY);
                supports = new CoverAutoResultReturnSupports();
                break;
            default:
        }
    }

    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        // 只针对接口返回文本格式数据进行封装
        if (methodParameter.getMethodAnnotation(ResponseBody.class) == null
                && methodParameter.getDeclaringClass().getAnnotation(RestController.class) == null) {
            return false;
        }

        return supports.supportsReturnType(methodParameter, param);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) {
        supports.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
    }

    private enum HandlerType {
        /**
         * 注解方式
         */
        ANNOTATION("annotation"),
        /**
         * 覆盖包方式
         */
        COVER("cover"),
        ;

        private String type;

        HandlerType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        private static final Map<String, HandlerType> DATA_MAP = Arrays.stream(HandlerType.values()).collect(Collectors.toMap(HandlerType::getType, item -> item));

        public static HandlerType resolve(String type, HandlerType defaultType) {
            if (DATA_MAP.containsKey(type)) {
                return DATA_MAP.get(type);
            }
            return defaultType;
        }
    }
}
