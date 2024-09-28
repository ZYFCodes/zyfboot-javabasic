package org.zyf.javabasic.project.vote.tair;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: SimpleTairClientImpl
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:01
 **/
@Service
@Slf4j
public class VoteTairClientImpl implements TairClient {

    private String appTairName;

    private boolean stopCache = false;

    public void init() {
    }

    private void digestLogger(String opt, long preTime, String keyOrKeySize, boolean hit) {
        log.info("{0},{1}ms,{2},{3},{4}", opt, (System.currentTimeMillis() - preTime), hit ? "T" : "F", appTairName, keyOrKeySize);
    }

    @Override
    public boolean delete(String key) {
        if (stopCache) {
            log.warn("当前关闭了对缓存系统的使用");
            return false;
        }
        if (StringUtils.isBlank(key)) {
            return false;
        }

        boolean res = false;
        long pre = System.currentTimeMillis();
        try {
            return true;
        } finally {
            digestLogger("Tair.delete", pre, key, res);

        }
    }

    @Override
    public <T> boolean put(String key, T value, int expireTime) {
        if (stopCache) {
            log.warn("当前关闭了对缓存系统的使用");
            return false;
        }

        if (StringUtils.isBlank(key)) {
            return false;
        }

        if (value == null) {
            return false;
        }

        boolean res = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.put");
//            res = gzoneTairCacheManager.putObjectWithExpire(key, value, expireTime);
            return res;
        } catch (Exception e) {
//            log.error(e, "TBCacheManager.putObjectWithExpire(Object, Object, int)"
//                            + " failed, with CATCHED message: {0}",
//                    e.getMessage());
            res = false;
            return res;
        } finally {
//            Profiler.release();
            digestLogger("Tair.put", pre, key, res);
        }
    }

    @Override
    public <T> Map<String, T> mget(List<String> keys) {
        if (stopCache) {
            log.warn("当前关闭了对缓存系统的使用");
            return new HashMap<String, T>();
        }

        if (keys == null || CollectionUtils.isEmpty(keys)) {
            return new HashMap<String, T>();
        }

        Map<String, T> returnMap = new HashMap<String, T>();
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.mget");
//            List<DataEntry> dataList = gzoneTairCacheManager.mgetObject(keys);
//            if (dataList == null) {
//                return new HashMap<String, T>();
//            }
//            for (DataEntry entry : dataList) {
//                String keyStr = (String) entry.getKey();
//                @SuppressWarnings("unchecked")
//                T tObj = (T) entry.getValue();
//                returnMap.put(keyStr, tObj);
//            }

            return returnMap;
        } catch (Exception e) {
//            log.error(e, "TBCacheManager.mgetObject(List<? extends Object>)"
//                            + " failed, with CATCHED message: {0}",
//                    e.getMessage());
            return new HashMap<String, T>();
        } finally {
//            Profiler.release();
            digestLogger("Tair.mget", pre, String.valueOf(keys.size()), returnMap.size() > 0);
        }
    }

    @Override
    public <T> boolean putObjectModifyDate(String key, T value, long modifyTime, int expireTime) {
        if (stopCache) {
            log.warn("当前关闭了对缓存系统的使用");
            return false;
        }

        if (StringUtils.isBlank(key)) {
            return false;
        }

        if (value == null) {
            return false;
        }

        boolean success = false;
//        ResultCode res;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.put");
//            res = gzoneTairCacheManager.putObjectModifyDate(key, value, modifyTime, expireTime);
//            if (res != null && res.isSuccess() && res.getCode() == ResultCode.SUCCESS.getCode()) {
//                success = true;
//            }
        } catch (Exception e) {
//            log.error(e, "TBCacheManager.putObjectWithExpire(Object, Object, int)"
//                            + " failed, with CATCHED message: {0}",
//                    e.getMessage());
            success = false;
        } finally {
//            Profiler.release();
            digestLogger("Tair.put", pre, key, success);
        }
        return success;
    }

    @Override
    public <T> T getModifyObject(String key) {
        if (stopCache) {
            log.warn("当前关闭了对缓存系统的使用");
            return null;
        }

        if (StringUtils.isBlank(key)) {
            return null;
        }

        boolean hit = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.get");
//            Object obj = gzoneTairCacheManager.getModifyObject(key);
//            @SuppressWarnings("unchecked")
//            T result = (obj == null) ? null : (T) obj;
//            hit = (result != null);
//            return result;
            return null;
        } catch (Exception e) {
//            log.error(e, "TBCacheManager.getModifyObject(Object)"
//                            + " failed, with CATCHED message: {0}",
//                    e.getMessage());
            hit = false;
            return null;
        } finally {
//            Profiler.release();
            digestLogger("Tair.get", pre, key, hit);
        }
    }

    @Override
    public <T> T get(String key) {
        if (stopCache) {
            log.warn("当前关闭了对缓存系统的使用");
            return null;
        }

        if (StringUtils.isBlank(key)) {
            return null;
        }

        boolean hit = false;
        long pre = System.currentTimeMillis();
        try {
            Object obj = new Object();
            T result = (obj == null) ? null : (T) obj;
            hit = (result != null);
            return result;
        } catch (Exception e) {
            log.error("TBCacheManager.getObject(Object) failed, with CATCHED message: {}", e.getMessage(), e);
            hit = false;
            return null;
        } finally {
            digestLogger("Tair.get", pre, key, hit);
        }
    }

    @Override
    public <T> T get(String key, int expireTime) {
        if (stopCache) {
            log.warn("当前关闭了对缓存系统的使用");
            return null;
        }

        if (StringUtils.isBlank(key)) {
            return null;
        }

        boolean hit = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.get_expire");
//            Object obj = gzoneTairCacheManager.getObject(key, expireTime);
//            @SuppressWarnings("unchecked")
//            T result = (obj == null) ? null : (T) obj;
//            hit = (result != null);
//            return result;
            return null;
        } catch (Exception e) {
//            log.error(e, "TBCacheManager.getObject(Object)"
//                            + " failed, with CATCHED message: {0}",
//                    e.getMessage());
//            hit = false;
            return null;
        } finally {
//            Profiler.release();
//            digestLogger("Tair.get_expire", pre, key, hit);
        }
    }


    @Override
    public <T> boolean putWithVersion(String key, T data, int version, int expire) {
        if (stopCache) {
            log.warn("当前关闭了对缓存系统的使用");
            return false;
        }

        if (StringUtils.isBlank(key)) {
            return false;
        }

        if (data == null) {
            return false;
        }

        boolean res = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.putWithVersion");
//            ResultCode resultCode = gzoneTairCacheManager.putObjectExpireResultCode(key, data, version,
//                    expire);
//            if (resultCode != null && resultCode.isSuccess()) {
//                /** put成功 */
//                res = true;
//                return res;
//            }
        } catch (Exception e) {
//            log.error(e, "TBCacheManager.putObjectWithExpire(Object, Object, int)"
//                            + " failed, with CATCHED message: {0}",
//                    e.getMessage());
            res = false;
            return res;
        } finally {
//            Profiler.release();
            digestLogger("Tair.putWithVersion", pre, key, res);
        }

        return res;
    }

    @Override
    public Integer incr(String key, int defalutValue, int expireTime) {
        return incr(key, 1, defalutValue, expireTime);
    }

    @Override
    public Integer desc(String key, int defalutValue, int expireTime) {
        return desc(key, 1, defalutValue, expireTime);
    }

    @Override
    public Integer desc(String key, int value, int defalutValue, int expireTime) {
        if (StringUtils.isBlank(key)) {
            throw new InvalidParameterException("Increase operation's key should not be null");
        }

        boolean hit = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.decr$expire");
//            Result<Integer> result = gzoneTairCacheManager.decr(key, value, defalutValue, expireTime);
//            if (result != null && result.isSuccess()) {
//                Integer intValue = result.getValue();
//                hit = (intValue != null);
//                return intValue;
//            }
//            log.info(
//                    "gzoneTairCacheManager.desc执行成功！key:{0}, value:{1}, defalutValue:{2}, expireTime:{3}, result:{4}",
//                    key, value, defalutValue, expireTime, result);
        } catch (Exception e) {
//            log.error(e, "desc获取原生TairManager计数服务失败");
            hit = false;
        } finally {
//            Profiler.release();
            digestLogger("Tair.desc$expire", pre, key, hit);
        }

        return null;
    }

    /**
     *
     */
    @Override
    public Integer incr(String key, int value, int defalutValue, int expireTime) {
        if (StringUtils.isBlank(key)) {
            throw new InvalidParameterException("Increase operation's key should not be null");
        }

        boolean hit = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.incr$expire");
//            Result<Integer> result = gzoneTairCacheManager.incr(key, value, defalutValue, expireTime);
//            if (result != null && result.isSuccess()) {
//                Integer intValue = result.getValue();
//                hit = (intValue != null);
//                return intValue;
//            }
//            log.info(
//                    "gzoneTairCacheManager.incr执行成功！key:{0}, value:{1}, defalutValue:{2}, expireTime:{3}, result:{4}",
//                    key, value, defalutValue, expireTime, result);
        } catch (Exception e) {
//            log.error(e, "incr获取原生TairManager计数服务失败");
            hit = false;
        } finally {
//            Profiler.release();
            digestLogger("Tair.incr", pre, key, hit);
        }

        return null;
    }

    /**
     *
     */
    @Override
    public Integer getCounter(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        boolean hit = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.getCounter");
//            Result<DataEntry> result = gzoneTairCacheManager.getInteger(key);
//            if (result != null && result.isSuccess() && result.getValue() != null) {
//                Integer intValue = (Integer) result.getValue().getValue();
//                hit = (intValue != null);
//                return intValue;
//            }
        } catch (Exception e) {
            hit = false;
//            log.error(e, "getCounter获取原生TairManager计数服务失败");
        } finally {
//            Profiler.release();
            digestLogger("Tair.getCounter", pre, key, hit);
        }
        return null;
    }

    /**
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    @Override
    public boolean putCounter(String key, Integer value, int expireTime) {
        if (StringUtils.isBlank(key) || value == null) {
            return false;
        }
        boolean hit = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.putCounter$expire");
//            gzoneTairCacheManager.removeObject(key);
//            Result<Integer> result = gzoneTairCacheManager.incr(key, 0, value, expireTime);
//            if (result != null && result.isSuccess() && value.equals(result.getValue())) {
//                hit = true;
//                return true;
//            }
        } catch (Exception e) {
//            log.error(e, "putCounter获取原生TairManager计数服务失败");
            hit = false;
        } finally {
//            Profiler.release();
            digestLogger("Tair.putCounter$expire", pre, key, hit);
        }
        return false;
    }

    /**
     *
     */
    @Override
    public Boolean isKeyExsit(String key) {
        if (StringUtils.isBlank(key)) {
            return Boolean.FALSE;
        }

        boolean hit = false;
        long pre = System.currentTimeMillis();
        try {
//            Profiler.enter("Tair.isKeyExist");
//            Result<DataEntry> result = gzoneTairCacheManager.getObjectWithVersionInfo(key);
//            if (result != null) {
//                ResultCode resultCode = result.getRc();
//                if (ResultCode.DATANOTEXSITS.getCode() == resultCode.getCode()) {
//                    hit = false;
//                    return Boolean.FALSE;
//                }
//                if (ResultCode.SUCCESS.getCode() == resultCode.getCode()) {
//                    hit = true;
//                    return Boolean.TRUE;
//                }
//            }
        } finally {
//            Profiler.release();
            digestLogger("Tair.isKeyExist", pre, key, hit);
        }
        return null;
    }

}