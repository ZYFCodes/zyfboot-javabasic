package org.zyf.javabasic.project.vote.model.attitud;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: zyfboot-javabasic
 * @description: 请求获取对应表态信息
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:43
 **/
@Data
public class AttitudeQueryReq implements Serializable {

    private static final long serialVersionUID = 7612653909366629868L;

    /**
     * 用户ID
     */
    String userId;
    /**
     * 内容ID
     */
    String contentId;
    /**
     * 内容类型
     */
    String contentType;
}
