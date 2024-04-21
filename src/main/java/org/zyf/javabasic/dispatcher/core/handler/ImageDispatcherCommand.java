package org.zyf.javabasic.dispatcher.core.handler;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.dispatcher.base.DispatcherInfo;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.core.DispatcherCommand;
import org.zyf.javabasic.dispatcher.core.DistributeExecutor;
import org.zyf.javabasic.dispatcher.core.model.DispatchTiming;
import org.zyf.javabasic.dispatcher.core.model.DistributionStrategy;
import org.zyf.javabasic.dispatcher.core.model.TargetUserCriteria;
import org.zyf.javabasic.dispatcher.enums.DistributionAnalyzeType;
import org.zyf.javabasic.dispatcher.exception.DistributionException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 图片分发处理
 * @author: zhangyanfeng
 * @create: 2024-04-21 20:13
 **/
@Service
public class ImageDispatcherCommand extends DispatcherCommand implements InitializingBean {
    @Override
    protected List<DistributionContent> fetchDistributionContent(DispatcherInfo dispatcherInfo) {
        List<DistributionContent> distributions = dispatcherInfo.getDispatcherInfo().get(DistributionAnalyzeType.IMAGE);
        //机构类型的基金可直接进行分发
        return distributions.stream().filter(DistributionContent::isValid).collect(Collectors.toList());
    }

    @Override
    protected TargetUserCriteria getTargetUser() {
        //直接模拟：直接对全部的用户生效
        return new TargetUserCriteria(Lists.emptyList(), true, StringUtils.EMPTY);
    }

    @Override
    protected DistributionStrategy getDistributionMode(TargetUserCriteria targetUser) {
        //直接模拟：直接对内容进行推送
        return new DistributionStrategy("推送", 1, true);
    }

    @Override
    protected DispatchTiming getDispatchTime(TargetUserCriteria targetUser, DistributionStrategy distributionStrategy) {
        return DispatchTiming.immediate();
    }

    @Override
    protected void handleDistributionException(DistributionException e) {
        System.out.println("处理异常！");
    }

    @Override
    protected void distributeContent(List<DistributionContent> contents, TargetUserCriteria targetUser, DistributionStrategy distributionStrategy, DispatchTiming dispatchTiming) {
        System.out.println("图片分发处理...........");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DistributeExecutor.register(DistributionAnalyzeType.IMAGE, this);
    }
}
