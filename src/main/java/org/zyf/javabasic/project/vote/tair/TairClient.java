package org.zyf.javabasic.project.vote.tair;

import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 模拟tair服务
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:59
 **/
public interface TairClient {

    public final static int SECOND = 1; //1s

    public final static int MINUTE = 60 * SECOND; // 1m

    public final static int HOUR = 60 * MINUTE; // 1h

    public final static int DAY = 24 * HOUR; // 1d

    /**
     * 单一删除功能
     */
    public boolean delete(String key);

    /**
     * 单一数据设置功能
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public <T> boolean put(String key, T value, int expireTime);

    /**
     * 设置带modifytime的key
     *
     * @param key
     * @param value
     * @param modifyTime
     * @param expireTime
     * @param <T>
     * @return
     */
    public <T> boolean putObjectModifyDate(String key, T value, long modifyTime, int expireTime);

    /**
     * 获取由putObjectModifyDate设置的key
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getModifyObject(String key);

    /**
     * 批量数据获取功能，返回Map<String, T>查到就在map中，查不到则不放
     *
     * @param keys
     * @return
     */
    public <T> Map<String, T> mget(List<String> keys);

    /**
     * 单一数据获取功能
     *
     * @param key
     * @return
     */
    public <T> T get(String key);

    /**
     * 单一数据获取，并指定过期时间
     *
     * @param key
     * @param expireTime
     * @return
     */
    public <T> T get(String key, int expireTime);

    /**
     * 增加计数。当key不存在时，第一次返回defaultValue+ 1，以后每次增加1。注意：调用incr就不要对该key进行put操作了！！数据数值可用get类接口获取。
     *
     * @param key
     * @param defalutValue
     * @param expireTime
     * @return
     */
    public Integer incr(String key, int defalutValue, int expireTime);

    /**
     * 增加计数。当key不存在时，第一次返回defaultValue+ 1，以后每次增加1。注意：调用incr就不要对该key进行put操作了！！数据数值可用get类接口获取。
     *
     * @param key
     * @param value
     * @param defalutValue
     * @param expireTime
     * @return
     */
    public Integer incr(String key, int value, int defalutValue, int expireTime);

    /**
     * 使用乐观锁的version
     *
     * @param key
     * @param data
     * @param version
     * @param expire
     * @return
     */
    public <T> boolean putWithVersion(String key, T data, int version, int expire);

    /**
     * 计数服务
     *
     * @param key
     * @param value
     * @param defalutValue
     * @param expireTime
     * @return
     */
    public Integer desc(String key, int value, int defalutValue, int expireTime);

    /**
     * 以1的速度增加
     *
     * @param key
     * @param defalutValue
     * @param expireTime
     * @return
     */
    public Integer desc(String key, int defalutValue, int expireTime);

    /**
     * 获取计数值
     *
     * @param key
     * @return
     */
    public Integer getCounter(String key);

    /**
     * 设置计算值，因为简单的put值不能incr, decr
     *
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public boolean putCounter(String key, Integer value, int expireTime);

    /**
     * 判断key是否存在
     *
     * @param key
     * @return true:存在 false:不存在 null:其他情况
     */
    public Boolean isKeyExsit(String key);

}

