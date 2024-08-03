package org.zyf.javabasic.project.vote.model.attitud;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: zyfboot-javabasic
 * @description: 态度展示基本内容
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:39
 **/
@Data
public class AttitudeVo implements Serializable {

    private static final long serialVersionUID = -2458491921612808273L;

    /**
     * 态度key
     */
    String id;
    /**
     * 态度描述
     */
    String name;
    /**
     * 持有这种态度的用户个数
     */
    long count;
    /**
     * 当前用户在某个内容下是否发表过该态度
     */
    boolean showed;
    /**
     * 态度图片
     */
    String pic;

    /**
     * 态度选中的图片
     */
    String checkedPic;
}


