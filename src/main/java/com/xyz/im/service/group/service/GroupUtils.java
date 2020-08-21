package com.xyz.im.service.group.service;

import com.xyz.im.domain.Group;
import com.xyz.im.service.common.dto.ContactDto;
import com.xyz.im.service.common.enums.ContactType;

/**
 * 群工具
 *
 * @author
 * @date 2020/8/21
 */
public class GroupUtils {

    /**
     * 群信息转换成通用联系人信息
     *
     * @param group 群
     * @return 通用联系人
     */
    public static ContactDto convert2Contact(Group group) {
        ContactDto contactDto = new ContactDto();
        contactDto.setType(ContactType.GROUP.getType());
        contactDto.setAvatar(group.getGroupAvatar());
        contactDto.setContactId(group.getId());
        contactDto.setName(group.getGroupName());
        return contactDto;
    }

}
