package com.xyz.im.base.exception;

import com.xyz.im.base.common.ActionStatus;

/**
 * @author xyz
 * @date 2020/5/18
 */
public class BusinessException extends RuntimeException {

    private ActionStatus status = ActionStatus.SERVER_ERROR;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ActionStatus getStatus() {
        return status;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    public BusinessException(ActionStatus status) {
        super(status.getReason());
        this.status = status;
    }

    public BusinessException(ActionStatus status, String message) {
        super(message);
        this.status = status;
    }
}
