package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: zyfboot-javabasic
 * @description: 通过定义扩展点开放必要的扩展能力
 * @author: zhangyanfeng
 * @create: 2025-03-09 14:55
 **/
@Slf4j
@Service
public class CouponOrderNewValidator extends BaseOrderNewValidator{
    @Override
    protected void validateExtension() {
        log.info("【Coupon】执行优惠券规则校验");
    }
}
