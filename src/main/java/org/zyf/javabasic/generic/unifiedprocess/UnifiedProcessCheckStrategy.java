package org.zyf.javabasic.generic.unifiedprocess;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yanfengzhang
 * @description 统一化校验流程 （举例：校验相关活动或影视的相关基本信息内容等）
 * @date 2021/2/9  23:57
 */
@Slf4j
@Service
public abstract class UnifiedProcessCheckStrategy<P> {
    public CheckResponse checkProcess(P param) throws Exception {
        /*基础参数校验*/
        CheckResponse response = checkBasicParam(param);
        if (response != null) {
            return response;
        }

        /*相关合法性校验*/
        response = checkValid(param);
        if (response != null) {
            return response;
        }

        /*冲突关联校验*/
        response = checkConflict(param);
        if (response != null) {
            return response;
        }

        /*验证码校验*/
        response = checkVerifyCode(param);
        if (response != null) {
            return response;
        }

        return response;
    }

    public abstract CheckResponse checkBasicParam(P param) throws Exception;

    public abstract CheckResponse checkValid(P param) throws Exception;

    public abstract CheckResponse checkVerifyCode(P param) throws Exception;

    public abstract CheckResponse checkConflict(P param) throws Exception;
}
