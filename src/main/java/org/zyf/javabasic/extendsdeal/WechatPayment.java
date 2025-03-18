package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.extendsdeal.exception.PaymentException;

/**
 * @program: zyfboot-javabasic
 * @description: 子类：WechatPayment，继承了 BasePayment 类
 * @author: zhangyanfeng
 * @create: 2025-03-09 15:19
 **/
@Slf4j
@Service
public class WechatPayment extends BasePayment{
//    public WechatPayment() throws PaymentException {
//        // ❌ 必须调用父类构造方法，无法避开 connectGateway()
//        super();
//    }
//
//    public String test(){
//        return "测试成功！！！";
//    }
}
