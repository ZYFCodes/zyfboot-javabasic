package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description  给定一个字符串 s 和一个整数 n，请将字符串左旋转 n 个字符。
 * @date 2023/6/5  22:01
 */
public class StrLeftRotate {
    /**
     * 可以将字符串的左旋转操作拆分为两个步骤：
     * 	1.	将前 n 个字符翻转。
     * 	2.	将剩余的字符翻转。
     * 	3.	将整个字符串翻转。
     */
    public String leftRotateString(String s, int n) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] charArray = s.toCharArray();
        int length = charArray.length;

        // 对前 n 个字符翻转
        reverse(charArray, 0, n - 1);

        // 对剩余的字符翻转
        reverse(charArray, n, length - 1);

        // 对整个字符串翻转
        reverse(charArray, 0, length - 1);

        return new String(charArray);
    }

    // 翻转字符数组指定范围内的字符
    private void reverse(char[] charArray, int start, int end) {
        while (start < end) {
            char temp = charArray[start];
            charArray[start] = charArray[end];
            charArray[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        StrLeftRotate solution = new StrLeftRotate();

        String s = "abcdefg";
        int n = 2;

        String rotatedString = solution.leftRotateString(s, n);

        System.out.println("左旋转后的字符串：" + rotatedString);
    }
}
