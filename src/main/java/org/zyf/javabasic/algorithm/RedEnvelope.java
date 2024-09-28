package org.zyf.javabasic.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: 实现一个类似的“二倍均值法”来分配红包
 * @author: zhangyanfeng
 * @create: 2024-09-19 10:32
 **/
public class RedEnvelope {
    // 保留两位小数
    public static double remainTwoDecimal(double num) {
        return Math.round(num * 100.0) / 100.0;
    }

    // 随机获取红包金额
    public static double getRandomRedPack(RedPack rp) {
        if (rp.getSurplusTotal() <= 0) {
            // 所有红包都已经分配完毕
            return 0;
        }

        // 如果是最后一个红包，直接返回剩余金额
        if (rp.getSurplusTotal() == 1) {
            return remainTwoDecimal(rp.getSurplusAmount());
        }

        Random rand = new Random();

        // 计算剩余平均金额
        double avgAmount = remainTwoDecimal(rp.getSurplusAmount() / rp.getSurplusTotal());

        // 最大可分配金额为 2 倍均值 - 0.01
        double max = remainTwoDecimal(2 * avgAmount - 0.01);

        // 随机生成红包金额
        double money = remainTwoDecimal(rand.nextDouble() * max + 0.01);

        // 更新剩余金额和剩余红包数量
        rp.setSurplusTotal(rp.getSurplusTotal() - 1);
        rp.setSurplusAmount(remainTwoDecimal(rp.getSurplusAmount() - money));

        return money;
    }

    // 测试函数
    public static void main(String[] args) {
        RedPack rp = new RedPack(10.00, 5); // 初始化：总金额 10 元，5 个红包

        List<Double> redPacks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            double money = getRandomRedPack(rp);
            redPacks.add(money);
        }

        System.out.println("每个红包的金额为：");
        for (double amount : redPacks) {
            System.out.println(amount + " 元");
        }
    }
}
