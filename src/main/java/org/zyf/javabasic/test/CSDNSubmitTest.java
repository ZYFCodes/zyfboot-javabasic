package org.zyf.javabasic.test;

import com.google.common.collect.Maps;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNSubmit2Test
 * @author: zhangyanfeng
 * @create: 2024-09-01 14:02
 **/
public class CSDNSubmitTest {
    public static void main(String[] args) throws IOException, ParseException {

        for (int i = 0; i < 160; i++) {
            exe("130191656","34318527");
            System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                    " 回复次数：" + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 回复次数：" + i +
                        " 存在异常！");
                ;
            }
        }
    }

    public static void exe(String articleId,String commentId) throws IOException, ParseException {
        // 请求URL
        String url = "https://blog.csdn.net/phoenix/web/v1/comment/submit";

        // 准备请求参数
        Map<String, String> parameters = new HashMap<>();
        parameters.put("commentId", commentId);
        parameters.put("content", "感谢回复评论点赞\uD83D\uDC4D\uD83D\uDC4D,希望对你有帮助");
        parameters.put("articleId", articleId);
        String form = getFormUrlEncodedData(parameters);

        // 创建HttpClient
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // 构建HttpPost请求
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            httpPost.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            httpPost.setHeader("Accept-Encoding", "gzip, deflate, br, zstd");
            httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("Connection", "log_Id_click=253; log_Id_pv=238; log_Id_view=286; p_uid=U010000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19405898120-1691850414206-685544!5744*1*xiaofeng10330111; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1713084887297; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22xiaofeng10330111%22%2C%22scope%22%3A1%7D%7D; cf_clearance=XMWU4xMPiaIdAetK9mRZQczpWrQw8sgp1E5ClpufgAM-1713717920-1.0.1.1-bA9M96DmKE67yyESCSVzwAQfKZSWOwaq0le8OyuGIbfOajiK0dLYliXHrYwy47_LyncUk6Z_A0CckkfHQ2IuUQ; FCCDCF=%5Bnull%2Cnull%2Cnull%2C%5B%22CP-FskAP-FskAEsACBENAzEoAP_gAEPgAARoINJB7D7FbSFCwH5zaLsAMAhHRsCAQoQAAASBAmABQAKQIAQCgkAQFASgBAACAAAAICZBIQIECAAACUAAQAAAAAAEAAAAAAAIIAAAgAEAAAAIAAACAAAAEAAIAAAAEAAAmAgAAIIACAAAhAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAQOhQD2F2K2kKFkPCmQWYAQBCijYEAhQAAAAkCBIAAgAUgQAgFIIAgAIFAAAAAAAAAQEgCQAAQABAAAIACgAAAAAAIAAAAAAAQQAAAAAIAAAAAAAAEAAAAAAAQAAAAIAABEhCAAQQAEAAAAAAAQAAAAAAAAAAABAAA%22%2C%222~2072.70.89.93.108.122.149.196.2253.2299.259.2357.311.313.323.2373.358.2415.415.449.2506.2526.486.494.495.2568.2571.2575.540.574.2624.609.2677.827.864.981.1029.1048.1051.1095.1097.1126.1205.1211.1276.1301.1365.1415.1423.1449.1570.1577.1598.1651.1716.1735.1753.1765.1870.1878.1889.1958~dv.%22%2C%2293F3F4AF-7190-4BE3-8FDC-BFC6D18FD6EA%22%5D%5D; cf_clearance=QsOo9JUWOmltfyEDwa6e1uYT6E5LreCRRMyEz7W_ZUk-1716736886-1.0.1.1-IuyPBdCyyx5Wlqwk2bOtC_ImFvNmfM9zBnpyRlEbEKIcQ0folNluVAvWovtTwhD5YxMJMqT80p.kCV8qr1v7zg; historyList-new=%5B%5D; fpv=95a6ffb9aa1e3b3ee974b6dc4d7a2bfa; csrfToken=e19KMLEPzo6GxX0KfQmm80Mv; HMACCOUNT=7FA25E471A580294; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSIsInVzZXJJZCI6IjYzMjE0OTFlN2ZkZTYzN2ZhM2E0NTFhYyIsInVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSJ9.7ZvHFeRVs9zjuvLCY5nyfHR86sS-Bfv1PbLR-SJxn2c; c_ins_prid=1713084886109_157557; c_ins_rid=1720342991428_437221; c_ins_fref=https://mp.csdn.net/mp_blog/creation/editor/140244821; c_segment=2; uuid_tt_dd=10_19405897440-1721924064334-906450; fid=20_54925035130-1723377085963-458324; FCNEC=%5B%5B%22AKsRol8nayU7eeTg9egfHd2G154RLWCn6i4gw6hi-VsZJmbkwKtnlcU1vCK3RYZ9r4VYXKkfNPAHPNCEUF4YJn5HFcI2KdwIR0tNpbBut1iGDhMLqMi0PFr6wanNx_VOFlcMVubrwl68rl9skcdTD5OGycV1hnfUWg%3D%3D%22%5D%5D; Hm_lvt_ec8a58cd84a81850bcbd95ef89524721=1723378618; csdn_newcert_xiaofeng10330111=1; csdn_newcert_m0_74022416=1; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608312=1; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; c_dl_prid=1722692842958_945648; c_dl_rid=1724809271365_229307; c_dl_fref=https://www.baidu.com/link; c_dl_fpage=/download/u012538644/6446979; c_dl_um=-; cms_blog_nav={%22cate1%22:{%22type%22:%22back-end%22%2C%22title%22:%22%E5%90%8E%E7%AB%AF%22}}; csdn_newcert_macbookpro11=1; 2401_86608357comment_new=1722740365361; m0_74022416comment_new=1722743720968; _ga=GA1.1.1003498981.1691850421; _ga_7W1N0GEY1P=GS1.1.1725258902.53.0.1725258908.54.0.0; dp_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6Njg1NzQyMywiZXhwIjoxNzI1ODY0OTQ4LCJpYXQiOjE3MjUyNjAxNDgsInVzZXJuYW1lIjoibTBfNzQwMjI0OTgifQ.UJc6n54R8Gmrg0TwGSKA4Cd6KW7ivWFYO20bALr6Jig; 2401_86609655comment_new=1725273017536; Hm_lpvt_ec8a58cd84a81850bcbd95ef89524721=1725279009; 2301_76981999comment_new=1723508010369; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111/article/details/141375167; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1724494228; Hm_lpvt_e5ef47b9f471504959267fd614d579cd=1725280099; ssxmod_itna=QqjxyDRGDtf6DXDnD+xbTG87D7BGC/KQYIiUDBw3q4iNDnD8x7YDvG+pFBGiBiR0OhxWx5Co24aW0A3ofPrr=ioNdD84i7DKqibDCqD1D3qDkHtxYA8Dt4DTD34DYDio=DBUHzQDFmX/6gU8j0RfQgDif2/4DRKqDgGeD1/dXZmM3emqDAzeGylqGf/4GgUkj6DY=DQdc27WtDj4bbTcuXDYPW7uTNxBjdxkdDCEtlnkQCh7DtI+5z7D+QWOD3FBqoBADoU0SNb4eUa7GxmDhRVY4dj44t9j+9XiD===; ssxmod_itna2=QqjxyDRGDtf6DXDnD+xbTG87D7BGC/KQYIiD8Meh8xGXhXPGaiKBAOkvx8rbI3TdqR9hiKhFjY/gyvFQMZwTb3euiLpewPZorbLLrqK9vdsr=TCD6Gay6KTdwy0Q6S=HkL8Yo2fUFkvVzhYwADE0FuPyfRED=Rrc/xoxphttCEbSKAP=p2oN1SKlSCtOBMKn3pAhD=QeY=f+Fqnu0iIQOlKickK/fW0OepY4/jpWXSHy7+4etIaO44OCNxG2I4GcDiQteD==; u010861107comment_new=1723508010369; tfstk=fnbjgMsqXOQPV5N81AFyV4H2-BL65OaE1fOOt13q6ELA6F1OsdJ2bdSWC1BpbhCaiCB56t62bN1i5CCOISvaiu5ciFYTLJW_8sf0ZPhzFZtve_dCwCHAdZPsIFYTL8orWn4BS9oHE7P1w7Ow1jHOBdn8NBOpWqKtk0nJELp9Whd92YdHGqn9WnF5wLA9Wdh9KldscK1bw6XTyUrqwsvSW2GHGnUFGcus5aOXcNCvFIOfPItXp3ReeCbC9sQH1NVKfd1hDtRlCywWyZ6fk3T_54YVOgBXViwsP3jRtNK5qJihtsBfvHQYP7x5o68vFNViSKIV6N-1kJhwHZXVPHbih8JNY_7vV9erPOx1DMLA5JaR4agedhCjfbtn5Q9UN7iiju9On8UF3T0DDQAfi7NShhxvZQTTN7iijndkaDV7NxtG.; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1725411238; 2401_86608273comment_new=1722740183971; tianshu11tianshucomment_new=1725355042627; xiaoxu2022xiaoxucomment_new=1725355042627; 2401_86608186comment_new=1722838386417; https_waf_cookie=46e5ede6-1fab-4a4e70d8d0199b435d824ea3b93182bef4a4; weixin_48861542comment_new=1722743720968; 2401_86608312comment_new=1725620102011; _clck=11wdv14%7C2%7Cfp0%7C0%7C1550; c_dsid=11_1725770274889.626503; c_hasSub=true; SESSION=8a85fe46-3516-4cf7-9ed7-0b8a62fa09f4; hide_login=1; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1725770392844%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E9%AB%98%E6%B4%AA%E5%B2%A9%22%2C%22blog-threeH-dialog-expa%22%3A1724495489897%7D; creative_btn_mp=3; c_utm_source=zhuzhantoolbar; toolbar_remind_num=1; dc_session_id=10_1725762426597.684304; __gads=ID=c795c26813c2bc45:T=1725595434:RT=1725785056:S=ALNI_MZhQt9Gsnf5F6jUcD77EJW3lGsuUQ; __eoi=ID=bf8f4c90cf2d5294:T=1722526844:RT=1725785056:S=AA-AfjbeW90QxCaN_ybAlwSCJiVk; dc_sid=2e8782b9cb18c030f1c08f0acf485d7c; UserName=xiaofeng10330111; UserInfo=f0b67fdaf1d34daf96d2e9a9d3419d00; UserToken=f0b67fdaf1d34daf96d2e9a9d3419d00; UserNick=%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF; AU=F6D; UN=xiaofeng10330111; BT=1725787393530; cms_blog_nav_flag=true; xiaofeng10330111comment_new=1725790278141; log_Id_click=254; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20230921102607.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A235%2C%22type%22%3A2%2C%22oldUser%22%3Atrue%2C%22useSeven%22%3Afalse%2C%22oldFullVersion%22%3Atrue%2C%22userName%22%3A%22xiaofeng10330111%22%7D; log_Id_view=287; log_Id_pv=239; waf_captcha_marker=b1e8093db1ea00a4eaaa44fd59a7dc8b179129775a4532f1aa15707ce20a0eda; _clsk=1pnzlaa%7C1725797036043%7C67%7C0%7Cr.clarity.ms%2Fcollect; _clsk=1pnzlaa%7C1725797036043%7C67%7C0%7Cr.clarity.ms%2Fcollect; dc_tos=sjhtik; c_pref=https%3A//blog.csdn.net/xiaofeng10330111/article/list/2; c_ref=https%3A//zyfcodes.blog.csdn.net/%3Ftype%3Dblog; c_page_id=default; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1725797072; dc_tos=sjhtjw");
            //httpPost.setHeader("Cookie", "__gpi=UID=000009b2dc650faa:T=1691850423:RT=1699192279:S=ALNI_MY9LBNZER5MIu2FziAVx45S1E6qsA; log_Id_click=253; log_Id_pv=238; log_Id_view=286; p_uid=U010000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19405898120-1691850414206-685544!5744*1*xiaofeng10330111; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1713084887297; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22xiaofeng10330111%22%2C%22scope%22%3A1%7D%7D; cf_clearance=XMWU4xMPiaIdAetK9mRZQczpWrQw8sgp1E5ClpufgAM-1713717920-1.0.1.1-bA9M96DmKE67yyESCSVzwAQfKZSWOwaq0le8OyuGIbfOajiK0dLYliXHrYwy47_LyncUk6Z_A0CckkfHQ2IuUQ; FCCDCF=%5Bnull%2Cnull%2Cnull%2C%5B%22CP-FskAP-FskAEsACBENAzEoAP_gAEPgAARoINJB7D7FbSFCwH5zaLsAMAhHRsCAQoQAAASBAmABQAKQIAQCgkAQFASgBAACAAAAICZBIQIECAAACUAAQAAAAAAEAAAAAAAIIAAAgAEAAAAIAAACAAAAEAAIAAAAEAAAmAgAAIIACAAAhAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAQOhQD2F2K2kKFkPCmQWYAQBCijYEAhQAAAAkCBIAAgAUgQAgFIIAgAIFAAAAAAAAAQEgCQAAQABAAAIACgAAAAAAIAAAAAAAQQAAAAAIAAAAAAAAEAAAAAAAQAAAAIAABEhCAAQQAEAAAAAAAQAAAAAAAAAAABAAA%22%2C%222~2072.70.89.93.108.122.149.196.2253.2299.259.2357.311.313.323.2373.358.2415.415.449.2506.2526.486.494.495.2568.2571.2575.540.574.2624.609.2677.827.864.981.1029.1048.1051.1095.1097.1126.1205.1211.1276.1301.1365.1415.1423.1449.1570.1577.1598.1651.1716.1735.1753.1765.1870.1878.1889.1958~dv.%22%2C%2293F3F4AF-7190-4BE3-8FDC-BFC6D18FD6EA%22%5D%5D; cf_clearance=QsOo9JUWOmltfyEDwa6e1uYT6E5LreCRRMyEz7W_ZUk-1716736886-1.0.1.1-IuyPBdCyyx5Wlqwk2bOtC_ImFvNmfM9zBnpyRlEbEKIcQ0folNluVAvWovtTwhD5YxMJMqT80p.kCV8qr1v7zg; historyList-new=%5B%5D; fpv=95a6ffb9aa1e3b3ee974b6dc4d7a2bfa; csrfToken=e19KMLEPzo6GxX0KfQmm80Mv; HMACCOUNT=7FA25E471A580294; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSIsInVzZXJJZCI6IjYzMjE0OTFlN2ZkZTYzN2ZhM2E0NTFhYyIsInVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSJ9.7ZvHFeRVs9zjuvLCY5nyfHR86sS-Bfv1PbLR-SJxn2c; c_ins_prid=1713084886109_157557; c_ins_rid=1720342991428_437221; c_ins_fref=https://mp.csdn.net/mp_blog/creation/editor/140244821; c_segment=2; uuid_tt_dd=10_19405897440-1721924064334-906450; fid=20_54925035130-1723377085963-458324; FCNEC=%5B%5B%22AKsRol8nayU7eeTg9egfHd2G154RLWCn6i4gw6hi-VsZJmbkwKtnlcU1vCK3RYZ9r4VYXKkfNPAHPNCEUF4YJn5HFcI2KdwIR0tNpbBut1iGDhMLqMi0PFr6wanNx_VOFlcMVubrwl68rl9skcdTD5OGycV1hnfUWg%3D%3D%22%5D%5D; Hm_lvt_ec8a58cd84a81850bcbd95ef89524721=1723378618; csdn_newcert_xiaofeng10330111=1; csdn_newcert_m0_74022416=1; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608312=1; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; c_dl_prid=1722692842958_945648; c_dl_rid=1724809271365_229307; c_dl_fref=https://www.baidu.com/link; c_dl_fpage=/download/u012538644/6446979; c_dl_um=-; cms_blog_nav={%22cate1%22:{%22type%22:%22back-end%22%2C%22title%22:%22%E5%90%8E%E7%AB%AF%22}}; csdn_newcert_macbookpro11=1; 2401_86608357comment_new=1722740365361; m0_74022416comment_new=1722743720968; _ga=GA1.1.1003498981.1691850421; _ga_7W1N0GEY1P=GS1.1.1725258902.53.0.1725258908.54.0.0; dp_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6Njg1NzQyMywiZXhwIjoxNzI1ODY0OTQ4LCJpYXQiOjE3MjUyNjAxNDgsInVzZXJuYW1lIjoibTBfNzQwMjI0OTgifQ.UJc6n54R8Gmrg0TwGSKA4Cd6KW7ivWFYO20bALr6Jig; 2401_86609655comment_new=1725273017536; Hm_lpvt_ec8a58cd84a81850bcbd95ef89524721=1725279009; 2301_76981999comment_new=1723508010369; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111/article/details/141375167; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1724494228; Hm_lpvt_e5ef47b9f471504959267fd614d579cd=1725280099; ssxmod_itna=QqjxyDRGDtf6DXDnD+xbTG87D7BGC/KQYIiUDBw3q4iNDnD8x7YDvG+pFBGiBiR0OhxWx5Co24aW0A3ofPrr=ioNdD84i7DKqibDCqD1D3qDkHtxYA8Dt4DTD34DYDio=DBUHzQDFmX/6gU8j0RfQgDif2/4DRKqDgGeD1/dXZmM3emqDAzeGylqGf/4GgUkj6DY=DQdc27WtDj4bbTcuXDYPW7uTNxBjdxkdDCEtlnkQCh7DtI+5z7D+QWOD3FBqoBADoU0SNb4eUa7GxmDhRVY4dj44t9j+9XiD===; ssxmod_itna2=QqjxyDRGDtf6DXDnD+xbTG87D7BGC/KQYIiD8Meh8xGXhXPGaiKBAOkvx8rbI3TdqR9hiKhFjY/gyvFQMZwTb3euiLpewPZorbLLrqK9vdsr=TCD6Gay6KTdwy0Q6S=HkL8Yo2fUFkvVzhYwADE0FuPyfRED=Rrc/xoxphttCEbSKAP=p2oN1SKlSCtOBMKn3pAhD=QeY=f+Fqnu0iIQOlKickK/fW0OepY4/jpWXSHy7+4etIaO44OCNxG2I4GcDiQteD==; xiaofeng10330111comment_new=1725273017536; u010861107comment_new=1723508010369; tfstk=fnbjgMsqXOQPV5N81AFyV4H2-BL65OaE1fOOt13q6ELA6F1OsdJ2bdSWC1BpbhCaiCB56t62bN1i5CCOISvaiu5ciFYTLJW_8sf0ZPhzFZtve_dCwCHAdZPsIFYTL8orWn4BS9oHE7P1w7Ow1jHOBdn8NBOpWqKtk0nJELp9Whd92YdHGqn9WnF5wLA9Wdh9KldscK1bw6XTyUrqwsvSW2GHGnUFGcus5aOXcNCvFIOfPItXp3ReeCbC9sQH1NVKfd1hDtRlCywWyZ6fk3T_54YVOgBXViwsP3jRtNK5qJihtsBfvHQYP7x5o68vFNViSKIV6N-1kJhwHZXVPHbih8JNY_7vV9erPOx1DMLA5JaR4agedhCjfbtn5Q9UN7iiju9On8UF3T0DDQAfi7NShhxvZQTTN7iijndkaDV7NxtG.; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1725411238; weixin_48861542comment_new=1722740365361; 2401_86608312comment_new=1722743720968; 2401_86608273comment_new=1722740183971; _clck=11wdv14%7C2%7Cfox%7C0%7C1550; c_hasSub=true; dc_session_id=10_1725512990604.591601; c_dsid=11_1725512994594.319571; creative_btn_mp=3; SESSION=db047118-0f34-42a4-a8ac-a904a1b66298; dc_sid=06f56b31b39a7f9d648792b86654d381; hide_login=1; https_waf_cookie=9f848986-fe70-43db84b8108a7ebf2b8a028b9cf44b492c12; tianshu11tianshucomment_new=1725355042627; xiaoxu2022xiaoxucomment_new=1725355042627; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1725519585237%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E6%94%BF%E4%BC%AF%22%2C%22blog-threeH-dialog-expa%22%3A1724495489897%7D; 2401_86608186comment_new=1722838386417; __gads=ID=dcf29a68b239f1fc-22804773f6df0084:T=1691850423:RT=1725524624:S=ALNI_MYuYasxdrs4gD62JoKd7SFw4KXT_g; __eoi=ID=bf8f4c90cf2d5294:T=1722526844:RT=1725524624:S=AA-AfjbeW90QxCaN_ybAlwSCJiVk; UserName=xiaofeng10330111; UserInfo=0f16b820252d452b9c329a7d7b6b4636; UserToken=0f16b820252d452b9c329a7d7b6b4636; UserNick=%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF; AU=F6D; UN=xiaofeng10330111; BT=1725525480573; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20230921102607.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A220%2C%22type%22%3A2%2C%22oldUser%22%3Atrue%2C%22useSeven%22%3Afalse%2C%22oldFullVersion%22%3Atrue%2C%22userName%22%3A%22xiaofeng10330111%22%7D; c_utm_source=app; utm_source=app; log_Id_pv=239; log_Id_view=287; waf_captcha_marker=500aeff87d0099f47fc6c7ce69c7fc86605081e0bf5384ac2727c1a31a1d153d; log_Id_click=254; _clsk=16n061n%7C1725525549452%7C7%7C0%7Cn.clarity.ms%2Fcollect; c_pref=https%3A//i.csdn.net/; c_ref=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dblog; c_page_id=default; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1725525565; dc_tos=sjc01r; dc_tos=sjc02x");
            // 设置请求体
            httpPost.setEntity(new StringEntity(form));

            // 发送请求并获取响应
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                // 输出响应状态码
                System.out.println("Response Code: " + response.getCode());
                // 输出响应体
                // System.out.println("Response Body: " + new String(String.valueOf(response.getEntity().getContent().read())));
            }
        }
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
