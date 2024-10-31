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
        cookie = "";
        cookie = "";
        cookie = "uuid_tt_dd=10_17862519460-1727669887241-272040; fid=20_79733434169-1727669887445-187579; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; c_segment=8; HMACCOUNT=AF46696B098133A7; csdn_newcert_2401_86609655=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_2401_86608312=1; csdn_newcert_m0_74022416=1; csdn_newcert_ZWL53223456789=1; csdn_newcert_XWL9875435=1; csdn_newcert_BXL3456=1; csdn_newcert_JH8876434=1; csdn_newcert_LH34568=1; csdn_newcert_LLN98765=1; csdn_newcert_ZF265467=1; csdn_newcert_CWFDSDFGHJ1098=1; csdn_newcert_ZXYokjhgf9=1; csdn_newcert_CMuhgf7654=1; csdn_newcert_LYLWDFGH4567=1; csdn_newcert_PZX9845=1; csdn_newcert_PZX87654323=1; csdn_newcert_SR9872345686689=1; csdn_newcert_JX275431234568=1; csdn_newcert_pengyou23452=1; csdn_newcert_DJV2980765=1; csdn_newcert_LJ11111111111111=1; csdn_newcert_macbookpro11=1; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; csrfToken=d4DGJtn5C5NyXyH8qWL93s8e; csdn_newcert_LFY5678=1; fpv=4d2e9e704d25dbbd6675cbbd7d2fd13a; 2401_86609655comment_new=1727689491207; LJ11111111111111comment_new=1728000690081; m0_74022416comment_new=1726223241160; BXL3456comment_new=1728527431173; Hm_lvt_ec8a58cd84a81850bcbd95ef89524721=1728875799; Hm_lpvt_ec8a58cd84a81850bcbd95ef89524721=1728966967; c_first_ref=www.google.com.hk; 2401_86608273comment_new=1728551593298; 2401_86608357comment_new=1728557167888; csdn_newcert_xiaofeng10330111=1; p_uid=U010000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_17862519460-1727669887241-272040!5744*1*pengyou23452; c_first_page=https%3A//blog.csdn.net/blogdevteam/article/details/111173049; _ga=GA1.2.122272612.1727670031; _ga_7W1N0GEY1P=GS1.1.1730104369.14.1.1730104422.7.0.0; c_dl_prid=-; c_dl_rid=1730130196413_815010; c_dl_fref=https://download.csdn.net/; c_dl_fpage=/; c_dl_um=-; XWL9875435comment_new=1730162189467; tfstk=f4wsiSAw8reUOi1NNnIEN0cs8oMbGr6ycniYqopwDAHTDxZYmquZgqPIloU-gjEVjmUQDPaZg-ZDhmEYoG0Vj6rgjxDAzaz1UlqMxdaaigltJHEncFH7Qr80jxDA8ILpxUEgyqBwDiMAAvnqfEHxWdIIvc0pBmptDeII-mHvBVpxpvnZVKntXrEdAm0KkU5GsD-j-lsna3cUIhizX297xJiTfCqsRCAgdWGsytgBHKeIfkAiWrpOF2FaMRkLBwLqpuVUWDM5hniQNkHtfrXkIAE8vSh_hs-ooWZLaXNHbGmQCuwjVX5vuql7pRkgOa9ryWqYhXEGPQmUMuyaGP7HHVP7vyGzLeJrpRa_BXMR4qprPA7DGHGkhDgPAMODiO6uc2aWsqkZ6DmeDMsBadctxDMAAMODifnnYRSCAQJG.; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1730269353; _clck=1kqghg7%7C2%7Cfqh%7C0%7C1734; c_hasSub=true; dc_sid=6d60e410131f151e755407bb25a4efb3; hide_login=1; creative_btn_mp=3; c_pref=https%3A//blog.csdn.net/2202_76097976/article/details/143088438%3Fspm%3D1001.2100.3001.7377%26utm_medium%3Ddistribute.pc_feed_blog.none-task-blog-personrec_tag-1-143088438-null-null.nonecase%26depth_1-utm_source%3Ddistribute.pc_feed_blog.none-task-blog-personrec_tag-1-143088438-null-null.nonecase; c_ref=https%3A//blog.csdn.net/; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1730377263349%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E6%9F%B3%E5%AE%B6%E6%AC%A3%22%2C%22blog-threeH-dialog-expa%22%3A1729143686242%7D; __gads=ID=d23f7510d6636e6e:T=1727670033:RT=1730380396:S=ALNI_MZGD1pzgr06RMW3ZFW_batcuAJgWA; __gpi=UID=00000f273bc94a9a:T=1727670033:RT=1730380396:S=ALNI_MaCNtJz1QKs-8Ht7u7TMjv3z_6itw; __eoi=ID=7f8251b0aa589042:T=1727670033:RT=1730380396:S=AA-AfjZ-kDNbDfGNY2mruf_VAqiS; FCNEC=%5B%5B%22AKsRol8Q-tuYzi6JEdMs08tknVfR7mqPdJC1NtPeS8Nnce26Bmq_A_sRM27wAFQDbM1EnqZt5jwMZB2KVo4VFWOKcNVqpGvVjdIwl8sMHxMAvUW68yC9AiDBRdevFjExVYWKUCNZJnsy_DtlyEXSRATW8VRyArihfw%3D%3D%22%5D%5D; dc_session_id=10_1730387810416.299334; SESSION=2f26257d-fce8-4160-beb4-7adf6f27b172; https_waf_cookie=f291cdca-afc0-42bc3459e42863bc60f439d12ec3adad7cb4; c_dsid=11_1730387813120.733843; UserName=CMuhgf7654; UserInfo=488f82e3fb7543d8a278fa37aba28cb7; UserToken=488f82e3fb7543d8a278fa37aba28cb7; UserNick=%E7%A8%8B%E9%A2%90%E7%8C%9B; AU=FFF; UN=CMuhgf7654; BT=1730387826145; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%22CMuhgf7654%22%7D; c_utm_medium=distribute.pc_feed_blog.none-task-blog-hot-2-143276866-null-null.nonecase; c_page_id=default; log_Id_pv=3; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1730387832; _clsk=ejwzup%7C1730387832062%7C4%7C0%7Cw.clarity.ms%2Fcollect; log_Id_view=151; waf_captcha_marker=71e858317e0caf7cbb0504a750c5c2d8805b557cda4ec58db573922aff4d2674; dc_tos=sm87t2; log_Id_click=7\n";

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

