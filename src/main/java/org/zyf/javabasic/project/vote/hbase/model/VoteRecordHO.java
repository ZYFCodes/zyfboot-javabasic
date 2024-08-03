package org.zyf.javabasic.project.vote.hbase.model;

import lombok.Data;

import java.util.Date;

/**
 * @program: zyfboot-javabasic
 * @description: 投票记录
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:08
 **/
@Data
public class VoteRecordHO {
    private String tenantId;

    private String bizId;

    private String votingId;

    private String userId;

    private String vote;

    private String sceneCode;

    private Date gmtModified;

    private Date gmtCreate;

    /**
     * 主要用于HBase操作，作为乐观锁标记
     */
    private Integer version;
}

