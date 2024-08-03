package org.zyf.javabasic.project.vote.model.vote;

import lombok.Data;
import org.zyf.javabasic.project.vote.hbase.model.VoteInfoHO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 投票信息
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:51
 **/
@Data
public class VotingInfo implements Serializable {

    private static final long serialVersionUID = 4256488290082854853L;

    private String userId;
    /**
     * 投票器ID
     */
    private String votingId;
    /**
     * 投票组ID
     */
    private String bizId;
    /**
     * 是否已投票
     */
    private boolean hasVoted;
    /**
     * 是否已发表观点
     */
    private boolean isPublished;
    /**
     * 投票结果
     */
    private String votingResult;
    /**
     * 投票时间
     */
    private Date votingDate;
    /**
     * 投票计数器
     */
    private List<VoteOptionVO> counters = new ArrayList<VoteOptionVO>();
    /**
     * 投票起始时间
     */
    private Date start;
    /**
     * 投票结束时间
     */
    private Date end;
    /**
     * 投票总数
     */
    private long total;
    /**
     * 投票选项
     */
    private String options;

    private Integer canResetVote;


    public static VotingInfo buildVotingInfo(VoteInfoHO voteInfoHO) {
        if (voteInfoHO == null) {
            return null;
        }
        VotingInfo votingInfo = new VotingInfo();
        votingInfo.setBizId(voteInfoHO.getBizId());
        votingInfo.setEnd(voteInfoHO.getVoteEnd());
        votingInfo.setStart(voteInfoHO.getVoteStart());
        votingInfo.setVotingId(voteInfoHO.getVotingId());
        votingInfo.setOptions(voteInfoHO.getOptions());
        votingInfo.setCanResetVote(voteInfoHO.getCanResetVote());

        return votingInfo;
    }
}
