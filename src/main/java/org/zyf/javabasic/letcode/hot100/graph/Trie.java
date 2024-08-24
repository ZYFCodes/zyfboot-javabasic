package org.zyf.javabasic.letcode.hot100.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 实现 Trie (前缀树)（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:07
 **/
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // 插入一个单词到 Trie 中
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            // 如果当前节点没有该字符的子节点，创建一个新节点
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            // 移动到下一个节点
            node = node.children.get(c);
        }
        // 标记单词的结尾
        node.isEndOfWord = true;
    }

    // 检索单词是否在 Trie 中
    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            // 如果当前节点没有该字符的子节点，单词不存在
            if (!node.children.containsKey(c)) {
                return false;
            }
            // 移动到下一个节点
            node = node.children.get(c);
        }
        // 返回是否为单词的结尾
        return node.isEndOfWord;
    }

    // 检查是否有单词以给定前缀开头
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            // 如果当前节点没有该字符的子节点，前缀不存在
            if (!node.children.containsKey(c)) {
                return false;
            }
            // 移动到下一个节点
            node = node.children.get(c);
        }
        // 前缀存在
        return true;
    }

    class TrieNode {
        // 子节点映射
        Map<Character, TrieNode> children;
        // 是否为单词的结尾
        boolean isEndOfWord;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        // 测试插入和搜索
        trie.insert("apple");
        System.out.println(trie.search("apple")); // 输出: true
        System.out.println(trie.search("app"));   // 输出: false
        System.out.println(trie.startsWith("app")); // 输出: true
        trie.insert("app");
        System.out.println(trie.search("app"));   // 输出: true
    }
}
