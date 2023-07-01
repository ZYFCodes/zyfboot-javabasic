package org.zyf.javabasic.letcode.jzoffer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符 “go” 时，第一个只出现一次的字符是 “g”；
 * 当从该字符流中读出前六个字符 “google” 时，第一个只出现一次的字符是 “l”。
 * @date 2023/6/5  23:11
 */
public class StrFirstAppearingOnce {
    private Map<Character, Integer> charCount;
    private Queue<Character> queue;

    public StrFirstAppearingOnce() {
        charCount = new HashMap<>();
        queue = new LinkedList<>();
    }

    public void insert(char ch) {
        // 更新哈希表中字符的出现次数
        charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        // 将字符添加到队列末尾
        queue.offer(ch);
    }

    /**
     * 为了快速找到字符流中第一个不重复的字符，可以借助哈希表和队列的结合使用。具体步骤如下：
     * 	1.	创建一个哈希表 charCount，用于存储字符及其出现的次数。
     * 	2.	创建一个队列 queue，用于按照字符流的顺序保存字符。
     * 	3.	每次读入一个字符时，进行以下操作：
     * 	•	将字符添加到队列 queue 的末尾。
     * 	•	更新哈希表 charCount 中对应字符的出现次数。
     * 	•	遍历队列 queue，找到第一个在哈希表 charCount 中出现次数为 1 的字符，并返回。
     * 	•	如果队列 queue 中没有只出现一次的字符，则返回 ‘#’。
     */
    public char firstAppearingOnce() {
        // 遍历队列，找到第一个只出现一次的字符
        while (!queue.isEmpty()) {
            char ch = queue.peek();
            // 如果当前字符出现次数为 1，则返回该字符
            if (charCount.get(ch) == 1) {
                return ch;
            } else {
                // 否则，移除队列头部的字符
                queue.poll();
            }
        }
        // 如果队列为空，则返回 '#'
        return '#';
    }

    public static void main(String[] args) {
        StrFirstAppearingOnce solution = new StrFirstAppearingOnce();

        String stream = "google";
        for (char ch : stream.toCharArray()) {
            solution.insert(ch);
            char firstNonRepeating = solution.firstAppearingOnce();
            System.out.println("当前字符流：" + stream.substring(0, stream.indexOf(ch) + 1));
            System.out.println("第一个不重复的字符：" + firstNonRepeating);
        }
    }

}
