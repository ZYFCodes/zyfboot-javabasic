package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 输入一个整数数组 postorder，表示二叉搜索树的后序遍历序列。
 * 判断该序列是否是某个二叉搜索树的后序遍历结果。
 * @date 2023/6/10  22:09
 */
public class VerifyPostorder {
    /**
     * 根据二叉搜索树的性质，后序遍历的最后一个元素是根节点，而前面的元素可以分为左子树和右子树两部分.
     * 左子树的所有元素都小于根节点的值，右子树的所有元素都大于根节点的值。
     * 首先，找到根节点，即后序遍历序列的最后一个元素。
     * 然后，遍历序列，找到第一个大于根节点的元素，
     * 该元素之前的部分即为左子树的后序遍历序列，该元素之后的部分即为右子树的后序遍历序列。
     * 接下来，递归地判断左子树和右子树是否是二叉搜索树的后序遍历序列。
     * 若左子树和右子树都是二叉搜索树的后序遍历序列，则整个序列是二叉搜索树的后序遍历序列。
     * <p>
     * 递归的终止条件为序列为空或只有一个元素，此时即为二叉搜索树的后序遍历序列。
     * 【时间复杂度】假设序列的长度为 n，递归过程中需要遍历整个序列，因此时间复杂度为 O(n)。
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return true;
        }

        return verify(postorder, 0, postorder.length - 1);
    }

    private boolean verify(int[] postorder, int start, int end) {
        // 终止条件：当start大于等于end时，表示只有一个节点或没有节点，满足条件
        if (start >= end) {
            return true;
        }

        // 根节点的值
        int root = postorder[end];
        // 左子树的最后一个节点
        int leftEnd = start;

        // 找到左子树的边界，左子树的所有节点值小于根节点的值
        while (postorder[leftEnd] < root) {
            leftEnd++;
        }

        // 遍历右子树，右子树的所有节点值大于根节点的值
        for (int i = leftEnd; i < end; i++) {
            if (postorder[i] < root) {
                // 右子树中存在小于根节点的值，不满足二叉搜索树的性质
                return false;
            }
        }

        // 递归判断左子树和右子树是否是二叉搜索树的后序遍历序列
        return verify(postorder, start, leftEnd - 1) && verify(postorder, leftEnd, end - 1);
    }

    public static void main(String[] args) {
        VerifyPostorder solution = new VerifyPostorder();

        // 测试用例1: 是二叉搜索树的后序遍历序列
        int[] postorder1 = {1, 3, 2, 6, 5};
        boolean result1 = solution.verifyPostorder(postorder1);
        // 输出: true
        System.out.println(result1);

        // 测试用例2: 不是二叉搜索树的后序遍历序列
        int[] postorder2 = {1, 6, 3, 2, 5};
        boolean result2 = solution.verifyPostorder(postorder2);
        // 输出: false
        System.out.println(result2);
    }
}
