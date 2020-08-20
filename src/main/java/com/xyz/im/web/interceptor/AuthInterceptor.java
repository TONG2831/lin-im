package com.xyz.im.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.xyz.im.base.common.ActionStatus;
import com.xyz.im.base.common.CommonCookieParams;
import com.xyz.im.base.common.CommonParams;
import com.xyz.im.base.exception.BusinessException;
import com.xyz.im.base.handler.returnn.JsonResult;
import com.xyz.im.base.utils.IpUtils;
import com.xyz.im.service.auth.cache.AuthCache;
import com.xyz.im.service.auth.dto.AuthUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 权限认证
 *
 * @author xyz
 * @date
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private AuthCache authCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 取出cookie中的 token
        String token = StringUtils.EMPTY;
        Cookie[] cookies = request.getCookies();
        if (Objects.nonNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(CommonCookieParams.LIN_ID, cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

        // 取出缓存中的用户登录信息
        AuthUser authUser;
        if (StringUtils.isBlank(token) || Objects.isNull(authUser = authCache.get(token))) {
            // handle
            JsonResult jsonResult = new JsonResult();
            jsonResult.setStatus(ActionStatus.NOT_LOGIN.getValue());
            jsonResult.setDesc("尚未登录");

            try {
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(jsonResult));
            } catch (IOException e) {
                throw new BusinessException("服务器异常，请稍后再试");
            }
            return false;
        }

        // 设置公共参数
        request.setAttribute(CommonParams.UID, authUser.getUid());
        request.setAttribute(CommonParams.IP, IpUtils.getRemoteHost(request));
        return true;
    }

}
