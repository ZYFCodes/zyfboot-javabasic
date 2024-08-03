package org.zyf.javabasic.project.vote.utils;

/**
 * @program: zyfboot-javabasic
 * @description: 投票组建类
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:55
 **/
public class VoteUtils {
    public static String buildVotingId(String dataType, String dataId) {
        return String.join("_", dataId, dataType);
    }
}
