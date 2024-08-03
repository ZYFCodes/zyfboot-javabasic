package org.zyf.javabasic.project.vote.tair;

/**
 * @program: zyfboot-javabasic
 * @description: 构建key
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:02
 **/
public class KeyBuilder {
    public static String buildVoteOptionsKey(String voteId) {
        return "VOTE_OPTIONS_V2_".concat(voteId);
    }

    public static String buildVoteInfoWithCounterKey(String voteId) {
        return "VOTE_INFO_WITH_COUNTER_".concat(voteId);
    }

    public static String buildUserRecordKey(String voteId, String userId) {
        return "VOTE_INFO_WITH_COUNTER_V1_".concat(voteId).concat("_").concat(userId);
    }


    public static String buildUserVoteStatusKey(String voteId, String userId) {
        return "VOTE_USER_STATUS_V1_".concat(voteId).concat("_").concat(userId);
    }
}
