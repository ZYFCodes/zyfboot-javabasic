package org.zyf.javabasic.designpatterns.template.check;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanfengzhang
 * @description 申诉内容修改检查
 * @date 2022/3/16  23:43
 */
@Service
@Slf4j
public class AppealUpdateCheck extends BizCheckTemplate<AppealMatters> {

    @Autowired
    private AppealCheckService appealCheckService;

    /**
     * 申诉内容参数检查
     *
     * @param param 请求参数信息
     * @return 校验结果
     */
    @Override
    protected CheckResponse checkParam(AppealMatters param) {
        if (!param.getType().equals(2)) {
            /*为了用于测试*/
            return CheckResponse.builder().pass(false).errorMsg("修改申诉内容类型不符合要求！").build();
        }
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 申诉内容业务逻辑检查
     *
     * @param param 请求参数信息
     * @return 校验结果
     */
    @Override
    protected CheckResponse checkBiz(AppealMatters param) {
        /*业务检查1：申诉的内容编码不能修改*/
        /*业务检查2：申诉的内容项已进行处理的不能在修改，新增的申诉项不得包含非法指定的相关内容*/
        /*业务检查3：新增的申诉项中，申诉人对应的申诉项中和申诉人当前享有的权益不匹配*/
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 申诉二次弹窗检查
     *
     * @param param 请求参数信息
     * @return 校验结果
     */
    @Override
    protected CheckResponse checkTwicePopup(AppealMatters param) {
        /*二次弹窗检查检查1：申诉的内容存在修改次数，请确认是否修改*/
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 申诉验证码校验
     *
     * @param param 请求参数信息
     * @return 校验结果
     */
    @Override
    protected CheckResponse checkVerifyCode(AppealMatters param) {
        /*验证码校验检查1：修改操作需本人进行验证码确认*/
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 申诉关联冲突校验
     *
     * @param param 请求参数信息
     * @return 校验结果
     */
    @Override
    protected CheckResponse checkConflict(AppealMatters param) {
        return appealCheckService.checkConflict(param);
    }
}
