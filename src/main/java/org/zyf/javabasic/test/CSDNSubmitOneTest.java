package org.zyf.javabasic.test;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNSubmitTest
 * @author: zhangyanfeng
 * @create: 2024-09-28 18:36
 **/
public class CSDNSubmitOneTest {
    public static void main(String[] args) {
        String articleId = "105360860";
        String cookie = "";
        cookie = "";
        cookie = "log_Id_click=253; log_Id_pv=238; log_Id_view=286; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19405898120-1691850414206-685544!5744*1*xiaofeng10330111; c_ins_fpage=/index.html; c_ins_um=-; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22xiaofeng10330111%22%2C%22scope%22%3A1%7D%7D; cf_clearance=XMWU4xMPiaIdAetK9mRZQczpWrQw8sgp1E5ClpufgAM-1713717920-1.0.1.1-bA9M96DmKE67yyESCSVzwAQfKZSWOwaq0le8OyuGIbfOajiK0dLYliXHrYwy47_LyncUk6Z_A0CckkfHQ2IuUQ; FCCDCF=%5Bnull%2Cnull%2Cnull%2C%5B%22CP-FskAP-FskAEsACBENAzEoAP_gAEPgAARoINJB7D7FbSFCwH5zaLsAMAhHRsCAQoQAAASBAmABQAKQIAQCgkAQFASgBAACAAAAICZBIQIECAAACUAAQAAAAAAEAAAAAAAIIAAAgAEAAAAIAAACAAAAEAAIAAAAEAAAmAgAAIIACAAAhAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAQOhQD2F2K2kKFkPCmQWYAQBCijYEAhQAAAAkCBIAAgAUgQAgFIIAgAIFAAAAAAAAAQEgCQAAQABAAAIACgAAAAAAIAAAAAAAQQAAAAAIAAAAAAAAEAAAAAAAQAAAAIAABEhCAAQQAEAAAAAAAQAAAAAAAAAAABAAA%22%2C%222~2072.70.89.93.108.122.149.196.2253.2299.259.2357.311.313.323.2373.358.2415.415.449.2506.2526.486.494.495.2568.2571.2575.540.574.2624.609.2677.827.864.981.1029.1048.1051.1095.1097.1126.1205.1211.1276.1301.1365.1415.1423.1449.1570.1577.1598.1651.1716.1735.1753.1765.1870.1878.1889.1958~dv.%22%2C%2293F3F4AF-7190-4BE3-8FDC-BFC6D18FD6EA%22%5D%5D; cf_clearance=QsOo9JUWOmltfyEDwa6e1uYT6E5LreCRRMyEz7W_ZUk-1716736886-1.0.1.1-IuyPBdCyyx5Wlqwk2bOtC_ImFvNmfM9zBnpyRlEbEKIcQ0folNluVAvWovtTwhD5YxMJMqT80p.kCV8qr1v7zg; historyList-new=%5B%5D; fpv=95a6ffb9aa1e3b3ee974b6dc4d7a2bfa; csrfToken=e19KMLEPzo6GxX0KfQmm80Mv; HMACCOUNT=7FA25E471A580294; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSIsInVzZXJJZCI6IjYzMjE0OTFlN2ZkZTYzN2ZhM2E0NTFhYyIsInVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSJ9.7ZvHFeRVs9zjuvLCY5nyfHR86sS-Bfv1PbLR-SJxn2c; c_ins_prid=1713084886109_157557; c_ins_rid=1720342991428_437221; c_ins_fref=https://mp.csdn.net/mp_blog/creation/editor/140244821; c_segment=2; uuid_tt_dd=10_19405897440-1721924064334-906450; fid=20_54925035130-1723377085963-458324; FCNEC=%5B%5B%22AKsRol8nayU7eeTg9egfHd2G154RLWCn6i4gw6hi-VsZJmbkwKtnlcU1vCK3RYZ9r4VYXKkfNPAHPNCEUF4YJn5HFcI2KdwIR0tNpbBut1iGDhMLqMi0PFr6wanNx_VOFlcMVubrwl68rl9skcdTD5OGycV1hnfUWg%3D%3D%22%5D%5D; csdn_newcert_xiaofeng10330111=1; csdn_newcert_m0_74022416=1; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608312=1; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; c_dl_um=-; cms_blog_nav={%22cate1%22:{%22type%22:%22back-end%22%2C%22title%22:%22%E5%90%8E%E7%AB%AF%22}}; csdn_newcert_macbookpro11=1; 2401_86608357comment_new=1722740365361; m0_74022416comment_new=1722743720968; _ga=GA1.1.1003498981.1691850421; _ga_7W1N0GEY1P=GS1.1.1725258902.53.0.1725258908.54.0.0; 2401_86609655comment_new=1725273017536; 2301_76981999comment_new=1723508010369; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1724494228; Hm_lpvt_e5ef47b9f471504959267fd614d579cd=1725280099; ssxmod_itna=QqjxyDRGDtf6DXDnD+xbTG87D7BGC/KQYIiUDBw3q4iNDnD8x7YDvG+pFBGiBiR0OhxWx5Co24aW0A3ofPrr=ioNdD84i7DKqibDCqD1D3qDkHtxYA8Dt4DTD34DYDio=DBUHzQDFmX/6gU8j0RfQgDif2/4DRKqDgGeD1/dXZmM3emqDAzeGylqGf/4GgUkj6DY=DQdc27WtDj4bbTcuXDYPW7uTNxBjdxkdDCEtlnkQCh7DtI+5z7D+QWOD3FBqoBADoU0SNb4eUa7GxmDhRVY4dj44t9j+9XiD===; ssxmod_itna2=QqjxyDRGDtf6DXDnD+xbTG87D7BGC/KQYIiD8Meh8xGXhXPGaiKBAOkvx8rbI3TdqR9hiKhFjY/gyvFQMZwTb3euiLpewPZorbLLrqK9vdsr=TCD6Gay6KTdwy0Q6S=HkL8Yo2fUFkvVzhYwADE0FuPyfRED=Rrc/xoxphttCEbSKAP=p2oN1SKlSCtOBMKn3pAhD=QeY=f+Fqnu0iIQOlKickK/fW0OepY4/jpWXSHy7+4etIaO44OCNxG2I4GcDiQteD==; u010861107comment_new=1723508010369; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1725411238; 2401_86608273comment_new=1722740183971; 2401_86608186comment_new=1722838386417; weixin_48861542comment_new=1722743720968; 2401_86608312comment_new=1725620102011; tianshu11tianshucomment_new=1725864926883; Hm_lvt_ec8a58cd84a81850bcbd95ef89524721=1726118079; c_dl_prid=1724809271365_229307; c_dl_rid=1726375509293_793801; c_dl_fref=https://blog.csdn.net/xiaofeng10330111; c_dl_fpage=/download/xiaofeng10330111/89599955; __gads=ID=c795c26813c2bc45:T=1725595434:RT=1726663250:S=ALNI_MZhQt9Gsnf5F6jUcD77EJW3lGsuUQ; __eoi=ID=bf8f4c90cf2d5294:T=1722526844:RT=1726663250:S=AA-AfjbeW90QxCaN_ybAlwSCJiVk; xiaoxu2022xiaoxucomment_new=1726139774231; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1727012014998%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E9%A6%A8%E5%84%BF%E4%B9%9F%E6%98%AF%E7%A0%81%E5%86%9C%E5%93%A6%22%2C%22blog-threeH-dialog-expa%22%3A1724495489897%7D; dc_sid=031f72886fb1be946fb1caeee1cb7693; Hm_lpvt_ec8a58cd84a81850bcbd95ef89524721=1727063898; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/2401_85012481/article/details/138956681; xiaofeng10330111comment_new=1727170835057; dc_session_id=10_1727515096296.882817; c_dsid=11_1727515097044.136747; _clck=11wdv14%7C2%7Cfpk%7C0%7C1550; creative_btn_mp=3; c_hasSub=true; SESSION=d5fa5e12-7551-4b43-bc8a-118258ec4cbd; hide_login=1; https_waf_cookie=e0166ae7-bbaf-40ec3c9c9ab639c8d7ad9fc0bd0e996bb2cc; p_uid=U010000; fe_request_id=1727519837813_0446_5489991; c_pref=https%3A//zyfcodes.blog.csdn.net/%3Ftype%3Dblog; c_ref=https%3A//so.csdn.net/so/search%3Fq%3DZGC%26t%3Dblog%26u%3Dxiaofeng10330111; c_utm_medium=distribute.pc_search_result.none-task-blog-2%7Eblog%7Efirst_rank_ecpm_v1%7Erank_v31_ecpm-1-105360860-null-null.nonecase; c_utm_term=ZGC; dc_tos=skiqua; referrer_search=1727522339438; UserName=tianshu11tianshu; UserInfo=2aa65a4f78974898af30c7b1657e7ca7; UserToken=2aa65a4f78974898af30c7b1657e7ca7; UserNick=%E9%A6%A8%E5%84%BF%E4%B9%9F%E6%98%AF%E7%A0%81%E5%86%9C%E5%93%A6; AU=07D; UN=tianshu11tianshu; BT=1727523868946; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%22tianshu11tianshu%22%7D; c_page_id=default; log_Id_pv=239; log_Id_view=287; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1727523872; _clsk=1vpiuvo%7C1727523872317%7C36%7C0%7Cr.clarity.ms%2Fcollect; waf_captcha_marker=eac8a5551bece55d6c4e6a4dc2144ed53a13cd2aa5179f55ca6109a781780e9c; tfstk=gGoiGKsc8W1Wue7QGpr6oRyxGtTLCOZbJjIYMoF28WPQMdNT6xlmnYzqX5djo2cnZiFa6VCmoliKWiF91oDqwoAJw3K-CAZQ0QdJP_yf5oy2DS--ZsvD4oRJwHe2a0pz0qCKwKbnLWw0QOSZgW5UO8V405zVTwyuTSrqgryF88yG7OS40vJ3hWP40oltXQVu0kme1S083xZtyD2gS7kHPiSmOgUgaAPh0Eo3ICVrQWjV05NAERMqgIjY44GnmzlXmMZtE2rUI2vV85zo7feIi3ja_DDZb5nw9GFZfvu-DXJV0S0EzzFZRBtb74GtzympsMNr8vDY-29fWS373jaSDQs__xkSV4Ekmt2nrvrh4QQFzL0RcJJxYZ_b7Jw3wRPWIXVflKjpKpbWpPyQC_pHKZtg7Jw3NpvhziUadRTG.; log_Id_click=254; dc_tos=skityr";
        CSDNComments commentsInit = new CSDNComments();
        List<String> comments = commentsInit.getComments(articleId);
        if (CollectionUtils.isEmpty(comments)) {
            System.out.println("请检查需要进行的文章处理信息");
            return;
        }

        String finalCookie = cookie;
        comments.forEach(coment -> {
            // 请求URL
            String url = "https://blog.csdn.net/phoenix/web/v1/comment/submit";

            // 准备请求参数
            Map<String, String> parameters = new HashMap<>();
            parameters.put("content", coment);
            parameters.put("articleId", articleId);
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
                        " 回复文章：" + articleId + "成功");
                Thread.sleep(22000);
            } catch (InterruptedException e) {
                System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 回复文章：" + articleId + " 存在异常！");
                ;
            }
        });

        System.out.println("评论结束！！！！！！！！");
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
}
