package org.zyf.javabasic.letcode.jd150.trie;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 单词搜索 II
 * @author: zhangyanfeng
 * @create: 2024-08-25 17:57
 **/
public class FindWords {
    // 定义四个方向（上下左右）
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();  // 创建 Trie 树
        // 将所有单词插入 Trie 中
        for (String word : words) {
            trie.insert(word);
        }

        Set<String> ans = new HashSet<>();  // 存储结果的集合
        // 遍历网格的每一个单元格，进行 DFS 搜索
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, trie, i, j, ans);
            }
        }

        return new ArrayList<>(ans);  // 返回结果的列表
    }

    // 深度优先搜索函数
    public void dfs(char[][] board, Trie now, int i1, int j1, Set<String> ans) {
        // 如果当前字符不在 Trie 的子节点中，则返回
        if (!now.children.containsKey(board[i1][j1])) {
            return;
        }
        char ch = board[i1][j1];  // 当前字符
        now = now.children.get(ch);  // 移动到子节点

        // 如果当前节点是一个单词的结束点，则将单词添加到结果集中
        if (!"".equals(now.word)) {
            ans.add(now.word);
        }

        // 标记当前单元格为访问过
        board[i1][j1] = '#';

        // 遍历四个方向进行 DFS 搜索
        for (int[] dir : dirs) {
            int i2 = i1 + dir[0], j2 = j1 + dir[1];
            if (i2 >= 0 && i2 < board.length && j2 >= 0 && j2 < board[0].length) {
                dfs(board, now, i2, j2, ans);
            }
        }

        // 恢复单元格状态为原始字符
        board[i1][j1] = ch;
    }

    // Trie（前缀树）实现
    class Trie {
        String word;  // 存储单词
        Map<Character, Trie> children;  // 存储子节点
        boolean isWord;  // 标记是否是单词的结束

        public Trie() {
            this.word = "";
            this.children = new HashMap<>();
        }

        // 将单词插入到 Trie 中
        public void insert(String word) {
            Trie cur = this;
            for (int i = 0; i < word.length(); ++i) {
                char c = word.charAt(i);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new Trie());
                }
                cur = cur.children.get(c);
            }
            cur.word = word;  // 设置单词结束标记
        }
    }
}
