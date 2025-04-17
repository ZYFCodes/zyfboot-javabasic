package org.zyf.javabasic.test.wzzz;

import com.google.common.collect.Maps;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private static final String USER_INFO_NONVAILD_FILE = "/Users/zyf/Downloads/csdn/user_info_nonvaild.txt";
    private static final String USER_INFO_FORNEW_FILE = "/Users/zyf/Downloads/csdn/user_info_fornew.txt";

    // 存储用户信息
    public static final Map<String, String> userInfo10 = new HashMap<>();
    public static final Map<String, String> userNewInfo30 = new HashMap<>();
    public static final Map<String, String> userNewInfo50 = new HashMap<>();
    public static final Map<String, String> userNewInfoMe = new HashMap<>();

    public static final Map<String, String> userNewInfoNonvaild = new HashMap<>();

    public static final Map<String, String> userNewInfoForNew = new HashMap<>();

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
        loadUserInfoFromFile(USER_INFO_NONVAILD_FILE, userNewInfoNonvaild);
        loadUserInfoFromFile(USER_INFO_FORNEW_FILE, userNewInfoForNew);
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
            int min = 5;
            int max = 9;
            return random.nextInt(max - min + 1) + min;
        }
        if (userNewInfo30.containsKey(userIdentification)) {
            // 生成18到29之间的随机数
            // nextInt(10)返回[0, 9]之间的值，加上18使得范围变成[18, 29]
            int min = 17;
            int max = 20;
            return random.nextInt(max - min + 1) + min;
        }
        if (userNewInfo50.containsKey(userIdentification)) {
            // 生成25到44之间的随机数
            // nextInt(20)返回[0, 19]之间的值，加上26使得范围变成[26, 45]
            int min = 27;
            int max = 36;
            return random.nextInt(max - min + 1) + min;
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

    public static int getRandomNumsForXH(String userIdentification) {
        if (userInfo10.containsKey(userIdentification)) {
            // 生成0到10之间的随机
            // nextInt(10)返回[0, 9]之间的值
            int min = 1;
            int max = 2;
            return random.nextInt(max - min + 1) + min;
        }
        if (userNewInfo30.containsKey(userIdentification)) {
            // 生成18到29之间的随机数
            // nextInt(10)返回[0, 9]之间的值，加上18使得范围变成[18, 29]
            int min = 5;
            int max = 7;
            return random.nextInt(max - min + 1) + min;
        }
        if (userNewInfo50.containsKey(userIdentification)) {
            // 生成25到44之间的随机数
            // nextInt(20)返回[0, 19]之间的值，加上26使得范围变成[26, 45]
            int min = 5;
            int max = 8;
            return random.nextInt(max - min + 1) + min;
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
        System.out.println("所有用户信息数：" + userAllInfo.size());
        // System.out.println(userAllInfo);
        //System.out.println(JSON.toJSON(userAllInfo));

        System.out.println("所有10用户信息数：" + userInfo10.size());
        // System.out.println(userInfo10);
        //System.out.println(JSON.toJSON(userInfo10));

        System.out.println("所有30用户信息数：" + userNewInfo30.size());
        //System.out.println(userNewInfo30);
        //System.out.println(JSON.toJSON(userNewInfo30));

        System.out.println("所有50用户信息数：" + userNewInfo50.size());
        //System.out.println(userNewInfo50);
        //System.out.println(JSON.toJSON(userNewInfo50));

        System.out.println("自身信息数：" + userNewInfoMe.size());
        //System.out.println(userNewInfoMe);
        //System.out.println(JSON.toJSON(userNewInfoMe));

        System.out.println("异常账号信息数：" + userNewInfoNonvaild.size());
        //System.out.println(userNewInfoNonvaild);
        //System.out.println(JSON.toJSON(userNewInfoNonvaild));

        System.out.println("新增账号信息数：" + userNewInfoForNew.size());
        // System.out.println(userNewInfoForNew);
        // System.out.println(JSON.toJSON(userNewInfoForNew));


        System.out.println("在线有效操作账号个数：" + userAllInfo.size() + ", 另外电脑侧登录无效账号个数：" + userNewInfoNonvaild.size());

        //打印最新的账号信息
//        try {
//            outputLogToFile(USER_INFO_10_FILE, userInfo10);
//            outputLogToFile(USER_INFO_30_FILE, userNewInfo30);
//            outputLogToFile(USER_INFO_50_FILE, userNewInfo50);
//            outputLogToFile(USER_INFO_ME_FILE, userNewInfoMe);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void outputLogToFile(String fileName, Map<String, String> userInfo) throws IOException {
        String[] getIds = fileName.split("/");
        String source = getIds[getIds.length - 1];
        // 获取当前日期的文件名
        String currentDate = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS").format(new Date());
        String logFilePath = "/Users/zyf/Downloads/csdn/" + currentDate + "-nonvaild-" + source;

        // 检查文件是否存在，不存在则创建
        File logFile = new File(logFilePath);
        if (!logFile.exists() && !logFile.createNewFile()) {
            throw new IOException("无法创建日志文件：" + logFilePath);
        }

        // 创建 PrintStream 用于输出日志
        try (PrintStream logPrintStream = new PrintStream(new FileOutputStream(logFile, true))) {
            logPrintStream.println("线上异常账号统计输出：");
            userInfo.entrySet().forEach(info -> {
                if (userNewInfoNonvaild.containsKey(info.getKey())) {
                    System.out.println(info.getKey() + "已经失效了，需要从" + userInfo.entrySet().size() + "中进行移除！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
                    return;
                }
                logPrintStream.println(info.getKey().concat(":").concat(info.getValue()));
            });
        }
    }
}
