package org.zyf.javabasic.letcode.featured75.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 反转字符串中的单词
 * @author: zhangyanfeng
 * @create: 2024-08-23 22:52
 **/
public class ReverseWords {
    public static String reverseWords(String s) {
        // 去除首尾的空格，并将中间多个空格缩减为一个空格
        s = s.trim().replaceAll("\\s+", " ");

        // 将字符串按空格分割成单词数组
        String[] words = s.split(" ");

        // 反转单词数组
        int left = 0, right = words.length - 1;
        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        // 将反转后的单词数组拼接成一个字符串
        return String.join(" ", words);
    }

    // 测试方法
    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue")); // 输出: "blue is sky the"
        System.out.println(reverseWords("  hello world  ")); // 输出: "world hello"
        System.out.println(reverseWords("a good   example")); // 输出: "example good a"
    }
}
