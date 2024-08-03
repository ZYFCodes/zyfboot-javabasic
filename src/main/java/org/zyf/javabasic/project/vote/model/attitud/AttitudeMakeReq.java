package org.zyf.javabasic.project.vote.model.attitud;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: zyfboot-javabasic
 * @description: 具体表态请求处置
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:40
 **/
@Data
public class AttitudeMakeReq implements Serializable {

    private static final long serialVersionUID = -8568112872535492763L;

    /**
     * 交互场景
     */
    public String interactScene;

    /**
     * 态度关联的数据id
     */
    public String dataId;

    /**
     * 交互类型 SHOW/UNSHOW
     */
    public String interactActionType;

    /**
     * 态度的key值
     */
    public String attitudeId;

    /**
     * 内容类型-用来匹配态度组
     */
    public String dataType;

    /**
     * 态度关联数据的评论父ID
     */
    public String commentFatherId;

    /**
     * 发表态度的用户Id
     */
    public String userId;
}
