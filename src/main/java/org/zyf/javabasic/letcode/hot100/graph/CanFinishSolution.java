package org.zyf.javabasic.letcode.hot100.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 课程表（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:01
 **/
public class CanFinishSolution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 创建图的邻接表和入度数组
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // 构建图和入度数组
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int preReq = prereq[1];
            graph[preReq].add(course);
            inDegree[course]++;
        }

        // 使用队列进行拓扑排序
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0; // 记录处理的节点数

        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int nextCourse : graph[course]) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 如果处理的节点数等于课程总数，则可以完成所有课程
        return count == numCourses;
    }

    public static void main(String[] args) {
        CanFinishSolution solution = new CanFinishSolution();

        // 示例 1
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(solution.canFinish(numCourses1, prerequisites1)); // 输出: true

        // 示例 2
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println(solution.canFinish(numCourses2, prerequisites2)); // 输出: false
    }
}
