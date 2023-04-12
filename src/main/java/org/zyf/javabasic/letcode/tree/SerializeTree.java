package org.zyf.javabasic.letcode.tree;

import org.zyf.javabasic.letcode.tree.base.TreeNode;

/**
 * @author yanfengzhang
 * @description 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * @date 2023/4/12  23:56
 */
public class SerializeTree {

    /*8Encodes a tree to a single string.*/
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val + "," + left + "," + right;
    }

    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        int[] index = {0};
        return deserializeHelper(dataArray, index);
    }

    private TreeNode deserializeHelper(String[] dataArray, int[] index) {
        if (dataArray[index[0]].equals("null")) {
            index[0]++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(dataArray[index[0]]));
        index[0]++;
        root.left = deserializeHelper(dataArray, index);
        root.right = deserializeHelper(dataArray, index);
        return root;
    }

    public static void main(String[] args) {
        /*构造一个二叉树*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        /*序列化二叉树*/
        String serialized = new SerializeTree().serialize(root);
        System.out.println("Serialized Tree: " + serialized);

        /*反序列化二叉树*/
        TreeNode deserialized = new SerializeTree().deserialize(serialized);
        System.out.println("Deserialized Tree: " + new SerializeTree().serialize(deserialized));
    }
}
