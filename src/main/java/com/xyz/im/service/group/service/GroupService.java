package com.xyz.im.service.group.service;

import com.xyz.im.dao.general.GroupMapper;
import com.xyz.im.dao.general.GroupMemberMapper;
import com.xyz.im.domain.Group;
import com.xyz.im.domain.GroupMember;
import com.xyz.im.service.common.dto.ContactDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 群
 *
 * @author xyz
 * @date 2020/8/21
 */
@Service
@Slf4j
public class GroupService {

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private GroupMemberMapper groupMemberMapper;

    /**
     * 查询用户加入的群
     *
     * @param uid 用户id
     * @return 用户群列表
     */
    public List<ContactDto> queryUserJoinedGroups(long uid) {

        List<GroupMember> groupMembers = groupMemberMapper.selectByUid(uid);
        if (CollectionUtils.isEmpty(groupMembers)) {
            return Collections.emptyList();
        }

        List<Long> groupIds = groupMembers.stream().map(GroupMember::getGroupId).collect(Collectors.toList());
        List<Group> groups = groupMapper.selectByIds(groupIds);

        return groups.stream().map(GroupUtils::convert2Contact).collect(Collectors.toList());
    }

}
