package org.zyf.javabasic.designpatterns.template.check;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 申诉基本校验检查（公用）
 * @date 2022/3/16  23:40
 */
@Service
@Slf4j
public class AppealCheckService {

    /**
     * 公用的创建和修改必要参数检查
     *
     * @param param 必要参数信息
     * @return 检查结果
     */
    public CheckResponse checkParam(AppealMatters param) {
        if (Objects.isNull(param)) {
            return CheckResponse.builder().pass(false).errorMsg("创建消息不能为空！").build();
        }

        if (StringUtils.isBlank(param.getCode())) {
            return CheckResponse.builder().pass(false).errorMsg("申诉编码不能为空！").build();
        }

        if (Objects.isNull(param.getType())) {
            return CheckResponse.builder().pass(false).errorMsg("申诉类型不能为空且必须为规定的类型！").build();
        }

        return CheckResponse.builder().pass(true).build();
    }

    /**
     * 公用的创建和修改关联冲突校验
     *
     * @param param 请求参数信息
     * @return 检查结果
     */
    public CheckResponse checkConflict(AppealMatters param) {
        /*关联冲突检查1：申诉的内容对应的申诉类型在当前系统指定暂停办理中，请进行替换*/
        /*关联冲突检查2：申诉的内容项直接存在相同申诉问题，请重新归类提交*/
        /*关联冲突检查3：申诉的内容项与实际享有权益存在冲突，请确认避免后续被系统锁定*/
        /*关联冲突检查4：申诉人当前存在其他非法内容，则本次提交需解决之前的内容*/
        return CheckResponse.builder().pass(true).build();
    }
}
