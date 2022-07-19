package org.zyf.javabasic.aop.redissync;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanfengzhang
 * @description 异步同步redis注解：将需要异步处理的内容放置到该信息内容上
 * --主要针对相关基本信息的增删改，批量的暂时先不考虑
 * @date 2022/7/13  23:10
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationRedisSync {
    /**
     * 需要异步处理的信息操作：新增1 修改2 删除3
     */
    int dealInfoType() default 0;

    /**
     * 需要异步处理的业务归属：0-敏感词业务；1-超范围资质业务；3-会员渠道业务；4-红包发布业务
     */
    int dealBizType() default 0;

    /**
     * 需要异步处理的信息名称
     */
    String dealInfo() default "";

    /**
     * 将要被删除的id
     */
    String deleteId() default "id";
}
