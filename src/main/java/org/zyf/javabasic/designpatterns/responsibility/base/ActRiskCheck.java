package org.zyf.javabasic.designpatterns.responsibility.base;

import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.template.check.CheckResponse;

/**
 * @author yanfengzhang
 * @description 风控校验
 * @date 2022/3/30 22:55
 */
@Component
public class ActRiskCheck implements ActivityCheck {
    /**
     * 活动风控检查
     *
     * @param activityDto 活动信息
     * @return 活动时效性检查结果 true-满足条件
     */
    @Override
    public CheckResponse check(ActivityDto activityDto) {
        /*风控校验1：如果当前活动非上线状态(1)，则不进行风控管理*/
        if (activityDto.getStatus() != 1) {
            return CheckResponse.builder().pass(true).build();
        }
        /*风控校验2：相关描述不得包含敏感词(以下用于模拟)*/
        String sensitive = "有敏感词";
        if (activityDto.getActivityName().contains(sensitive) || activityDto.getActivityConfig().contains(sensitive)) {
            return CheckResponse.builder().pass(false).errorMsg("[活动设置中存在敏感词，请修正相关配置内容！]").build();
        }

        return CheckResponse.builder().pass(true).build();
    }
}
