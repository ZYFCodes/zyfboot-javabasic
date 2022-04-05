package org.zyf.javabasic.skills.deleteifelse.service;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 */
public interface UserPayService {

    /**
     * 功能描述：计算应付价格
     *
     * @param orderPrice BigDecimal
     * @return BigDecimal
     * @author yanfengzhang
     * @date 2019-12-31 18:09
     */
    BigDecimal quote(BigDecimal orderPrice);
}
