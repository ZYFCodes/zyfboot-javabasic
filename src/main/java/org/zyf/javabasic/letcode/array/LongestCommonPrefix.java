package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:输入: ["flower","flow","flight"] 输出: "fl"
 * 示例 2:输入: ["dog","racecar","car"] 输出: "" 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * @date 2023/4/2  19:02
 */
public class LongestCommonPrefix {

    /**
     * 最优解法是使用字典树（Trie）的方法。对于所有的字符串，构建一棵Trie树，
     * 然后从根节点开始，沿着第一个字符串的字符进行遍历，
     * 如果当前节点有且只有一个子节点，那么我们就继续沿着这个子节点向下遍历，
     * 直到遇到有多个子节点的节点，这时候我们就找到了所有字符串的最长公共前缀。
     * <p>
     * 具体步骤如下：
     * 构建 Trie 树，将所有字符串插入 Trie 树中。
     * 从 Trie 树的根节点开始，遍历第一个字符串的字符。
     * 设当前遍历到的字符为c，如果当前节点有一个子节点是以c为字符的，就继续往下遍历这个子节点，
     * 否则就结束遍历，此时当前节点就是所有字符串的最长公共前缀的结尾。
     * 时间复杂度是 O(mn)，其中m是字符串的平均长度，n是字符串的数量。
     * <p>
     * 因为在构建 Trie 树的过程中，每个节点最多只会被访问一次。同时，
     * 由于 Trie 树的高度为m，所以查找最长公共前缀的时间复杂度也是O(m)。
     */
    public String longestCommonPrefix(String[] strs) {
        /*检查输入是否为空*/
        if (strs == null || strs.length == 0) {
            return "";
        }
        /*只有一个字符串时，它本身就是公共前缀*/
        if (strs.length == 1) {
            return strs[0];
        }
        /*构建Trie树*/
        Trie trie = new Trie();
        for (String str : strs) {
            /*将所有字符串插入Trie树中*/
            trie.insert(str);
        }
        /*查找最长公共前缀*/
        return trie.searchLongestPrefix(strs[0]);
    }

    class TrieNode {
        /**
         * 存储节点的数组
         */
        private final TrieNode[] links;
        /**
         * 标记该节点是否是字符串的结尾
         */
        private boolean isEnd;

        /**
         * 初始化节点数组
         */
        public TrieNode() {
            links = new TrieNode[26];
        }

        /**
         * 判断节点数组中是否包含指定字符的节点
         */
        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        /**
         * 获取指定字符的节点
         */
        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        /**
         * 添加节点
         */
        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        /**
         * 标记该节点是字符串的结尾
         */
        public void setEnd() {
            isEnd = true;
        }

        /**
         * 判断该节点是否是字符串的结尾
         */
        public boolean isEnd() {
            return isEnd;
        }
    }

    class Trie {
        /**
         * 根节点
         */
        private final TrieNode root;

        /**
         * 初始化根节点
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * 插入字符串到Trie树中
         */
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (!node.containsKey(c)) {
                    /*如果不存在该字符节点，就添加一个新节点*/
                    node.put(c, new TrieNode());
                }
                /*移动到下一个节点*/
                node = node.get(c);
            }
            /*标记该节点是字符串的结尾*/
            node.setEnd();
        }

        /**
         * 查找最长公共前缀
         */
        public String searchLongestPrefix(String word) {
            TrieNode node = root;
            StringBuilder prefix = new StringBuilder();
            for (char c : word.toCharArray()) {
                /*当前字符的节点存在且有多个子节点或是字符串的结尾*/
                if (node.containsKey(c) && (node.isEnd() || nodeHasMultipleChildren(node))) {
                    /*移动到下一个节点*/
                    node = node.get(c);
                    /*添加字符到前缀中*/
                    prefix.append(c);
                } else {
                    /*没有匹配上*/
                    break;
                }
            }
            return prefix.toString();
        }

        /**
         * 判断节点是否有多个子节点
         */
        private boolean nodeHasMultipleChildren(TrieNode node) {
            int count = 0;
            for (int i = 0; i < node.links.length; i++) {
                if (node.links[i] != null) {
                    count++;
                    if (count > 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs1));

        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs2));
    }

}
