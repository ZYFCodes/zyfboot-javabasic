package org.zyf.javabasic.springextend.other;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 示例：初始化缓存数据
 * @author: zhangyanfeng
 * @create: 2025-03-10 08:44
 **/
@Service
public class CacheService {
    private Map<String, String> cache = new HashMap<>();

    // 使用 @PostConstruct 预加载缓存
    @PostConstruct
    public void preloadCache() {
        cache.put("user1", "John Doe");
        cache.put("user2", "Jane Smith");
        System.out.println("缓存数据已加载");
    }

    public String getFromCache(String key) {
        return cache.get(key);
    }
}
