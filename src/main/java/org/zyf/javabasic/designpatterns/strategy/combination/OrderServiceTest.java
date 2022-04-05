package org.zyf.javabasic.designpatterns.strategy.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/8  23:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testCombination() throws ExecutionException {
        log.info("测试场景1：（订单编号-000001）用户未开通会员，在pc端通过微信支付，当前实际购买总价为131.7元");
        log.info("按该场景实际符合策略：用户需进行原价支付");
        Order order1 = new Order();
        order1.setCode("000001");
        order1.setAmount(new BigDecimal("131.7"));
        order1.setSource(OrderSourceEnum.PC);
        order1.setPayMethod(OrderPayMethodEnum.WEIXIN);
        order1.setPayMember(MemberTypeEnum.NONACTIVATED);
        OrderConsumerResult orderConsumerResult1 = orderService.orderService(order1);
        log.info("测试场景1结果：用户订单为[{}],用户需要支付的金额为{}元,用户享受权益信息：[{}]",
                orderConsumerResult1.getCode(), orderConsumerResult1.getAmountSpent(), orderConsumerResult1.getRight());

        log.info("测试场景2：（订单编号-000002）用户为新晋会员，在pc端通过支付宝支付，当前实际购买总价为131.7元");
        log.info("按该场景实际符合策略：支持满120减100优惠，同时赠送新晋套餐");
        Order order2 = new Order();
        order2.setCode("000002");
        order2.setAmount(new BigDecimal("131.7"));
        order2.setSource(OrderSourceEnum.PC);
        order2.setPayMethod(OrderPayMethodEnum.ZHIFUBAO);
        order2.setPayMember(MemberTypeEnum.NEWCOMER);
        OrderConsumerResult orderConsumerResult2 = orderService.orderService(order2);
        log.info("测试场景2结果：用户订单为[{}],用户需要支付的金额为{}元,用户享受权益信息：[{}]",
                orderConsumerResult2.getCode(), orderConsumerResult2.getAmountSpent(), orderConsumerResult2.getRight());

        log.info("测试场景3：（订单编号-000003）用户未开通会员，在pc端通过本地钱包支付，当前实际购买总价为131.7元");
        log.info("按该场景实际符合策略：存入钱包50得60，订单满30减30");
        Order order3 = new Order();
        order3.setCode("000003");
        order3.setAmount(new BigDecimal("131.7"));
        order3.setSource(OrderSourceEnum.PC);
        order3.setPayMethod(OrderPayMethodEnum.WALLET);
        order3.setPayMember(MemberTypeEnum.NONACTIVATED);
        OrderConsumerResult orderConsumerResult3 = orderService.orderService(order3);
        log.info("测试场景3结果：用户订单为[{}],用户需要支付的金额为{}元,用户享受权益信息：[{}]",
                orderConsumerResult3.getCode(), orderConsumerResult3.getAmountSpent(), orderConsumerResult3.getRight());

        log.info("测试场景4：（订单编号-000004）用户未开通会员，在手机端通过微信支付，当前实际购买总价为131.7元");
        log.info("按该场景实际符合策略：订单满10减3，送腾讯会员3天");
        Order order4 = new Order();
        order4.setCode("000004");
        order4.setAmount(new BigDecimal("131.7"));
        order4.setSource(OrderSourceEnum.MOBILE);
        order4.setPayMethod(OrderPayMethodEnum.WEIXIN);
        order4.setPayMember(MemberTypeEnum.NONACTIVATED);
        OrderConsumerResult orderConsumerResult4 = orderService.orderService(order4);
        log.info("测试场景4结果：用户订单为[{}],用户需要支付的金额为{}元,用户享受权益信息：[{}]",
                orderConsumerResult4.getCode(), orderConsumerResult4.getAmountSpent(), orderConsumerResult4.getRight());

        log.info("测试场景5：（订单编号-000005）用户为新晋会员，在手机端通过支付宝支付，当前实际购买总价为131.7元");
        log.info("按该场景实际符合策略：支持满130减120优惠，同时赠送新晋套餐");
        Order order5 = new Order();
        order5.setCode("000005");
        order5.setAmount(new BigDecimal("131.7"));
        order5.setSource(OrderSourceEnum.MOBILE);
        order5.setPayMethod(OrderPayMethodEnum.ZHIFUBAO);
        order5.setPayMember(MemberTypeEnum.NEWCOMER);
        OrderConsumerResult orderConsumerResult5 = orderService.orderService(order5);
        log.info("测试场景5结果：用户订单为[{}],用户需要支付的金额为{}元,用户享受权益信息：[{}]",
                orderConsumerResult5.getCode(), orderConsumerResult5.getAmountSpent(), orderConsumerResult5.getRight());

        log.info("测试场景6：（订单编号-000006）用户为黄金会员，在手机端通过本地钱包支付，当前实际购买总价为131.7元");
        log.info("按该场景实际符合策略：存入钱包200得400，套餐支持满100减20优惠，同时送减免整合套餐和内部优惠无限制满5减5（2张）");
        Order order6 = new Order();
        order6.setCode("000006");
        order6.setAmount(new BigDecimal("131.7"));
        order6.setSource(OrderSourceEnum.MOBILE);
        order6.setPayMethod(OrderPayMethodEnum.WALLET);
        order6.setPayMember(MemberTypeEnum.GOLD);
        OrderConsumerResult orderConsumerResult6 = orderService.orderService(order6);
        log.info("测试场景6结果：用户订单为[{}],用户需要支付的金额为{}元,用户享受权益信息：[{}]",
                orderConsumerResult6.getCode(), orderConsumerResult6.getAmountSpent(), orderConsumerResult6.getRight());
    }
}
