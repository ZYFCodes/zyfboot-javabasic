package org.zyf.javabasic.test.wzzz;

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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 任务：给自己的账号开始刷浏览量
 * @author: zhangyanfeng
 * @create: 2024-09-30 22:28
 **/
public class CSDNSubmitManyTest {

    private static final SecureRandom secureRandom = new SecureRandom();
    // 时间格式为 "yyyy-MM-dd HH:mm"
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public static void main(String[] args) {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Set<Integer> articleIds = CSDNArticles.getRandomArticleIds(30);

        String cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "uuid_tt_dd=10_10799280460-1729181125668-130168; fid=20_69363885578-1729181127059-913233; c_first_ref=default; c_segment=8; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1729181143; HMACCOUNT=2B5DA67F120A89C5; csdn_newcert_weixin_48861542=1; csdn_newcert_LJ11111111111111=1; csdn_newcert_DJV2980765=1; csdn_newcert_pengyou23452=1; pengyou23452comment_new=1728097831868; csdn_newcert_CWFDSDFGHJ1098=1; csdn_newcert_CMuhgf7654=1; csdn_newcert_ZXYokjhgf9=1; csdn_newcert_LFY5678=1; csrfToken=91wpItN4kYOzin0n9ITzBVJG; csdn_newcert_ZWL53223456789=1; csdn_newcert_XWL9875435=1; csdn_newcert_BXL3456=1; csdn_newcert_JH8876434=1; csdn_newcert_LH34568=1; csdn_newcert_LLN98765=1; csdn_newcert_ZF265467=1; csdn_newcert_JX275431234568=1; csdn_newcert_SR9872345686689=1; csdn_newcert_PZX9845=1; csdn_newcert_PZX87654323=1; csdn_newcert_LYLWDFGH4567=1; csdn_newcert_m0_74022416=1; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_macbookpro11=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_xiaofeng10330111=1; c_ins_prid=-; c_ins_rid=1729313983688_920679; c_ins_fref=https://edu.csdn.net/; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1729314060817; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dblog; csdn_newcert_2401_86608312=1; CMuhgf7654comment_new=1729608929367; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSIsInVzZXJJZCI6IjYzMjE0OTFlN2ZkZTYzN2ZhM2E0NTFhYyIsInVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSJ9.7ZvHFeRVs9zjuvLCY5nyfHR86sS-Bfv1PbLR-SJxn2c; CWFDSDFGHJ1098comment_new=1729470903118; xiaofeng10330111comment_new=1730731031833; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1731226578321%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E6%80%9D%E4%BA%98er%22%2C%22blog-threeH-dialog-expa%22%3A1729305626760%7D; dc_sid=9d4acd7611e8ae1ab2142719c6120304; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608357=1; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1731248990:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1731248990:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=a196d5e2b4979ec0:T=1729211486:RT=1731248990:S=AA-AfjbE9fL1C-ymryWPWJIEyM0x; FCCDCF=%5Bnull%2Cnull%2Cnull%2Cnull%2Cnull%2Cnull%2C%5B%5B13%2C%22%5B%5C%22DBABL~BVQqAAAAAg%5C%22%2C%5B%5B7%2C%5B1731248991%2C853317000%5D%5D%5D%5D%22%5D%5D%5D; FCNEC=%5B%5B%22AKsRol8gVNU26Bs-_1fmCVflnpjp9UbJsrzD5BwEmq4b-l-PuF-zORb1gaeWT5DLtHtgG71s7DIlQK3987I3iskSn1o7jOs3cqzA_85pfhAsB96CKkSynBMp_r35MtkzLinnf-D3wSuvJGrrsaCCa6haF5J29Sg5lA%3D%3D%22%5D%5D; SESSION=6a1fae28-6ceb-4283-b7e1-3223dc40f4f4; UserName=xiaofeng10330111; UserInfo=21bc77fcc372494b80b0e04afd8c06aa; UserToken=21bc77fcc372494b80b0e04afd8c06aa; UserNick=%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF; AU=F6D; UN=xiaofeng10330111; BT=1731253446020; p_uid=U110000; https_waf_cookie=5a5431ff-16ce-4a886de35e89283550551029fa910eb817e0; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1731256170; c_pref=https%3A//zyfcodes.blog.csdn.net/article/details/127499647%3Fspm%3D1001.2014.3001.5502; c_ref=https%3A//mp.csdn.net/mp_blog/creation/success/127499647; _clck=odihnm%7C2%7Cfqs%7C0%7C1751; dc_tos=smsjr0; _clsk=l58wg5%7C1731336444739%7C1%7C0%7Cj.clarity.ms%2Fcollect; dc_session_id=10_1731336450295.658529\n";

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
