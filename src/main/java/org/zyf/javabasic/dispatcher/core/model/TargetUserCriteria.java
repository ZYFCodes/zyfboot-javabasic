package org.zyf.javabasic.dispatcher.core.model;

import lombok.Data;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 表示分发目标用户的条件
 * @author: zhangyanfeng
 * @create: 2024-04-21 17:09
 **/
@Data
public class TargetUserCriteria {
    // 圈定的用户列表
    private List<String> targetedUsers;

    // 标识是否包含所有用户
    private boolean allUsers;

    // 用于目标定位的特定用户特征
    private String userFeature;

    /**
     * 构造一个 TargetUserCriteria 对象。
     *
     * @param targetedUsers 圈定的用户列表
     * @param allUsers      是否包含所有用户的标识
     * @param userFeature   用于目标定位的特定用户特征
     */
    public TargetUserCriteria(List<String> targetedUsers, boolean allUsers, String userFeature) {
        this.targetedUsers = targetedUsers;
        this.allUsers = allUsers;
        this.userFeature = userFeature;
    }
}
