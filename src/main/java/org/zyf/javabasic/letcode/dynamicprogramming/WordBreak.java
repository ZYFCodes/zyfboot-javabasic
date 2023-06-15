package org.zyf.javabasic.letcode.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * * 字典中的单词可以重复使用。
 * * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]  输出: true
 * 解释: "leetcode" 可以被拆分为 "leet" 和 "code" 这两个单词。
 * 示例 2：输入: s = "applepenapple", wordDict = ["apple", "pen”]. 输出: true
 * 解释: "applepenapple" 可以被拆分为 "apple", "pen" 和 "apple" 这三个单词。
 * 注意你可以重复使用字典中的单词。
 * @date 2023/5/11  23:55
 */
public class WordBreak {
    /**
     * 单词拆分问题可以使用动态规划来解决。我们可以使用一个布尔数组 dp，其中 dp[i] 表示字符串 s 的前 i 个字符是否可以被拆分为字典中的单词。
     * 初始条件为 dp[0] = true，表示空字符串可以被拆分。
     * 然后，我们可以通过遍历字符串的每个位置 i，并在每个位置检查是否存在可拆分的位置 j（j 小于 i），使得字符串从位置 j 到位置 i 的子串在字典中存在。
     * 如果存在这样的 j，且 dp[j] 为 true，则说明从位置 j 到位置 i 的子串可以被拆分为字典中的单词，因此将 dp[i] 设置为 true。
     * 最终，dp[s.length()] 即为判断字符串 s 是否可以被拆分的结果。
     * <p>
     * 该算法的时间复杂度为 O(n^2)，其中 n 是字符串 s 的长度。
     * 通过动态规划的思想，我们可以在一次遍历中计算出是否可以将字符串拆分为字典中的单词。
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // 检查位置 j 到位置 i 的子串是否在字典中
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> wordDict1 = Arrays.asList("leet", "code");
        boolean canBreak1 = new WordBreak().wordBreak(s1, wordDict1);
        // 输出：是否可以拆分：true
        System.out.println("是否可以拆分：" + canBreak1);

        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        boolean canBreak2 = new WordBreak().wordBreak(s2, wordDict2);
        // 输出：是否可以拆分：true
        System.out.println("是否可以拆分：" + canBreak2);

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        boolean canBreak3 = new WordBreak().wordBreak(s3, wordDict3);
        // 输出：是否可以拆分：false
        System.out.println("是否可以拆分：" + canBreak3);
    }

}
