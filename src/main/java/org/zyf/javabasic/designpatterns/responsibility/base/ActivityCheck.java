package org.zyf.javabasic.designpatterns.responsibility.base;

import org.zyf.javabasic.designpatterns.template.check.CheckResponse;

/**
 * @author yanfengzhang
 * @description 活动常规校验
 * @date 2022/3/30 22:43
 */
public interface ActivityCheck {
    /**
     * 活动常规校验
     *
     * @param activityDto 活动信息
     * @return 校验结果
     */
    CheckResponse check(ActivityDto activityDto);
}
