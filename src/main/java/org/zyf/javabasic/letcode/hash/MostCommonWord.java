package org.zyf.javabasic.letcode.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。
 * 返回出现次数最多，同时不在禁用列表中的单词。题目保证至少有一个出现次数最多的单词。
 * 段落中的单词不区分大小写。答案都是小写字母。
 * @date 2023/4/9  22:21
 */
public class MostCommonWord {

    /**
     * 通过哈希表和字符串处理来解决。具体步骤如下：
     * <p>
     * 创建一个哈希表，用于存储每个单词的出现次数。
     * 将原始字符串中所有非字母字符都替换为空格，并将整个字符串转换成小写字母形式。
     * 将字符串按照空格分割成一个个单词，并在哈希表中统计每个单词出现的次数。
     * 遍历禁用单词列表 banned，将其对应的出现次数都设为 0。
     * 遍历哈希表，找到出现次数最多的单词，排除禁用单词列表中的单词。
     * 返回出现次数最多的单词。
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        /*Step 1: 创建哈希表并初始化*/
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : banned) {
            wordCount.put(word, 0);
        }

        /*Step 2: 将字符串中所有非字母字符替换为空格，并将整个字符串转换成小写字母形式*/
        paragraph = paragraph.replaceAll("[^a-zA-Z ]", " ").toLowerCase();

        /*Step 3: 统计每个单词出现的次数*/
        String[] words = paragraph.split("\\s+");
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        /*Step 4: 将禁用单词列表中的单词对应的出现次数都设为 0*/
        for (String word : banned) {
            wordCount.put(word, 0);
        }

        /*Step 5: 找到出现次数最多的单词，排除禁用单词列表中的单词*/
        int maxCount = 0;
        String result = "";
        for (String word : wordCount.keySet()) {
            if (wordCount.get(word) > maxCount) {
                maxCount = wordCount.get(word);
                result = word;
            }
        }

        /*Step 6: 返回出现次数最多的单词*/
        return result;
    }

    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        String result = new MostCommonWord().mostCommonWord(paragraph, banned);
        System.out.println(result);
    }

}

