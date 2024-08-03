package org.zyf.javabasic.project.vote.model.vote;

import lombok.Data;
import org.zyf.javabasic.project.vote.model.attitud.AttitudeMakeReq;
import org.zyf.javabasic.project.vote.utils.VoteUtils;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 投票请求信息
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:54
 **/
@Data
public class VotingInfoReq {
    /**
     * 租户Id
     */
    private String tenantId;

    /**
     * 投票器ID
     */
    @NotBlank
    private String votingId;

    /**
     * 业务ID
     */
    private String bizId;

    /**
     * 投票用户ID
     */
    @NotBlank
    private String userId;
    /**
     * 投票结果
     */
    @NotBlank
    private String votingResult;

    /**
     * 如果重新投票的话，要传之前投票的选项
     */
    private String oldVoteResult;

    /**
     * 场景码
     */
    private String sceneCode;

    /**
     * 投票时间
     */
    private Date votingDate = new Date();
    /**
     * 请求上下文
     */
    private Map<String, Object> context;

    public static VotingInfoReq convert2VotingRequest(AttitudeMakeReq showAttitudeRequest) {
        VotingInfoReq request = new VotingInfoReq();
        request.setVotingId(VoteUtils.buildVotingId(showAttitudeRequest.getDataType(), showAttitudeRequest.getDataId()));
        request.setUserId(showAttitudeRequest.getUserId());
        Date votingDate = new Date();
        request.setVotingResult(showAttitudeRequest.getAttitudeId());
        request.setVotingDate(votingDate);
        request.setBizId(showAttitudeRequest.getDataId());
        return request;
    }
}
