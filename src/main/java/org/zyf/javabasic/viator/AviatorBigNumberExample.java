package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * @program: zyfboot-javabasic
 * @description: 验证 Aviator 的大数计算和精度控制功能
 * @author: zhangyanfeng
 * @create: 2023-10-29 18:12
 **/
public class AviatorBigNumberExample {
    public static void main(String[] args) {
        Object rt = AviatorEvaluator.exec("9223372036854775807100.356M * 2");
        System.out.println(rt + " " + rt.getClass());  // 18446744073709551614200.712 class java.math.BigDecimal
        rt = AviatorEvaluator.exec("92233720368547758074+1000");
        System.out.println(rt + " " + rt.getClass());  // 92233720368547759074 class java.math.BigInteger
        BigInteger a = new BigInteger(String.valueOf(Long.MAX_VALUE) + String.valueOf(Long.MAX_VALUE));
        BigDecimal b = new BigDecimal("3.2");
        BigDecimal c = new BigDecimal("9999.99999");
        rt = AviatorEvaluator.exec("a+10000000000000000000", a);
        System.out.println(rt + " " + rt.getClass());  // 92233720368547758089223372036854775807 class java.math.BigInteger
        rt = AviatorEvaluator.exec("b+c*2", b, c);
        System.out.println(rt + " " + rt.getClass());  // 20003.19998 class java.math.BigDecimal
        rt = AviatorEvaluator.exec("a*b/c", a, b, c);
        System.out.println(rt + " " + rt.getClass());  // 2.951479054745007313280155218459508E+34 class java.math.BigDecimal

    }
}
