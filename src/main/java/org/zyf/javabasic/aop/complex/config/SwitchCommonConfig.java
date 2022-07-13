package org.zyf.javabasic.aop.complex.config;

import com.google.common.collect.Lists;
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
}
