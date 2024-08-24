package org.zyf.javabasic.letcode.featured75.graph;

/**
 * @program: zyfboot-javabasic
 * @description: 省份数量
 * @author: zhangyanfeng
 * @create: 2024-08-24 11:23
 **/
public class FindCircleNum {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n]; // 记录每个城市是否被访问过
        int provinces = 0; // 省份计数

        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // 如果城市 i 未被访问
                dfs(isConnected, visited, i); // 使用 DFS 遍历所有与城市 i 相连的城市
                provinces++; // 发现一个新的省份
            }
        }

        return provinces;
    }

    // 深度优先搜索
    private void dfs(int[][] isConnected, boolean[] visited, int city) {
        visited[city] = true; // 标记当前城市为已访问

        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[city][i] == 1 && !visited[i]) { // 如果城市 i 与当前城市直接相连且未被访问
                dfs(isConnected, visited, i); // 递归访问城市 i
            }
        }
    }

    // 主函数用于测试
    public static void main(String[] args) {
        FindCircleNum solution = new FindCircleNum();

        // 示例 1
        int[][] isConnected1 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        System.out.println(solution.findCircleNum(isConnected1)); // 输出: 2

        // 示例 2
        int[][] isConnected2 = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        System.out.println(solution.findCircleNum(isConnected2)); // 输出: 3
    }
}
