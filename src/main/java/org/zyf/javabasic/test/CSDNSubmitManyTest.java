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
 * @description: 任务：给自己的账号开始刷浏览量
 * @author: zhangyanfeng
 * @create: 2024-09-30 22:28
 **/
public class CSDNSubmitManyTest {

    private static final SecureRandom secureRandom = new SecureRandom();

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
        cookie = "";
        cookie = "log_Id_click=253; log_Id_pv=238; log_Id_view=286; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19405898120-1691850414206-685544!5744*1*xiaofeng10330111; c_ins_fpage=/index.html; c_ins_um=-; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22xiaofeng10330111%22%2C%22scope%22%3A1%7D%7D; cf_clearance=XMWU4xMPiaIdAetK9mRZQczpWrQw8sgp1E5ClpufgAM-1713717920-1.0.1.1-bA9M96DmKE67yyESCSVzwAQfKZSWOwaq0le8OyuGIbfOajiK0dLYliXHrYwy47_LyncUk6Z_A0CckkfHQ2IuUQ; FCCDCF=%5Bnull%2Cnull%2Cnull%2C%5B%22CP-FskAP-FskAEsACBENAzEoAP_gAEPgAARoINJB7D7FbSFCwH5zaLsAMAhHRsCAQoQAAASBAmABQAKQIAQCgkAQFASgBAACAAAAICZBIQIECAAACUAAQAAAAAAEAAAAAAAIIAAAgAEAAAAIAAACAAAAEAAIAAAAEAAAmAgAAIIACAAAhAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAQOhQD2F2K2kKFkPCmQWYAQBCijYEAhQAAAAkCBIAAgAUgQAgFIIAgAIFAAAAAAAAAQEgCQAAQABAAAIACgAAAAAAIAAAAAAAQQAAAAAIAAAAAAAAEAAAAAAAQAAAAIAABEhCAAQQAEAAAAAAAQAAAAAAAAAAABAAA%22%2C%222~2072.70.89.93.108.122.149.196.2253.2299.259.2357.311.313.323.2373.358.2415.415.449.2506.2526.486.494.495.2568.2571.2575.540.574.2624.609.2677.827.864.981.1029.1048.1051.1095.1097.1126.1205.1211.1276.1301.1365.1415.1423.1449.1570.1577.1598.1651.1716.1735.1753.1765.1870.1878.1889.1958~dv.%22%2C%2293F3F4AF-7190-4BE3-8FDC-BFC6D18FD6EA%22%5D%5D; cf_clearance=QsOo9JUWOmltfyEDwa6e1uYT6E5LreCRRMyEz7W_ZUk-1716736886-1.0.1.1-IuyPBdCyyx5Wlqwk2bOtC_ImFvNmfM9zBnpyRlEbEKIcQ0folNluVAvWovtTwhD5YxMJMqT80p.kCV8qr1v7zg; historyList-new=%5B%5D; fpv=95a6ffb9aa1e3b3ee974b6dc4d7a2bfa; csrfToken=e19KMLEPzo6GxX0KfQmm80Mv; HMACCOUNT=7FA25E471A580294; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSIsInVzZXJJZCI6IjYzMjE0OTFlN2ZkZTYzN2ZhM2E0NTFhYyIsInVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSJ9.7ZvHFeRVs9zjuvLCY5nyfHR86sS-Bfv1PbLR-SJxn2c; c_ins_prid=1713084886109_157557; c_ins_rid=1720342991428_437221; c_ins_fref=https://mp.csdn.net/mp_blog/creation/editor/140244821; c_segment=2; uuid_tt_dd=10_19405897440-1721924064334-906450; fid=20_54925035130-1723377085963-458324; FCNEC=%5B%5B%22AKsRol8nayU7eeTg9egfHd2G154RLWCn6i4gw6hi-VsZJmbkwKtnlcU1vCK3RYZ9r4VYXKkfNPAHPNCEUF4YJn5HFcI2KdwIR0tNpbBut1iGDhMLqMi0PFr6wanNx_VOFlcMVubrwl68rl9skcdTD5OGycV1hnfUWg%3D%3D%22%5D%5D; csdn_newcert_xiaofeng10330111=1; csdn_newcert_m0_74022416=1; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608312=1; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; c_dl_um=-; csdn_newcert_macbookpro11=1; _ga=GA1.1.1003498981.1691850421; _ga_7W1N0GEY1P=GS1.1.1725258902.53.0.1725258908.54.0.0; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1724494228; Hm_lpvt_e5ef47b9f471504959267fd614d579cd=1725280099; ssxmod_itna=QqjxyDRGDtf6DXDnD+xbTG87D7BGC/KQYIiUDBw3q4iNDnD8x7YDvG+pFBGiBiR0OhxWx5Co24aW0A3ofPrr=ioNdD84i7DKqibDCqD1D3qDkHtxYA8Dt4DTD34DYDio=DBUHzQDFmX/6gU8j0RfQgDif2/4DRKqDgGeD1/dXZmM3emqDAzeGylqGf/4GgUkj6DY=DQdc27WtDj4bbTcuXDYPW7uTNxBjdxkdDCEtlnkQCh7DtI+5z7D+QWOD3FBqoBADoU0SNb4eUa7GxmDhRVY4dj44t9j+9XiD===; ssxmod_itna2=QqjxyDRGDtf6DXDnD+xbTG87D7BGC/KQYIiD8Meh8xGXhXPGaiKBAOkvx8rbI3TdqR9hiKhFjY/gyvFQMZwTb3euiLpewPZorbLLrqK9vdsr=TCD6Gay6KTdwy0Q6S=HkL8Yo2fUFkvVzhYwADE0FuPyfRED=Rrc/xoxphttCEbSKAP=p2oN1SKlSCtOBMKn3pAhD=QeY=f+Fqnu0iIQOlKickK/fW0OepY4/jpWXSHy7+4etIaO44OCNxG2I4GcDiQteD==; Hm_lvt_ec8a58cd84a81850bcbd95ef89524721=1726118079; c_dl_prid=1724809271365_229307; c_dl_rid=1726375509293_793801; c_dl_fref=https://blog.csdn.net/xiaofeng10330111; c_dl_fpage=/download/xiaofeng10330111/89599955; xiaoxu2022xiaoxucomment_new=1726139774231; csdn_newcert_DJV2980765=1; dc_sid=2236d12434567d0a574a399ff98989fa; csdn_newcert_pengyou23452=1; csdn_newcert_JX275431234568=1; csdn_newcert_SR9872345686689=1; csdn_newcert_LYLWDFGH4567=1; csdn_newcert_ZXYokjhgf9=1; csdn_newcert_CMuhgf7654=1; csdn_newcert_CWFDSDFGHJ1098=1; csdn_newcert_ZF265467=1; csdn_newcert_LLN98765=1; csdn_newcert_LH34568=1; csdn_newcert_JH8876434=1; csdn_newcert_XWL9875435=1; csdn_newcert_ZWL53223456789=1; csdn_newcert_LFY5678=1; csdn_newcert_BXL3456=1; csdn_newcert_PZX87654323=1; csdn_newcert_PZX9845=1; csdn_newcert_LJ11111111111111=1; tfstk=gG_rNq661eXft9a6GILe_3L9LNLJWU2_Yw9Bt6fHNLvkRupHK6BvO213epAF3KtCVpTkKyWGh9-Lwp9HL91hVSa_5_CJJeYUCPa1l8i5sTRkx0c0tBRQCF75dvCJJe2b72m2F_BZgw97TeV2oBOrEevkqSRD1K0kt4AnmSRpneAorLvmoBdIxB0lKsV29KvnsEx2brRMq5OZgcxzawKyI_vqSs_2ZW9aWK0nWZRPzdfoj20huQ-PR93EDcLhq_62fgyZzeClX9dGEY2yga5N8GX0l48FiGbeoZ2mGLjOgw85rWakoaWPrnWnOJOAfC5efiNnbKshMZ-VzlGpMa1hyhTgWYvCc17eTweLuO5lUGxGrYSrYvd0xZQd4vmeqId2CSPVfXbb_FgWT0oKvnXBgdNRwDnpq3R2CSPmvDK-lIJ_wFf..; cms_blog_nav={%22cate1%22:{%22type%22:%22blockchain%22%2C%22title%22:%22%E5%8C%BA%E5%9D%97%E9%93%BE%22}}; Hm_lpvt_ec8a58cd84a81850bcbd95ef89524721=1728017166; c_first_ref=default; JH8876434comment_new=1728097473982; pengyou23452comment_new=1728098240923; JX275431234568comment_new=1728097831868; SR9872345686689comment_new=1728097398998; PZX9845comment_new=1728097398998; LYLWDFGH4567comment_new=1728097374058; CMuhgf7654comment_new=1728098036535; xiaofeng10330111comment_new=1728576202371; CWFDSDFGHJ1098comment_new=1728095796337; ZF265467comment_new=1728095796337; LH34568comment_new=1728097473982; XWL9875435comment_new=1728527431173; macbookpro11comment_new=1728527893501; 2401_86608357comment_new=1728098574871; DJV2980765comment_new=1728098240923; PZX87654323comment_new=1728097374058; BXL3456comment_new=1728527431173; ZWL53223456789comment_new=1728527893501; LFY5678comment_new=1728527893501; 2401_86608312comment_new=1728098574871; LJ11111111111111comment_new=1728555194217; ZXYokjhgf9comment_new=1728098036535; m0_74022416comment_new=1728000689235; p_uid=U010000; _clck=11wdv14%7C2%7Cfq3%7C0%7C1550; c_hasSub=true; dc_session_id=10_1729175765288.453571; SESSION=916090cc-ace8-433f-9594-3316846f6056; hide_login=1; weixin_48861542comment_new=1727923514877; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dblog; c_dsid=11_1729179618383.496997; log_Id_pv=239; log_Id_view=287; creative_btn_mp=1; loginbox_strategy=%7B%22taskId%22%3A349%2C%22abCheckTime%22%3A1729179628814%2C%22version%22%3A%22exp11%22%2C%22blog-threeH-dialog-exp11tipShowTimes%22%3A1%7D; popPageViewTimes=1; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1728005232,1729179639; log_Id_click=254; UserName=tianshu11tianshu; UserInfo=be7a087402ab4ad08ddc4c64f2c1f0b6; UserToken=be7a087402ab4ad08ddc4c64f2c1f0b6; UserNick=%E9%A6%A8%E5%84%BF%E4%B9%9F%E6%98%AF%E7%A0%81%E5%86%9C%E5%93%A6; AU=07D; UN=tianshu11tianshu; BT=1729179917705; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%22tianshu11tianshu%22%7D; https_waf_cookie=5a538a3f-8153-4dad08d78bc0a95a64466838b7da7eb06f9e; cms_blog_nav_flag=true; c_pref=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dblog; c_ref=https%3A//blog.csdn.net/%3Fspm%3D1001.2014.3001.4477; c_utm_medium=distribute.pc_feed_blog.none-task-blog-hot-2-142898129-null-null.nonecase; c_page_id=default; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1729179939; _clsk=1gjqfq1%7C1729179939357%7C5%7C0%7Cr.clarity.ms%2Fcollect; waf_captcha_marker=ac40e082ab902b0d5ffd368e7775942d50fb0e0349e3772d8f9345163001ab24; dc_tos=slibse\n";

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

