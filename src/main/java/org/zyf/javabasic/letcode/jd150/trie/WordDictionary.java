package org.zyf.javabasic.letcode.jd150.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 添加与搜索单词 - 数据结构设计
 * @author: zhangyanfeng
 * @create: 2024-08-25 17:50
 **/
public class WordDictionary {
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEndOfWord;

        TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        return searchInTrie(word, 0, root);
    }

    private boolean searchInTrie(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char c = word.charAt(index);
        if (c == '.') {
            // 对于 '.', 遍历所有子节点
            for (TrieNode child : node.children.values()) {
                if (searchInTrie(word, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            // 对于普通字符, 只在子节点中查找
            TrieNode child = node.children.get(c);
            if (child == null) {
                return false;
            }
            return searchInTrie(word, index + 1, child);
        }
    }
}
