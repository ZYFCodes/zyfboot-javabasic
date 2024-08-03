package org.zyf.javabasic.project.vote.core;

import org.zyf.javabasic.project.vote.model.vote.*;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 投票管理器
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:15
 **/
public interface VoteManager {
    /**
     * 创建投票
     */
    VoteRes createVoting(VoteCreateReq request);

    /**
     * 查询投票详情
     */
    VotingInfo queryVotingInfo(String votingId, String userId);

    /**
     * 查询投票详情,带投票计数
     * userId 为空就不带用户投票信息
     */
    VotingInfo queryVotingInfoWithCounter(String votingId, String userId, List<VoteOption> counterColumns);

    /**
     * 存储投票信息
     */
    VoteRes storeVotingResult(VotingStoreReq request);

    /**
     * 删除投票记录
     */
    void deleteVotingRecord(String voteId, String userId);
}
