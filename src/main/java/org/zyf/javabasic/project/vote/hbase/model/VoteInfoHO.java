package org.zyf.javabasic.project.vote.hbase.model;

import lombok.Data;
import org.zyf.javabasic.project.vote.model.vote.VoteOption;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 投票信息
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:53
 **/
@Data
public class VoteInfoHO {
    /**
     * 投票组ID
     */
    private String bizId;

    /**
     * 投票器ID
     */
    private String votingId;

    /**
     * 状态
     */
    private String status;

    /**
     * 描述
     */
    private String desc;

    /**
     * 投票起始时间
     */
    private Date voteStart;

    /**
     * 投票结束时间
     */
    private Date voteEnd;

    /**
     * 生成日期
     */
    private Date gmtModified;

    /**
     * 修改日期
     */
    private Date gmtCreate;

    /**
     * 主要用于HBase操作，作为乐观锁标记
     */
    private Integer version;

    /**
     * 投票选项
     */
    private String options;

    /**
     * 是否可以重新投票 0 不可以 1 可以
     */
    private Integer canResetVote;

    /**
     * counter
     */
    private List<VoteOption> counterMap = new ArrayList<VoteOption>();
}