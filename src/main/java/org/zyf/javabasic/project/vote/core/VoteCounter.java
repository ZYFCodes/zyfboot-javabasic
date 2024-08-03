package org.zyf.javabasic.project.vote.core;

import org.zyf.javabasic.project.vote.model.vote.VoteRes;
import org.zyf.javabasic.project.vote.model.vote.VotingInfoReq;

/**
 * @program: zyfboot-javabasic
 * @description: 投票计数器
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:13
 **/
public interface VoteCounter {

    /**
     * 投票计数自增
     */
    VoteRes inc(String votingId, VotingInfoReq request);

    /**
     * 投票计数自减
     */
    VoteRes decr(String votingId, VotingInfoReq request);
}