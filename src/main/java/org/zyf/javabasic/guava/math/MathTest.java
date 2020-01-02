package org.zyf.javabasic.guava.math;

import com.google.common.math.DoubleMath;
import com.google.common.math.IntMath;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.math.RoundingMode;

/**
 * 描述：数学运算
 *
 * @author yanfengzhang
 * @date 2019-12-31 10:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathTest {

    /**
     * 功能描述：int数学运算     ----LongMath与BigIntegerMath同理
     *
     * @author yanfengzhang
     * @date 2019-12-31 10:42
     */
    @Test
    public void testIntMath() {
        /*加*/
        int add = IntMath.checkedAdd(1, 2);
        TestCase.assertEquals(3, add);
        /*减*/
        int sub = IntMath.checkedSubtract(1, 2);
        TestCase.assertEquals(-1, sub);
        /*乘*/
        int multi = IntMath.checkedMultiply(1, 2);
        TestCase.assertEquals(2, multi);
        /*除 需要指定舍入格式*/
        int divide1 = IntMath.divide(2, 3, RoundingMode.DOWN);
        TestCase.assertEquals(0, divide1);
        int divide2 = IntMath.divide(2, 3, RoundingMode.FLOOR);
        TestCase.assertEquals(0, divide2);
        int divide3 = IntMath.divide(2, 3, RoundingMode.UP);
        TestCase.assertEquals(1, divide3);
        /*次方*/
        int pow = IntMath.checkedPow(1, 2);
        TestCase.assertEquals(1, pow);
        /*最大公约数*/
        int gcd = IntMath.gcd(3, 2);
        TestCase.assertEquals(1, gcd);
        /*取模*/
        int mod = IntMath.mod(3, 5);
        TestCase.assertEquals(3, mod);
        /*取幂*/
        int pow00 = IntMath.pow(3, 5);
        TestCase.assertEquals(243, pow00);
        /*是否2的幂*/
        boolean isPowerOfTwo = IntMath.isPowerOfTwo(4);
        TestCase.assertEquals(true, isPowerOfTwo);
        /*阶乘*/
        int factorial = IntMath.factorial(7);
        TestCase.assertEquals(5040, factorial);
        /*二项式系数*/
        int binomial = IntMath.binomial(4, 3);
        TestCase.assertEquals(4, binomial);
    }

    /**
     * 功能描述：浮点数运算JDK的Math包已经涵盖比较全面 但是guava依旧提供了一些方法
     *
     * @author yanfengzhang
     * @date 2019-12-31 10:44
     */
    @Test
    public void testDoubleMath() {
        /*判断该浮点数是不是一个整数*/
        boolean isMathematicalInteger = DoubleMath.isMathematicalInteger(4.0);
        TestCase.assertEquals(true, isMathematicalInteger);
        /*舍入为int；对无限小数、溢出抛出异常同理tolong，toBigInteger*/
        int roundToInt = DoubleMath.roundToInt(3.14, RoundingMode.DOWN);
        TestCase.assertEquals(3, roundToInt);
        /*2的浮点对数，并舍入为int*/
        int log2 = DoubleMath.log2(3.14, RoundingMode.DOWN);
        TestCase.assertEquals(1, log2);
    }
}
