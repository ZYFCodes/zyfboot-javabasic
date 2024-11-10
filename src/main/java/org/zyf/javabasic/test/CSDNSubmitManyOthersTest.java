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
 * @description: 给指定的文章列表随机的评论
 * @author: zhangyanfeng
 * @create: 2024-10-16 23:32
 **/
public class CSDNSubmitManyOthersTest {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static void main(String[] args) {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        Set<Integer> articleIds = CSDNArticles.getRandomArticleIdsForOthers(48);

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
        cookie = "";
        cookie = "";
        cookie = "";
        cookie = "uuid_tt_dd=10_10799280460-1729181125668-130168; fid=20_69363885578-1729181127059-913233; c_first_ref=default; c_segment=8; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1729181143; HMACCOUNT=2B5DA67F120A89C5; csdn_newcert_weixin_48861542=1; csdn_newcert_LJ11111111111111=1; csdn_newcert_DJV2980765=1; csdn_newcert_pengyou23452=1; pengyou23452comment_new=1728097831868; csdn_newcert_CWFDSDFGHJ1098=1; csdn_newcert_CMuhgf7654=1; csdn_newcert_ZXYokjhgf9=1; csdn_newcert_LFY5678=1; csrfToken=91wpItN4kYOzin0n9ITzBVJG; csdn_newcert_ZWL53223456789=1; csdn_newcert_XWL9875435=1; csdn_newcert_BXL3456=1; csdn_newcert_JH8876434=1; csdn_newcert_LH34568=1; csdn_newcert_LLN98765=1; csdn_newcert_ZF265467=1; csdn_newcert_JX275431234568=1; csdn_newcert_SR9872345686689=1; csdn_newcert_PZX9845=1; csdn_newcert_PZX87654323=1; csdn_newcert_LYLWDFGH4567=1; csdn_newcert_m0_74022416=1; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_macbookpro11=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_xiaofeng10330111=1; c_ins_prid=-; c_ins_rid=1729313983688_920679; c_ins_fref=https://edu.csdn.net/; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1729314060817; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dblog; csdn_newcert_2401_86608312=1; SESSION=d22a24c7-a227-4c2c-9281-9ce786857c16; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1730128104432%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E6%9D%8E%E5%AD%90%E8%AC%87%22%2C%22blog-threeH-dialog-expa%22%3A1729305626760%7D; CMuhgf7654comment_new=1729608929367; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1730130994:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1730130994:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=a196d5e2b4979ec0:T=1729211486:RT=1730130994:S=AA-AfjbE9fL1C-ymryWPWJIEyM0x; UserName=xiaofeng10330111; UserInfo=b0d33b3e577c49308d4618c21cdcd109; UserToken=b0d33b3e577c49308d4618c21cdcd109; UserNick=%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF; AU=F6D; UN=xiaofeng10330111; BT=1730131019631; p_uid=U110000; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSIsInVzZXJJZCI6IjYzMjE0OTFlN2ZkZTYzN2ZhM2E0NTFhYyIsInVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSJ9.7ZvHFeRVs9zjuvLCY5nyfHR86sS-Bfv1PbLR-SJxn2c; CWFDSDFGHJ1098comment_new=1729470903118; xiaofeng10330111comment_new=1730731031833; dc_sid=b4767707147033fdd3d823a82fc7ed20; https_waf_cookie=9adc0c0b-a87b-43eecc457b22d4459031d1366745e7172a64; _clck=odihnm%7C2%7Cfqr%7C0%7C1751; dc_session_id=10_1731222266585.893602; c_dsid=11_1731222267075.779830; c_hasSub=true; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20230921102607.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A222%2C%22type%22%3A2%2C%22oldUser%22%3Atrue%2C%22useSeven%22%3Afalse%2C%22oldFullVersion%22%3Atrue%2C%22userName%22%3A%22xiaofeng10330111%22%7D; is_advert=1; c_utm_medium=distribute.pc_feed_blog.none-task-blog-personrec_tag-1-143651808-null-null.nonecase; c_page_id=default; _clsk=1k4ggpt%7C1731222275656%7C2%7C0%7Cw.clarity.ms%2Fcollect; waf_captcha_marker=3cdf19b107b8566209ca88ddbab885c06d9aaa8ee6972b91579133fe2bd0623a; creative_btn_mp=3; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1731222291; log_Id_view=120; c_pref=https%3A//blog.csdn.net/%3Fspm%3D1001.2014.3001.4477; c_ref=https%3A//i.csdn.net/; log_Id_pv=56; dc_tos=smq3q1; log_Id_click=3\n";


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
