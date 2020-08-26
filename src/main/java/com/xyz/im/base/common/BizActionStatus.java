package com.xyz.im.base.common;

import com.xyz.bu.common.ActionStatus;

/**
 * 业务用
 *
 * @author xyz
 * @date 2020/7/8
 */
public class BizActionStatus extends ActionStatus {

    private BizActionStatus() {
        super();
    }

    private BizActionStatus(int status, String reason) {
        super(status, reason);
    }

    public static final BizActionStatus DEMO = new BizActionStatus(10001, "demo");
    public static final BizActionStatus IM_EX = new BizActionStatus(1008, "im ex");

}
