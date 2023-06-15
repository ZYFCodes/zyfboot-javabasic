package org.zyf.javabasic.letcode.stack;

import org.zyf.javabasic.letcode.stack.base.IPTreeNode;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 给定一个二叉树，每个节点包含一个 IP 段的范围，其中 ip1 和 ip2 分别表示左子树和右子树的 IP 范围。
 * 现在给定一个 IP 地址，判断该 IP 是否在任意节点的 IP 范围内。
 * @date 2023/6/11  00:09
 */
public class IPAddressRange {
    public boolean isIPInRange(IPTreeNode root, String ip) {
        if (root == null || ip == null) {
            return false;
        }

        Stack<IPTreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            IPTreeNode node = stack.pop();

            if (isIPInRange(ip, node.ip1, node.ip2)) {
                return true;
            }

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        return false;
    }

    private boolean isIPInRange(String ip, String startIP, String endIP) {
        long ipValue = ipToLong(ip);
        long startValue = ipToLong(startIP);
        long endValue = ipToLong(endIP);

        return ipValue >= startValue && ipValue <= endValue;
    }

    private long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        long result = 0;

        for (int i = 0; i < 4; i++) {
            result = result * 256 + Long.parseLong(parts[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        IPTreeNode root = new IPTreeNode("192.168.0.1", "192.168.0.100");
        root.left = new IPTreeNode("10.0.0.1", "10.0.0.10");
        root.right = new IPTreeNode("172.16.0.1", "172.16.0.50");

        IPAddressRange solution = new IPAddressRange();
        String ip = "10.0.0.5";
        boolean isInRange = solution.isIPInRange(root, ip);
        // 输出 10.0.0.5 is in range: true
        System.out.println(ip + " is in range: " + isInRange);
    }
}
