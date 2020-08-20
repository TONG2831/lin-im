package com.xyz.im.service.user.service;

import com.xyz.im.base.common.CommonCookieParams;
import com.xyz.im.base.common.Constant;
import com.xyz.im.base.exception.BusinessException;
import com.xyz.im.base.utils.EncryptUtils;
import com.xyz.im.dao.general.AccountMapper;
import com.xyz.im.domain.Account;
import com.xyz.im.service.auth.cache.AuthCache;
import com.xyz.im.service.auth.dto.AuthUser;
import com.xyz.im.service.user.dto.LoginResultDto;
import com.xyz.im.service.user.enums.LoginType;
import com.xyz.im.service.user.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.UUID;

/**
 * 登录Service
 *
 * @author xyz
 * @date 2020/5/26
 */
@Service
@Slf4j
public class LoginService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AuthCache authCache;

    /**
     * 注册
     */
    public void register() {

    }

    /**
     * 找回
     */
    public void getBack() {

    }

    /**
     * 根据账号登录
     * <p>
     * 注册时限制账号不得为纯数字、不得包含. 登录时即可用一个字段区分登录方式
     *
     * @param param
     * @param response
     */
    public LoginResultDto loginByAccount(LoginVo param, HttpServletResponse response) {
        String account = StringUtils.trim(param.getAccount());
        String password = StringUtils.trim(param.getPassword());

        // encrypt password
        password = EncryptUtils.md5Encode(password);

        // check login type and get
        LoginType loginType = LoginUtils.checkTypeByAccount(account);
        Account resultAccount = getAccountInfo(account, password, loginType);
        if (Objects.isNull(resultAccount)) {
            throw new BusinessException("账号或密码有误");
        }

        // gen token
        String token = UUID.randomUUID().toString();

        // token and account info into cache
        AuthUser authUser = LoginUtils.buildAuthUser(resultAccount);
        authCache.set(token, authUser);

        // token into cookie
        Cookie cookie = new Cookie(CommonCookieParams.LIN_ID, token);
        cookie.setMaxAge(Constant.ONE_DAY_SECONDS);
        cookie.setPath("/");
        response.addCookie(cookie);

        return LoginResultDto.builder()
                .uid(resultAccount.getId())
                .build();
    }

    /**
     * 根据登录方式查询账号信息
     *
     * @param account
     * @param password
     * @param loginType
     * @return perhaps null
     */
    private Account getAccountInfo(String account, String password, LoginType loginType) {
        Account resultAccount;
        switch (loginType) {
            case MAIL:
                resultAccount = accountMapper.selectByMailAndPassword(account, password);
                break;
            case MOBILE:
                resultAccount = accountMapper.selectByPhoneAndPassword(account, password);
                break;
            case ACCOUNT:
            default:
                resultAccount = accountMapper.selectByAccountAndPassword(account, password);
                break;
        }

        return resultAccount;
    }
}
