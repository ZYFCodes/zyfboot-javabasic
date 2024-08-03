package org.zyf.javabasic.project.vote.core;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.project.vote.enums.VoteStatusEnum;
import org.zyf.javabasic.project.vote.hbase.core.VoteInfoHAO;
import org.zyf.javabasic.project.vote.hbase.core.VoteRecordsHAO;
import org.zyf.javabasic.project.vote.hbase.model.VoteInfoHO;
import org.zyf.javabasic.project.vote.hbase.model.VoteRecordHO;
import org.zyf.javabasic.project.vote.model.vote.*;
import org.zyf.javabasic.project.vote.tair.KeyBuilder;
import org.zyf.javabasic.project.vote.tair.TairClient;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 投票管理器实现
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:16
 **/
@Service
@Slf4j
public class VoteManagerImpl implements VoteManager {

    @Resource
    private VoteInfoHAO voteInfoHAO;

    @Resource
    private VoteRecordsHAO voteRecordsHAO;

    @Resource
    private TairClient tairClient;

    /**
     * 创建投票
     */
    @Override
    public VoteRes createVoting(VoteCreateReq request) {
        VoteInfoHO voteInfoHO = new VoteInfoHO();
        voteInfoHO.setStatus(VoteStatusEnum.RUNNING.getStatus());
        if (request.getOptions() != null) {
            voteInfoHO.setOptions(JSON.toJSONString(request.getOptions()));
        }
        voteInfoHO.setBizId(request.getBizId());
        voteInfoHO.setVotingId(request.getVotingId());
        voteInfoHO.setVoteStart(request.getVoteStart());
        voteInfoHO.setVoteEnd(request.getVoteEnd());
        voteInfoHO.setCanResetVote(request.getCanResetVote());
        voteInfoHO.setGmtCreate(new Date());
        voteInfoHO.setGmtModified(new Date());
        voteInfoHO.setDesc(request.getDes());
        VoteRes voteRes = new VoteRes();
        try {
            boolean res = voteInfoHAO.createVotingInfo(voteInfoHO);
            voteRes.setSuccess(res);
        } catch (Exception e) {
            log.error("voting create error,{0}", request, e);
        }

        return voteRes;
    }

    /**
     * 查询投票详情
     */
    @Override
    public VotingInfo queryVotingInfo(String votingId, String userId) {
        VoteInfoHO voteInfoHO = voteInfoHAO.queryVoting(votingId);
        if (voteInfoHO == null) {
            log.warn("query votingInfo null,{},{}", votingId, userId);
            return null;
        }
        VotingInfo votingInfo = VotingInfo.buildVotingInfo(voteInfoHO);
        if (StringUtils.isBlank(userId)) {
            return votingInfo;
        }
        VoteRecordHO voteRecordHO = voteRecordsHAO.queryVotingRecord(votingId, userId);
        if (voteRecordHO != null) {
            votingInfo.setHasVoted(true);
            votingInfo.setVotingResult(voteRecordHO.getVote());
            votingInfo.setVotingDate(voteRecordHO.getGmtCreate());
            votingInfo.setUserId(voteRecordHO.getUserId());
        }
        return votingInfo;
    }

    @Override
    public VotingInfo queryVotingInfoWithCounter(String votingId, String userId, List<VoteOption> counterColumns) {
        VotingInfo votingInfo = tairClient.get(KeyBuilder.buildVoteInfoWithCounterKey(votingId));
        if (votingInfo == null) {
            VoteInfoHO voteInfoHO = voteInfoHAO.queryVotingWitchCounter(votingId, counterColumns);
            if (null == voteInfoHO) {
                return null;
            }
            votingInfo = VotingInfo.buildVotingInfo(voteInfoHO);
            List<VoteOptionVO> list = new ArrayList<VoteOptionVO>();
            for (VoteOption voteOption : voteInfoHO.getCounterMap()) {
                VoteOptionVO voteOptionVO = new VoteOptionVO();
                voteOptionVO.setKey(voteOption.getKey());
                voteOptionVO.setDes(voteOption.getDes());
                voteOptionVO.setCount(voteOption.getCount());
                list.add(voteOptionVO);
            }
            if (votingInfo != null) {
                votingInfo.getCounters().addAll(list);
                /**
                 * 添加缓存
                 */
                tairClient.put(KeyBuilder.buildVoteInfoWithCounterKey(votingId), votingInfo, 10 * TairClient.MINUTE);
            }
        }

        if (StringUtils.isBlank(userId)) {
            return votingInfo;
        }
        String voteStr = tairClient.getModifyObject(KeyBuilder.buildUserVoteStatusKey(votingId, userId));
        Boolean hasVoted = StringUtils.isEmpty(voteStr) ? null : Boolean.parseBoolean(voteStr);
        if (hasVoted != null && !hasVoted) {
            // 有值 且 没有投票
            log.info("[VotingManagerImpl.queryVotingInfoWithCounter] user vote status hit cache");
            return votingInfo;
        }

        String recordStr = tairClient.getModifyObject(KeyBuilder.buildUserRecordKey(votingId, userId));
        VoteRecordHO voteRecordHO = JSON.parseObject(recordStr, VoteRecordHO.class);
        if (voteRecordHO == null) {
            voteRecordHO = voteRecordsHAO.queryVotingRecord(votingId, userId);
            /**
             * 添加缓存
             */
            if (voteRecordHO != null) {

                tairClient.putObjectModifyDate(KeyBuilder.buildUserRecordKey(votingId, userId), JSON.toJSONString(voteRecordHO),
                        System.currentTimeMillis() + 60 * 1000, 10 * TairClient.MINUTE);
            }
        }
        log.info("[VotingManagerImpl.queryVotingInfoWithCounter] votingRecordHo:" + JSON.toJSONString(voteRecordHO));
        if (voteRecordHO != null) {
            votingInfo.setHasVoted(true);
            votingInfo.setVotingResult(voteRecordHO.getVote());
            votingInfo.setVotingDate(voteRecordHO.getGmtCreate());
            votingInfo.setUserId(voteRecordHO.getUserId());
            tairClient.putObjectModifyDate(KeyBuilder.buildUserVoteStatusKey(votingId, userId), Boolean.toString(true),
                    System.currentTimeMillis() + 60 * 1000, 10 * TairClient.MINUTE);
        } else {
            tairClient.putObjectModifyDate(KeyBuilder.buildUserVoteStatusKey(votingId, userId), Boolean.toString(false),
                    System.currentTimeMillis() + 60 * 1000, 10 * TairClient.MINUTE);
        }

        return votingInfo;
    }

    @Override
    public VoteRes storeVotingResult(VotingStoreReq request) {
        VoteRes result = new VoteRes();
        VoteRecordHO voteRecordHO = new VoteRecordHO();
        voteRecordHO.setBizId(request.getBizId());
        voteRecordHO.setGmtCreate(request.getVotingDate());
        voteRecordHO.setGmtModified(request.getVotingDate());
        voteRecordHO.setUserId(request.getUserId());
        voteRecordHO.setVote(request.getVotingResult());
        voteRecordHO.setVotingId(request.getVotingId());
        voteRecordHO.setTenantId(request.getTenantId());
        voteRecordHO.setSceneCode(request.getSceneCode());
        try {
            voteRecordsHAO.storeVotingRecord(voteRecordHO);
            String recordKey = KeyBuilder.buildUserRecordKey(request.getVotingId(), request.getUserId());
            String voteStatusKey = KeyBuilder.buildUserVoteStatusKey(request.getVotingId(), request.getUserId());
            long modifyTime = System.currentTimeMillis() + 60 * 1000;
            tairClient.putObjectModifyDate(recordKey, JSON.toJSONString(voteRecordHO), modifyTime, 10 * TairClient.MINUTE);
            tairClient.putObjectModifyDate(voteStatusKey, Boolean.toString(true), modifyTime, 10 * TairClient.MINUTE);

            result.setSuccess(true);
            return result;
        } catch (Exception e) {
            log.error("storeVotingResult failed,{0}", request, e);
        }
        return result;
    }

    @Override
    public void deleteVotingRecord(String voteId, String userId) {
        //删除投票记录
        voteRecordsHAO.deleteVotingRecord(voteId, userId);
        tairClient.delete(KeyBuilder.buildUserVoteStatusKey(voteId, userId));
        tairClient.delete(KeyBuilder.buildUserRecordKey(voteId, userId));
    }
}

