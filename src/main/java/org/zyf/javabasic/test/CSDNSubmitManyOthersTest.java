package org.zyf.javabasic.test;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNSubmitManyTest
 * @author: zhangyanfeng
 * @create: 2024-10-16 23:32
 **/
public class CSDNSubmitManyOthersTest {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static void main(String[] args) {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Set<Integer> articleIds = CSDNArticles.getRandomArticleIdsForOthers(43);

        String cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";

        //对圈定的文章进行评论处理
        AtomicInteger num = new AtomicInteger();
        String finalCookie = cookie;
        articleIds.forEach(articleId -> {
            num.getAndIncrement();

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
            try (CloseableHttpClient client = HttpClients.createDefault()) {
                // 构建HttpPost请求
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
                httpPost.setHeader("Accept-Encoding", "gzip, deflate, br, zstd");
                httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
                httpPost.setHeader("Connection", "keep-alive");
                httpPost.setHeader("Cookie", finalCookie);
                // 设置请求体
                httpPost.setEntity(new StringEntity(form));

                // 发送请求并获取响应
                try (CloseableHttpResponse response = client.execute(httpPost)) {
                    // 输出响应状态码
                    System.out.println("Response Code: " + response.getCode());
                    // 输出响应体
                    // System.out.println("Response Body: " + new String(String.valueOf(response.getEntity().getContent().read())));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 回复文章：" + articleId + " 成功, 这是自开始进行的第" + num.get() + "次。");
                Thread.sleep(22000);
            } catch (InterruptedException e) {
                System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 回复文章：" + articleId + " 存在异常！, 这是第" + num.get() + "次了");
                ;
            }
        });

        System.out.println("评论结束！！！！！！！！");
        // 输出操作耗时
        costTime(startTime);
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
        return comments.get(randomIndex);
    }

    private static void costTime(long startTime) {
        // 记录结束时间
        long endTime = System.currentTimeMillis();

        // 计算耗时
        long elapsedTime = endTime - startTime;
        long seconds = elapsedTime / 1000;

        if (seconds > 60) {
            long minutes = seconds / 60;
            // 剩余秒数
            seconds = seconds % 60;
            System.out.println("总耗时: " + minutes + " 分 " + seconds + " 秒");
        } else {
            System.out.println("总耗时: " + seconds + " 秒");
        }
    }
}
