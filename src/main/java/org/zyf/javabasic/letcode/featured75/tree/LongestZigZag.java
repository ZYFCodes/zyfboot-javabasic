package org.zyf.javabasic.letcode.featured75.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 二叉树中的最长交错路径
 * @author: zhangyanfeng
 * @create: 2024-08-24 10:21
 **/
public class LongestZigZag {
    // f[u] 记录从节点 u 向左的最长交错路径长度
    // g[u] 记录从节点 u 向右的最长交错路径长度
    Map<TreeNode, Integer> f = new HashMap<>();
    Map<TreeNode, Integer> g = new HashMap<>();
    // 队列用于广度优先遍历
    Queue<TreeNode[]> q = new LinkedList<>();

    public int longestZigZag(TreeNode root) {
        // 初始化树的状态
        dp(root);
        int maxAns = 0;
        // 遍历所有节点，找到最长交错路径
        for (TreeNode u : f.keySet()) {
            maxAns = Math.max(maxAns, Math.max(f.get(u), g.get(u)));
        }
        return maxAns;
    }

    public void dp(TreeNode o) {
        // 初始化状态
        f.put(o, 0);
        g.put(o, 0);
        // 根节点入队
        q.offer(new TreeNode[]{o, null});

        while (!q.isEmpty()) {
            TreeNode[] y = q.poll();
            TreeNode u = y[0], x = y[1];
            // 初始化当前节点的状态
            f.put(u, 0);
            g.put(u, 0);

            // 更新状态
            if (x != null) {
                if (x.left == u) {
                    // 如果当前节点是其父节点的左子节点，更新 f[u]
                    f.put(u, g.get(x) + 1);
                }
                if (x.right == u) {
                    // 如果当前节点是其父节点的右子节点，更新 g[u]
                    g.put(u, f.get(x) + 1);
                }
            }
            // 左子节点入队
            if (u.left != null) {
                q.offer(new TreeNode[]{u.left, u});
            }
            // 右子节点入队
            if (u.right != null) {
                q.offer(new TreeNode[]{u.right, u});
            }
        }
    }

    public static void main(String[] args) {
        // 示例 1
        TreeNode root1 = new TreeNode(1,
                null,
                new TreeNode(1,
                        new TreeNode(1,
                                new TreeNode(1),
                                new TreeNode(1)),
                        null)
        );
        LongestZigZag solution = new LongestZigZag();
        System.out.println(solution.longestZigZag(root1)); // 输出应为 3
    }
}
