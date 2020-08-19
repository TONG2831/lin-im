package com.xyz.im.base.cache;

import com.xyz.im.base.common.Constant;

/**
 * @author xyz
 * @date 2019-07-04
 */
public interface RedisKey {

    static String format(Object... parts) {
        StringBuilder builder = new StringBuilder("im");
        for (int i = 0; i < parts.length; i++) {
            builder.append(Constant.DOT).append("%s");
        }
        return String.format(builder.toString(), parts);
    }

    static String keyOfSession(String token) {
        return format("session", token);
    }

}
