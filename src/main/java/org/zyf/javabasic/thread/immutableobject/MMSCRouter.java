package org.zyf.javabasic.thread.immutableobject;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 彩信中心路由规则管理器 模式角色：ImmutableObject.ImmutableObject
 * 使用不可变对象维护路由表
 * @date 2021/1/16  18:26
 */
public final class MMSCRouter {
    /**
     * 用volatile关键修饰，保证在多线程环境下该变量的可见性
     */
    private static volatile MMSCRouter instance = new MMSCRouter();
    /**
     * 维护手机号前缀与彩信中心之间的映射关系
     */
    private final Map<String, MMSCInfo> routeMap;

    public MMSCRouter() {
        /*将数据库中的数据加载到内存，存为Map*/
        this.routeMap = MMSCRouter.retrieveRouteMapFromDB();
    }

    private static Map<String, MMSCInfo> retrieveRouteMapFromDB() {
        Map<String, MMSCInfo> mMSCRouters = Maps.newHashMap();
        /*todo 省略转换代码*/
        return mMSCRouters;
    }

    public static MMSCRouter getInstance() {
        return instance;
    }

    /**
     * @param phonePrefix
     * @return
     * @description 根据手机号前缀获取对应彩信中心信息
     */
    public MMSCInfo getMMSC(String phonePrefix) {
        return routeMap.get(phonePrefix);
    }

    /**
     * @param newInstance
     * @description 将当前MMSCRouter实例更新为指定的新实例
     */
    public static void setInstance(MMSCRouter newInstance) {
        instance = newInstance;
    }

    private static Map<String, MMSCInfo> deepCopy(Map<String, MMSCInfo> m) {
        Map<String, MMSCInfo> result = Maps.newHashMap();
        for (String key : m.keySet()) {
            result.put(key, new MMSCInfo(m.get(key)));
        }
        return result;
    }

    public Map<String, MMSCInfo> getRouteMap() {
        /*进行防御性复制*/
        return Collections.unmodifiableMap(deepCopy(routeMap));
    }


}
