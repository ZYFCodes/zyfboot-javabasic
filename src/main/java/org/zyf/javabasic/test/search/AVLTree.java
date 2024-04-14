package org.zyf.javabasic.test.search;

/**
 * @program: zyfboot-javabasic
 * @description: 表示 AVL 树（平衡二叉搜索树）及其基本操作，包括插入、删除和查找
 * @author: zhangyanfeng
 * @create: 2024-04-14 12:22
 **/
public class AVLTree {
    TreeNode root;

    // 获取节点高度
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 更新节点高度
    private void updateHeight(TreeNode node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    // 获取平衡因子
    private int getBalance(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // 右旋转
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        // 执行旋转
        x.right = y;
        y.left = T2;

        // 更新节点高度
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // 左旋转
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        // 执行旋转
        y.left = x;
        x.right = T2;

        // 更新节点高度
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // 插入节点
    public TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            return new TreeNode(val);
        }

        if (val < node.val) {
            node.left = insert(node.left, val);
        } else if (val > node.val) {
            node.right = insert(node.right, val);
        } else {
            return node; // 重复值不插入
        }

        // 更新节点高度
        updateHeight(node);

        // 获取平衡因子
        int balance = getBalance(node);

        // 左旋转
        if (balance > 1 && val < node.left.val) {
            return rightRotate(node);
        }

        // 右旋转
        if (balance < -1 && val > node.right.val) {
            return leftRotate(node);
        }

        // 左右旋转
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 右左旋转
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 中序遍历打印树
    public void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.val + " ");
            inOrderTraversal(node.right);
        }
    }

    // 查找节点
    public boolean search(TreeNode node, int val) {
        if (node == null) {
            return false;
        }

        if (val == node.val) {
            return true;
        } else if (val < node.val) {
            return search(node.left, val);
        } else {
            return search(node.right, val);
        }
    }

    // AVL 树节点
    class TreeNode {
        int val;
        int height;
        TreeNode left;
        TreeNode right;

        // 构造函数
        public TreeNode(int val) {
            this.val = val;
            this.height = 1; // 初始高度为 1
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();

        // 插入节点
        avlTree.root = avlTree.insert(avlTree.root, 10);
        avlTree.root = avlTree.insert(avlTree.root, 20);
        avlTree.root = avlTree.insert(avlTree.root, 30);
        avlTree.root = avlTree.insert(avlTree.root, 40);
        avlTree.root = avlTree.insert(avlTree.root, 50);
        avlTree.root = avlTree.insert(avlTree.root, 25);

        // 中序遍历打印树
        System.out.println("AVL 树中序遍历结果：");
        avlTree.inOrderTraversal(avlTree.root);

        // 查找节点
        int target = 30;
        if (avlTree.search(avlTree.root, target)) {
            System.out.println("\n节点 " + target + " 存在于 AVL 树中。");
        } else {
            System.out.println("\n节点 " + target + " 不存在于 AVL 树中。");
        }
    }
}
