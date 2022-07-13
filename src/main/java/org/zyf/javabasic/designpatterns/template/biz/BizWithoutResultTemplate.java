package org.zyf.javabasic.designpatterns.template.biz;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yanfengzhang
 * @description 没有返回参数的调用
 * @date 2022/4/24  23:24
 */
public abstract class BizWithoutResultTemplate<Command extends BaseCommand> extends BizTemplate<Command, BaseResult> {

    public BizWithoutResultTemplate(Command command) {
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
