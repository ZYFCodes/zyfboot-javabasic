package org.zyf.javabasic.designpatterns.template.check;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yanfengzhang
 * @description 申诉内容删除检查
 * @date 2022/3/16  23:44
 */
@Service
@Slf4j
public class AppealDeleteCheck extends BizCheckTemplate<Long> {
    @Autowired
    private AppealCheckService appealCheckService;

    /**
     * 申诉内容参数检查
     *
     * @return 校验结果
     * @id id 请求参数信息
     */
    @Override
    protected CheckResponse checkParam(Long id) {
        if (!checkPosInteger(id)) {
            return CheckResponse.builder().pass(false).errorMsg("申诉内容id不合法！").build();
        }
        /*id必须为有效ID*/
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 申诉内容业务逻辑检查
     *
     * @return 校验结果
     * @id id 请求参数信息
     */
    @Override
    protected CheckResponse checkBiz(Long id) {
        /*业务检查1：申诉的内容必须存在*/
        /*业务检查2：申诉的内容项全部处理完成的不能删除*/
        /*业务检查3：申诉人当前享有的权益存在需要进行续费的暂时不能删除申诉内容*/
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 申诉二次弹窗检查
     *
     * @return 校验结果
     * @id id 请求参数信息
     */
    @Override
    protected CheckResponse checkTwicePopup(Long id) {
        /*二次弹窗检查检查1：申请确认是否删除*/
        /*二次弹窗检查检查2：相关未享受的权益删除后不再享有，申请确认是否删除*/
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 申诉验证码校验
     *
     * @return 校验结果
     * @id id 请求参数信息
     */
    @Override
    protected CheckResponse checkVerifyCode(Long id) {
        /*验证码校验检查1：修改操作需本人进行验证码确认*/
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 申诉关联冲突校验（不涉及直接返回通过）
     *
     * @return 校验结果
     * @id id 请求参数信息
     */
    @Override
    protected CheckResponse checkConflict(Long id) {
        return CheckResponse.builder().pass(true).build();
    }

    /**
     * ID信息合法性:必须为正整数
     *
     * @param id id信息
     * @return true-合法；false-非法
     */
    public static boolean checkPosInteger(Long id) {
        return null != id && id > 0L;
    }
}
