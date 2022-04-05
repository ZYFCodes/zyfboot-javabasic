package org.zyf.javabasic.designpatterns.template.check;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yanfengzhang
 * @description 业务数据校验模版
 * @date 2022/3/16  23:35
 */
@Slf4j
@Service
public abstract class BizCheckTemplate<P> {

    /**
     * 校验全流程模版
     *
     * @param param 基本入参
     * @return 校验结果
     * @throws Exception 业务异常
     */
    public CheckResponse checkProcess(P param) {
        /*1.基础参数校验*/
        CheckResponse checkResponse = checkParam(param);
        if (!checkResponse.isPass()) {
            return checkResponse;
        }

        /*2.业务逻辑校验*/
        checkResponse = checkBiz(param);
        if (!checkResponse.isPass()) {
            return checkResponse;
        }

        /*3.冲突关联校验*/
        checkResponse = checkConflict(param);
        if (!checkResponse.isPass()) {
            return checkResponse;
        }

        /*4.验证码校验*/
        checkResponse = checkVerifyCode(param);
        if (!checkResponse.isPass()) {
            return checkResponse;
        }

        /*5.二次弹窗校验*/
        checkResponse = checkTwicePopup(param);
        if (!checkResponse.isPass()) {
            return checkResponse;
        }

        return checkResponse;
    }

    protected abstract CheckResponse checkParam(P param);

    protected abstract CheckResponse checkBiz(P param);

    protected abstract CheckResponse checkTwicePopup(P param);

    protected abstract CheckResponse checkVerifyCode(P param);

    protected abstract CheckResponse checkConflict(P param);
}
