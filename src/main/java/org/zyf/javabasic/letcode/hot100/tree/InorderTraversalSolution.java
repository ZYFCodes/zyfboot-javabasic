package org.zyf.javabasic.letcode.hot100.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树的中序遍历（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 10:45
 **/
public class InorderTraversalSolution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // 先遍历左子树，将节点入栈
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // 弹出栈顶元素并访问
            current = stack.pop();
            result.add(current.val);

            // 处理右子树
            current = current.right;
        }

        return result;
    }

    // 测试主函数
    public static void main(String[] args) {
        // 构造测试用例
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        // 创建 Solution 实例并进行测试
        InorderTraversalSolution solution = new InorderTraversalSolution();
        List<Integer> result = solution.inorderTraversal(root);

        // 打印结果
        System.out.println(result);  // 输出应为 [1, 3, 2]
    }
}
