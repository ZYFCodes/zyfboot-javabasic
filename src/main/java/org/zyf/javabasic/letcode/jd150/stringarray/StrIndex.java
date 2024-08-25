package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 找出字符串中第一个匹配项的下标
 * @author: zhangyanfeng
 * @create: 2024-08-25 10:27
 **/
public class StrIndex {
    public int strStr(String haystack, String needle) {
        // 获取 haystack 和 needle 的长度
        int n = haystack.length();
        int m = needle.length();

        // 遍历 haystack 的每个位置，尝试匹配 needle
        for (int i = 0; i <= n - m; i++) {
            // 取出 haystack 中的子串
            String substring = haystack.substring(i, i + m);

            // 检查子串是否与 needle 相等
            if (substring.equals(needle)) {
                return i; // 如果匹配，返回起始位置
            }
        }

        // 如果没有找到匹配，返回 -1
        return -1;
    }
}
