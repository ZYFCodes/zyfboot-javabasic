package org.zyf.javabasic.designpatterns.responsibility.base;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 活动对应业务处理
 * @date 2022/3/30 23:01
 */
@Service
public class ActivityBizService {

    @Autowired
    private List<ActivityCheck> activityCheckList;

    /**
     * 活动常规校验结果
     *
     * @param activityDto 活动信息
     * @return z
     */
    public List<String> check(ActivityDto activityDto) {
        List<String> checkResults = Lists.newArrayList();
        for (ActivityCheck activityCheck : activityCheckList) {
            if (!activityCheck.check(activityDto).isPass()) {
                checkResults.add(activityCheck.check(activityDto).getErrorMsg());
            }
        }
        return checkResults;
    }
}
