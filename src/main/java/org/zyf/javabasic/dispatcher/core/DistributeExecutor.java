package org.zyf.javabasic.dispatcher.core;

import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.zyf.javabasic.dispatcher.base.DispatcherInfo;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.enums.DistributionAnalyzeType;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: zyfboot-javabasic
 * @description: 分发执行器
 * @author: zhangyanfeng
 * @create: 2024-04-21 20:24
 **/
@Log4j2
@Service
public class DistributeExecutor {

    private static Map<DistributionAnalyzeType, DispatcherCommand> dispatcherCommandMap = Maps.newHashMap();

    public static DispatcherCommand getByUserType(DistributionAnalyzeType distributionAnalyzeType) {
        return dispatcherCommandMap.get(distributionAnalyzeType);
    }

    public static void register(DistributionAnalyzeType distributionAnalyzeType, DispatcherCommand userPayService) {
        Assert.notNull(distributionAnalyzeType, "distributionAnalyzeType can't be null");
        dispatcherCommandMap.put(distributionAnalyzeType, userPayService);
    }

    /**
     * 执行分发操作
     *
     * @param dispatcherInfo 分发信息
     */
    public void executeDistribution(DispatcherInfo dispatcherInfo) {
        if (Objects.isNull(dispatcherInfo) || StringUtils.isBlank(dispatcherInfo.getDispatcherContents())) {
            return;
        }

        try {
            // 获取分发数据内容
            String dispatcherContents = dispatcherInfo.getDispatcherContents();
            Map<DistributionAnalyzeType, List<DistributionContent>> distributionAnalyzeTypeMap = DispatcherInfo.restoreDispatcherMap(dispatcherContents);
            if (MapUtils.isEmpty(distributionAnalyzeTypeMap)) {
                return;
            }
            //设置其解析数据
            dispatcherInfo.setDispatcherInfo(distributionAnalyzeTypeMap);

            if (MapUtils.isEmpty(dispatcherCommandMap)) {
                log.warn("分发处置器初始化失败！");
                return;
            }

            distributionAnalyzeTypeMap.keySet();

            //进行相关类型的分发
            for (DistributionAnalyzeType dispatcherType : DistributionAnalyzeType.values()) {
                if (!distributionAnalyzeTypeMap.keySet().contains(dispatcherType)) {
                    log.warn("该类型没有数据暂不进行分发操作！");
                    continue;
                }
                DispatcherCommand dispatcherCommand = dispatcherCommandMap.get(dispatcherType);
                if (Objects.isNull(dispatcherCommand)) {
                    continue;
                }
                //实际类型分发处理
                dispatcherCommand.execute(dispatcherInfo);
            }

            System.out.println("Distribution executed successfully.");
        } catch (Exception e) {
            // 处理异常
            handleException(e);
        }
    }

    /**
     * 处理异常
     *
     * @param e 异常
     */
    private void handleException(Exception e) {
        // 这里可以根据不同的异常类型进行不同的处理方式
        // 默认情况下，可以简单地打印异常信息
        e.printStackTrace();
    }
}
