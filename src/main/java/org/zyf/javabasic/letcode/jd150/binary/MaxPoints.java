package org.zyf.javabasic.letcode.jd150.binary;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 直线上最多的点数
 * @author: zhangyanfeng
 * @create: 2024-08-25 19:43
 **/
public class MaxPoints {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n < 3) return n;

        int maxPointsOnLine = 1;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeMap = new HashMap<>();
            int duplicate = 0; // 记录与基准点重合的点数
            int maxForCurrentPoint = 0;

            for (int j = i + 1; j < n; j++) {
                int deltaX = points[j][0] - points[i][0];
                int deltaY = points[j][1] - points[i][1];

                if (deltaX == 0 && deltaY == 0) {
                    // 基准点与某点重合
                    duplicate++;
                    continue;
                }

                // 化简分数形式的斜率
                int gcd = gcd(deltaX, deltaY);
                deltaX /= gcd;
                deltaY /= gcd;

                // 确保斜率的唯一性（处理垂直和水平的情况）
                String slope = deltaX + "/" + deltaY;
                slopeMap.put(slope, slopeMap.getOrDefault(slope, 0) + 1);
                maxForCurrentPoint = Math.max(maxForCurrentPoint, slopeMap.get(slope));
            }

            // 计算基于当前基准点的最大点数
            maxPointsOnLine = Math.max(maxPointsOnLine, maxForCurrentPoint + duplicate + 1);
        }

        return maxPointsOnLine;
    }

    // 计算最大公约数
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
