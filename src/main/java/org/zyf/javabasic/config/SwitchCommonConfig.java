package org.zyf.javabasic.config;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.servicegovernance.enums.HostEnv;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 实时配置内容处理   只是模拟
 * @date 2022/6/22  23:28
 */
public class SwitchCommonConfig {

    /**
     * 获取执行打印的机器信息
     *
     * @return 执行打印的机器信息
     */
    public static List<String> getIpsForPrintLog() {
        List<String> ipsForPrintLog = Lists.newArrayList();
        return ipsForPrintLog;
    }

    /**
     * 获取执行打印的环境信息
     *
     * @return 执行打印的环境信息
     */
    public static List<HostEnv> getEnvsForPrintLog() {
        List<HostEnv> ensForPrintLog = Lists.newArrayList();
        ensForPrintLog.add(HostEnv.TEST);
        return ensForPrintLog;
    }

    /**
     * 是否统一开启打印日志
     *
     * @return true-开启
     */
    public static boolean openPrintLog() {
        return true;
    }

    public static List<String> getMockProductStore() {
        String mockProductStoreInterface = "mock_product_store_rpc,com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:set," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:setCount," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:setnx," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:expire," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:del," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:batchDel," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:addItems," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:rpush," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:msetBytes," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:msetString," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:hset," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:hmset," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:hmsetByte," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:hsetnx," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:hmsetnx," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:hdel," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:delhashmap," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:exists," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:prefixPut," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:prefixMultiPut," +
                "com.sankuai.meituan.waimai.kv.groupproductstorebase.client.WmKvTairClient:prefixDel";

        if (StringUtils.isBlank(mockProductStoreInterface)) {
            return org.assertj.core.util.Lists.newArrayList();
        }
        return Splitter.on(",").omitEmptyStrings().splitToList(mockProductStoreInterface);
    }
}
