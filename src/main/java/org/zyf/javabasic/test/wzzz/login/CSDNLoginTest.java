package org.zyf.javabasic.test.wzzz.login;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.zyf.javabasic.test.wzzz.CSDNUserInfos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: login
 * @author: zhangyanfeng
 * @create: 2025-03-23 11:17
 **/
public class CSDNLoginTest {
    static List<String> nonVaildUser = Lists.newArrayList();

    public static void main(String[] args) throws IOException {
//        Map<String, String> userInfo = CSDNUserInfos.getAllUserInfo();
//        // 将 Map 的条目转换为 List
//        List<Map.Entry<String, String>> entryList = new ArrayList<>(userInfo.entrySet());
//        // 打乱 List 中的条目顺序
//        Collections.shuffle(entryList);

        Map<String, String> userInfo = Maps.newHashMap();
        userInfo.put("15560826537", "csdn20142014");
        List<Map.Entry<String, String>> entryList = new ArrayList<>(userInfo.entrySet());

        for (Map.Entry<String, String> entry : entryList) {
            // 执行登录和记录操作
            doLoginAndLog(entry.getKey(), entry.getValue());
        }

//        if (CollectionUtils.isNotEmpty(nonVaildUser)) {
//            outputLogToFile();
//        }
    }


    public static void call() throws IOException {
        Map<String, String> userInfo = CSDNUserInfos.getAllUserInfo();
        // 将 Map 的条目转换为 List
        List<Map.Entry<String, String>> entryList = new ArrayList<>(userInfo.entrySet());
        // 打乱 List 中的条目顺序
        Collections.shuffle(entryList);

        for (Map.Entry<String, String> entry : entryList) {
            // 执行登录和记录操作
            doLoginAndLog(entry.getKey(), entry.getValue());
        }

        if (CollectionUtils.isNotEmpty(nonVaildUser)) {
            outputLogToFile();
        }
    }


    public static void doLoginAndLog(String userIdentification, String pwdOrVerifyCode) {
        String url = "https://passport.csdn.net/v1/register/pc/login/doLogin";
        String logoutUrl = "https://passport.csdn.net/account/logout";  // 登出 URL

        // 登录请求的 JSON 参数
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("agreedPrivacyPolicy", "0");
        jsonObject.addProperty("loginType", "1");
        jsonObject.addProperty("pwdOrVerifyCode", pwdOrVerifyCode);
        jsonObject.addProperty("uaToken", "");
        jsonObject.addProperty("userIdentification", userIdentification);
        jsonObject.addProperty("webUmidToken", "");

        String jsonPayload = jsonObject.toString();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);

            // 设置请求头
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            post.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

            // 设置请求体
            StringEntity entity = new StringEntity(jsonPayload);
            post.setEntity(entity);

            // 发送请求
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                //System.out.println("Status Code: " + statusCode);
                System.out.println("=============================================================================================Response Body: " + responseBody);

                // 提取 Set-Cookie
                if (statusCode == 200) {
                    Header[] cookies = response.getHeaders("Set-Cookie");
                    //System.out.println("Cookies:");
                    //Arrays.stream(cookies).forEach(header -> System.out.println(header.getValue()));

                    // 将 Cookie 保存，用于后续请求
                    StringBuilder cookieString = new StringBuilder();
                    Arrays.stream(cookies).forEach(header -> {
                        // 提取每个 Cookie 的 key=value
                        String cookiePart = header.getValue().split(";")[0];
                        cookieString.append(cookiePart).append("; ");
                    });

                    // 进行登出请求
                    logout(userIdentification, httpClient, logoutUrl, cookieString.toString());

                    Thread.sleep(6000);

                } else {
                    //nonVaildUser.add(userIdentification + "-- "+ statusCode);
                    System.out.println(userIdentification + "登录失败，请检查参数！");
                }
            }
        } catch (Exception e) {
            //nonVaildUser.add(userIdentification+ "-- 登录报错");
            e.printStackTrace();
        }
    }

    // 执行登出请求
    private static void logout(String userIdentification, CloseableHttpClient httpClient, String logoutUrl, String cookies) {
        try {
            HttpPost logoutPost = new HttpPost(logoutUrl);

            // 设置登出请求的请求头
            logoutPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            logoutPost.setHeader("Cookie", cookies);
            logoutPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

            // 发送登出请求
            try (CloseableHttpResponse response = httpClient.execute(logoutPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(userIdentification + "在" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 退出账号，基本状态码：" + statusCode + " 成功, 退出时回调打印：" + responseBody);
                //System.out.println("Logout Status Code: " + statusCode);
                //System.out.println("Logout Response Body: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void outputLogToFile() throws IOException {
        // 获取当前日期的文件名
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String logFilePath = "/Users/zyf/Downloads/csdn/" + currentDate + "-userVaild.txt";

        // 检查文件是否存在，不存在则创建
        File logFile = new File(logFilePath);
        if (!logFile.exists() && !logFile.createNewFile()) {
            throw new IOException("无法创建日志文件：" + logFilePath);
        }

        Map<String, String> userInfo = CSDNUserInfos.getAllUserInfo();
        // 创建 PrintStream 用于输出日志
        try (PrintStream logPrintStream = new PrintStream(new FileOutputStream(logFile, true))) {
            logPrintStream.println("线上异常账号统计输出：");
            nonVaildUser.forEach(nonVaildUser -> {
                if(StringUtils.isNotBlank(userInfo.get(nonVaildUser))){
                    logPrintStream.println(nonVaildUser.toString().concat(":").concat(userInfo.get(nonVaildUser)));
                } else {
                    logPrintStream.println(nonVaildUser.toString());
                }
            });
        }
    }
}
