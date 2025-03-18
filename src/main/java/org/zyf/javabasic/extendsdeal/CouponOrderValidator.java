package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: zyfboot-javabasic
 * @description: 子类覆盖父类核心逻辑
 * @author: zhangyanfeng
 * @create: 2025-03-09 14:33
 **/
@Slf4j
@Service
public class CouponOrderValidator extends BaseOrderValidator{
    @Override
    protected void checkUserStatus() {
        // ❌ 忽略调用 super.checkUserStatus()
        log.info("【Coupon】仅执行优惠券状态校验");
    }
}
