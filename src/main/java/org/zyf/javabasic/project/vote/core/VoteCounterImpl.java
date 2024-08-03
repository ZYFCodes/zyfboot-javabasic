package org.zyf.javabasic.project.vote.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.project.vote.hbase.core.VoteInfoHAO;
import org.zyf.javabasic.project.vote.model.vote.VoteRes;
import org.zyf.javabasic.project.vote.model.vote.VotingInfoReq;
import org.zyf.javabasic.project.vote.tair.KeyBuilder;
import org.zyf.javabasic.project.vote.tair.TairClient;

import javax.annotation.Resource;

/**
 * @program: zyfboot-javabasic
 * @description: 投票计数器实现
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:14
 **/
@Service
@Slf4j
public class VoteCounterImpl implements VoteCounter {

    @Resource
    private VoteInfoHAO voteInfoHAO;

    @Resource
    private TairClient tairClient;

    @Override
    public VoteRes inc(String votingId, VotingInfoReq request) {
        VoteRes result = new VoteRes();
        voteInfoHAO.incrementAndGet(votingId, request.getVotingResult());
        /**
         * 删除缓存
         */
        tairClient.delete(KeyBuilder.buildVoteInfoWithCounterKey(votingId));
        result.setSuccess(true);
        return result;
    }

    @Override
    public VoteRes decr(String votingId, VotingInfoReq request) {
        VoteRes result = new VoteRes();
        //兜底，不可以搞出0来,防止用户高并发导致的不一致
        long count = voteInfoHAO.decrease(votingId, request.getVotingResult());
        if (count < 0) {
            voteInfoHAO.incrementAndGet(votingId, request.getVotingResult());
        }
        /**
         * 删除缓存
         */
        tairClient.delete(KeyBuilder.buildVoteInfoWithCounterKey(votingId));
        result.setSuccess(true);
        return result;
    }
}
