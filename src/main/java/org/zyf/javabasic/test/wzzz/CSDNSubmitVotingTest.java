package org.zyf.javabasic.test.wzzz;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: submitVoting
 * @author: zhangyanfeng
 * @create: 2025-02-15 11:25
 **/
public class CSDNSubmitVotingTest {

    public static void main(String[] args) {
        String userIdentification="13948734830";
        String cookie="csdn_newcert_LJ11111111111111=1; csdn_newcert_DJV2980765=1; csdn_newcert_pengyou23452=1; csdn_newcert_SR9872345686689=1; csdn_newcert_PZX87654323=1; csdn_newcert_CMuhgf7654=1; csdn_newcert_CWFDSDFGHJ1098=1; csdn_newcert_LYLWDFGH4567=1; csdn_newcert_JX275431234568=1; csdn_newcert_PZX9845=1; csdn_newcert_ZXYokjhgf9=1; csdn_newcert_ZF265467=1; csdn_newcert_LLN98765=1; csdn_newcert_LH34568=1; csdn_newcert_JH8876434=1; csdn_newcert_BXL3456=1; csdn_newcert_XWL9875435=1; csdn_newcert_LFY5678=1; csdn_newcert_macbookpro11=1; csdn_newcert_2401_86608312=1; csdn_newcert_2401_89508972=1; csdn_newcert_2401_89509470=1; c_ins_prid=-; c_ins_rid=1735865600518_372279; c_ins_fref=https://blog.csdn.net/2401_89360015; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1735865601137; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoiMjQwMV84OTM2MDAxNSIsInVzZXJJZCI6IjY3NzczNTAxZmMyOTJlMTJmZWNiZGEzYyIsInVzZXJuYW1lIjoiMjQwMV84OTM2MDAxNSJ9.qGdSRoAGABKhi6gVhzvHOm92ARD339Ubu8jjJN3FeLI; csdn_newcert_2401_89360015=1; csdn_newcert_2401_89360075=1; csdn_newcert_zcc_victor=1; csdn_newcert_JFF201412031708=1; csdn_newcert_2401_89465532=1; csdn_newcert_2401_89473361=1; csdn_newcert_2401_89496917=1; csdn_newcert_2401_89357748=1; csdn_newcert_2401_89358905=1; csdn_newcert_AKE2014=1; csdn_newcert_JFJFJFJFJFJF2014=1; csdn_newcert_2401_89496617=1; csdn_newcert_2401_89359839=1; csdn_newcert_JF20141203=1; csdn_newcert_sinat_20516251=1; csdn_newcert_u011374437=1; csdn_newcert_akppp=1; dc_sid=a82cec484dddd5c6fa70762984d79163; FCCDCF=%5Bnull%2Cnull%2Cnull%2Cnull%2Cnull%2Cnull%2C%5B%5B13%2C%22%5B%5C%22DBABL~BVQqAAAAAg%5C%22%2C%5B%5B7%2C%5B1736653136%2C354310000%5D%5D%5D%5D%22%5D%5D%5D; FCNEC=%5B%5B%22AKsRol-1UkIDAAg6MsFwm5I5d7sj6FgyQT-jeWULnuEqSgJYqxQJnGmsRMcM7i6GV267M6TWOIrvQ7Y0_HefazdTPeJ_zD0BzJbnjVssHlBvJ-5h_jefT3GdItNEEcvLmm10hNuoUebXupK_-CeXf8r82pHsO1GoUg%3D%3D%22%5D%5D; csdn_newcert_m0_74022416=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_xiaoxu2022xiaoxu=1; csdn_newcert_2401_86609655=1; csdn_newcert_u010861107=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608186=1; csdn_newcert_2401_86608273=1; HMACCOUNT=2B5DA67F120A89C5; uuid_tt_dd=10_20886653410-1736662831923-179767; fid=20_97458744433-1736663253424-121458; c_segment=7; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_20886653410-1736662831923-179767!5744*1*CMuhgf7654; csdn_newcert_xiaofeng10330111=1; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1737169944; _ga=GA1.2.881309776.1737198515; _ga_7W1N0GEY1P=GS1.1.1737215237.3.1.1737215244.53.0.0; csdn_newcert_m0_74022498=1; csdn_newcert_ZWL53223456789=1; csdn_newcert_qq_42878414=1; csdn_newcert_STC91s=1; csdn_newcert_weixin_48602413=1; csdn_newcert_2401_89508257=1; csdn_newcert_2402_89486958=1; csdn_newcert_xingyi51=1; csdn_newcert_2401_89487402=1; csdn_newcert_2401_89488159=1; csdn_newcert_2401_89508915=1; csdn_newcert_2401_89509302=1; csdn_newcert_2401_89509544=1; csdn_newcert_2401_89509701=1; csdn_newcert_2401_89509744=1; csdn_newcert_2401_89510128=1; csdn_newcert_2401_89510197=1; csdn_newcert_2401_89510257=1; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111/article/details/139702063%3Fspm%3D1001.2014.3001.5501; csdn_newcert_ZXMXMXM66666=1; csdn_newcert_2401_89510271=1; csdn_newcert_QQQ777111_=1; csdn_newcert_2402_89510315=1; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1738067784:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1738067784:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=a196d5e2b4979ec0:T=1729211486:RT=1738067784:S=AA-AfjbE9fL1C-ymryWPWJIEyM0x; p_uid=U010000; _clck=6feo0j%7C2%7Cftg%7C0%7C1829; dc_session_id=10_1739583504574.125487; SESSION=5e5a0a17-320d-4d5d-89c5-01f7c4448d28; c_dsid=11_1739583505903.745386; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1739583506325%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E6%AF%95%E5%B0%8F%E9%BE%99%22%2C%22blog-threeH-dialog-expa%22%3A1738067287488%7D; hide_login=1; creative_btn_mp=3; c_utm_source=979528761; utm_source=979528761; c_utm_medium=distribute.app_letter_persion.1065508.nonecase; toolbar_remind_num=3; tfstk=gZfiZUcmLHiW3II-XMR_eQTui4wKfVOXpihvDIK4LH-BDPt9XZScoaJV6hEfn9jhqjKwXOncnC1dBjKTfIbV2Iq827FR5NOBguE-KzAZIQ8DuxJZDp-eMEAlXiCh5NOjR7mqKhbsmrOydGRV0BJelEdq_fSqTX-BlEowufzh8HteuEJZQXkezUDwgISV8yYXYnlwCuxkgBfUYpUR4qs8v6TMI37wbNQOTlptCNKn_f5hBdxziHcqg6YGJGSbyfPkYOBV6B5aYmdFP9Iw-QVmoeXl8C8Gc7G6_a7F3df0H4tfKwWJK9gT5efGrsYkd-ovDLfF6C63qbKPdZ5DLNZoFeBVy1BcXkG2XTbFxLCKvbdFsGfwKQSz3vkzA8hX8rCEhx9wRexJ9Nzhrlp1VUU3-YapQeTs2y4nh5vwRex8-yDr9d8B50C..; c_pref=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dlately; c_ref=https%3A//i.csdn.net/; log_Id_click=871; UserName=xingyi51; UserInfo=35ce62e63add482ebbd2b348c1b63c3e; UserToken=35ce62e63add482ebbd2b348c1b63c3e; UserNick=xingyi51; AU=34D; UN=xingyi51; BT=1739590798925; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%22xingyi51%22%7D; c_page_id=default; log_Id_view=12024; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1739590807; log_Id_pv=1355; dc_tos=srpguz";
        doSubmitVoting( userIdentification,  cookie);

    }

    public static void doSubmitVoting(String userIdentification, String cookie) {
        //对圈定的文章进行评论处理
        AtomicInteger num = new AtomicInteger();
        String finalCookie = cookie;
        for (int i = 0; i < 9; i++) {
            num.getAndIncrement();

            // 请求URL
            String url = "https://bizapi.csdn.net/blog-activity/web/v1/vote/submitVoting";

            // 准备请求参数
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("voteCount", 1);
            parameters.put("voteNo", "150");
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
                httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
                httpPost.setHeader("Accept", "application/json, text/plain, */*");
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
                        " 投票：" + i + " 成功, 这是自开始进行的第" + num.get() + "次。");
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                System.out.println(userIdentification + "在" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 投票：" + i + " 存在异常！, 这是第" + num.get() + "次了");
            }
        }
        System.out.println(userIdentification + "在" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                "投票完成！！！");
    }

    // 辅助方法：将Map转为URL编码的表单数据格式
    private static String getFormUrlEncodedData(Map<String, Object> params) throws UnsupportedEncodingException {
        StringBuilder form = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (form.length() > 0) {
                form.append("&");
            }
            form.append(URLEncoder.encode(entry.getKey(), String.valueOf(StandardCharsets.UTF_8)));
            form.append("=");
            form.append(URLEncoder.encode(entry.getValue().toString(), String.valueOf(StandardCharsets.UTF_8)));
        }
        return form.toString();
    }
}
