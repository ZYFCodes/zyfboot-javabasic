package org.zyf.javabasic.project.vote.attitude;

import org.zyf.javabasic.project.vote.model.attitud.AttitudeVo;
import org.zyf.javabasic.project.vote.model.attitud.AttitudeMakeReq;
import org.zyf.javabasic.project.vote.model.attitud.AttitudeQueryReq;
import org.zyf.javabasic.project.vote.model.attitud.AttitudeShowRes;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 表态服务
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:19
 **/
public interface AttitudeService {
    /**
     * 查询态度
     *
     * @param attitudeQueryReq
     * @return
     */
    List<AttitudeVo> queryAttitude(AttitudeQueryReq attitudeQueryReq);

    /**
     * 表明态度
     *
     * @param showAttitudeRequest
     * @return
     */
    AttitudeShowRes makeAttitude(AttitudeMakeReq showAttitudeRequest);
}

