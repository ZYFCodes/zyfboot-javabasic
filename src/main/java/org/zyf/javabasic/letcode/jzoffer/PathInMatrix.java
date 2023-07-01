package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中的上、下、左、右四个相邻格子之间移动。
 * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再次进入该格子。
 * 例如，在下面的3×4的矩阵中包含一条字符串 “bfce” 的路径。
 * a b t g
 * c f c s
 * j d e h
 * @date 2023/6/6  22:11
 */
public class PathInMatrix {
    /**
     * 可以使用深度优先搜索（DFS）来解决这个问题。具体步骤如下：
     * 1.	遍历矩阵中的每个格子，将每个格子作为路径的起点。
     * 2.	在每个起点开始进行DFS搜索，判断是否存在一条路径可以匹配目标字符串。
     * •	首先判断当前起点是否越界或者与目标字符串的第一个字符不匹配，若不匹配则返回false。
     * •	若当前起点与目标字符串的第一个字符匹配，则开始进行DFS搜索。
     * •	在DFS搜索过程中，分别向上、下、左、右四个方向进行递归搜索，直到匹配完整个目标字符串或者无法继续匹配。
     * •	在递归搜索过程中，需要注意标记已访问的格子，以避免路径重复进入。
     * •	如果在搜索过程中匹配到了完整的目标字符串，则返回true；否则，返回false。
     * 3.	如果在遍历完所有起点后仍未找到匹配的路径，则返回false。
     */
    public static boolean hasPath(char[][] matrix, String target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0 || target == null || target.length() == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(matrix, i, j, target, 0, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(char[][] matrix, int row, int col, String target, int index, boolean[][] visited) {
        if (index == target.length()) {
            // 匹配成功
            return true;
        }

        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length ||
                matrix[row][col] != target.charAt(index) || visited[row][col]) {
            // 越界、字符不匹配或已访问过的情况
            return false;
        }

        // 标记当前格子为已访问
        visited[row][col] = true;

        // 在上、下、左、右四个方向递归搜索
        boolean found = dfs(matrix, row - 1, col, target, index + 1, visited) ||
                dfs(matrix, row + 1, col, target, index + 1, visited) ||
                dfs(matrix, row, col - 1, target, index + 1, visited) ||
                dfs(matrix, row, col + 1, target, index + 1, visited);

        // 恢复当前格子为未访问，方便其他路径使用
        visited[row][col] = false;

        return found;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'a', 'b', 't', 'g'},
                {'c', 'f', 'c', 's'},
                {'j', 'd', 'e', 'h'}
        };
        String target = "bfce";
        boolean result = hasPath(matrix, target);
        // 输出: true
        System.out.println(result);
    }
}
