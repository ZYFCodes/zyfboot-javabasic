package org.zyf.javabasic.workflowpipline.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @program: zyfboot-javabasic
 * @description: 执行任务节点基本信息
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:11
 **/
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
@Documented
public @interface WorkerInfo {
    /**
     * worker的名称
     */
    String name();


    /**
     * 当前worker的上下文中如果Items为空，
     * 则忽略执行当前worker
     */
    boolean skipIfItemsIsEmpty() default false;
}
