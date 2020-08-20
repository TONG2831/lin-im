package com.xyz.im.dao.general;

import com.xyz.im.domain.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zhu WeiJie
 * @date 2020/8/20
 */
public interface AccountMapper {
    int insert(Account record);

    int insertOrUpdate(Account record);

    int insertOrUpdateSelective(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    int updateBatch(List<Account> list);

    int updateBatchSelective(List<Account> list);

    int batchInsert(@Param("list") List<Account> list);

    Account selectByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    Account selectByMailAndPassword(@Param("mail") String mail, @Param("password") String password);

    Account selectByPhoneAndPassword(@Param("phone") String phone, @Param("password") String password);

}