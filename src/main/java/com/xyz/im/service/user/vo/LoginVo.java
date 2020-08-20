package com.xyz.im.service.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 账号登陆VO
 *
 * @author xyz
 * @date 2020/5/26
 */
@Data
@ApiModel
public class LoginVo {

    @ApiModelProperty("账号 账号/邮箱/手机号")
    @NotBlank(message = "账号不得为空")
    private String account;

    @ApiModelProperty("密码 ")
    @NotBlank(message = "密码不得为空")
    private String password;
}
