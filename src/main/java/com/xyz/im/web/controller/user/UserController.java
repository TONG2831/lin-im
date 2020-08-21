package com.xyz.im.web.controller.user;

import com.xyz.im.base.common.CommonParams;
import com.xyz.im.base.handler.param.RequestAttributeParam;
import com.xyz.im.base.utils.BizAssert;
import com.xyz.im.service.common.dto.ContactDto;
import com.xyz.im.service.common.enums.ContactType;
import com.xyz.im.service.group.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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

    @Resource
    private GroupService groupService;

    @ApiOperation(value = "取用户联系人列表 其中会有参数区分 好友和群")
    @GetMapping(value = "/queryContacts")
    public List<ContactDto> queryContacts(@ApiIgnore @RequestAttributeParam(name = CommonParams.UID, required = false, defaultValue = "0") long uid,
                                          @ApiParam("联系人类型 0:好友列表 1:群列表") @RequestParam(name = "type", required = false, defaultValue = "0") int type) {
        ContactType contactType = ContactType.resolve(type);
        BizAssert.notNull(contactType, "联系人类型有误");

        switch (contactType) {
            case GROUP:
                return groupService.queryUserJoinedGroups(uid);
            case FRIEND:
            default:
                return Collections.emptyList();
        }
    }

    /**
     * 加群
     */
    public void joinGroup() {

    }

}
