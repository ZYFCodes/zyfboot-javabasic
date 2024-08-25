package org.zyf.javabasic.letcode.jd150.graph;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 最小基因变化
 * @author: zhangyanfeng
 * @create: 2024-08-25 17:42
 **/
public class MinMutation {
    public int minMutation(String start, String end, String[] bank) {
        // 将基因库转为集合以便快速查找
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // 如果目标基因序列不在基因库中，则直接返回 -1
        if (!bankSet.contains(end)) {
            return -1;
        }

        // 初始化 BFS 队列，起始基因序列和变化次数为 0
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int mutations = 0;

        // 进行 BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                // 如果当前基因序列等于目标基因序列，则返回变化次数
                if (curr.equals(end)) {
                    return mutations;
                }

                // 尝试每个可能的变化
                for (int j = 0; j < curr.length(); j++) {
                    char[] chars = curr.toCharArray();
                    for (char c = 'A'; c <= 'Z'; c++) {
                        if (chars[j] == c) continue; // 如果字符相同则跳过
                        chars[j] = c;
                        String next = new String(chars);
                        // 如果变换后的基因序列在基因库中，且未被访问过
                        if (bankSet.contains(next)) {
                            queue.offer(next);
                            bankSet.remove(next); // 标记为已访问
                        }
                    }
                }
            }
            mutations++; // 增加变化次数
        }

        // 如果无法到达目标基因序列，返回 -1
        return -1;
    }
}
