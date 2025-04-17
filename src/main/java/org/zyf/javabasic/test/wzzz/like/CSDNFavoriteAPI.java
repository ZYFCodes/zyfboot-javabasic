package org.zyf.javabasic.test.wzzz.like;

import okhttp3.*;
import org.json.JSONObject;
import org.zyf.javabasic.common.Article;
import org.zyf.javabasic.test.wzzz.CSDNArticles;
import org.zyf.javabasic.test.wzzz.fetcher.AllUserInfoGetUtil;

import java.io.IOException;
import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: CSDNFavoriteAPI
 * @author: zhangyanfeng
 * @create: 2025-03-29 13:42
 **/
public class CSDNFavoriteAPI {
    private static final String API_URL = "https://mp-action.csdn.net/interact/wrapper/pc/favorite/v1/api/addFavoriteInFolds";

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();

        String cookie = "";
        cookie = "";
        cookie = "Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1742616568; csdn_newcert_2401_89465532=1; csdn_newcert_2401_89473361=1; csdn_newcert_2401_89496617=1; csdn_newcert_2401_89496917=1; csdn_newcert_sinat_20516251=1; csdn_newcert_2401_89508257=1; csdn_newcert_2402_89486958=1; csdn_newcert_xingyi51=1; csdn_newcert_2401_89488159=1; csdn_newcert_2401_89508915=1; csdn_newcert_2401_89508972=1; csdn_newcert_2401_89509302=1; csdn_newcert_2401_89509470=1; csdn_newcert_2401_89509544=1; csdn_newcert_2401_89509701=1; csdn_newcert_2401_89509744=1; csdn_newcert_2401_89510128=1; csdn_newcert_2401_89510257=1; csdn_newcert_ZXMXMXM66666=1; csdn_newcert_2401_89510271=1; csdn_newcert_QQQ777111_=1; csdn_newcert_2402_89510315=1; csdn_newcert_SVQ20241216=1; csdn_newcert_H2013212994=1; csdn_newcert_yzz19900508=1; csdn_newcert_Jhao987654=1; csdn_newcert_mingzhuo5432=1; csdn_newcert_erliu1995=1; dc_sid=3f1f94731e535708a1145e82a5780f38; csdn_newcert_suyang199312=1; csdn_newcert_kunming19850325=1; csdn_newcert_xuanyan19920827=1; csdn_newcert_wuyou199624=1; csdn_newcert_hanyu5201314=1; csdn_newcert_yuchen9876545=1; csdn_newcert_yongqiang9976543=1; csdn_newcert_QINQIN520520=1; csdn_newcert_LIAN5201314LL=1; csdn_newcert_2501_91206100=1; csdn_newcert_2501_91260213=1; csdn_newcert_2501_91260241=1; csdn_newcert_2501_91260272=1; csdn_newcert_2501_91260293=1; csdn_newcert_2501_91260312=1; csdn_newcert_2501_91260338=1; csdn_newcert_2501_91260390=1; csdn_newcert_2501_91260412=1; csdn_newcert_2501_91260429=1; csdn_newcert_2501_91260443=1; csdn_newcert_2501_91260472=1; csdn_newcert_2501_91260583=1; csdn_newcert_2501_91278374=1; csdn_newcert_2501_91278445=1; csdn_newcert_2501_91278582=1; csdn_newcert_2501_91278597=1; csdn_newcert_2501_91287259=1; csdn_newcert_2501_91287291=1; csdn_newcert_2501_91287314=1; csdn_newcert_2501_91287396=1; csdn_newcert_2501_91287454=1; csdn_newcert_2501_91287480=1; csdn_newcert_2501_91287502=1; csdn_newcert_2501_91287532=1; csdn_newcert_2501_91287558=1; csdn_newcert_2501_91287580=1; csdn_newcert_2501_91287625=1; csdn_newcert_2501_91287693=1; csdn_newcert_2501_91287722=1; csdn_newcert_2501_91287777=1; csdn_newcert_2501_91287812=1; csdn_newcert_2501_91287888=1; csdn_newcert_2501_91287950=1; csdn_newcert_2501_91301799=1; csdn_newcert_2501_91301821=1; csdn_newcert_2501_91302028=1; csdn_newcert_2501_91302130=1; csdn_newcert_2501_91302165=1; csdn_newcert_2501_91302923=1; csdn_newcert_2501_91302961=1; c_first_ref=default; c_first_page=https%3A//i.csdn.net/%23/user-center/profile; csdn_newcert_2501_91302975=1; csdn_newcert_2501_91302991=1; csdn_newcert_2501_91303035=1; csdn_newcert_2501_91303050=1; csdn_newcert_2501_91303067=1; csdn_newcert_2501_91303120=1; csdn_newcert_2501_91318232=1; csdn_newcert_2501_91318249=1; csdn_newcert_2501_91318280=1; csdn_newcert_2501_91318324=1; csdn_newcert_2501_91318337=1; csdn_newcert_2501_91318349=1; csdn_newcert_2501_91318363=1; csdn_newcert_2501_91318380=1; csdn_newcert_2501_91318399=1; csdn_newcert_2501_91318416=1; csdn_newcert_2501_91318430=1; csdn_newcert_2501_91318448=1; csdn_newcert_2501_91318517=1; csdn_newcert_2501_91318540=1; csdn_newcert_2501_91318562=1; csdn_newcert_2501_91318588=1; csdn_newcert_2501_91318647=1; c_ab_test=1; fid=20_93723229955-1742699429607-941358; uuid_tt_dd=11_71156464980-1742699429608-452642; c_segment=2; HMACCOUNT=2B5DA67F120A89C5; csdn_newcert_2501_91318661=1; csdn_newcert_2501_91318680=1; csdn_newcert_2501_91318733=1; csdn_newcert_2501_91318745=1; csdn_newcert_2501_91318760=1; csdn_newcert_2501_91318776=1; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1742728954:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1742728954:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=a196d5e2b4979ec0:T=1729211486:RT=1742728954:S=AA-AfjbE9fL1C-ymryWPWJIEyM0x; csdn_newcert_xiaofeng10330111=1; csdn_newcert_jarfg35=1; p_uid=U010000; csdn_newcert_hy098543=1; csdn_newcert_wenlong5o02=1; hide_login=1; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1743216625576%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E5%AD%9F%E6%96%B0%EF%BD%9E%22%7D; creative_btn_mp=3; SESSION=804ad96f-5a1a-43ec-b3db-0049b6b9516d; tfstk=gNaKC41EN1dKBRgc-wsMr-Y_PnCGOzFUxJPXrYDHVReTa8LH-40nVbMTUe4uZ7ZJySkw-W43ZBranST3-Muky2urPtXcoZfUT4u5YCpQVpuswjdCr0iS8V_a342OoZVUOGlB6eQ0-BB7dvHSPDi71AMZGLgSA4_16bGkd3TQF51tQjGSOYGIfdG-iLgSP8N16bksFX_HlA1IFUEfqIqdZMjLLJat9DHb6i8WyBh6nxFIBUTSXXnpXWM9PUaTjohwUxQ9CAV7guiTyNYZz5qYeuw1NUM8fjexiJb6J4Z_Brnb_tLEplwUy2mVyQhTD5wL-5CkX8e_gouQRT8nLuN_PA4CZUHbmSut_zXwRYE_VymEzp6K6yFYyuIr0r4xSyvmHbxC61Ky4DGwh-7cUqd4rAltn6pX43oM_fHc6M-y4DGZ6xfgf3-rjC5..; UserName=2501_91318349; UserInfo=148c63b86c534010985c43e9661cab50; UserToken=148c63b86c534010985c43e9661cab50; UserNick=%E5%BA%9F%E7%89%A9%E5%88%A9%E7%94%A8%EF%BC%81; AU=D0D; UN=2501_91318349; BT=1743223675967; c_pref=https%3A//www.csdn.net/; c_ref=https%3A//blog.csdn.net/2401_86609655%3Ftype%3Dblog; dc_session_id=10_1743226834760.501836; c_dsid=11_1743227193878.879299; c_page_id=default; log_Id_pv=1; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1743227195; _clck=erao71%7C2%7Cfum%7C0%7C1908; _clsk=13iakx0%7C1743227196465%7C1%7C0%7Cv.clarity.ms%2Fcollect; log_Id_view=30; log_Id_click=3; dc_tos=stvf7t";

        // 创建 JSON 请求体
        JSONObject json = new JSONObject();
        json.put("url", "https://blog.csdn.net/2401_86609655/article/details/141931548");
        json.put("source", "blog");
        json.put("sourceId", 141931548);
        json.put("title", "案例分析：大对象复用的目标和注意点3");
        json.put("description", "文章浏览阅读1.2k次，点赞19次，收藏15次。对于“”的优化...");
        json.put("folderIdList", Arrays.asList(42741619)); // 收藏夹 ID
        json.put("fromType", "PC");
        json.put("username", "2501_91318349");
        json.put("author", "2401_86609655");

        // 创建请求体
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());

        // 构造请求
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                .addHeader("cookie", cookie)
                //.addHeader("UserToken", USER_TOKEN)
                .addHeader("Referer", "https://blog.csdn.net/2401_86609655/article/details/141931548")
                .addHeader("Origin", "https://blog.csdn.net")
                .addHeader("Accept", "*/*")
                .addHeader("Accept-Encoding", "gzip, deflate, br, zstd")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Connection", "keep-alive")
                .addHeader("Sec-Fetch-Dest", "empty")
                .addHeader("Sec-Fetch-Mode", "cors")
                .addHeader("Sec-Fetch-Site", "same-site")
                .build();

        // 发送请求
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("收藏成功: " + response.body().string());
            } else {
                System.out.println("收藏失败: " + response.code() + " - " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void call(String userIdentification, String cookie,String url,Integer folderId) {
        String[] getIds = url.split("/");
        String sourceId = getIds[getIds.length-1];
        OkHttpClient client = new OkHttpClient();

        String username = AllUserInfoGetUtil.getUserName(userIdentification);
        Article article = CSDNArticles.getArticleById(Integer.valueOf(sourceId.toString()));

        System.out.println(String.format("基本信息：userIdentification-%s,username-%s,url-%s,folderId-%s,sourceId-%s,title-%s",
                userIdentification,username,url,folderId,sourceId,article.getTitle()));

        // 创建 JSON 请求体
        JSONObject json = new JSONObject();
        json.put("url", url);
        json.put("source", "blog");
        json.put("sourceId", sourceId);
        json.put("title",article.getTitle());
        json.put("description", "文章浏览阅读1.2k次，点赞19次，收藏15次。对于“”的优化...");
        json.put("folderIdList", Arrays.asList(folderId)); // 收藏夹 ID
        json.put("fromType", "PC");
        json.put("username", username);
        json.put("author", "xiaofeng10330111");

        // 创建请求体
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json.toString());

        // 构造请求
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36")
                .addHeader("cookie", cookie)
                //.addHeader("UserToken", USER_TOKEN)
                .addHeader("Referer", url)
                .addHeader("Origin", "https://blog.csdn.net")
                .addHeader("Accept", "*/*")
                .addHeader("Accept-Encoding", "gzip, deflate, br, zstd")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.9")
                .addHeader("Connection", "keep-alive")
                .addHeader("Sec-Fetch-Dest", "empty")
                .addHeader("Sec-Fetch-Mode", "cors")
                .addHeader("Sec-Fetch-Site", "same-site")
                .build();

        // 发送请求
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("收藏成功: " + response.body().string());
            } else {
                System.out.println("收藏失败: " + response.code() + " - " + response.message());
            }

            Thread.sleep(200);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
