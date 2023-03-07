package com.joyintech.tams.systemtools.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis 工具类
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // set
    public boolean set(String key, Object value, Long expireSecond) {
        try {
            redisTemplate.opsForValue().set(key, value, expireSecond, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // get
    public Object get(String key) {
        return null == key ? null : redisTemplate.opsForValue().get(key);
    }

}
