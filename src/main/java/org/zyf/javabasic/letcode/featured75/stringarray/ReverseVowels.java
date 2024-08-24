package org.zyf.javabasic.letcode.featured75.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 反转字符串中的元音字母
 * @author: zhangyanfeng
 * @create: 2024-08-23 22:48
 **/
public class ReverseVowels {
    public static String reverseVowels(String s) {
        // 将字符串转换为字符数组
        char[] chars = s.toCharArray();
        // 定义元音字母集
        String vowels = "aeiouAEIOU";

        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // 如果左指针指向的不是元音，向右移动
            while (left < right && vowels.indexOf(chars[left]) == -1) {
                left++;
            }
            // 如果右指针指向的不是元音，向左移动
            while (left < right && vowels.indexOf(chars[right]) == -1) {
                right--;
            }
            // 交换左指针和右指针的元音字母
            if (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                // 移动指针
                left++;
                right--;
            }
        }

        // 将字符数组转换为字符串并返回
        return new String(chars);
    }

    // 测试方法
    public static void main(String[] args) {
        System.out.println(reverseVowels("hello")); // 输出: "holle"
        System.out.println(reverseVowels("leetcode")); // 输出: "leotcede"
    }
}
