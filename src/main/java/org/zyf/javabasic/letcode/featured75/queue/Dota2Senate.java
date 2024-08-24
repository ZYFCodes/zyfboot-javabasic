package org.zyf.javabasic.letcode.featured75.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: Dota2 参议院
 * @author: zhangyanfeng
 * @create: 2024-08-24 09:30
 **/
public class Dota2Senate {
    public String predictPartyVictory(String senate) {
        // 创建两个队列分别存储 Radiant 和 Dire 阵营参议员的索引
        Queue<Integer> radiant = new LinkedList<>();
        Queue<Integer> dire = new LinkedList<>();

        int n = senate.length();

        // 将每个参议员的索引分别加入对应的队列
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                radiant.add(i);
            } else {
                dire.add(i);
            }
        }

        // 模拟投票过程
        while (!radiant.isEmpty() && !dire.isEmpty()) {
            int rIndex = radiant.poll();
            int dIndex = dire.poll();

            // 谁的索引小谁就可以禁用对方阵营的参议员，并将自己放回队列
            if (rIndex < dIndex) {
                radiant.add(rIndex + n);  // 将索引加上 n，表示下一轮的顺序
            } else {
                dire.add(dIndex + n);     // 将索引加上 n，表示下一轮的顺序
            }
        }

        // 如果 Radiant 阵营的队列为空，Dire 胜利；否则 Radiant 胜利
        return radiant.isEmpty() ? "Dire" : "Radiant";
    }

    public static void main(String[] args) {
        Dota2Senate solution = new Dota2Senate();

        // 测试用例
        System.out.println(solution.predictPartyVictory("RD"));   // 输出: Radiant
        System.out.println(solution.predictPartyVictory("RDD"));  // 输出: Dire
    }
}
