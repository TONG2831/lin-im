package com.xyz.im.service.auth.cache;

import com.alibaba.fastjson.JSON;
import com.xyz.im.base.cache.MyRedisTemplate;
import com.xyz.im.base.cache.RedisKey;
import com.xyz.im.base.common.Constant;
import com.xyz.im.service.auth.dto.AuthUser;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AuthCache {

    @Resource
    private MyRedisTemplate myRedisTemplate;

    public void set(String token, AuthUser authUser) {
        String cacheKey = RedisKey.keyOfSession(token);
        myRedisTemplate.setex(cacheKey, Constant.ONE_DAY_SECONDS, JSON.toJSONString(authUser));
    }

    public AuthUser get(String token) {
        String cacheKey = RedisKey.keyOfSession(token);
        String result = myRedisTemplate.get(cacheKey);
        return JSON.parseObject(result, AuthUser.class);
    }

}
