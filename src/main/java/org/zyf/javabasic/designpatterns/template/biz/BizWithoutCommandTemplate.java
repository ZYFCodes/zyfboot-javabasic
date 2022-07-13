package org.zyf.javabasic.designpatterns.template.biz;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yanfengzhang
 * @description 没有入参的调用
 * @date 2022/4/24  23:23
 */
public abstract class BizWithoutCommandTemplate<Result extends BaseResult> extends BizTemplate<BaseCommand, Result> {

    public BizWithoutCommandTemplate(Result result) {
        super(new BaseCommand(), result);
    }

    @Override
    protected boolean templateValidate() {
        if (result == null || StringUtils.isBlank(methodName) || templateLogger == null) {
            return false;
        }
        return true;
    }
}
