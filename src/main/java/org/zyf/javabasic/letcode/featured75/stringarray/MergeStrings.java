package org.zyf.javabasic.letcode.featured75.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 交替合并字符串
 * @author: zhangyanfeng
 * @create: 2024-08-23 22:10
 **/
public class MergeStrings {
    public static String mergeAlternately(String word1, String word2) {
        StringBuilder merged = new StringBuilder();
        int i = 0, j = 0;
        int n1 = word1.length(), n2 = word2.length();

        // 交替添加字符
        while (i < n1 && j < n2) {
            merged.append(word1.charAt(i++));
            merged.append(word2.charAt(j++));
        }

        // 将剩余部分追加到结果中
        while (i < n1) {
            merged.append(word1.charAt(i++));
        }
        while (j < n2) {
            merged.append(word2.charAt(j++));
        }

        return merged.toString();
    }

    // 测试方法
    public static void main(String[] args) {
        System.out.println(mergeAlternately("abc", "pqr")); // 输出: "apbqcr"
        System.out.println(mergeAlternately("ab", "pqrs")); // 输出: "apbqrs"
        System.out.println(mergeAlternately("abcd", "pq")); // 输出: "apbqcd"
    }
}
