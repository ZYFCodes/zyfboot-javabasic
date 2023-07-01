package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 给定一棵二叉树和其中的一个节点，请找出中序遍历顺序的下一个节点并返回。
 * 注意，树中的节点不仅包含左右子节点，同时包含指向父节点的指针。
 * @date 2023/6/8  23:30
 */
public class NextTreeNode {
    /**
     * 根据中序遍历的顺序，下一个节点有以下几种情况：
     * 1.	如果当前节点有右子树，则下一个节点是右子树的最左节点；
     * 2.	如果当前节点没有右子树，但是它是其父节点的左子节点，则下一个节点是其父节点；
     * 3.	如果当前节点没有右子树，且它是其父节点的右子节点，则需要沿着父节点的指针一直向上找，
     * 直到找到一个节点是其父节点的左子节点，那么这个父节点就是下一个节点。
     */
    public TreeNode getNext(TreeNode pNode) {
        if (pNode == null) {
            return null;
        }

        // 如果当前节点有右子树，则下一个节点是右子树的最左节点
        if (pNode.right != null) {
            TreeNode node = pNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // 如果当前节点没有右子树，且它是其父节点的左子节点，则下一个节点是其父节点
        if (pNode.parent != null && pNode.parent.left == pNode) {
            return pNode.parent;
        }

        // 如果当前节点没有右子树，且它是其父节点的右子节点，则需要沿着父节点的指针向上找
        // 直到找到一个节点是其父节点的左子节点，那么这个父节点就是下一个节点
        TreeNode parent = pNode.parent;
        while (parent != null && parent.right == pNode) {
            pNode = parent;
            parent = pNode.parent;
        }
        return parent;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        NextTreeNode solution = new NextTreeNode();

        // 构建二叉树
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        root.left = node2;
        root.right = node3;
        node2.parent = root;
        node3.parent = root;

        node2.left = node4;
        node2.right = node5;
        node4.parent = node2;
        node5.parent = node2;

        node3.left = node6;
        node3.right = node7;
        node6.parent = node3;
        node7.parent = node3;

        TreeNode next = solution.getNext(node5);
        if (next != null) {
            // 打印结果
            System.out.println(next.val);
        } else {
            System.out.println("null");
        }
    }
}
