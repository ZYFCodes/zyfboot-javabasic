package org.zyf.javabasic.test;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/3/9  20:26
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;

        System.out.println(a.equals(b));
        System.out.println(a == b);

        Integer c = 1;
        int d = 1;

        System.out.println(c == d);

        String s1 = String.valueOf(d);
        String s2 = String.valueOf(c);
        System.out.println(s1.equals(s2));
    }
}
