package org.zyf.javabasic.designpatterns.template.controller;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  21:10
 */
public abstract class BizTemplate<Command extends BaseCommand, Result extends BaseResult> {
    protected BizResponse<Result> response = BizResponse.success();
    /**
     * 请求参数
     */
    protected Command command;
    /**
     * 请求结果
     */
    protected Result result;

    /**
     * 方法名称，用于记录日志
     */
    protected String methodName;
    /**
     * 日志
     */
    protected Logger templateLogger = LoggerFactory.getLogger(BizTemplate.class);
    /**
     * 验证器集合
     */
    protected List<Validation> validationList = new ArrayList<>(10);

    public BizTemplate(Command command, Result result) {
        this.methodName = this.getClass().getName();
        this.command = command;
        this.result = result;
    }

    public BizTemplate(Result result) {
        this.methodName = this.getClass().getName();
        this.command = (Command) new BaseCommand();
        this.result = result;
    }

    public BizTemplate(Command command) {
        this.methodName = this.getClass().getName();
        this.command = command;
        this.result = (Result) new BaseResult();
    }

    /**
     * check template
     *
     * @return
     */
    protected boolean templateValidate() {
        if (command == null || result == null || StringUtils.isBlank(methodName) || templateLogger == null) {
            return false;
        }
        return true;
    }

    /**
     * before
     * you can write the logic code that execute before the primay business logic
     * for example validate paramaters, check preposed data and so on
     */
    public abstract void doBefore();

    /**
     * primay business logic
     */
    public abstract void doBiz() throws Exception;

    public void doException() throws Exception {

    }

    /**
     * after
     * you can write the logic code that execute after the primay business logic
     */
    public void doAfter() {
    }

    /**
     * add validation
     *
     * @param check
     * @param msg
     */
    public void addValidation(boolean check, String msg) {
        this.validationList.add(new Validation(check, msg));
    }

    /**
     * execute validation
     *
     * @param msg,  result
     * @param flag, record if the result is false, default is false
     */
    protected void doValidate(StringBuilder msg, boolean flag) {
        if (CollectionUtils.isNotEmpty(validationList)) {
            for (Validation validation : validationList) {
                if (!validation.check) {
                    msg.append(" ");
                    msg.append(validation.msg);
                    msg.append(" ");
                    flag = true;
                }
            }
        }

        if (flag) {
            throw new ZYFServerException(ErrorCode.PARAMS_CHECK_FAILED.getCode(), String.format(ErrorCode.PARAMS_CHECK_FAILED.getMsg(), msg.toString()));
        }
    }

    /**
     * execute
     *
     * @return
     */
    public BizResponse<Result> execute() throws Exception {
        long threadId = Thread.currentThread().getId();
        if (!this.templateValidate()) {
            throw new IllegalArgumentException("the usage of WebControllerBizTemplate is illegal");
        }

        this.doBefore();
        this.doValidate(new StringBuilder(), false);

        long startTime = System.currentTimeMillis();
        try {
            this.doBiz();
        } catch (Exception e) {
            this.doException();
            throw e;
        }

        this.doAfter();
        response.setData(result);

        return response;
    }

    /**
     * validator
     *
     * @author sunwei
     */
    class Validation {
        boolean check;
        String msg;

        public Validation(boolean check, String msg) {
            super();
            this.check = check;
            this.msg = msg;
        }

    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Logger getTemplateLogger() {
        return templateLogger;
    }

    public void setTemplateLogger(Logger templateLogger) {
        this.templateLogger = templateLogger;
    }

}

