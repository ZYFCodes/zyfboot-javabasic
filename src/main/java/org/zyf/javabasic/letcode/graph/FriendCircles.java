package org.zyf.javabasic.letcode.graph;

/**
 * @author yanfengzhang
 * @description 给定一个n x n的矩阵M，表示一个社交关系图。M[i][j] = 1表示第i个人和第j个人是直接朋友关系，M[i][j] = 0表示不是直接朋友关系。
 * 你需要计算出总共有多少个朋友圈。
 * 例如，给定下面的矩阵M：
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 该矩阵表示有3个人，第0个人和第1个人是直接朋友，所以他们属于一个朋友圈；第2个人与自己独立，所以属于另一个朋友圈。因此，总共有2个朋友圈。
 * 要求编写一个函数 int findCircleNum(int[][] M)，返回朋友圈的数量。
 * 注意：
 * * n在[1,200]的范围内。
 * * M[i][i] = 1，对角线上的元素表示每个人和自己是直接朋友关系。
 * @date 2021/5/5  20:40
 */
public class FriendCircles {
    /**
     * Friend Circles问题可以使用并查集（Union Find）算法来解决，以下是最优解法的分析：
     * 1. 创建一个大小为n的数组parent，用于记录每个人所属的朋友圈编号。
     * 2. 初始化parent数组，将每个人的朋友圈编号初始化为自身的编号，即parent[i] = i。
     * 3. 遍历矩阵M的每个元素M[i][j]，当M[i][j]为1时，表示第i个人和第j个人是直接朋友关系。
     * 4. 对于每对直接朋友关系的人，使用并查集将他们合并到同一个朋友圈中。
     * 5. 合并操作的实现是通过找到根节点的方式，比较两个人所属朋友圈的根节点，将一个朋友圈的根节点指向另一个朋友圈的根节点。
     * 6. 统计并查集中根节点的数量，即为朋友圈的数量。
     * 使用并查集算法可以快速合并朋友圈，使得查找和合并操作的时间复杂度为O(1)。因此，该解法的时间复杂度为O(n^2)，其中n为人数。
     */
    public int findCircleNum(int[][] M) {
        int n = M.length;
        int[] parent = new int[n];
        int count = n;

        // 初始化parent数组
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 遍历矩阵M，进行合并操作
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 1) {
                    int root1 = findRoot(parent, i);
                    int root2 = findRoot(parent, j);
                    if (root1 != root2) {
                        parent[root1] = root2;
                        count--;
                    }
                }
            }
        }

        return count;
    }

    private int findRoot(int[] parent, int i) {
        while (parent[i] != i) {
            // 路径压缩，将i的父节点直接指向父节点的父节点
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    public static void main(String[] args) {
        int[][] M = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };

        int result = new FriendCircles().findCircleNum(M);
        System.out.println("朋友圈的数量：" + result);
    }
}
