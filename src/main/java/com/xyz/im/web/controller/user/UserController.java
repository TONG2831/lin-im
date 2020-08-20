package com.xyz.im.web.controller.user;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * im-用户
 *
 * @author xyz
 * @date 2020/8/20
 */
@Api(value = "im-用户", description = "im-用户")
@RestController
@RequestMapping(value = "/user", headers = "Accept=application/json")
public class UserController {

    /**
     * 取用户联系人列表 其中会有参数区分 好友和群
     */
    public void queryContacts() {

    }

}
