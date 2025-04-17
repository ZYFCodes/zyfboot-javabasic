package org.zyf.javabasic.test.wzzz.like;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: favorite
 * @author: zhangyanfeng
 * @create: 2025-03-22 17:24
 **/
public class Favorite {
    public static void main(String[] args) {
        String urlAr = "https://blog.csdn.net/2401_86609655/article/details/141931548";
        String cookie = "";
        cookie = "";
        cookie = "JSESSIONID=1FDA057CFB4242BCE82C10B99118A4D9; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1742616568; csdn_newcert_2401_89465532=1; csdn_newcert_2401_89473361=1; csdn_newcert_2401_89496617=1; csdn_newcert_2401_89496917=1; csdn_newcert_sinat_20516251=1; csdn_newcert_2401_89508257=1; csdn_newcert_2402_89486958=1; csdn_newcert_xingyi51=1; csdn_newcert_2401_89488159=1; csdn_newcert_2401_89508915=1; csdn_newcert_2401_89508972=1; csdn_newcert_2401_89509302=1; csdn_newcert_2401_89509470=1; csdn_newcert_2401_89509544=1; csdn_newcert_2401_89509701=1; csdn_newcert_2401_89509744=1; csdn_newcert_2401_89510128=1; csdn_newcert_2401_89510257=1; csdn_newcert_ZXMXMXM66666=1; csdn_newcert_2401_89510271=1; csdn_newcert_QQQ777111_=1; csdn_newcert_2402_89510315=1; csdn_newcert_SVQ20241216=1; csdn_newcert_H2013212994=1; csdn_newcert_yzz19900508=1; csdn_newcert_Jhao987654=1; csdn_newcert_mingzhuo5432=1; csdn_newcert_erliu1995=1; dc_sid=3f1f94731e535708a1145e82a5780f38; csdn_newcert_suyang199312=1; csdn_newcert_kunming19850325=1; csdn_newcert_xuanyan19920827=1; csdn_newcert_wuyou199624=1; csdn_newcert_hanyu5201314=1; csdn_newcert_yuchen9876545=1; csdn_newcert_yongqiang9976543=1; csdn_newcert_QINQIN520520=1; csdn_newcert_LIAN5201314LL=1; csdn_newcert_2501_91206100=1; csdn_newcert_2501_91260213=1; csdn_newcert_2501_91260241=1; csdn_newcert_2501_91260272=1; csdn_newcert_2501_91260293=1; csdn_newcert_2501_91260312=1; csdn_newcert_2501_91260338=1; csdn_newcert_2501_91260390=1; csdn_newcert_2501_91260412=1; csdn_newcert_2501_91260429=1; csdn_newcert_2501_91260443=1; csdn_newcert_2501_91260472=1; csdn_newcert_2501_91260583=1; csdn_newcert_2501_91278374=1; csdn_newcert_2501_91278445=1; csdn_newcert_2501_91278582=1; csdn_newcert_2501_91278597=1; csdn_newcert_2501_91287259=1; csdn_newcert_2501_91287291=1; csdn_newcert_2501_91287314=1; csdn_newcert_2501_91287396=1; csdn_newcert_2501_91287454=1; csdn_newcert_2501_91287480=1; csdn_newcert_2501_91287502=1; csdn_newcert_2501_91287532=1; csdn_newcert_2501_91287558=1; csdn_newcert_2501_91287580=1; csdn_newcert_2501_91287625=1; csdn_newcert_2501_91287693=1; csdn_newcert_2501_91287722=1; csdn_newcert_2501_91287777=1; csdn_newcert_2501_91287812=1; csdn_newcert_2501_91287888=1; csdn_newcert_2501_91287950=1; csdn_newcert_2501_91301799=1; csdn_newcert_2501_91301821=1; csdn_newcert_2501_91302028=1; csdn_newcert_2501_91302130=1; csdn_newcert_2501_91302165=1; csdn_newcert_2501_91302923=1; csdn_newcert_2501_91302961=1; c_first_ref=default; c_first_page=https%3A//i.csdn.net/%23/user-center/profile; csdn_newcert_2501_91302975=1; csdn_newcert_2501_91302991=1; csdn_newcert_2501_91303035=1; csdn_newcert_2501_91303050=1; csdn_newcert_2501_91303067=1; csdn_newcert_2501_91303120=1; csdn_newcert_2501_91318232=1; csdn_newcert_2501_91318249=1; csdn_newcert_2501_91318280=1; csdn_newcert_2501_91318324=1; csdn_newcert_2501_91318337=1; csdn_newcert_2501_91318349=1; csdn_newcert_2501_91318363=1; csdn_newcert_2501_91318380=1; csdn_newcert_2501_91318399=1; csdn_newcert_2501_91318416=1; csdn_newcert_2501_91318430=1; csdn_newcert_2501_91318448=1; csdn_newcert_2501_91318517=1; csdn_newcert_2501_91318540=1; csdn_newcert_2501_91318562=1; csdn_newcert_2501_91318588=1; csdn_newcert_2501_91318647=1; c_ab_test=1; fid=20_93723229955-1742699429607-941358; uuid_tt_dd=11_71156464980-1742699429608-452642; c_segment=2; HMACCOUNT=2B5DA67F120A89C5; csdn_newcert_2501_91318661=1; csdn_newcert_2501_91318680=1; csdn_newcert_2501_91318733=1; csdn_newcert_2501_91318745=1; csdn_newcert_2501_91318760=1; csdn_newcert_2501_91318776=1; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1742728954:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1742728954:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=a196d5e2b4979ec0:T=1729211486:RT=1742728954:S=AA-AfjbE9fL1C-ymryWPWJIEyM0x; csdn_newcert_xiaofeng10330111=1; csdn_newcert_jarfg35=1; p_uid=U010000; csdn_newcert_hy098543=1; csdn_newcert_wenlong5o02=1; _clck=erao71%7C2%7Cfuk%7C0%7C1908; hide_login=1; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1743216625576%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E5%AD%9F%E6%96%B0%EF%BD%9E%22%7D; creative_btn_mp=3; dc_session_id=10_1743223658365.588182; SESSION=804ad96f-5a1a-43ec-b3db-0049b6b9516d; tfstk=gNaKC41EN1dKBRgc-wsMr-Y_PnCGOzFUxJPXrYDHVReTa8LH-40nVbMTUe4uZ7ZJySkw-W43ZBranST3-Muky2urPtXcoZfUT4u5YCpQVpuswjdCr0iS8V_a342OoZVUOGlB6eQ0-BB7dvHSPDi71AMZGLgSA4_16bGkd3TQF51tQjGSOYGIfdG-iLgSP8N16bksFX_HlA1IFUEfqIqdZMjLLJat9DHb6i8WyBh6nxFIBUTSXXnpXWM9PUaTjohwUxQ9CAV7guiTyNYZz5qYeuw1NUM8fjexiJb6J4Z_Brnb_tLEplwUy2mVyQhTD5wL-5CkX8e_gouQRT8nLuN_PA4CZUHbmSut_zXwRYE_VymEzp6K6yFYyuIr0r4xSyvmHbxC61Ky4DGwh-7cUqd4rAltn6pX43oM_fHc6M-y4DGZ6xfgf3-rjC5..; c_dsid=11_1743223659384.546652; UserName=2501_91318349; UserInfo=148c63b86c534010985c43e9661cab50; UserToken=148c63b86c534010985c43e9661cab50; UserNick=%E5%BA%9F%E7%89%A9%E5%88%A9%E7%94%A8%EF%BC%81; AU=D0D; UN=2501_91318349; BT=1743223675967; c-sidebar-collapse=0; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20231011044944.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A0%2C%22type%22%3A0%2C%22oldUser%22%3Afalse%2C%22useSeven%22%3Atrue%2C%22oldFullVersion%22%3Afalse%2C%22userName%22%3A%222501_91318349%22%7D; log_Id_click=3; dc_tos=stvc0b; c_pref=https%3A//www.csdn.net/; c_ref=https%3A//blog.csdn.net/2401_86609655%3Ftype%3Dblog; c_page_id=default; log_Id_pv=4; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1743223692; log_Id_view=92";

        OkHttpClient client = new OkHttpClient();

        // 构建 URL
        HttpUrl url = HttpUrl.parse("https://mp-action.csdn.net/interact/wrapper/pc/favorite/v1/api/folderListWithCheck")
                .newBuilder()
                .addQueryParameter("url", urlAr)
                .build();

        // 构建请求
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "*/*")
                .addHeader("origin", "https://blog.csdn.net")
                .addHeader("referer", urlAr)
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                .addHeader("cookie", cookie) // 用真实的 Cookie 替换这里
                .build();

        // 发送请求
        Call call = client.newCall(request);
        try (Response response = call.execute()) {
            if (response.isSuccessful()) {
                System.out.println("Status: " + response.code());
                String responseBodyStr = response.body().string();  // 只能调用一次
               // System.out.println("Response Body:\n" + responseBodyStr);

                Result urlResult = JSON.parseObject(responseBodyStr, Result.class);
                if (urlResult == null || urlResult.getCode() != 200) {
                    System.out.println("返回数据并不合法");
                    return;
                }

                FavoriteRes likeInfo = urlResult.getData();
                FavoriteDetail favoriteDetail = likeInfo.getResult().get(0);
                if(favoriteDetail.getIsFavorite()){
                    //如果为true则收藏过了，直接返回
                    System.out.println("收藏过了====================");
                    return;
                }

                //没有收藏则收藏
                doFavorite(urlAr,favoriteDetail.getUsername(),favoriteDetail.getID().toString(),cookie);

                check( urlAr,  cookie);

            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doFavorite(String url,String author,String folderId, String cookie) {
        String[] getIds = url.split("/");
        String sourceId = getIds[getIds.length-1];
        OkHttpClient client = new OkHttpClient();

        // 请求体内容，替换为你自己的 JSON 内容
        // 请求体 JSON 字符串（替换为你实际的参数）
        String jsonBody = "{\n" +
                "  \"url\": \"https://blog.csdn.net/2401_86609655/article/details/144597752\",\n" +
                "  \"source\": \"blog\",\n" +
                "  \"sourceId\": 144597752,\n" +
                "  \"title\": \"Linux下跨语言调用C++实践14\",\n" +
                "  \"description\": \"文章浏览阅读909次，点赞24次，收藏9次。为了达到业务方开箱即用的目的...\",\n" +
                "  \"author\": \"2401_86609655\",\n" +
                "  \"username\": \"2401_86609655\",\n" +
                "  \"folderIdList\": [39369104],\n" +
                "  \"fromType\": \"PC\"\n" +
                "}";
        jsonBody = jsonBody.replace("https://blog.csdn.net/2401_86609655/article/details/144597752",url);
        jsonBody = jsonBody.replace("144597752",sourceId);
        jsonBody = jsonBody.replaceAll("2401_86609655",author);
        jsonBody = jsonBody.replaceAll("39369104",folderId);

        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"),
                jsonBody
        );

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .addHeader("Origin", "https://blog.csdn.net")
                .addHeader("Referer", url)
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                .addHeader("Cookie", cookie) // 若需要身份认证，请加上完整cookie
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("响应码: " + response.code());
                System.out.println("响应体: " + response.body().string());
            } else {
                System.err.println("请求失败，响应码: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void check(String articleUrl, String cookie) throws Exception {
        OkHttpClient client = new OkHttpClient();

        // 替换为你要查询的文章链接
        String targetUrl = "https://mp-action.csdn.net/interact/wrapper/pc/favorite/v1/api/checkFavoriteByUrl?url=" + articleUrl;

        Request request = new Request.Builder()
                .url(targetUrl)
                .get()
                .addHeader("origin", "https://blog.csdn.net")
                .addHeader("referer", articleUrl)
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                .addHeader("cookie", cookie)
                .build();

        Response response = client.newCall(request).execute();

        System.out.println(response.code());
        System.out.println(response.body().string());
    }



    @Data
    static class FavoriteRes{
        private Integer total;
        private List<FavoriteDetail> result;
    }

    @Data
    static class FavoriteDetail{
        private Integer ID;
        private Integer IsDefault;
        private String Description;
        private Integer FavoriteNum;
        private Boolean IsFavorite;
        private String Username;
    }

    @Data
    static class Result {
        private int code;
        private String msg;
        private FavoriteRes data;
    }
}
