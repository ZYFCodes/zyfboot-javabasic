package org.zyf.javabasic.letcode.featured75.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉搜索树中的搜索
 * @author: zhangyanfeng
 * @create: 2024-08-24 11:04
 **/
public class SearchBST {
    public TreeNode searchBST(TreeNode root, int val) {
        // 从根节点开始查找
        if (root == null) return null; // 根节点为空，返回 null

        if (root.val == val) {
            return root; // 找到值为 val 的节点，返回该节点及其子树
        } else if (val < root.val) {
            return searchBST(root.left, val); // 在左子树中继续查找
        } else {
            return searchBST(root.right, val); // 在右子树中继续查找
        }
    }

    // 主函数用于测试
    public static void main(String[] args) {
        SearchBST solution = new SearchBST();

        // 示例 1
        TreeNode root1 = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1),
                        new TreeNode(3)
                ),
                new TreeNode(7)
        );
        TreeNode result1 = solution.searchBST(root1, 2);
        printTree(result1); // 输出: 2 1 3

        // 示例 2
        TreeNode root2 = new TreeNode(4,
                new TreeNode(2,
                        new TreeNode(1),
                        new TreeNode(3)
                ),
                new TreeNode(7)
        );
        TreeNode result2 = solution.searchBST(root2, 5);
        printTree(result2); // 输出: []

        // 自定义测试用例
        TreeNode root3 = new TreeNode(10,
                new TreeNode(5,
                        new TreeNode(3),
                        new TreeNode(7)
                ),
                new TreeNode(15)
        );
        TreeNode result3 = solution.searchBST(root3, 7);
        printTree(result3); // 输出: 7
    }

    // 打印树节点的辅助函数
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("[]");
            return;
        }
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        System.out.println(result);
    }
}
