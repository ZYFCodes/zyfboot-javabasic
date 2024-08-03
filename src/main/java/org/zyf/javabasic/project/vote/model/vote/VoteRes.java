package org.zyf.javabasic.project.vote.model.vote;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: zyfboot-javabasic
 * @description: 投票结果
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:49
 **/
@Data
public class VoteRes implements Serializable {

    private static final long serialVersionUID = -645671080215769107L;

    /**
     * 成功状态
     */
    private boolean success = false;

    /**
     * 结果描述
     */
    private String resultDesc;

    /**
     * 当前投票器是否开放
     */
    private boolean isOpen = false;
}