package org.zyf.javabasic.project.vote.hbase.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.project.vote.hbase.model.VoteInfoHO;
import org.zyf.javabasic.project.vote.model.vote.VoteOption;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 投票处理
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:09
 **/
@Service
@Slf4j
public class VoteInfoHAO {
    /**
     * 创建投票组
     *
     * @param
     * @return
     */
    public boolean createVotingInfo(VoteInfoHO voteInfoHO) {
        return true;
    }

    /**
     * 查询投票组信息
     *
     * @param votingId
     * @return
     */
    public VoteInfoHO queryVoting(String votingId) {
        return null;
    }

    /**
     * 查询投票组信息
     */
    public VoteInfoHO queryVotingWitchCounter(String votingId, List<VoteOption> counterColumn) {
        return null;
    }

    /**
     * 自增计数
     */
    public long incrementAndGet(String votingId, String option) {
        return 1;
    }

    public long decrease(String votingId, String option) {
        return 1;
    }
}
