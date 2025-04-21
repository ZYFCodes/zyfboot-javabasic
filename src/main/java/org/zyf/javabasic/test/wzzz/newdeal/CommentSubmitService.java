package org.zyf.javabasic.test.wzzz.newdeal;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.test.wzzz.CSDNComments;
import org.zyf.javabasic.test.wzzz.CSDNUserRandomCommentsNum;
import org.zyf.javabasic.test.wzzz.SleepTimeGenerator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 评论处理机制
 * @author: zhangyanfeng
 * @create: 2025-04-18 13:22
 **/
@Component
@Slf4j
public class CommentSubmitService {
    private static String zhiDingNum = null;

    public void doSubmit(String userIdentification, String cookie) {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        Set<Integer> articleIds = Sets.newHashSet();
        if (StringUtils.isNotBlank(zhiDingNum)) {
            //如果指定，则返回指定的文章评论
            articleIds = CSDNUserRandomCommentsNum.getRandomZhiDingArticleIds(userIdentification, zhiDingNum);
        } else {
            articleIds = CSDNUserRandomCommentsNum.getNormal(userIdentification);
        }

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
            parameters.put("content", CSDNComments.getRandomString(articleId, comments));
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
                log.info("用户【{}】在{}回复文章：{} 成功, 这是自开始进行的第{}次。",
                        userIdentification,
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()),
                        articleId,
                        num.get());
                Thread.sleep(SleepTimeGenerator.generateSleepTime());
            } catch (InterruptedException e) {
                log.info("用户【{}】在{}回复文章：{}  存在异常！这是自开始进行的第{}次。",
                        userIdentification,
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()),
                        articleId,
                        num.get());
            }
        });
        log.info("用户【{}】在{}评论结束！{}",
                userIdentification,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()),
                costTime(startTime)
        );
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
