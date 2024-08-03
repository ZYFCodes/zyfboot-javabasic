package org.zyf.javabasic.project.vote.model.vote;

import lombok.Data;

import java.util.Date;

/**
 * @program: zyfboot-javabasic
 * @description: 存储投票结果请求
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:58
 **/
@Data
public class VotingStoreReq {
    private String tenantId;

    private String votingId;

    private String bizId;

    private String userId;

    private String votingResult;

    private String sceneCode;

    private Date votingDate;
}