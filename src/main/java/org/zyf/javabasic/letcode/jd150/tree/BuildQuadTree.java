package org.zyf.javabasic.letcode.jd150.tree;

/**
 * @program: zyfboot-javabasic
 * @description: 建立四叉树
 * @author: zhangyanfeng
 * @create: 2024-08-25 18:23
 **/
public class BuildQuadTree {
    public Node construct(int[][] grid) {
        return buildQuadTree(grid, 0, 0, grid.length);
    }

    private Node buildQuadTree(int[][] grid, int row, int col, int size) {
        // 创建当前节点
        Node node = new Node();

        // 检查当前区域是否为一个叶子节点
        if (isUniform(grid, row, col, size)) {
            node.isLeaf = true;
            node.val = grid[row][col] == 1;
            return node;
        }

        // 当前区域不是一个叶子节点，分为四个子区域
        node.isLeaf = false;
        int halfSize = size / 2;
        node.topLeft = buildQuadTree(grid, row, col, halfSize);
        node.topRight = buildQuadTree(grid, row, col + halfSize, halfSize);
        node.bottomLeft = buildQuadTree(grid, row + halfSize, col, halfSize);
        node.bottomRight = buildQuadTree(grid, row + halfSize, col + halfSize, halfSize);

        return node;
    }

    private boolean isUniform(int[][] grid, int row, int col, int size) {
        int firstValue = grid[row][col];
        for (int r = row; r < row + size; r++) {
            for (int c = col; c < col + size; c++) {
                if (grid[r][c] != firstValue) {
                    return false;
                }
            }
        }
        return true;
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }
    }
}
