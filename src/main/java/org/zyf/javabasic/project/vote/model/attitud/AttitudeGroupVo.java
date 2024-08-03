package org.zyf.javabasic.project.vote.model.attitud;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 表态组件归类
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:40
 **/
@Data
public class AttitudeGroupVo implements Serializable {

    private static final long serialVersionUID = 6633172325484962566L;

    /**
     * 态度组ID
     */
    String groupId;
    /**
     * 态度列表
     */
    List<AttitudeVo> attitudes;
}
