package org.zyf.javabasic.skills.deleteifelse.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.skills.deleteifelse.factory.UserPayServiceStrategyFactory;
import org.zyf.javabasic.skills.deleteifelse.service.UserPayService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * 描述：用户是专属会员---订单金额大于30元,7折价格/////否则9折价格
 *
 * @author yanfengzhang
 * @date 2019-12-31 18:11
 */
@Service
public class ParticularlyVipPayServiceImpl implements UserPayService, InitializingBean {
    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        int payPrice = orderPrice.intValue();
        if (payPrice > 30) {
            return new BigDecimal(payPrice * 0.7);
        }
        return new BigDecimal(payPrice * 0.9);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserPayServiceStrategyFactory.register("ParticularlyVip", this);
    }
}
