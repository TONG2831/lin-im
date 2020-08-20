package com.xyz.im.dao.general;

import com.xyz.im.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xyz
 * @date 2020/8/20
 */
public interface UserMapper {
    int insert(User record);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateBatch(List<User> list);

    int updateBatchSelective(List<User> list);

    int batchInsert(@Param("list") List<User> list);

    User selectByUid(@Param("uid") long uid);
}