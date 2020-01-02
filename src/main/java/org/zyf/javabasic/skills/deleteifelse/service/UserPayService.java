package org.zyf.javabasic.skills.deleteifelse.service;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 */
public interface UserPayService {

    /**
     * 功能描述：计算应付价格
     * @author yanfengzhang
     * @date 2019-12-31 18:09
     * @param orderPrice BigDecimal
     * @return BigDecimal
    */
    BigDecimal quote(BigDecimal orderPrice);
}
