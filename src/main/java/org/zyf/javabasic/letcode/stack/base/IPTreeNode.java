package org.zyf.javabasic.letcode.stack.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/6/11  23:54
 */
public class IPTreeNode {
    public String ip1;
    public String ip2;
    public IPTreeNode left;
    public IPTreeNode right;

    public IPTreeNode(String ip1, String ip2) {
        this.ip1 = ip1;
        this.ip2 = ip2;
    }
}
