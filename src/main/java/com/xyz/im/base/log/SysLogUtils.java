package com.xyz.im.base.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统级日志
 *
 * @author xyz
 * @date 2020/7/9
 */
public class SysLogUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger("SystemLogger");

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
