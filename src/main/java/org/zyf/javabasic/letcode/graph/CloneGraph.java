package org.zyf.javabasic.letcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 给定一个无向连通图中的一个节点的引用 Node，返回该图的深拷贝（克隆）。
 * 图中的每个节点都包含一个值（int）和一个包含其邻居的列表（list of Node）。
 * 深拷贝的图应该以相同的方式构建，其具有与原始图相同的节点数目，并且可以通过 Node 引用找到对应的新节点。
 * 新图中的每个节点都应该从原始图中的节点复制其值和邻居信息。
 * 注意：
 * * 节点值的范围是整数（int）。
 * * 无向图不包含自环（self-loop），即节点不会直接连接到自身。
 * * 无向图中两个节点之间最多存在一条边，即无向图是简单图（simple graph）。
 * * 在深拷贝的图中，任何两个节点之间都不存在直接的边连接。
 * 例如，给定如下无向图的一个节点引用 1：
 * 1 -- 2
 * |    |
 * 4 -- 3
 * 返回结果应为克隆的无向图的一个节点引用 1'，其结构如下：
 * 1'-- 2'
 * |    |
 * 4'-- 3'
 * 每个节点都具有相同的节点值和邻居列表。
 * @date 2022/6/2  23:36
 */
public class CloneGraph {
    private Map<Node, Node> visited;

    public Node cloneGraph(Node node) {
        visited = new HashMap<>();
        return cloneNode(node);
    }

    private Node cloneNode(Node node) {
        if (node == null) {
            return null;
        }

        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        Node clone = new Node(node.val);
        visited.put(node, clone);

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneNode(neighbor));
        }

        return clone;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        // 创建原始图
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.neighbors.add(node2);
        node1.neighbors.add(node4);

        node2.neighbors.add(node1);
        node2.neighbors.add(node3);

        node3.neighbors.add(node2);
        node3.neighbors.add(node4);

        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        // 克隆图
        CloneGraph cloneGraph = new CloneGraph();
        Node clonedNode = cloneGraph.cloneGraph(node1);

        // 打印原始图和克隆图的节点值和邻居列表
        System.out.println("Original Graph:");
        printGraph(node1);

        System.out.println("Cloned Graph:");
        printGraph(clonedNode);
    }

    private static void printGraph(Node node) {
        if (node == null) {
            return;
        }

        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            System.out.print("Node " + currNode.val + ": [");

            for (int i = 0; i < currNode.neighbors.size(); i++) {
                Node neighbor = currNode.neighbors.get(i);
                System.out.print(neighbor.val);

                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }

                if (i != currNode.neighbors.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println("]");
        }
    }
}
