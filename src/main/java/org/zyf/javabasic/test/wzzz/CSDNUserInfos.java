package org.zyf.javabasic.test.wzzz;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
        int randomNums = 30;
        if (userInfo10.containsKey(userIdentification)) {
            randomNums = 10;
        }
        if (userNewInfo30.containsKey(userIdentification)) {
            randomNums = 30;
        }
        if (userNewInfo50.containsKey(userIdentification)) {
            randomNums = 49;
        }
        if (userNewInfoMe.containsKey(userIdentification)) {
            randomNums = 40;
        }

        return randomNums;
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
