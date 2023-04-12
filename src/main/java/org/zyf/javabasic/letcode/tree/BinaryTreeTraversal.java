package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 二叉树遍历：包括前序遍历、中序遍历、后序遍历、层序遍历等
 * @date 2023/4/10  23:25
 */
public class BinaryTreeTraversal {

    /**
     * 前序遍历
     *
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        /*存储遍历结果的列表*/
        List<Integer> res = new ArrayList<>();
        /*如果根节点为空，直接返回空列表*/
        if (root == null) {
            return res;
        }
        /*使用栈来辅助迭代*/
        Stack<TreeNode> stack = new Stack<>();
        /*先将根节点入栈*/
        stack.push(root);
        /*只要栈不为空，就继续遍历*/
        while (!stack.isEmpty()) {
            /*取出栈顶节点*/
            TreeNode node = stack.pop();
            /*将节点的值加入遍历结果列表*/
            res.add(node.val);
            /*如果右子节点不为空，将其入栈*/
            if (node.right != null) {
                stack.push(node.right);
            }
            /*如果左子节点不为空，将其入栈*/
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        /*返回遍历结果*/
        return res;
    }

    /**
     * 中序遍历
     *
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        /*存储遍历结果的列表*/
        List<Integer> res = new ArrayList<>();
        /*使用栈来辅助迭代*/
        Stack<TreeNode> stack = new Stack<>();
        /*从根节点开始遍历*/
        TreeNode cur = root;
        /*只要当前节点不为空或者栈不为空，就继续遍历*/
        while (cur != null || !stack.isEmpty()) {
            /*先遍历左子树*/
            while (cur != null) {
                /*将当前节点入栈*/
                stack.push(cur);
                /*继续遍历左子树*/
                cur = cur.left;
            }
            /*取出栈顶节点，即当前节点*/
            cur = stack.pop();
            /*将节点的值加入遍历结果列表*/
            res.add(cur.val);
            /*继续遍历右子树*/
            cur = cur.right;
        }
        /*返回遍历结果*/
        return res;
    }

    /**
     * 后序遍历
     *
     * @param root 根节点
     * @return 遍历结果
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        /*存储遍历结果的列表*/
        List<Integer> res = new ArrayList<>();
        /*如果根节点为空，直接返回空列表*/
        if (root == null) {
            return res;
        }
        /*使用栈来辅助迭代*/
        Stack<TreeNode> stack = new Stack<>();
        /*先将根节点入栈*/
        stack.push(root);
        /*使用集合来记录已经访问过的节点*/
        Set<TreeNode> visited = new HashSet<>();
        /*只要栈不为空，就继续遍历*/
        while (!stack.isEmpty()) {
            /*取出栈顶节点，但不弹出*/
            TreeNode node = stack.peek();
            /*如果当前节点是叶子节点 or 或者当前节点已经访问过 
            /*or 或者当前节点的右子节点已经访问过 or 或者当前节点只有左子节点且左子节点已经访问过*/
            if ((node.left == null && node.right == null) ||
                    visited.contains(node) ||
                    (node.right != null && visited.contains(node.right)) ||
                    (node.left != null && node.right == null && visited.contains(node.left))) {
                /*将节点的值加入遍历结果列表*/
                res.add(node.val);
                /*将当前节点弹出栈*/
                stack.pop();
                /*标记当前节点已经访问过*/
                visited.add(node);
            }
            /*否则，将当前节点的子节点入栈*/
            else {
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        /*返回遍历结果*/
        return res;
    }

    /**
     * 层序遍历
     *
     * @param root 根节点
     * @return 遍历结果
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        /*存储遍历结果的列表*/
        List<List<Integer>> res = new ArrayList<>();
        /*如果根节点为空，直接返回空列表*/
        if (root == null) {
            return res;
        }
        /*使用队列来辅助遍历*/
        Queue<TreeNode> queue = new LinkedList<>();
        /*先将根节点入队*/
        queue.offer(root);
        /*只要队列不为空，就继续遍历*/
        while (!queue.isEmpty()) {
            /*当前层的节点数*/
            int levelSize = queue.size();
            /*存储当前层的节点值*/
            List<Integer> level = new ArrayList<>();
            /*遍历当前层的所有节点*/
            for (int i = 0; i < levelSize; i++) {
                /*取出队首节点*/
                TreeNode node = queue.poll();
                /*将节点的值加入当前层的节点值列表*/
                level.add(node.val);
                /*如果左子节点不为空，将其入队*/
                if (node.left != null) {
                    queue.offer(node.left);
                }
                /*如果右子节点不为空，将其入队*/
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            /*将当前层的节点值列表加入遍历结果列表*/
            res.add(level);
        }
        /*返回遍历结果*/
        return res;
    }

    public static void main(String[] args) {
        /*创建一个二叉树*/
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        /*层序遍历二叉树*/
        List<List<Integer>> res = new BinaryTreeTraversal().levelOrder(root);

        /*输出遍历结果 [[3], [9, 20], [15, 7]]*/
        System.out.println(res);
    }

}
