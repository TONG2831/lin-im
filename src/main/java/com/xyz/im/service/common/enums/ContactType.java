package com.xyz.im.service.common.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 联系人类型
 *
 * @author xyz
 * @date 2020/8/21
 */
public enum ContactType {

    /**
     * 好友
     */
    FRIEND(0),

    /**
     * 群
     */
    GROUP(1),

    ;

    private int type;

    private static Map<Integer, ContactType> map = Arrays.stream(ContactType.values()).collect(Collectors.toMap(ContactType::getType, item -> item));

    ContactType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public static ContactType resolve(int type) {
        return map.get(type);
    }

}
