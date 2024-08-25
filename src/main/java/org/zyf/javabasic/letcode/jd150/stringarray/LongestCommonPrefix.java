package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 最长公共前缀
 * @author: zhangyanfeng
 * @create: 2024-08-25 10:15
 **/
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 从第一个字符串的第一个字符开始比较
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // 如果在当前位置字符不匹配或已到达其他字符串的末尾
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }

        // 如果第一个字符串本身是最长公共前缀
        return strs[0];
    }

    public static void main(String[] args) {
        LongestCommonPrefix solution = new LongestCommonPrefix();

        // 测试用例
        System.out.println(solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // 输出: "fl"
        System.out.println(solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));    // 输出: ""
    }
}
