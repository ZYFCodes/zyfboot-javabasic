package org.zyf.javabasic.skills.deleteifelse.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.skills.deleteifelse.factory.UserPayServiceStrategyFactory;
import org.zyf.javabasic.skills.deleteifelse.service.UserPayService;

import java.math.BigDecimal;

/**
 * 描述：用户是普通会员
 * 情况1：该用户超级会员刚过期并且尚未使用过临时折扣-->临时折扣使用次数更新-->8折价格
 * 情况2：非以上情况-->9折价格
 *
 * @author yanfengzhang
 * @date 2019-12-31 18:15
 */
@Service
public class VipPayServiceImpl implements UserPayService, InitializingBean {
    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        int payPrice = orderPrice.intValue();
        /*该用户超级会员刚过期并且尚未使用过临时折扣*/
        if (conditions()) {
            /*临时折扣使用次数更新*/
            updateSomething();
            return new BigDecimal(payPrice * 0.8);
        }
        return new BigDecimal(payPrice * 0.9);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserPayServiceStrategyFactory.register("Vip", this);
    }

    /**
     * 功能描述：满足一定的条件
     *
     * @author yanfengzhang
     * @date 2020-01-02 09:11
     */
    private boolean conditions() {
        return true;
    }

    private void updateSomething() {

    }
}
