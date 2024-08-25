package org.zyf.javabasic.letcode.jd150.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉搜索树迭代器
 * @author: zhangyanfeng
 * @create: 2024-08-25 14:24
 **/
public class BSTIterator {
    private Stack<TreeNode> stack;

    // 初始化迭代器，压入左侧路径上的所有节点
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushLeftBranch(root);
    }

    // 检查是否有下一个节点
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // 返回下一个最小的节点值
    public int next() {
        TreeNode node = stack.pop(); // 弹出栈顶节点
        int result = node.val;
        pushLeftBranch(node.right); // 如果有右子树，将右子树的左侧路径压入栈中
        return result;
    }

    // 将从当前节点开始的左侧路径上的所有节点压栈
    private void pushLeftBranch(TreeNode node) {
        while (node != null) {
            stack.push(node); // 压入当前节点
            node = node.left; // 向左移动
        }
    }
}
