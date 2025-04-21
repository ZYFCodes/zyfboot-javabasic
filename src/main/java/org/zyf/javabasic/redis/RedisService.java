package org.zyf.javabasic.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: zyfboot-javabasic
 * @description: 封装一个 RedisService 工具类
 * @author: zhangyanfeng
 * @create: 2025-04-18 12:18
 **/

@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    // 设置 key-value
    public void set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }

    // 获取 key
    public String get(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }

    // 设置带过期时间（秒）
    public void setWithExpire(String key, String value, int seconds) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.setex(key, seconds, value);
        }
    }

    // 删除 key
    public void delete(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.del(key);
        }
    }

    // 判断 key 是否存在
    public boolean exists(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.exists(key);
        }
    }
}
