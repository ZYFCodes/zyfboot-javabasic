package org.zyf.javabasic.rbtest;

/**
 * @program: zyfboot-javabasic
 * @description:
 * @author: zhangyanfeng
 * @create: 2023-08-19 13:10
 **/
public class RedBlackTreeDelete {

    private TreeNode root;

    enum Color {
        RED, BLACK
    }

    class TreeNode {
        int key;
        Color color;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int key, Color color) {
            this.key = key;
            this.color = color;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    // 省略其他方法...

    public void delete(int key) {
        TreeNode nodeToDelete = findNodeToDelete(root, key);
        if (nodeToDelete != null) {
            deleteNode(nodeToDelete);
        }
    }

    private void deleteNode(TreeNode nodeToDelete) {
        TreeNode replacementNode = (nodeToDelete.left != null && nodeToDelete.right != null)
                ? findSuccessor(nodeToDelete.right)
                : nodeToDelete;

        TreeNode childNode = (replacementNode.left != null) ? replacementNode.left : replacementNode.right;

        if (childNode != null) {
            childNode.parent = replacementNode.parent;
        }

        if (replacementNode.parent == null) {
            root = childNode;
        } else if (replacementNode == replacementNode.parent.left) {
            replacementNode.parent.left = childNode;
        } else {
            replacementNode.parent.right = childNode;
        }

        if (replacementNode != nodeToDelete) {
            nodeToDelete.key = replacementNode.key; // 将替代节点的值赋给被删除节点
        }

        if (replacementNode.color == Color.BLACK) {
            deleteFixup(childNode, replacementNode.parent); // 进行颜色调整和可能的旋转操作
        }
    }

    // 寻找要删除的节点
    private TreeNode findNodeToDelete(TreeNode current, int key) {
        if (current == null || current.key == key) {
            return current;
        }
        if (key < current.key) {
            return findNodeToDelete(current.left, key);
        }
        return findNodeToDelete(current.right, key);
    }

    // 寻找后继节点
    private TreeNode findSuccessor(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    private void deleteFixup(TreeNode x, TreeNode xParent) {
        while (x != root && (x == null || x.color == Color.BLACK)) {
            if (x == xParent.left) {
                TreeNode sibling = xParent.right;

                if (sibling.color == Color.RED) {
                    // 情况 1：兄弟节点为红色
                    sibling.color = Color.BLACK;
                    xParent.color = Color.RED;
                    leftRotate(xParent);
                    sibling = xParent.right;
                }

                if ((sibling.left == null || sibling.left.color == Color.BLACK) &&
                        (sibling.right == null || sibling.right.color == Color.BLACK)) {
                    // 情况 2：兄弟节点和其子节点都为黑色
                    sibling.color = Color.RED;
                    x = xParent;
                    xParent = xParent.parent;
                } else {
                    if (sibling.right == null || sibling.right.color == Color.BLACK) {
                        // 情况 3：兄弟节点是黑色，且兄弟节点的左子节点是红色
                        if (sibling.left != null) {
                            sibling.left.color = Color.BLACK;
                        }
                        sibling.color = Color.RED;
                        rightRotate(sibling);
                        sibling = xParent.right;
                    }

                    // 情况 4：兄弟节点是黑色，且兄弟节点的右子节点是红色
                    sibling.color = xParent.color;
                    xParent.color = Color.BLACK;
                    if (sibling.right != null) {
                        sibling.right.color = Color.BLACK;
                    }
                    leftRotate(xParent);
                    x = root; // 完成调整，退出循环
                }
            } else {
                // 同上，但是左右对调
            }
        }

        if (x != null) {
            x.color = Color.BLACK; // 将 x 染黑
        }
    }

    private void leftRotate(TreeNode x) {
        TreeNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(TreeNode y) {
        TreeNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }
        x.right = y;
        y.parent = x;
    }
}
