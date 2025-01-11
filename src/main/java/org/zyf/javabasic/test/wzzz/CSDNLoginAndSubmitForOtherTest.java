package org.zyf.javabasic.test.wzzz;

import com.google.gson.JsonObject;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNLoginAndSubmitForOtherTest
 * @author: zhangyanfeng
 * @create: 2024-12-06 21:32
 **/
public class CSDNLoginAndSubmitForOtherTest {

    private static final SecureRandom secureRandom = new SecureRandom();
    // 时间格式为 "yyyy-MM-dd HH:mm"
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    // 用于统计每篇文章被返回的次数
    private static Map<Integer, Integer> articleFrequencyMap = new ConcurrentHashMap<>();

    //至少初始化50篇文章
    private static String userIdentificationFlag = "";

    public static void commitDealNew() {
        // 记录程序开始时间
        long startTime = System.currentTimeMillis();
        Map<String, String> userInfo = CSDNUserInfos.getAllUserInfo();
        // 将 Map 的条目转换为 List
        List<Map.Entry<String, String>> entryList = new ArrayList<>(userInfo.entrySet());
        // 打乱 List 中的条目顺序
        Collections.shuffle(entryList);

        // 创建线程池（限制线程数量为 CPU 核心数或自定义值）
        int threadCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        // 创建 AtomicInteger 来跟踪循环次数
        AtomicInteger num = new AtomicInteger(1);
        // 记录线程池任务提交开始时间
        long taskStartTime = System.currentTimeMillis();
        for (Map.Entry<String, String> entry : entryList) {
            executorService.submit(() -> {
                String currentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime());
                System.out.println("账号：" + entry.getKey() + " 在 " + currentTime +
                        " 进行登陆和评论数据, 该账号当前位于第" + num.getAndIncrement() + "位。");

                // 执行登录和提交操作
                doLoginAndSubmit(entry.getKey(), entry.getValue());
            });
        }

        // 关闭线程池并等待所有任务完成
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.err.println("线程池等待任务完成时被中断：" + e.getMessage());
        }

        // 记录线程池任务执行完成时间
        long taskEndTime = System.currentTimeMillis();
        // 输出线程池任务总时间
        long taskDuration = taskEndTime - taskStartTime;
        long taskSeconds = (taskDuration / 1000) % 60;
        long taskMinutes = (taskDuration / (1000 * 60)) % 60;
        long taskHours = taskDuration / (1000 * 60 * 60);

        System.out.println(String.format("本次线程池完成所有任务总时间：%s小时 %s分钟 %s秒", taskHours, taskMinutes, taskSeconds));

        // 输出一共有多少篇文章
        System.out.println("本次全部账号登陆并随机选取文章进行评论，统计当前随机选取文章一共有 " + articleFrequencyMap.size() + " 篇。");
        // 输出每篇文章被返回的次数
        System.out.println("具体到每篇文章被随机命中的次数进行统计输出结果如下：");
        // 使用 AtomicInteger 来作为计数器
        AtomicInteger index = new AtomicInteger(1);
        articleFrequencyMap.forEach((articleId, count) -> {
            System.out.println("编号 " + index.getAndIncrement() + " - 文章 ID " + articleId + " 被返回了 " + count + " 次");
        });

        // 记录程序结束时间
        long endTime = System.currentTimeMillis();

        // 计算程序执行时长
        long duration = endTime - startTime;
        long hours = duration / (1000 * 60 * 60);
        long minutes = (duration % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (duration % (1000 * 60)) / 1000;

        // 统计所有 value 的总和
        int totalFrequency = articleFrequencyMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        // 输出程序评论执行时长数据
        System.out.println(String.format("本次执行总共花费时间：%s小时 %s分钟 %s秒, 一共评论文章%s篇, 所有辅助账号一共评论%s次！",
                hours, minutes, seconds, articleFrequencyMap.size(), totalFrequency));

    }

    public static void doLoginAndSubmit(String userIdentification, String pwdOrVerifyCode) {
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
                //System.out.println("Response Body: " + responseBody);

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

                    //System.out.println("Saved Cookie: " + cookieString.toString().trim());

                    //执行业务逻辑
                    doSubmit(userIdentification, cookieString.toString());


                    // 进行登出请求
                    logout(userIdentification, httpClient, logoutUrl, cookieString.toString());

                    Thread.sleep(15000);

                } else {
                    System.out.println(userIdentification + "登录失败，请检查参数！");
                }
            }
        } catch (Exception e) {
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

    public static void doSubmit(String userIdentification, String cookie) {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        int randomNums = CSDNUserInfos.getRandomNums(userIdentification);

        Set<Integer> articleIds = CSDNArticles.getRandomArticleIdsForZhidingUser(userIdentificationFlag, randomNums);
        //对圈定的文章进行评论处理
        AtomicInteger num = new AtomicInteger();
        String finalCookie = cookie;
        articleIds.forEach(articleId -> {
            num.getAndIncrement();

            articleFrequencyMap.put(articleId, articleFrequencyMap.getOrDefault(articleId, 0) + 1);

            // 请求URL
            String url = "https://blog.csdn.net/phoenix/web/v1/comment/submit";

            // 准备请求参数
            List<String> comments = CSDNComments.getComments(articleId.toString());
            Map<String, String> parameters = new HashMap<>();
            parameters.put("content", getRandomString(comments));
            parameters.put("articleId", articleId.toString());
            String form = null;
            try {
                form = getFormUrlEncodedData(parameters);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            // 创建HttpClient
            try (org.apache.hc.client5.http.impl.classic.CloseableHttpClient client = org.apache.hc.client5.http.impl.classic.HttpClients.createDefault()) {
                // 构建HttpPost请求
                org.apache.hc.client5.http.classic.methods.HttpPost httpPost = new org.apache.hc.client5.http.classic.methods.HttpPost(url);
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
                httpPost.setHeader("Accept-Encoding", "gzip, deflate, br, zstd");
                httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
                httpPost.setHeader("Connection", "keep-alive");
                httpPost.setHeader("Cookie", finalCookie);
                // 设置请求体
                httpPost.setEntity(new org.apache.hc.core5.http.io.entity.StringEntity(form));

                // 发送请求并获取响应
                try (org.apache.hc.client5.http.impl.classic.CloseableHttpResponse response = client.execute(httpPost)) {
                    // 输出响应状态码
                    //System.out.println("Response Code: " + response.getCode());
                    // 输出响应体
                    // System.out.println("Response Body: " + new String(String.valueOf(response.getEntity().getContent().read())));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println(userIdentification + "在" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 回复文章：" + articleId + " 成功, 这是自开始进行的第" + num.get() + "次。");
                Thread.sleep(SleepTimeGenerator.generateSleepTime());
            } catch (InterruptedException e) {
                System.out.println(userIdentification + "在" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 回复文章：" + articleId + " 存在异常！, 这是第" + num.get() + "次了");
                ;
            }
        });

        System.out.println(userIdentification + "在" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) + "评论结束！" + costTime(startTime));
    }

    // 辅助方法：将Map转为URL编码的表单数据格式
    private static String getFormUrlEncodedData(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder form = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (form.length() > 0) {
                form.append("&");
            }
            form.append(URLEncoder.encode(entry.getKey(), String.valueOf(StandardCharsets.UTF_8)));
            form.append("=");
            form.append(URLEncoder.encode(entry.getValue(), String.valueOf(StandardCharsets.UTF_8)));
        }
        return form.toString();
    }

    public static String getRandomString(List<String> comments) {
        if (comments == null || comments.isEmpty()) {
            throw new IllegalArgumentException("The input comments cannot be null or empty.");
        }

        // 生成一个随机索引
        // size() 只会返回 0 到 size()-1
        int randomIndex = secureRandom.nextInt(comments.size());

        //增加随机性
        // 打乱列表以确保随机性
        Collections.shuffle(comments);

        //获取该评论，如果有%s的信息则替换为时间
        String comment = comments.get(randomIndex);
        if (comment.contains("%s")) {
            comment = String.format(comment, getCurrentBeijingTime());
        }

        return comment;
    }

    /**
     * 获取当前北京时间，格式为 yyyy-MM-dd HH:mm
     *
     * @return 当前的北京时间字符串
     */
    public static String getCurrentBeijingTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        return now.format(DATE_TIME_FORMATTER);
    }

    private static String costTime(long startTime) {
        // 记录结束时间
        long endTime = System.currentTimeMillis();

        // 计算耗时
        long elapsedTime = endTime - startTime;
        long seconds = elapsedTime / 1000;

        if (seconds > 60) {
            long minutes = seconds / 60;
            // 剩余秒数
            seconds = seconds % 60;
            return "   本次总耗时: " + minutes + " 分 " + seconds + " 秒";
        } else {
            return "   本次总耗时: " + seconds + " 秒";
        }
    }
}
