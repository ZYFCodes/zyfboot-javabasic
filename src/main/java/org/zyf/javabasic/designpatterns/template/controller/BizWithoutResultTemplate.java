package org.zyf.javabasic.designpatterns.template.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author yanfengzhang
 * @description 没有返回参数的调用
 * @date 2022/4/24  21:24
 */
public abstract class BizWithoutResultTemplate<Command extends BaseCommand> extends BizTemplate<Command, BaseResult> {

    public BizWithoutResultTemplate(String methodName, Command command, Logger templateLogger) {
        super(command, new BaseResult());
    }

    @Override
    protected boolean templateValidate() {
        if (command == null || StringUtils.isBlank(methodName) || templateLogger == null) {
            return false;
        }
        return true;
    }
}
