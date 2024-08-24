package org.zyf.javabasic.letcode.featured75.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 删除二叉搜索树中的节点
 * @author: zhangyanfeng
 * @create: 2024-08-24 11:10
 **/
public class DeleteNode {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null; // 空树，直接返回 null

        // 找到要删除的节点
        if (key < root.val) {
            root.left = deleteNode(root.left, key); // 在左子树中递归删除
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key); // 在右子树中递归删除
        } else {
            // 找到要删除的节点
            if (root.left == null) {
                return root.right; // 节点没有左子树，返回右子树
            } else if (root.right == null) {
                return root.left; // 节点没有右子树，返回左子树
            }
            // 节点有两个子树
            TreeNode minNode = getMin(root.right); // 找到右子树中的最小节点
            root.val = minNode.val; // 用最小节点的值替换当前节点的值
            root.right = deleteNode(root.right, minNode.val); // 删除右子树中的最小节点
        }
        return root; // 返回更新后的根节点
    }

    // 找到以 root 为根的树中的最小节点
    private TreeNode getMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    // 主函数用于测试
    public static void main(String[] args) {
        DeleteNode solution = new DeleteNode();

        // 示例 1
        TreeNode root1 = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2),
                        new TreeNode(4)
                ),
                new TreeNode(6, null, new TreeNode(7))
        );
        TreeNode result1 = solution.deleteNode(root1, 3);
        printTree(result1); // 输出: [5, 4, 6, 2, null, null, 7]

        // 示例 2
        TreeNode root2 = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2),
                        new TreeNode(4)
                ),
                new TreeNode(6, null, new TreeNode(7))
        );
        TreeNode result2 = solution.deleteNode(root2, 0);
        printTree(result2); // 输出: [5, 3, 6, 2, 4, null, 7]

        // 示例 3
        TreeNode root3 = null;
        TreeNode result3 = solution.deleteNode(root3, 0);
        printTree(result3); // 输出: []
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
