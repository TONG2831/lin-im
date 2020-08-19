package com.xyz.im.web.exception;

import com.xyz.im.base.common.ActionStatus;
import com.xyz.im.base.exception.BusinessException;
import com.xyz.im.base.handler.returnn.JsonResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author xyz
 * @date
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public JsonResult handle(Exception e) {
        // 处理业务异常
        if (e instanceof BusinessException) {
            return JsonResult.builder(((BusinessException) e).getStatus().getValue(), e.getMessage());
        }

        // 处理参数校验异常
        if (e instanceof MethodArgumentNotValidException) {
            return JsonResult.builder(ActionStatus.SERVER_ERROR.getValue(), ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
        }

        String desc = e.getMessage();
        if (desc == null || "".equals(desc)) {
            desc = ActionStatus.SERVER_ERROR.getReason();
        }
        return JsonResult.builder(ActionStatus.SERVER_ERROR.getValue(), desc);
    }
}
