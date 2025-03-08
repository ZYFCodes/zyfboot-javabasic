package org.zyf.javabasic.test.wzzz;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @program: zyfboot-javabasic
 * @description: csdn账号整合
 * @author: zhangyanfeng
 * @create: 2024-11-19 22:20
 **/
public class CSDNUserInfos {
    public static final Map<String, String> userAllInfo = Maps.newHashMap();

    // 文件路径
    private static final String USER_INFO_10_FILE = "/Users/zyf/Downloads/csdn/user_info_10.txt";
    private static final String USER_INFO_30_FILE = "/Users/zyf/Downloads/csdn/user_info_30.txt";
    private static final String USER_INFO_50_FILE = "/Users/zyf/Downloads/csdn/user_info_50.txt";
    private static final String USER_INFO_ME_FILE = "/Users/zyf/Downloads/csdn/user_info_me.txt";

    // 存储用户信息
    public static final Map<String, String> userInfo10 = new HashMap<>();
    public static final Map<String, String> userNewInfo30 = new HashMap<>();
    public static final Map<String, String> userNewInfo50 = new HashMap<>();
    public static final Map<String, String> userNewInfoMe = new HashMap<>();

    static Random random = new Random();  // 创建一个Random对象

    /**
     * 从指定文件中加载用户信息
     *
     * @param filePath  文件路径
     * @param targetMap 存储到的目标Map
     */
    private static void loadUserInfoFromFile(String filePath, Map<String, String> targetMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 忽略空行和注释行
                if (line.trim().isEmpty() || line.trim().startsWith("#")) {
                    continue;
                }
                // 解析行内容
                String[] parts = line.split(":", 2); // 假设用":"分隔
                if (parts.length == 2) {
                    targetMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("读取文件失败：" + filePath + ", 错误：" + e.getMessage());
        }
    }

    /**
     * 初始化加载用户信息
     */
    static {
        loadUserInfoFromFile(USER_INFO_10_FILE, userInfo10);
        loadUserInfoFromFile(USER_INFO_30_FILE, userNewInfo30);
        loadUserInfoFromFile(USER_INFO_50_FILE, userNewInfo50);
        loadUserInfoFromFile(USER_INFO_ME_FILE, userNewInfoMe);
        // 合并新用户到总用户列表
        userAllInfo.putAll(userInfo10);
        userAllInfo.putAll(userNewInfo30);
        userAllInfo.putAll(userNewInfo50);
        userAllInfo.putAll(userNewInfoMe);
    }

    public static Map<String, String> getAllUserInfo() {
        return userAllInfo;
    }

    public static int getRandomNums(String userIdentification) {
        if (userInfo10.containsKey(userIdentification)) {
            // 生成0到10之间的随机
            // nextInt(10)返回[0, 9]之间的值
            int num = random.nextInt(10) + 1;
            if(num<8){
                num=num+2;
            }
            return num;
        }
        if (userNewInfo30.containsKey(userIdentification)) {
            // 生成18到29之间的随机数
            // nextInt(10)返回[0, 9]之间的值，加上18使得范围变成[18, 29]
            return random.nextInt(10) + 18;
        }
        if (userNewInfo50.containsKey(userIdentification)) {
            // 生成25到44之间的随机数
            // nextInt(20)返回[0, 19]之间的值，加上26使得范围变成[26, 45]
            int min = 39;
            int max = 44;
            return random.nextInt(max-min +1) + min;
        }
        if (userNewInfoMe.containsKey(userIdentification)) {
            // 生成1到10之间的随机数
            // nextInt(10)返回[0, 9]之间的值，加上1使得范围变成[1, 10]
            return random.nextInt(10) + 1;
        }

        // 默认返回5到25之间的随机数
        // nextInt(10)返回[0, 9]之间的值，加上5使得范围变成[5, 14]
        return random.nextInt(10) + 5;
    }

    public static Set<String> get10UserInfoKeys() {
        return userInfo10.keySet();
    }


    public static void main(String[] args) {
        System.out.println("所有用户信息");
        System.out.println(userAllInfo);
        System.out.println(userAllInfo.size());
        System.out.println(JSON.toJSON(userAllInfo));

        System.out.println("所有10用户信息");
        System.out.println(userInfo10);
        System.out.println(userInfo10.size());
        System.out.println(JSON.toJSON(userInfo10));

        System.out.println("所有30用户信息");
        System.out.println(userNewInfo30);
        System.out.println(userNewInfo30.size());
        System.out.println(JSON.toJSON(userNewInfo30));

        System.out.println("所有50用户信息");
        System.out.println(userNewInfo50);
        System.out.println(userNewInfo50.size());
        System.out.println(JSON.toJSON(userNewInfo50));

        System.out.println("自身信息");
        System.out.println(userNewInfoMe);
        System.out.println(userNewInfoMe.size());
        System.out.println(JSON.toJSON(userNewInfoMe));
    }
}
