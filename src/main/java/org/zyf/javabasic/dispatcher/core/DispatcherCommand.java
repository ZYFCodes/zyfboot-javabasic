package org.zyf.javabasic.dispatcher.core;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.zyf.javabasic.dispatcher.base.DispatcherInfo;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.core.model.DispatchTiming;
import org.zyf.javabasic.dispatcher.core.model.DistributionStrategy;
import org.zyf.javabasic.dispatcher.core.model.TargetUserCriteria;
import org.zyf.javabasic.dispatcher.exception.DistributionException;

import java.util.List;
import java.util.Objects;

/**
 * @program: zyfboot-javabasic
 * @description: 分发器模版设计
 * @author: zhangyanfeng
 * @create: 2024-04-21 16:42
 **/
@Log4j2
public abstract class DispatcherCommand {

    // 获取真实分发数据内容
    protected abstract List<DistributionContent> fetchDistributionContent(DispatcherInfo dispatcherInfo);

    // 获取目标用户
    protected abstract TargetUserCriteria getTargetUser();

    // 获取分发方式
    protected abstract DistributionStrategy getDistributionMode(TargetUserCriteria targetUser);

    // 获取分发时刻
    protected abstract DispatchTiming getDispatchTime(TargetUserCriteria targetUser, DistributionStrategy distributionStrategy);

    // 异常处理
    protected abstract void handleDistributionException(DistributionException e);

    // 分发数据
    protected abstract void distributeContent(List<DistributionContent> contents, TargetUserCriteria targetUser,
                                              DistributionStrategy distributionStrategy, DispatchTiming dispatchTiming);

    // 执行分发命令
    public void execute(DispatcherInfo dispatcherInfo) {
        try {
            // 获取分发数据
            List<DistributionContent> contents = fetchDistributionContent(dispatcherInfo);
            if (CollectionUtils.isEmpty(contents)) {
                return;
            }

            //获取对应分发数据的人群配置要求
            TargetUserCriteria targetUser = getTargetUser();
            if (Objects.isNull(targetUser)) {
                //先模拟错误码吧
                throw new DistributionException(1, "对应分发类型没有对应人群配置信息，请进行配置后分发！");
            }

            //获取对应的分发方式
            DistributionStrategy mode = getDistributionMode(targetUser);
            if (Objects.isNull(mode)) {
                //先模拟错误码吧
                throw new DistributionException(2, "对应分发类型没有对应分发方式配置信息，请进行配置后分发！");
            }

            //获取对应的分发时机内容信息
            DispatchTiming dispatchTiming = getDispatchTime(targetUser, mode);
            if (Objects.isNull(dispatchTiming)) {
                //先模拟错误码吧
                throw new DistributionException(3, "对应分发类型关联人群和分发方式没有对应分发时机配置信息，请进行检查！");
            }

            //按对应的内容进行实际的分发
            distributeContent(contents, targetUser, mode, dispatchTiming);
        } catch (DistributionException e) {
            // 自定义异常处理
            handleDistributionException(e);
        } catch (Exception e) {
            // 对其他所有异常进行统一处理
            handleException(e);
        }
    }

    private void handleException(Exception e) {
        // 记录异常日志
        log.error("An unknown error occurred:", e);

        // 发送警报或通知管理员
        //alertManager.sendAlert("An unknown error occurred: " + e.getMessage());
    }
}
