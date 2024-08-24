package org.zyf.javabasic.letcode.featured75.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 叶子相似的树
 * @author: zhangyanfeng
 * @create: 2024-08-24 10:05
 **/
public class LeafSimilar {
    // 获取叶节点值的辅助函数
    private void getLeafValues(TreeNode node, List<Integer> leafValues) {
        if (node == null) {
            return;
        }
        // 如果是叶节点，添加到结果列表
        if (node.left == null && node.right == null) {
            leafValues.add(node.val);
            return;
        }
        // 递归遍历左右子树
        getLeafValues(node.left, leafValues);
        getLeafValues(node.right, leafValues);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // 存储两棵树的叶节点值
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();

        // 获取两棵树的叶节点值
        getLeafValues(root1, leaves1);
        getLeafValues(root2, leaves2);

        // 比较两个叶节点值序列
        return leaves1.equals(leaves2);
    }

    public static void main(String[] args) {
        // 示例 1
        TreeNode root1 = new TreeNode(3,
                new TreeNode(5,
                        new TreeNode(6),
                        new TreeNode(2,
                                new TreeNode(7),
                                new TreeNode(4))
                ),
                new TreeNode(1,
                        new TreeNode(9),
                        new TreeNode(8))
        );
        TreeNode root2 = new TreeNode(3,
                new TreeNode(5,
                        new TreeNode(6),
                        new TreeNode(7)),
                new TreeNode(1,
                        new TreeNode(4),
                        new TreeNode(2,
                                new TreeNode(9),
                                new TreeNode(8)))
        );
        LeafSimilar solution = new LeafSimilar();
        System.out.println(solution.leafSimilar(root1, root2)); // 输出应为 true

        // 示例 2
        TreeNode root3 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3)
        );
        TreeNode root4 = new TreeNode(1,
                new TreeNode(3),
                new TreeNode(2)
        );
        System.out.println(solution.leafSimilar(root3, root4)); // 输出应为 false
    }
}
