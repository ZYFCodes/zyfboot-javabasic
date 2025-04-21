package org.zyf.javabasic.test.wzzz;

import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: 随机休眠时间
 * @author: zhangyanfeng
 * @create: 2024-11-20 22:13
 **/
public class SleepTimeGenerator {
    // 方法：生成休眠时间
    public static int generateSleepTime() {
        // 定义时间段和对应的权重
        int[] timeRanges = {30000, 40000, 50000}; // 时间起点：22秒、24秒、26秒
        int[] weights = {60, 30, 10}; // 权重：22-24秒占60%，24-26秒占30%，26秒占10%

        // 计算权重总和
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }

        // 随机生成一个介于 [0, totalWeight) 之间的数
        Random random = new Random();
        int randomWeight = random.nextInt(totalWeight);

        // 根据权重选择范围
        int selectedRange = timeRanges[timeRanges.length - 1]; // 默认选最后一个范围
        for (int i = 0; i < weights.length; i++) {
            if (randomWeight < weights[i]) {
                selectedRange = timeRanges[i];
                break;
            }
            randomWeight -= weights[i];
        }

        // 在选中的范围内随机生成具体时间（范围 + 0~2000毫秒随机）
        return selectedRange + random.nextInt(2000);
    }

    public static void main(String[] args) {
        System.out.println("生成 10 组随机休眠时间（单位：毫秒）：");
        for (int i = 1; i <= 10; i++) {
            int sleepTime = SleepTimeGenerator.generateSleepTime();
            System.out.println("第 " + i + " 次：休眠时间 = " + sleepTime + " 毫秒 (" + sleepTime / 1000.0 + " 秒)");
        }
    }
}
