package com.xyz.im.service.user.service;


import com.xyz.im.base.utils.RegexUtils;
import com.xyz.im.domain.Account;
import com.xyz.im.service.auth.dto.AuthUser;
import com.xyz.im.service.user.enums.LoginType;

/**
 * 登录Utils
 *
 * @author Zhu WeiJie
 * @date 2020/7/9
 */
class LoginUtils {

    private LoginUtils() {
    }

    static AuthUser buildAuthUser(Account account) {
        AuthUser authUser = new AuthUser();
        authUser.setUid(account.getId());
        authUser.setAccount(account.getAccount());
        authUser.setEnPassword(account.getPassword());
        authUser.setMail(account.getMail());
        authUser.setMobile(account.getPhone());
        return authUser;
    }

    /**
     * 校验登录类型
     *
     * @param account
     * @return
     */
    static LoginType checkTypeByAccount(String account) {
        if (RegexUtils.checkMail(account)) {
            return LoginType.MAIL;
        } else if (RegexUtils.checkMobile(account)) {
            return LoginType.MOBILE;
        }
        return LoginType.ACCOUNT;
    }
}
