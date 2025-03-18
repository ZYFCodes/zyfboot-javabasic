package org.zyf.javabasic.test.doubledeal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @program: zyfboot-javabasic
 * @description: 基本测试
 * @author: zhangyanfeng
 * @create: 2025-03-11 17:21
 **/
public class BaseTest {
    public static void main(String[] args) {
        double d1 = 0.1;
        double d2 = 0.2;
        System.out.println(d1 + d2); // 输出：0.30000000000000004
        System.out.println(0.1 + 0.2 == 0.3); // 输出：false

        double result = 1.05 - 1.01;
        System.out.println(result); // 输出：0.040000000000000036

        System.out.println(new BigDecimal(0.1));
        // 输出：0.1000000000000000055511151231257827021181583404541015625


        DecimalFormat df = new DecimalFormat("#.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println(df.format(3.35)); // 输出：3.35

        BigDecimal amount = new BigDecimal("3.35");
        System.out.println(amount.setScale(1, RoundingMode.HALF_UP)); // 输出：3.4
    }

    public static String formatAmount(BigDecimal amount, int scale) {
        if (amount == null) return "0.00";
        return amount.setScale(scale, RoundingMode.HALF_UP).toPlainString();
    }
}
