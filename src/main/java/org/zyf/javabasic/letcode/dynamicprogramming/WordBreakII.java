package org.zyf.javabasic.letcode.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，为 s 添加空格，使其成为一个句子中的单词拼接，返回所有可能的句子。
 * 说明：
 * * 拆分时可以重复使用字典中的单词。
 * * 你可以假设字典中没有重复的单词。
 * 示例 1:
 * 输入:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * 输出:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 示例 2:
 * 输入:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * 输出:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * 解释: 注意你可以重复使用字典中的单词。
 * 示例 3:
 * 输入:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出:
 * []
 * @date 2023/5/11  23:08
 */
public class WordBreakII {
    /**
     * 最优解法采用动态规划和回溯的结合来解决单词拆分 II 问题。
     * 首先使用动态规划判断给定的字符串 s 是否可以被拆分成字典中的单词。
     * 可以使用一个布尔数组 dp，其中 dp[i] 表示从字符串的开头到第 i 个字符（不包含第 i 个字符）的子串是否可以被拆分成字典中的单词。
     * 初始化时，将 dp[0] 设置为 true，表示空字符串可以被拆分。
     * 然后，使用回溯算法生成所有可能的句子。
     * 对于给定的字符串 s，从开头开始逐个字符进行遍历。
     * 对于每个遍历到的位置 i，如果 dp[i] 为 true，则说明从开头到第 i 个字符的子串可以被拆分成字典中的单词。
     * 接下来，需要递归生成剩余部分的句子，并将其与当前单词拼接起来形成完整的句子。
     * 为了避免重复计算，可以使用一个哈希表 memo 来存储已经计算过的位置，以及对应的生成的句子列表。
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();

        return backtrack(s, 0, wordSet, memo);
    }

    private List<String> backtrack(String s, int start, Set<String> wordSet, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> sentences = new ArrayList<>();
        if (start == s.length()) {
            sentences.add("");
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            if (wordSet.contains(word)) {
                List<String> nextSentences = backtrack(s, end, wordSet, memo);
                for (String nextSentence : nextSentences) {
                    sentences.add(word + (nextSentence.isEmpty() ? "" : " ") + nextSentence);
                }
            }
        }

        memo.put(start, sentences);
        return sentences;
    }

    public static void main(String[] args) {
        String s1 = "catsanddog";
        List<String> wordDict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> sentences1 = new WordBreakII().wordBreak(s1, wordDict1);
        System.out.println("所有可能的句子：");
        for (String sentence : sentences1) {
            System.out.println(sentence);
        }
        System.out.println();

        String s2 = "pineapplepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        List<String> sentences2 = new WordBreakII().wordBreak(s2, wordDict2);
        System.out.println("所有可能的句子：");
        for (String sentence : sentences2) {
            System.out.println(sentence);
        }
        System.out.println();

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        List<String> sentences3 = new WordBreakII().wordBreak(s3, wordDict3);
        System.out.println("所有可能的句子：");
        for (String sentence : sentences3) {
            System.out.println(sentence);
        }
    }
}
