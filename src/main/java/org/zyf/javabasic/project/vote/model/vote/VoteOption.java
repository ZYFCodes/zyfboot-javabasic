package org.zyf.javabasic.project.vote.model.vote;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: zyfboot-javabasic
 * @description: 投票选项
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:46
 **/
@Data
public class VoteOption implements Serializable {

    private static final long serialVersionUID = -6904442209809378L;

    /**
     * key
     */
    private String key;
    /**
     * 描述
     */
    private String des;

    /**
     * 计数
     */
    private long count;
}
