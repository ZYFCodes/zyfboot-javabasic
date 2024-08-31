package org.zyf.javabasic.test;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: HttpPostExample
 * @author: zhangyanfeng
 * @create: 2024-08-31 09:08
 **/
public class HttpPostExample {
    public static void main(String[] args) throws IOException, ParseException {
        // 请求URL
        String url = "https://blog.csdn.net/phoenix/web/v1/comment/submit";

        // 准备请求参数
        Map<String, String> parameters = new HashMap<>();
        parameters.put("commentId", "34280906");
        parameters.put("content", "希望对你有帮助");
        parameters.put("articleId", "105361083");
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
            httpPost.setHeader("Cookie", "__gpi=UID=000009b2dc650faa:T=1691850423:RT=1699192279:S=ALNI_MY9LBNZER5MIu2FziAVx45S1E6qsA; log_Id_click=253; log_Id_pv=238; log_Id_view=286; p_uid=U010000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19405898120-1691850414206-685544!5744*1*xiaofeng10330111; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1713084887297; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22xiaofeng10330111%22%2C%22scope%22%3A1%7D%7D; cf_clearance=XMWU4xMPiaIdAetK9mRZQczpWrQw8sgp1E5ClpufgAM-1713717920-1.0.1.1-bA9M96DmKE67yyESCSVzwAQfKZSWOwaq0le8OyuGIbfOajiK0dLYliXHrYwy47_LyncUk6Z_A0CckkfHQ2IuUQ; FCCDCF=%5Bnull%2Cnull%2Cnull%2C%5B%22CP-FskAP-FskAEsACBENAzEoAP_gAEPgAARoINJB7D7FbSFCwH5zaLsAMAhHRsCAQoQAAASBAmABQAKQIAQCgkAQFASgBAACAAAAICZBIQIECAAACUAAQAAAAAAEAAAAAAAIIAAAgAEAAAAIAAACAAAAEAAIAAAAEAAAmAgAAIIACAAAhAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAQOhQD2F2K2kKFkPCmQWYAQBCijYEAhQAAAAkCBIAAgAUgQAgFIIAgAIFAAAAAAAAAQEgCQAAQABAAAIACgAAAAAAIAAAAAAAQQAAAAAIAAAAAAAAEAAAAAAAQAAAAIAABEhCAAQQAEAAAAAAAQAAAAAAAAAAABAAA%22%2C%222~2072.70.89.93.108.122.149.196.2253.2299.259.2357.311.313.323.2373.358.2415.415.449.2506.2526.486.494.495.2568.2571.2575.540.574.2624.609.2677.827.864.981.1029.1048.1051.1095.1097.1126.1205.1211.1276.1301.1365.1415.1423.1449.1570.1577.1598.1651.1716.1735.1753.1765.1870.1878.1889.1958~dv.%22%2C%2293F3F4AF-7190-4BE3-8FDC-BFC6D18FD6EA%22%5D%5D; cf_clearance=QsOo9JUWOmltfyEDwa6e1uYT6E5LreCRRMyEz7W_ZUk-1716736886-1.0.1.1-IuyPBdCyyx5Wlqwk2bOtC_ImFvNmfM9zBnpyRlEbEKIcQ0folNluVAvWovtTwhD5YxMJMqT80p.kCV8qr1v7zg; historyList-new=%5B%5D; fpv=95a6ffb9aa1e3b3ee974b6dc4d7a2bfa; csrfToken=e19KMLEPzo6GxX0KfQmm80Mv; HMACCOUNT=7FA25E471A580294; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSIsInVzZXJJZCI6IjYzMjE0OTFlN2ZkZTYzN2ZhM2E0NTFhYyIsInVzZXJuYW1lIjoieGlhb2ZlbmcxMDMzMDExMSJ9.7ZvHFeRVs9zjuvLCY5nyfHR86sS-Bfv1PbLR-SJxn2c; c_ins_prid=1713084886109_157557; c_ins_rid=1720342991428_437221; c_ins_fref=https://mp.csdn.net/mp_blog/creation/editor/140244821; c_segment=2; uuid_tt_dd=10_19405897440-1721924064334-906450; tianshu11tianshucomment_new=1722533159906; xiaoxu2022xiaoxucomment_new=1722476158044; 2401_86608186comment_new=1722730869421; weixin_48861542comment_new=1722743720968; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1722813149; fid=20_54925035130-1723377085963-458324; FCNEC=%5B%5B%22AKsRol8nayU7eeTg9egfHd2G154RLWCn6i4gw6hi-VsZJmbkwKtnlcU1vCK3RYZ9r4VYXKkfNPAHPNCEUF4YJn5HFcI2KdwIR0tNpbBut1iGDhMLqMi0PFr6wanNx_VOFlcMVubrwl68rl9skcdTD5OGycV1hnfUWg%3D%3D%22%5D%5D; u010861107comment_new=1723024333405; Hm_lvt_ec8a58cd84a81850bcbd95ef89524721=1723378618; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1722353371,1724494228; Hm_lpvt_e5ef47b9f471504959267fd614d579cd=1724494228; __gads=ID=dcf29a68b239f1fc-22804773f6df0084:T=1691850423:RT=1724498670:S=ALNI_MYuYasxdrs4gD62JoKd7SFw4KXT_g; __eoi=ID=bf8f4c90cf2d5294:T=1722526844:RT=1724498670:S=AA-AfjbeW90QxCaN_ybAlwSCJiVk; csdn_newcert_xiaofeng10330111=1; csdn_newcert_m0_74022416=1; csdn_newcert_m0_74022498=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_2401_86608273=1; csdn_newcert_2401_86608312=1; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; dc_sid=32ab20219bbad0c72910f4ccbcc1b162; c_dl_prid=1722692842958_945648; c_dl_rid=1724809271365_229307; c_dl_fref=https://www.baidu.com/link; c_dl_fpage=/download/u012538644/6446979; c_dl_um=-; _ga_7W1N0GEY1P=GS1.1.1724915035.52.0.1724915035.60.0.0; _ga=GA1.2.1003498981.1691850421; xiaofeng10330111comment_new=1724894542602; creative_btn_mp=3; c_hasSub=true; Hm_lpvt_ec8a58cd84a81850bcbd95ef89524721=1724993980; ssxmod_itna=euG=D5AIgD8D7zDX8qgDCqiIKYvtWwK0Qw7HN=HDl=mYxA5D8D6DQeGTbn=5T8NnmB2xQLKtSRIq6Y7QwKI5IU8euoFDneG0DQKGmDBKDSDWKD9UAr4GG0xBYDQxAYDGDDP9DGTVgRD7EtymuUj3kj6tLtDmfj0DGjRDiUoxi5xguXSRbwSDD3oxB6DxAf0D7tZ3LdDbqDu76j5LqDLIlWFq=QDbOpy4=WDtTT2S4DH0kX/3kHoKghfIBvqmYhFKhxiz7m4WhGqS7E5Dr4=YOeriXqf6Ddoz0uxzAZdeD===; ssxmod_itna2=euG=D5AIgD8D7zDX8qgDCqiIKYvtWwK0Qw7HN=D6E9nD0yi3K03=p9=oXNnD6AhIQzVbiqqrs=AcoF2woq9jvb85CFZ3cpTV=08RX8EcLLN2eM=UvI5YE+I802DxV6/cMxsVT39g7=+1z2nsq+5Fscn1APe+Znwxn6nD8l+G/7na7FD7dgTvqZEr0ngO/chHDzGfG2SEUScHYhEr=OmNjzpES/4U19nAGxvi7W0d4weU9/b=O4IEcCpuDylN1I5Ie7vwkXA9D3uNEzcRP/8XtdZdIF=cGgYG2HBvWS0dt37TI8eb6qISDBN7c//2SfHh47gzRaza=84IenISqlAB8eb5KD/AYb7e=t9lIANmYR/xorAYNEohRLSnYz0KLwpSFh0cbU4=uqhFehQDb/WiYiQPnt/eo1Ku8EDxP7bcuK+bNG3D070eDLxD2zGDD===; tfstk=fZHqaN0QAR0Wbju3Koea43khoPeYI-YIiAa_jcmgcr4D6CUiaVmtC5iifOrgrD4b5jTYS4rUbNq0jPvlqky06ja_lPyZX2-WRp9IDmeTEeTBd3NwK4wTSdwGTztydRYBRL_4Ow2LIfZ8Mg_rqzEajsqiS82ubzfGmV40ZaqUAR2gIVquElq3SoV0mzXuyl2gSV2MzHFzTLr3iH52LincOzF4-0RdIOzAdSznbzWM-Yr2Iym0zOXgkZDcNc0M7Own94katVpNe-iEZ8qqahfuoWlmlu3wLt4mUmD0lxY5hrDxqXMsrhXg7bc3OrcJydFm9qG3aYTVeyco4cNrwhCb5XEiWWH6JtUmjDHKOJJNbulEq8SyENEksYHtgNfaiuEzR3-rOGDWYOom5-1O670_4ytxD1CTiSrzR3-P61FAFuzBDmC..; fe_request_id=1725014756235_3576_4962551; hide_login=1; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dblog; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1725014822433%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF%22%2C%22blog-threeH-dialog-expa%22%3A1724495489897%7D; SESSION=5ffd9e10-e6f1-4eef-8709-170cd6783267; UserName=xiaofeng10330111; UserInfo=e184e3e867a742a6964ff8d78a38ba20; UserToken=e184e3e867a742a6964ff8d78a38ba20; UserNick=%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF; AU=F6D; UN=xiaofeng10330111; BT=1725026084313; cms_blog_nav_flag=true; cms_blog_nav={%22cate1%22:{%22type%22:%22back-end%22%2C%22title%22:%22%E5%90%8E%E7%AB%AF%22}}; log_Id_click=254; log_Id_pv=239; log_Id_view=287; _clck=11wdv14%7C2%7Cfos%7C0%7C1550; https_waf_cookie=f83d2c45-43f8-4a89e3efa63a1702008f96bb2e72a94fabb7; dc_session_id=10_1725066205995.639912; waf_captcha_marker=37dc3d6ca0ca6ab284f0e7141a74788c76018912c0fea6b043188fe00cdacf51; c_pref=https%3A//blog.csdn.net/xiaofeng10330111/article/details/127557093%3Fcsdn_share_tail%3D%257B%2522type%2522%253A%2522blog%2522%252C%2522rType%2522%253A%2522article%2522%252C%2522rId%2522%253A%2522127557093%2522%252C%2522source%2522%253A%2522xiaofeng10330111%2522%257D; c_ref=https%3A//mp.csdn.net/mp_blog/creation/success/105361083; c_dsid=11_1725066227090.561637; c_page_id=default; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1725066228; _clsk=yo0bgb%7C1725066229378%7C3%7C0%7Cp.clarity.ms%2Fcollect; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20230921102607.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A219%2C%22type%22%3A2%2C%22oldUser%22%3Atrue%2C%22useSeven%22%3Afalse%2C%22oldFullVersion%22%3Atrue%2C%22userName%22%3A%22xiaofeng10330111%22%7D;");  // 替换为实际的cookie

            // 设置请求体
            httpPost.setEntity(new StringEntity(form));

            // 发送请求并获取响应
            try (CloseableHttpResponse response = client.execute(httpPost)) {
                // 输出响应状态码
                System.out.println("Response Code: " + response.getCode());
                // 输出响应体
                System.out.println("Response Body: " + new String(String.valueOf(response.getEntity().getContent().read())));
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
