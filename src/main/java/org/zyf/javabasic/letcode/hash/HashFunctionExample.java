package org.zyf.javabasic.letcode.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author yanfengzhang
 * @description 首先指定输入数据为"Hello World"，然后通过MessageDigest类创建散列函数对象，
 * 指定散列函数为SHA-256，接着通过update()方法将输入数据添加到散列函数中，
 * 并通过digest()方法计算散列值，最后将散列值转换为十六进制字符串并输出。
 * <p>
 * 验证散列值的正确性可以通过类似的方式进行，即重新计算输入数据的散列值并将其与之前计算得到的散列值进行比较。
 * 可以将上述代码中的"Hello World"替换为其他字符串，重新计算散列值，
 * 并将其与之前计算得到的散列值进行比较，判断原始数据是否被篡改或损坏。
 * @date 2023/4/9  16:51
 */
public class HashFunctionExample {
    public static void main(String[] args) {
        try {
            /*输入数据*/
            String data = "Hello World";

            /*创建MessageDigest对象，指定散列函数为SHA-256*/
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            /*计算散列值*/
            md.update(data.getBytes());
            byte[] hash = md.digest();

            /*将字节数组转换为十六进制字符串*/
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02X", b));
            }

            /*输出散列值*/
            System.out.println(hexString.toString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}

