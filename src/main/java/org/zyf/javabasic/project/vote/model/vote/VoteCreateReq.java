package org.zyf.javabasic.project.vote.model.vote;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 投票请求
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:51
 **/
@Data
public class VoteCreateReq implements Serializable {

    private static final long serialVersionUID = 5436649150509341586L;

    /**
     * 投票器ID
     */
    private String votingId;

    /**
     * 投票组ID
     */
    private String bizId;

    /**
     * 投票起始时间
     */
    private Date voteStart;

    /**
     * 投票结束时间
     */
    private Date voteEnd;

    /**
     * 描述
     */
    private String des;

    /**
     * 是否能重新投票
     */
    private Integer canResetVote;

    /**
     * 投票选项
     */
    private List<VoteOption> options = new ArrayList<VoteOption>();
}