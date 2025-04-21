package org.zyf.javabasic.test.wzzz.subscribe;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.test.wzzz.like.CsdnLike;

import java.util.Objects;

/**
 * @program: zyfboot-javabasic
 * @description: CsdnSubscribeService
 * @author: zhangyanfeng
 * @create: 2025-04-20 20:58
 **/
public class CsdnSubscribeServiceTest {

    public static void main(String[] args) {
        String columnId = "12948277";
        String cookie="";
        cookie="";
        cookie="";
        cookie="";
        cookie="";
        cookie="Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1742616568,1744213625,1744213806; csdn_newcert_2501_91651594=1; csdn_newcert_2501_91651544=1; csdn_newcert_2501_91651383=1; csdn_newcert_2501_91651218=1; csdn_newcert_2501_91651303=1; csdn_newcert_2501_91651160=1; csdn_newcert_2501_91651127=1; csdn_newcert_2501_91650954=1; csdn_newcert_2501_91650936=1; csdn_newcert_2501_91650872=1; csdn_newcert_2501_91651253=1; csdn_newcert_2501_91652183=1; csdn_newcert_tianshu11tianshu=1; csdn_newcert_2501_91660265=1; fid=20_46622555328-1744729469672-273572; uuid_tt_dd=11_63424344622-1744729469672-771639; c_segment=7; csdn_newcert_2501_91660343=1; csdn_newcert_2501_91660514=1; csdn_newcert_2501_91660400=1; csdn_newcert_2501_91660430=1; csdn_newcert_2501_91660470=1; csdn_newcert_2501_91660486=1; csdn_newcert_2501_91661210=1; csdn_newcert_2501_91661267=1; csdn_newcert_2501_91661310=1; csdn_newcert_2501_91661377=1; csdn_newcert_2501_91667082=1; csdn_newcert_2501_91688610=1; csdn_newcert_2501_91688640=1; csdn_newcert_2501_91688658=1; csdn_newcert_2501_91688683=1; csdn_newcert_2501_91688707=1; csdn_newcert_2501_91688737=1; csdn_newcert_2501_91688765=1; csdn_newcert_2501_91688784=1; csdn_newcert_2501_91688803=1; csdn_newcert_2501_91688823=1; csdn_newcert_2501_91688838=1; csdn_newcert_2501_91688851=1; csdn_newcert_2501_91688893=1; csdn_newcert_2501_91688906=1; csdn_newcert_2501_91688925=1; csdn_newcert_2501_91688935=1; csdn_newcert_2501_91688949=1; csdn_newcert_2501_91688964=1; csdn_newcert_2501_91688986=1; csdn_newcert_2501_91689000=1; csdn_newcert_2501_91689013=1; csdn_newcert_2501_91689029=1; csdn_newcert_2501_91689047=1; Hm_lvt_e5ef47b9f471504959267fd614d579cd=1744551828,1744733991; csdn_newcert_pengyou23452=1; csdn_newcert_DJV2980765=1; csdn_newcert_2501_91721467=1; csdn_newcert_2501_91721479=1; csdn_newcert_2501_91721491=1; csdn_newcert_2501_91721579=1; csdn_newcert_2501_91721614=1; csdn_newcert_2501_91721626=1; csdn_newcert_sdfwsc=1; csdn_newcert_2401_86609655=1; csdn_newcert_2301_76981999=1; csdn_newcert_2401_86608312=1; csdn_newcert_LJ11111111111111=1; csrfToken=PoFkN_5gsP07H0xHZOHc1Tu3; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1744983199:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1744983199:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=b85dd8807a1abeff:T=1744983199:RT=1744983199:S=AA-AfjbzQ67fJZw9uwXtD1A3U0-P; FCNEC=%5B%5B%22AKsRol_YBM1Ps0vekzTdqPkb2wJ1d16-s0gQqTHYqyh5y7mxW-wZo0HlArV44oCJSnLHmk0K979mP78zFCLgh6GoWXvJsnM_UW-ESTxBsLWNE000hIN5_eyaAqA4wfRDBpwZyCfjUY2OavwT42rk1dCGWdeEOaG00A%3D%3D%22%5D%5D; csdn_newcert_PZX9845=1; csdn_newcert_PZX87654323=1; csdn_newcert_LYLWDFGH4567=1; csdn_newcert_CMuhgf7654=1; csdn_newcert_ZXYokjhgf9=1; csdn_newcert_ZF265467=1; csdn_newcert_LLN98765=1; csdn_newcert_LH34568=1; csdn_newcert_JH8876434=1; csdn_newcert_BXL3456=1; csdn_newcert_XWL9875435=1; csdn_newcert_ZWL53223456789=1; csdn_newcert_2401_89357748=1; dc_sid=d9b2a34ec65d7e7ba3291fade5576d48; csdn_newcert_qq_42878414=1; csdn_newcert_2401_89360015=1; csdn_newcert_AKE2014=1; csdn_newcert_STC91s=1; csdn_newcert_JF20141203=1; csdn_newcert_JFF201412031708=1; csdn_newcert_2401_89465532=1; csdn_newcert_2401_89473361=1; csdn_newcert_2401_89496617=1; csdn_newcert_2401_89496917=1; csdn_newcert_akppp=1; csdn_newcert_sinat_20516251=1; csdn_newcert_weixin_48602413=1; csdn_newcert_2401_89508257=1; csdn_newcert_2402_89486958=1; c_first_ref=default; HMACCOUNT=2B5DA67F120A89C5; csdn_newcert_xingyi51=1; csdn_newcert_2401_89488159=1; csdn_newcert_2401_89508915=1; csdn_newcert_2401_89509302=1; csdn_newcert_2401_89509544=1; csdn_newcert_2401_89509744=1; csdn_newcert_2401_89510257=1; csdn_newcert_2401_89510271=1; csdn_newcert_2402_89510315=1; csdn_newcert_H2013212994=1; csdn_newcert_2501_91652317=1; csdn_newcert_2501_91651722=1; csdn_newcert_2501_91651629=1; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1745063797136%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E5%91%A8%E8%89%BA%E2%82%AC%C2%A3%C2%A5%22%2C%22blog-threeH-dialog-expa%22%3A1744987523747%7D; csdn_newcert_xiaofeng10330111=1; _clck=erao71%7C2%7Cfv8%7C0%7C1908; creative_btn_mp=3; fe_request_id=1745123052575_6526_3445661; Hm_lpvt_e5ef47b9f471504959267fd614d579cd=1745128962; ssxmod_itna=YqfxnDBmKmqkDXQG7GT0=KRCG7DkI9KG//jDBTe4iNDnD8x7YDvGGTDGx8YXY52PWeRx7KdHSA84KaorG37rWPhUM4eD=xYQDwxYoDUxGtDpxG=dmDee=D5xGoDPxDeDAjKDCgNHKDdncFCcpaOw6cOnxGWpPKDmaKDRooDSeBzZrHFqi5DiPoDXxYDaPKDupHO1xi8D79fpgY1D7pmIU3Ahxi3ABKAG40OO3FHDBR1S2HOS4rrgictMnxeeAbrTBhxKZreQg2TgliuYi051GhoFD2tamTvsi0SFYD==; ssxmod_itna2=YqfxnDBmKmqkDXQG7GT0=KRCG7DkI9KG//D8MlwB4GXhNeGaKmH15Im=9kvnxQwQeXDDwQ4R0EMwQK/UGC7LAcC2hIuFlnrQSr+5c=x2vF+v+tjjwUt49vQD6AL0Lglz3WzbHopynTV20WsDmtRPceoiidefuaj=P1b3+x6Y5hFhoh4re1xEP142wdwUumTnmv7UAQ4ZmCqi7Ts9hWs/wQ1hgQl47FgFwt23RU3hEi+prLcD51sof2WIMseyfcYT5NR3ZzVfky9zWy2KPc7/H=PstNs/5j6Fo4q0EmgfCf=c6E7Qy0PEauGb28DcAC6+i+dOqn1owkCoGngGi4TvMHHCYvDDwPiDjKDeLY4D; tfstk=gL6mm4DiaUgBg_QKkaJbt1jIYPFJlm96qNH9WdLaaU87fdpAH_YGbwR9_GEXbajfooTYfo1iShKZ3xJwgLJyzNZjBo6ASCSANn-ZQEkP4ntB_NLODlmfEwDtHrNfbAv9QyULpJIbcd9aJZrFJYTXfg8a2IrE2P46QyULehzFpkJNWOj3v0jyPhxZ3E7wzL8WYAJwuCuraH8y7d7wg0SyXHMZ0n824ux6zFJwQNSr6eUH-dJlUz6qyAUcOTWkmIYFqWMZINkpieS2rAyREn4w8i8oQA8Ax7QdbNziBp5CrOAF5RDWHMfyUnfubb82ts7vVN2n3EWl0t8A3zHvutsP9OQzbA8Ga6Rpu_a7CI5Ck6RlerDW8_SlXQf7fjLOOh76OOUIhESNA91pKRmMq6jyEg8jaXrh9AtzXTls1I-WqeC4BR9D-lXifuqoOud2VngLquc_nI-WqeEuqX-Hg3tjJ; c_first_page=https%3A//blog.csdn.net/xiaofeng10330111/article/details/53034130; dc_tos=sv0fsi; dc_session_id=10_1745149220599.913416; c_dsid=11_1745149220786.839699; bc_bot_fp=586758c1d019b3ae913bb57fe376f478; SESSION=6e5dd091-2fdc-4078-81a0-ab1b81f6f42a; hide_login=1; UserName=2501_91652765; UserInfo=c3202a7daecb48469263ce3ada91f7a4; UserToken=c3202a7daecb48469263ce3ada91f7a4; UserNick=%E7%A6%A4%E6%A2%93%E7%9D%BF; AU=8CA; UN=2501_91652765; BT=1745152382146; p_uid=U010000; csdn_newcert_2501_91652765=1; c_pref=https%3A//mpbeta.csdn.net/mp_others/manage/course; c_ref=https%3A//blog.csdn.net/xiaofeng10330111%3Ftype%3Dblog; c_page_id=default; log_Id_pv=200; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1745152404; log_Id_view=3198; https_waf_cookie=b4239cfc-8716-45bb9547359a4fad46cac22e0979e37e5cf9; bc_bot_session=17451524551af345dfb2373827; _clsk=107yryp%7C1745153796830%7C25%7C0%7Cj.clarity.ms%2Fcollect; log_Id_click=142; dc_tos=sv0pq9";

        doSubscribe( cookie,  columnId);
    }

    public static String doSubscribe(String cookie, String columnId) {
        try {
            OkHttpClient client = new OkHttpClient();

            // 构造请求体（这里是 x-www-form-urlencoded，假设订阅参数是 articleId=144597752）
            RequestBody body = new FormBody.Builder()
                    .add("columnId", columnId)
                    .build();

            // 构造请求
            Request request = new Request.Builder()
                    .url("https://blog.csdn.net/phoenix/web/v1/subscribe/subscribe")
                    .post(body)
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .header("Accept", "application/json, text/javascript, */*; q=0.01")
                    .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                    .header("Origin", "https://blog.csdn.net")
                    //.header("Referer", "https://blog.csdn.net/2401_86609655/article/details/144597752?spm=1001.2014.3001.5502")
                    .header("X-Requested-With", "XMLHttpRequest")
                    // 注意：此处的 Cookie 很长，你可以只保留必要的几个认证字段（如 SESSION, UserToken）
                    .header("Cookie", cookie)
                    .build();

            // 发起请求
            Call call = client.newCall(request);

            Response response = call.execute();

            // 打印响应信息
            int code = 0;
            String responseBodyStr = "";
            if (response.isSuccessful()) {
                code = response.code();
                responseBodyStr = response.body().string();  // 只能调用一次
                System.out.println("请求成功！");
                System.out.println("响应码: " + code);
                System.out.println("响应体: " + responseBodyStr);
            } else {
                System.out.println("请求失败，状态码: " + code);
            }

            Result urlResult = JSON.parseObject(responseBodyStr, Result.class);
            if (urlResult == null || urlResult.getCode() != 200) {
                String describtionDetail = Objects.nonNull(urlResult) ? urlResult.getMessage() : "urlResult is null";
                System.out.println("返回数据并不合法:" + describtionDetail);
                return describtionDetail;
            }

            SubscribeInfo subscribeInfo = urlResult.getData();
            if (subscribeInfo.getStatus()) {
                System.out.println("订阅成功=====================VVVVVVVVVVVVVVVVV============================："+columnId);
            } else {
                Thread.sleep(300);
                System.out.println("未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，未订阅，现在开始订阅："+columnId);
                doSubscribe(cookie, columnId);
            }

            Thread.sleep(300);
            response.close();
            return "";
        } catch (Exception e) {
            System.out.println("doLike error:" + e);
            e.printStackTrace();
            return "";
        }
    }

    @Data
    static class SubscribeInfo {
        private String msg;
        private Boolean status = false;
    }

    @Data
    static class Result {
        private int code;
        private String message;
        private SubscribeInfo data;
    }
}
