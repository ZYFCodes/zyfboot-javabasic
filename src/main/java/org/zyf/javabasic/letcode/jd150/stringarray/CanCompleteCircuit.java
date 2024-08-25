package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 加油站
 * @author: zhangyanfeng
 * @create: 2024-08-25 09:44
 **/
public class CanCompleteCircuit {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalGas = 0;  // 总油量
        int totalCost = 0; // 总消耗
        int start = 0;     // 起点
        int tank = 0;      // 当前油箱剩余油量

        // 遍历所有加油站
        for (int i = 0; i < gas.length; i++) {
            totalGas += gas[i];
            totalCost += cost[i];
            tank += gas[i] - cost[i];

            // 如果当前油量不足以到达下一个加油站
            if (tank < 0) {
                // 将起点设为下一个加油站
                start = i + 1;
                // 重置油箱
                tank = 0;
            }
        }

        // 判断是否可以绕环一圈
        return totalGas >= totalCost ? start : -1;
    }

    public static void main(String[] args) {
        CanCompleteCircuit solution = new CanCompleteCircuit();

        // 测试用例 1
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println(solution.canCompleteCircuit(gas1, cost1)); // 输出: 3

        // 测试用例 2
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println(solution.canCompleteCircuit(gas2, cost2)); // 输出: -1
    }
}
