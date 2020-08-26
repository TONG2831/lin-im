package com.xyz.im.base.utils;

import com.xyz.bu.exception.BusinessException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author xyz
 * @date 2020/7/16
 */
public class BizAssert {

    /**
     * not null and continue
     *
     * @param object
     * @param message
     */
    public static void notNull(@Nullable Object object, @NonNull String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }

    /**
     * is null and continue
     *
     * @param object
     * @param message
     */
    public static void isNull(@Nullable Object object, @NonNull String message) {
        if (object != null) {
            throw new BusinessException(message);
        }
    }

    /**
     * not true and continue
     *
     * @param flag
     * @param message
     */
    public static void notTrue(boolean flag, @NonNull String message) {
        if (flag) {
            throw new BusinessException(message);
        }
    }

    /**
     * is true and continue
     *
     * @param flag
     * @param message
     */
    public static void isTrue(boolean flag, @NonNull String message) {
        if (!flag) {
            throw new BusinessException(message);
        }
    }
}
