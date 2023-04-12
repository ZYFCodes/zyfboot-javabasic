package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * @date 2023/4/12  23:48
 */
public class MirrorTree {

    /**
     * 递归交换每个节点的左右子树
     * <p>
     * 递归函数 mirrorTree 接收一个树节点 root 作为参数，返回将该节点为根的子树转化为镜像后的根节点。
     * 首先判断根节点是否为空，若为空则直接返回 null。
     * 否则，我们先将根节点的左右子树分别保存在 left 和 right 中。
     * 然后将根节点的左子树转化为镜像后作为新的右子树，右子树转化为镜像后作为新的左子树。
     * 最后返回根节点即可。
     * 该算法的时间复杂度和空间复杂度均为 $O(n)$，其中 $n$ 是树中节点的个数。
     *
     * @param root 给定的树的根节点
     * @return 返回镜像后的树的根节点
     */
    public TreeNode mirrorTree(TreeNode root) {
        /*如果根节点为空，直接返回 null*/
        if (root == null) {
            return null;
        }
        /*保存当前节点的左子树，作为递归后的右子树*/
        TreeNode left = root.left;
        /*递归交换当前节点的左右子树*/
        root.left = mirrorTree(root.right);
        root.right = mirrorTree(left);
        /*返回镜像后的根节点*/
        return root;
    }

    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        /*创建一个栈，用于存放待翻转的节点*/
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            /*取出栈顶元素*/
            TreeNode node = stack.pop();
            /*翻转当前节点的左右子树*/
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            /*将左右子节点非空的节点入栈*/
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }


    public static void main(String[] args) {
        Integer[] nums = {4, 2, 7, 1, 3, 6, 9};
        TreeNode root = createTree(nums);

        TreeNode newRoot = new MirrorTree().mirrorTree(root);

        /*验证镜像是否正确*/
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(newRoot);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                System.out.println("Test failed.");
                return;
            }
            queue.offer(node1.left);
            queue.offer(node2.left);
            queue.offer(node1.right);
            queue.offer(node2.right);
        }
        System.out.println("Test passed.");
    }

    public static TreeNode createTree(Integer[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nums[0]);
        queue.offer(root);
        int i = 1;
        while (i < nums.length) {
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }
            Integer leftVal = nums[i++];
            Integer rightVal = i < nums.length ? nums[i++] : null;
            node.left = leftVal != null ? new TreeNode(leftVal) : null;
            node.right = rightVal != null ? new TreeNode(rightVal) : null;
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return root;
    }
}
