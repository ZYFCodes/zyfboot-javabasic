package org.zyf.javabasic.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @program: zyfboot-javabasic
 * @description: 创建配置类注入 JedisPool（连接池）
 * @author: zhangyanfeng
 * @create: 2025-04-18 12:15
 **/
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(10);
        config.setMinIdle(2);

        if (password == null || password.isEmpty()) {
            return new JedisPool(config, host, port);
        } else {
            return new JedisPool(config, host, port, 2000, password);
        }
    }
}
