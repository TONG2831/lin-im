package com.xyz.im.base.common;

/**
 * 基础
 *
 * @author xyz
 * @date 2020/7/8
 */
public class ActionStatus {

    private int value;
    private String reason;

    public static final ActionStatus OK = new ActionStatus(200, "OK");
    public static final ActionStatus FORBIDDEN = new ActionStatus(403, "Forbidden");
    public static final ActionStatus NOT_LOGIN = new ActionStatus(4031, "NOT LOGIN");
    public static final ActionStatus SERVER_ERROR = new ActionStatus(1007, "server error");

    protected ActionStatus() {
    }

    protected ActionStatus(int value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public int getValue() {
        return value;
    }

    public String getReason() {
        return reason;
    }

}
