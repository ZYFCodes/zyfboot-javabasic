package org.zyf.javabasic.letcode.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 给定两个单词 beginWord 和 endWord，以及一个字典列表 wordList，找到从 beginWord 到 endWord 的最短转换序列的长度。
 * 每次转换只能改变一个字母，并且转换后的单词必须在字典列表 wordList 中。
 * 注意：
 * * 每个给定的单词长度相同。
 * * 所有单词只包含小写字母。
 * * 假设不存在重复的单词。
 * * beginWord 和 endWord 不为空，且它们不同。
 * 例如，给定 beginWord = "hit"，endWord = "cog"，wordList = ["hot","dot","dog","lot","log","cog"]，
 * 最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog"，转换序列的长度为 5。
 * 你需要返回从 beginWord 到 endWord 的最短转换序列的长度。如果不存在这样的转换序列，则返回 0。
 * @date 2023/5/2  22:27
 */
public class WordLadder {

    /**
     * 针对单词接龙（Word Ladder）问题，可以使用广度优先搜索（BFS）算法来找到最短转换序列的长度。以下是该算法的最优解法分析：
     * 1. 构建图：将字典列表中的单词作为图中的节点，如果两个单词只相差一个字母，则在它们之间添加一条边。可以使用哈希表来存储每个单词对应的邻居列表，以便快速查找。
     * 2. BFS搜索：从起始单词开始，使用队列进行广度优先搜索。首先将起始单词加入队列，并初始化访问标记和距离为1。然后，从队列中取出一个单词，并遍历它的邻居列表。
     * 3. 判断终止条件：如果当前单词等于目标单词，说明已经找到了最短转换序列，返回当前距离即可。
     * 4. 转换单词：对于当前单词的每个字母，尝试用所有可能的小写字母进行替换，生成新的单词。如果新的单词存在于字典列表中，并且之前没有访问过，将其加入队列，并更新其距离和访问标记。
     * 5. 继续搜索：重复步骤3和步骤4，直到队列为空。如果队列为空时仍未找到目标单词，则说明无法从起始单词转换到目标单词，返回0。
     * 该解法的时间复杂度取决于字典列表中的单词数量和每个单词的长度。在构建图时，需要遍历字典列表，并对每个单词的每个字母进行替换，
     * 时间复杂度为 O(M * N)，其中 M 是字典列表的长度，N 是单词的长度。在广度优先搜索中，每个单词最多会入队一次，因此总体时间复杂度为 O(M * N)。
     * 由于单词接龙问题可以看作是在图中寻找最短路径的问题，因此广度优先搜索算法是该问题的最优解法之一。
     * 使用广度优先搜索可以有效地找到起始单词到目标单词的最短转换序列的长度，时间复杂度相对较低。
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 将字典列表转换为集合，方便快速查找
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            // 如果目标单词不在字典列表中，无法进行转换，返回0
            return 0;
        }

        // 创建队列用于广度优先搜索
        Queue<String> queue = new LinkedList<>();
        // 将起始单词加入队列
        queue.offer(beginWord);
        // 距离初始化为1
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();

                // 尝试转换单词的每个字母
                char[] wordChars = currentWord.toCharArray();
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];

                    // 替换字母，生成新的单词
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) {
                            continue; // 跳过相同的字母
                        }

                        wordChars[j] = c;
                        String newWord = String.valueOf(wordChars);

                        if (newWord.equals(endWord)) {
                            // 找到目标单词，返回当前距离加1
                            return level + 1;
                        }

                        if (wordSet.contains(newWord)) {
                            // 将新的单词加入队列
                            queue.offer(newWord);
                            // 避免重复访问
                            wordSet.remove(newWord);
                        }
                    }

                    // 恢复原始字母
                    wordChars[j] = originalChar;
                }
            }
            // 增加距离
            level++;
        }
        // 无法进行转换，返回0
        return 0;
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();

        // 示例测试用例
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int ladderLength1 = wordLadder.ladderLength(beginWord1, endWord1, wordList1);
        System.out.println("Test Case 1:");
        System.out.println("Begin Word: " + beginWord1);
        System.out.println("End Word: " + endWord1);
        System.out.println("Word List: " + wordList1);
        System.out.println("Minimum Length: " + ladderLength1);
        System.out.println();

        // 自定义测试用例
        String beginWord2 = "hot";
        String endWord2 = "dog";
        List<String> wordList2 = Arrays.asList("hot", "dog", "dot");
        int ladderLength2 = wordLadder.ladderLength(beginWord2, endWord2, wordList2);
        System.out.println("Test Case 2:");
        System.out.println("Begin Word: " + beginWord2);
        System.out.println("End Word: " + endWord2);
        System.out.println("Word List: " + wordList2);
        System.out.println("Minimum Length: " + ladderLength2);
        System.out.println();
    }
}
