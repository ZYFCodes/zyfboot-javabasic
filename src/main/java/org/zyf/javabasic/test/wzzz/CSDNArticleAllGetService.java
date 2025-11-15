package org.zyf.javabasic.test.wzzz;

import com.google.common.collect.Sets;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 过去对应账号的文章列表
 * @author: zhangyanfeng
 * @create: 2025-03-22 14:33
 **/
@Service
@Log4j2
public class CSDNArticleAllGetService {

    @Resource
    private CSDNLoginDealService loginDealService;

    public void doSubmit() {
        String cookies = "csdn_newcert_2401_86608273=1; HMACCOUNT=2B5DA67F120A89C5; uuid_tt_dd=10_20886653410-1736662831923-179767; fid=20_97458744433-1736663253424-121458; c_segment=7; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; csdn_newcert_xiaofeng10330111=1; csdn_newcert_m0_74022498=1; csdn_newcert_ZWL53223456789=1; csdn_newcert_qq_42878414=1; csdn_newcert_STC91s=1; csdn_newcert_weixin_48602413=1; csdn_newcert_2401_89508257=1; csdn_newcert_2402_89486958=1; csdn_newcert_xingyi51=1; csdn_newcert_2401_89487402=1; csdn_newcert_2401_89488159=1; csdn_newcert_2401_89508915=1; csdn_newcert_2401_89509302=1; csdn_newcert_2401_89509544=1; csdn_newcert_2401_89509701=1; csdn_newcert_2401_89509744=1; csdn_newcert_2401_89510128=1; csdn_newcert_2401_89510197=1; csdn_newcert_2401_89510257=1; csdn_newcert_ZXMXMXM66666=1; csdn_newcert_2401_89510271=1; csdn_newcert_QQQ777111_=1; csdn_newcert_2402_89510315=1; csdn_newcert_SVQ20241216=1; csdn_newcert_H2013212994=1; csdn_newcert_2501_90647030=1; csdn_newcert_lunyelancha=1; historyList-new=%5B%5D; csdn_newcert_2501_90648645=1; csdn_newcert_2501_90648487=1; csdn_newcert_pingcao185=1; csdn_newcert_gujian1014010101=1; csdn_newcert_QxiaoQQ=1; csdn_newcert_2501_90655610=1; csdn_newcert_qq_33518830=1; _ga=GA1.2.881309776.1737198515; _ga_7W1N0GEY1P=GS1.1.1740286121.4.0.1740286146.35.0.0; c_ins_prid=1735865600518_372279; c_ins_rid=1740305260785_314869; c_ins_fref=https://blog.csdn.net/2501_90655610; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoiMjUwMV85MDY1NTYxMCIsInVzZXJJZCI6IjY3YmFmMzZkNDNlNWMyN2U3MTZlMDFjZSIsInVzZXJuYW1lIjoiMjUwMV85MDY1NTYxMCJ9.jFiE2okDjfYBl37_p6rs5nNS_hxG4ONw55hhRMlyXHs; csdn_newcert_m0_73933556=1; dc_sid=57be7336334935765f246155a0bc280e; c_ab_test=1; csdn_newcert_DJV29807654=1; csdn_newcert_wdfdgygg77=1; csdn_newcert_kjj987=1; csdn_newcert_wire290=1; csdn_newcert_shark094=1; csdn_newcert_jarfg35=1; csdn_newcert_cloudman08=1; csdn_newcert_wengang345=1; csdn_newcert_junhui469=1; csdn_newcert_yaxin0765=1; csdn_newcert_xiayan827=1; csdn_newcert_hy098543=1; csdn_newcert_wenlong5o02=1; csdn_newcert_haicha028765=1; csdn_newcert_kai176567=1; csdn_newcert_deying0865423=1; csdn_newcert_chendong52329=1; csdn_newcert_suyang199312=1; csdn_newcert_kunming19850325=1; csdn_newcert_xuanyan19920827=1; csdn_newcert_wuyou199624=1; JSESSIONID=1FDA057CFB4242BCE82C10B99118A4D9; cknowvip-pc-close=1742018684778; csdn_newcert_hanyu5201314=1; csdn_newcert_sigen520520=1; csdn_newcert_ERG2oKns=1; csdn_newcert_chenzheng52029=1; csdn_newcert_WTYlaoda34=1; csdn_newcert_BaoLong543235=1; csdn_newcert_BXia47765432=1; csdn_newcert_GAoS97654=1; csdn_newcert_LIKai287656=1; csdn_newcert_RFS2jhj=1; csdn_newcert_DM19990205=1; csdn_newcert_EAJ20020527=1; csdn_newcert_YOW20011108=1; csdn_newcert_XSY19850824=1; csdn_newcert_ZHAO19911023=1; csdn_newcert_WGY1990730=1; csdn_newcert_TSJ19981027=1; csdn_newcert_XSK19930822=1; csdn_newcert_CHE19960919=1; csdn_newcert_ZGR20041209=1; csdn_newcert_ZJR20050718=1; csdn_newcert_2501_91205736=1; csdn_newcert_2501_91205858=1; csdn_newcert_2501_91205894=1; csdn_newcert_2501_91205926=1; csdn_newcert_2501_91206193=1; csdn_newcert_2501_91206293=1; csdn_newcert_2501_91206315=1; csdn_newcert_2501_91206613=1; csdn_newcert_2501_91206635=1; csdn_newcert_2501_91206658=1; csdn_newcert_2501_91206682=1; csdn_newcert_2501_91206720=1; csdn_newcert_2501_91206746=1; csdn_newcert_2501_91206763=1; csdn_newcert_2501_91206784=1; csdn_newcert_2501_91206810=1; csdn_newcert_2501_91206840=1; csdn_newcert_ZL5205201314=1; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/FrenzyTechAI/article/details/136066011; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_20886653410-1736662831923-179767!5744*1*xiaofeng10330111; ssxmod_itna=eqjxcDnDgDB7t4BP8e5GkiDOnIKk0iTo47IddD/4wDnqD=GFDK40EA7eKD7+n9Mjw2pWK9aRRP=QF=Du3pQ5F3aoIaQKxiTD4q07Db4GkDAqiOD7u+IYD4fKGwD0eG+DD4DW8qDUZA=qDd9Z9CyEpOm2P89xGWIoKDmOKDRroDSQ7sVOvF875Di=oDXoYDaoKDu6vOzxi8D7FLIxE1D7PpA9fA+xi3EDvA+40OktIHDBbzlgvOl8RtacTNdPhxLWRDQAD45rGetE1WMj6ozXio4ODbanqMsF4xrnUDD=; ssxmod_itna2=eqjxcDnDgDB7t4BP8e5GkiDOnIKk0iTo47IdG9i=3aDBMEe7P6LO9HD7mx8OziHXxRiB85N=R7B+y0IR4STFxwR9ExTaEdNImkXmrvZp5qp8rCKONuXzUpgQzeWvCtnBZUAF/zy9EkEcB2GSSEiyC3ExKGovU2r0If2wK8DKniL4XGoD2wvnBrhoOf0IbuDI41GsR22UK1DHkSAbK328XaahFjr4xqaWD7QHx7=Ded5xD===; tfstk=gUbsZhsZDtQEr5khsRFeO7GoIV8batazljOAZs3ZMFLtlI1yLNuansSCcT5JBIPgnrtAUt02B1PisMXPwV7ajEuXSEYYU8zzz5fMoEBikYdJI6CHMWRTpAiOkEYYUJRxpakJoTDlqTIO9WO2gChOHhCp9pArWEp9DvnpwppvktpvJXdyixHvHC3LOIvpHELAH6h9fodjfd1_6TyF7jhvg1pIkqQToh95vmuxkwO6fOf9d111RCt6eHcWzJb5w1QkGZV-cK1lXORhhzwBJN61WHTbl2YNNMB6AGwjRHjdZZKCx8ilZ1B123QTRWxCS_89dZVmodINMZ-fW8h2BNXNR3bmCJJV4679ATeERtxfXgLOl8ad4auyFnCscXtolB9zOWimmq1ezEmAups9XBA1sWNICnx9tBTYOWim0hdHT4FQOAtc.; csdn_newcert_2501_91206257=1; _clck=6feo0j%7C2%7Cfuf%7C0%7C1829; c_dsid=11_1742616453629.008391; creative_btn_mp=3; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1742616568; hide_login=1; p_uid=U010000; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1742616736937%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF%22%2C%22blog-threeH-dialog-expa%22%3A1742620596269%7D; csdn_newcert_tianshu11tianshu=1; csdn_newcert_2401_86609655=1; csdn_newcert_macbookpro11=1; csdn_newcert_2301_76981999=1; UN=2401_86609655; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1742625088:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1742625088:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=a196d5e2b4979ec0:T=1729211486:RT=1742625088:S=AA-AfjbE9fL1C-ymryWPWJIEyM0x; c_page_id=default; dc_session_id=10_1742566596550.205161; _clsk=1jzpduw%7C1742626456463%7C49%7C0%7Cq.clarity.ms%2Fcollect; SESSION=f81421a4-87e9-4b3e-bcdb-07a3bfa1dd4c; c_pref=https%3A//blog.csdn.net/2401_86609655%3Ftype%3Dblog; c_ref=https%3A//i.csdn.net/; log_Id_pv=241; log_Id_click=219; UserName=2401_86609655; UserInfo=9be802d743814ab38797c4d222716d88; UserToken=9be802d743814ab38797c4d222716d88; UserNick=CloudZzzzzzz; AU=D44; BT=1742628034583; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1742628037; dc_tos=stiked; log_Id_view=2801";
        cookies ="";
        cookies ="";
        cookies ="";
        cookies ="";
        System.out.println("cookies============================"+cookies);// 请求URL
        String url = "https://blog.csdn.net/community/home-api/v1/get-business-list";

        // 准备请求参数
        Map<String, String> parameters = new HashMap<>();
        parameters.put("page", "1");
        parameters.put("size", "20");
        parameters.put("businessType", "blog");
        parameters.put("orderby", "");
        parameters.put("noMore", "");
        parameters.put("year", "");
        parameters.put("month", "");
        parameters.put("username", "xiaofeng10330111");
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
            httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
            httpPost.setHeader("Accept", "application/json, text/plain, */*");
            httpPost.setHeader("Accept-Encoding", "gzip, deflate, br, zstd");
            httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpPost.setHeader("Connection", "keep-alive");
            httpPost.setHeader("sec-ch-ua", "\"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"");
            httpPost.setHeader("sec-ch-ua-mobile", "?0");
            httpPost.setHeader("sec-ch-ua-platform", "\"macOS\"");
            httpPost.setHeader("sec-fetch-dest", "empty");
            httpPost.setHeader("sec-fetch-mode", "cors");
            httpPost.setHeader("sec-fetch-site", "same-origin");
            httpPost.setHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36\n" +
                    "Privacy and security panel\n" +
                    "Test how a website behaves with limited third-party cookies and find relevant issues in the new 'Privacy' section of the evolved 'Privacy and security' panel.\n" +
                    "\n" +
                    "Calibrated CPU throttling presets\n" +
                    "Based on your users' experience, automatically calibrate and use more accurate CPU throttling presets for low- and mid-tier mobile devices.\n" +
                    "\n" +
                    "First- and third-party highlighting in Performance\n" +
                    "Reduce the noise of third-party data and hover over entries in a new table in Summary to distinguish between first- and third-party data in performance traces.");
            httpPost.setHeader("Cookie", cookies);
            // 设置请求体
            httpPost.setEntity(new org.apache.hc.core5.http.io.entity.StringEntity(form));

            // 发送请求并获取响应
            try (org.apache.hc.client5.http.impl.classic.CloseableHttpResponse response = client.execute(httpPost)) {
                // 输出响应状态码
                System.out.println("Response Code: " + response.getCode());
                // 输出响应体
                System.out.println("Response Body: " + new String(String.valueOf(response.getEntity().getContent().read())));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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


    public static void main(String[] args) {
        CSDNArticleAllGetService accountAllGetService = new CSDNArticleAllGetService();
        accountAllGetService.doSubmit();
    }
}
