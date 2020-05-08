package org.zyf.javabasic.test;

/**
 * 描述：关于字符串的一些疑问测试
 *
 * @author yanfengzhang
 * @date 2020-05-08 09:40
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "123";
        String b = new String("123");
        System.out.println(a == b);
        System.out.println(a.equals(b));
    }
}
