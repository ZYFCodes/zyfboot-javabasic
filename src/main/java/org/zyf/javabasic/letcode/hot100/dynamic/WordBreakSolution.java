package org.zyf.javabasic.letcode.hot100.dynamic;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 单词拆分（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:10
 **/
public class WordBreakSolution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp数组，其中dp[i]表示s的前i个字符能否被字典中的单词拼接而成
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // 初始化dp[0]为true，表示空字符串可以被拼接而成

        // 遍历字符串s的每个字符
        for (int i = 1; i <= s.length(); i++) {
            // 遍历字典中的每个单词
            for (String word : wordDict) {
                // 如果当前的单词长度不超过i，且s的前i个字符中的最后一个匹配word
                if (i >= word.length() && s.substring(i - word.length(), i).equals(word)) {
                    dp[i] = dp[i] || dp[i - word.length()];
                }
            }
        }

        // 返回dp[s.length()]，表示s能否被拼接而成
        return dp[s.length()];
    }

    public static void main(String[] args) {
        WordBreakSolution solution = new WordBreakSolution();
        List<String> wordDict = Lists.newArrayList("leet", "code");
        String s = "leetcode";
        System.out.println(solution.wordBreak(s, wordDict)); // 输出: true

        wordDict = Lists.newArrayList("apple", "pen");
        s = "applepenapple";
        System.out.println(solution.wordBreak(s, wordDict)); // 输出: true

        wordDict = Lists.newArrayList("cats", "dog", "sand", "and", "cat");
        s = "catsandog";
        System.out.println(solution.wordBreak(s, wordDict)); // 输出: false
    }
}
