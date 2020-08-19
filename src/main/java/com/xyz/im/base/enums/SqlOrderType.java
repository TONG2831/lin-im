package com.xyz.im.base.enums;

/**
 * SQl 排序规则
 *
 * @author xyz
 * @date 2020/7/6
 */
public enum SqlOrderType {

    /**
     * 升序
     */
    ASC,

    /**
     * 降序
     */
    DESC,
    ;

    public static SqlOrderType resolve(String orderType, SqlOrderType defaultType) {
        for (SqlOrderType type : SqlOrderType.values()) {
            if (type.name().equals(orderType)) {
                return type;
            }
        }
        return defaultType;
    }
}
