package com.xyz.im.base.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * im 日志
 *
 * @author xyz
 * @date 2020/8/24
 */
public class ImLogUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger("ImLogger");

    public static void error(String format, Object... arguments) {
        LOGGER.error(format, arguments);
    }

    public static void warn(String format, Object... arguments) {
        LOGGER.warn(format, arguments);
    }

    public static void info(String format, Object... arguments) {
        LOGGER.info(format, arguments);
    }

}
