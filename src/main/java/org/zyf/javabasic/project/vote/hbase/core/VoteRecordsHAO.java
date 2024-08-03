package org.zyf.javabasic.project.vote.hbase.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.project.vote.hbase.model.VoteRecordHO;

/**
 * @program: zyfboot-javabasic
 * @description: 投票记录处理
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:10
 **/
@Service
@Slf4j
public class VoteRecordsHAO {
    /**
     * 查询指定用户投票记录
     *
     * @param votingId
     * @param userId
     * @return
     */
    public VoteRecordHO queryVotingRecord(String votingId, String userId) {
        return null;
    }

    /**
     * 存储投票记录
     *
     * @param voteRecordHO
     * @return
     */
    public boolean storeVotingRecord(VoteRecordHO voteRecordHO) {
        return true;
    }

    /**
     * 删除投票记录
     */
    public void deleteVotingRecord(String voteId, String userId) {

    }
}
