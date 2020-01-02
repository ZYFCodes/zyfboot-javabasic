package org.zyf.javabasic.skills.deleteifelse.test;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.skills.deleteifelse.factory.UserPayServiceStrategyFactory;
import org.zyf.javabasic.skills.deleteifelse.service.UserPayService;

import java.math.BigDecimal;

/**
 * 描述：通过策略模式、工厂模式以及Spring的InitializingBean，提升了代码的可读性以及可维护性，彻底消灭了一坨if-else
 * 注：1.这里使用的并不是严格意义上面的策略模式和工厂模式。
 *
 * @author yanfengzhang
 * @date 2019-12-31 18:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteIfElseSkill {

    @Test
    public void testDeleteIfElseSkill(){
        User user=new User();
        UserPayService strategy = UserPayServiceStrategyFactory.getByUserType("ParticularlyVip");

        user.setOrderPrice(new BigDecimal("50"));
        BigDecimal payPrice1= strategy.quote(user.getOrderPrice());
        System.out.println("用户为专属会员，并且订单金额为50，按会员优惠最后应支付："+payPrice1);

        user.setOrderPrice(new BigDecimal("20"));
        BigDecimal payPrice2= strategy.quote(user.getOrderPrice());
        System.out.println("用户为专属会员，并且订单金额为20，按会员优惠最后应支付："+payPrice2);
    }

    @Data
    private static class User{
        private String uid;
        private String vipType;
        private BigDecimal orderPrice;
    }
}
