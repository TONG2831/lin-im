package com.xyz.im.web.controller.login;

import com.xyz.im.service.user.dto.LoginResultDto;
import com.xyz.im.service.user.service.LoginService;
import com.xyz.im.service.user.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * im-登录
 *
 * @author xyz
 * @date 2020/8/20
 */
@Api(value = "im-登录", description = "im-登录")
@RestController
@RequestMapping(value = "/login", headers = "Accept=application/json")
public class LoginController {

    @Resource
    private LoginService loginService;

    @ApiOperation(value = "通过账号登陆(账号、邮箱、手机号)")
    @PostMapping(value = "/by_account")
    public LoginResultDto login(@ApiParam @RequestBody @Valid LoginVo paramVo,
                                HttpServletResponse response) {
        return loginService.loginByAccount(paramVo, response);
    }

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public Object register(@ApiParam @RequestBody @Valid Object paramVo,
                           HttpServletResponse response) {
        return null;
    }

    @ApiOperation(value = "校验邮箱验证码")
    @PostMapping(value = "/check/mail_code")
    public void checkMailCode(@ApiParam @RequestBody @Valid Object paramVo) {
    }

}