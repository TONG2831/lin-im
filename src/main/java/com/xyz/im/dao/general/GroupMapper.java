package com.xyz.im.dao.general;

import com.xyz.im.domain.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xyz
 * @date 2020/8/21
 */
public interface GroupMapper {
    int insert(Group record);

    int insertOrUpdate(Group record);

    int insertOrUpdateSelective(Group record);

    int insertSelective(Group record);

    Group selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Group record);

    int updateByPrimaryKey(Group record);

    int updateBatch(List<Group> list);

    int updateBatchSelective(List<Group> list);

    int batchInsert(@Param("list") List<Group> list);

    List<Group> selectByIds(@Param("ids") List<Long> ids);

}