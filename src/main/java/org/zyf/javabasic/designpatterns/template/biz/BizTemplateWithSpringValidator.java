package org.zyf.javabasic.designpatterns.template.biz;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

/**
 * @author yanfengzhang
 * @description 支持Spring Validator
 * @date 2022/4/24  23:22
 */
public abstract class BizTemplateWithSpringValidator<Command extends BaseCommand, Result extends BaseResult> extends BizTemplate<Command, Result> {

    private BindingResult br;

    public BizTemplateWithSpringValidator(Command command, Result result, BindingResult br) {
        super(command, result);
        this.br = br;
    }

    @Override
    protected void doValidate(StringBuilder msg, boolean flag) {
        if (br.hasErrors()) {
            for (ObjectError error : br.getAllErrors()) {
                msg.append(" ");
                msg.append(error.getDefaultMessage());
                msg.append(" ");
            }
        }
        flag = br.hasErrors();
        super.doValidate(msg, flag);
    }
}
