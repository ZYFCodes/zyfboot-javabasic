package org.zyf.javabasic.letcode.featured75.graph;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 除法求值
 * @author: zhangyanfeng
 * @create: 2024-08-24 11:35
 **/
public class CalcEquation {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 用于映射变量到图中的节点索引
        int nvars = 0;
        Map<String, Integer> variables = new HashMap<>();

        // 构建变量的映射关系
        int n = equations.size();
        for (int i = 0; i < n; i++) {
            // 如果变量不存在，则添加到映射中
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }

        // 初始化邻接表
        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++) {
            edges[i] = new ArrayList<>();
        }

        // 构建图的边
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0));
            int vb = variables.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb, values[i]));
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }

        // 处理每个查询
        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;

            // 如果查询中的变量都在图中
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0));
                int ib = variables.get(query.get(1));

                // 如果查询的两个变量相同，结果为1.0
                if (ia == ib) {
                    result = 1.0;
                } else {
                    // 使用 BFS 查找从 ia 到 ib 的路径
                    Queue<Integer> points = new LinkedList<>();
                    points.offer(ia);
                    double[] ratios = new double[nvars];
                    Arrays.fill(ratios, -1.0);
                    ratios[ia] = 1.0;

                    while (!points.isEmpty() && ratios[ib] < 0) {
                        int x = points.poll();
                        for (Pair pair : edges[x]) {
                            int y = pair.index;
                            double val = pair.value;
                            if (ratios[y] < 0) {
                                ratios[y] = ratios[x] * val;
                                points.offer(y);
                            }
                        }
                    }
                    result = ratios[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    // 辅助类，表示图中的边
    class Pair {
        int index;  // 目标节点的索引
        double value;  // 边的权重

        Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        CalcEquation solution = new CalcEquation();

        // Test Case 1
        List<List<String>> equations1 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );
        double[] values1 = {2.0, 3.0};
        List<List<String>> queries1 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x")
        );
        double[] result1 = solution.calcEquation(equations1, values1, queries1);
        System.out.println(Arrays.toString(result1)); // Expected: [6.0, 0.5, -1.0, 1.0, -1.0]

        // Test Case 2
        List<List<String>> equations2 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("bc", "cd")
        );
        double[] values2 = {1.5, 2.5, 5.0};
        List<List<String>> queries2 = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("c", "b"),
                Arrays.asList("bc", "cd"),
                Arrays.asList("cd", "bc")
        );
        double[] result2 = solution.calcEquation(equations2, values2, queries2);
        System.out.println(Arrays.toString(result2)); // Expected: [3.75, 0.4, 5.0, 0.2]

        // Test Case 3
        List<List<String>> equations3 = Arrays.asList(
                Arrays.asList("a", "b")
        );
        double[] values3 = {0.5};
        List<List<String>> queries3 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "c"),
                Arrays.asList("x", "y")
        );
        double[] result3 = solution.calcEquation(equations3, values3, queries3);
        System.out.println(Arrays.toString(result3)); // Expected: [0.5, 2.0, -1.0, -1.0]

        // Boundary Test Case
        List<List<String>> equations4 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("c", "d"),
                Arrays.asList("d", "e")
        );
        double[] values4 = {2.0, 2.0, 2.0, 2.0};
        List<List<String>> queries4 = Arrays.asList(
                Arrays.asList("a", "e"),
                Arrays.asList("e", "a"),
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c"),
                Arrays.asList("c", "d"),
                Arrays.asList("d", "e")
        );
        double[] result4 = solution.calcEquation(equations4, values4, queries4);
        System.out.println(Arrays.toString(result4)); // Expected: [16.0, 0.0625, 2.0, 2.0, 2.0, 2.0]
    }
}
