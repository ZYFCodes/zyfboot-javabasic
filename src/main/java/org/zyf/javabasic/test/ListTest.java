package org.zyf.javabasic.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest {

    public static void main(String[] args) {

        JSONObject resultConfigJson = new JSONObject();
        resultConfigJson.put("rr", 0.1);
        System.out.println(resultConfigJson.getInteger("rr"));

        testSubList();


    }

    private static Map<Integer, Integer> getRefuelPackages(int number) {
        List<Integer> instances = Lists.newArrayList(9, 7, 5, 3, 1);
        Map<Integer, Integer> packages = new HashMap<>();
        for (int i = 0; i < instances.size() && number != 0; i++) {
            int divideNumber = number / instances.get(i);
            int modNumber = number % instances.get(i);
            if (divideNumber > 0) {
                packages.put(instances.get(i), divideNumber);
            }
            number = modNumber;
        }
        return packages;
    }

    public static void testSubList() {
        // 模拟 userInfo 生成 311 条数据
        Map<String, String> userInfo = new HashMap<>();
        for (int i = 1; i <= 311; i++) {
            userInfo.put(String.valueOf(i), "value" + i);
        }

        // 将 EntrySet 转换为 List
        List<Map.Entry<String, String>> entryList = new ArrayList<>(userInfo.entrySet());

        // 计算每份数据大小
        int totalSize = entryList.size();
        int chunkSize = (int) Math.ceil(totalSize / 5.0); // 向上取整

        // 生成 5 份数据
        List<List<Map.Entry<String, String>>> partitions = new ArrayList<>();
        for (int i = 0; i < totalSize; i += chunkSize) {
            partitions.add(new ArrayList<>(entryList.subList(i, Math.min(i + chunkSize, totalSize))));
        }

        // 遍历每一份数据
        for (int i = 0; i < partitions.size(); i++) {
            System.out.println("第 " + (i + 1) + " 份数据（共 " + partitions.get(i).size() + " 条）：");
            for (Map.Entry<String, String> entry : partitions.get(i)) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
            System.out.println("----------------------");
        }
    }
}
