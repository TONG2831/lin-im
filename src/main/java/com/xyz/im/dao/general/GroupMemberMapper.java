package com.xyz.im.dao.general;

import com.xyz.im.domain.GroupMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xyz
 * @date 2020/8/21
 */
public interface GroupMemberMapper {
    int insert(GroupMember record);

    int insertOrUpdate(GroupMember record);

    int insertOrUpdateSelective(GroupMember record);

    int insertSelective(GroupMember record);

    GroupMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GroupMember record);

    int updateByPrimaryKey(GroupMember record);

    int updateBatch(List<GroupMember> list);

    int updateBatchSelective(List<GroupMember> list);

    int batchInsert(@Param("list") List<GroupMember> list);

    List<GroupMember> selectByUid(@Param("uid") long uid);

}