package org.zyf.javabasic.letcode.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。
 * 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程bi 。
 * <p>
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * @date 2023/4/9  15:55
 */
public class CanFinishCourses {

    /**
     * 使用队列实现拓扑排序算法。具体实现方法是：
     * 统计所有节点的入度，将入度为 0 的节点加入队列。
     * 取出队首节点，输出该节点，并将以该节点为起点的所有边移除，更新它们的入度。如果某个节点入度变为 0，则将该节点加入队列。
     * 重复第 2 步，直到队列为空或者无法再取出入度为 0 的节点。
     * 如果最终能够输出所有节点，说明不存在环；否则，说明存在环。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*记录每个节点的入度*/
        int[] inDegree = new int[numCourses];
        for (int[] pre : prerequisites) {
            inDegree[pre[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        /*将入度为 0 的节点加入队列*/
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        /*取出队首节点，输出该节点，并将以该节点为起点的所有边移除，更新它们的入度*/
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;
            for (int[] pre : prerequisites) {
                if (pre[1] == curr) {
                    inDegree[pre[0]]--;
                    if (inDegree[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }

        /*如果最终能够输出所有节点，说明不存在环；否则，说明存在环*/
        return count == numCourses;
    }
}
