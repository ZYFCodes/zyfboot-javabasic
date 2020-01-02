package org.zyf.javabasic.skills.deleteifelse.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.skills.deleteifelse.factory.UserPayServiceStrategyFactory;
import org.zyf.javabasic.skills.deleteifelse.service.UserPayService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * 描述：用户是超级会员---8折价格
 *
 * @author yanfengzhang
 * @date 2019-12-31 18:13
 */
@Service
public class SuperVipPayServiceImpl implements UserPayService , InitializingBean {
    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        int payPrice = orderPrice.intValue();
        return new BigDecimal(payPrice * 0.8);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        UserPayServiceStrategyFactory.register("SuperVip",this);
    }
}
