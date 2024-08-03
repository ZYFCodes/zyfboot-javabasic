package org.zyf.javabasic.project.vote.model.attitud;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @program: zyfboot-javabasic
 * @description: 表态处理结果告知
 * @author: zhangyanfeng
 * @create: 2022-10-01 16:44
 **/
@Data
public class AttitudeShowRes implements Serializable {

    private static final long serialVersionUID = 2096728558584084620L;

    boolean success;

    String desc;

    public static AttitudeShowRes checkAttitudeRequest(AttitudeMakeReq showAttitudeRequest) {
        AttitudeShowRes errorResult = null;
        if (StringUtils.isBlank(showAttitudeRequest.getAttitudeId())) {
            errorResult = new AttitudeShowRes();
            errorResult.setSuccess(false);
            errorResult.setDesc("态度不存在");
            return errorResult;
        }
        if (StringUtils.isBlank(showAttitudeRequest.getDataType())) {
            errorResult = new AttitudeShowRes();
            errorResult.setSuccess(false);
            errorResult.setDesc("态度组不存在");
            return errorResult;
        }
        if (StringUtils.isBlank(showAttitudeRequest.getUserId())) {
            errorResult = new AttitudeShowRes();
            errorResult.setSuccess(false);
            errorResult.setDesc("用户不存在");
            return errorResult;
        }
        return errorResult;
    }
}

