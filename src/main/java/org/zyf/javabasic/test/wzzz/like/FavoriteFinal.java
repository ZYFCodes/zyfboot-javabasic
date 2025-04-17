package org.zyf.javabasic.test.wzzz.like;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.zyf.javabasic.test.wzzz.CSDNArticles;
import org.zyf.javabasic.test.wzzz.CSDNLoginAndSubmitTest;
import org.zyf.javabasic.test.wzzz.CSDNUserInfos;
import org.zyf.javabasic.test.wzzz.login.CSDNLoginTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: Favorite
 * @author: zhangyanfeng
 * @create: 2025-03-29 14:07
 **/
public class FavoriteFinal {
    public static void main(String[] args) throws IOException, IOException {

        //操作新用户去全部收藏
        Map<String, String> userInfo = CSDNUserInfos.getAllUserInfo();
        if(MapUtils.isNotEmpty(CSDNUserInfos.userNewInfoForNew)){
            userInfo = CSDNUserInfos.userNewInfoForNew;
            System.out.println(userInfo);
        }
        userInfo.clear();;
        userInfo.put("19260193307", "csdn20142014");
        // 将 Map 的条目转换为 List
        List<Map.Entry<String, String>> entryList = new ArrayList<>(userInfo.entrySet());
        // 打乱 List 中的条目顺序
        Collections.shuffle(entryList);

        // 创建 AtomicInteger 来跟踪循环次数
        AtomicInteger num = new AtomicInteger(1);
        for (Map.Entry<String, String> entry : entryList) {
            if(CSDNUserInfos.userNewInfoNonvaild.containsKey(entry.getKey())){
                System.out.println("账号：" + entry.getKey() + "已经失效了，不在参与点赞、评论、收藏行为！！！");
                continue;
            }
            String currentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime());
            System.out.println("账号：" + entry.getKey() + " 在 " + currentTime +
                    " 进行登陆和评论数据, 该账号当前位于第" + num.getAndIncrement() + "位。");

            // 执行登录和提交操作
            doLoginAndFavorite(entry.getKey(), entry.getValue());
        }

        CSDNLoginAndSubmitTest.commitDealNew();
        //CsdnBlogFetcher.call();
        //FavoriteFinal.call();
        //CSDNLoginTest.call();

        System.out.println("执行完成！！！！");
    }

    public static void call() throws IOException, IOException {

        Map<String, String> userInfo = CSDNUserInfos.getAllUserInfo();
//        userInfo.clear();;
//        userInfo.put("19260193307", "csdn20142014");
        // 将 Map 的条目转换为 List
        List<Map.Entry<String, String>> entryList = new ArrayList<>(userInfo.entrySet());
        // 打乱 List 中的条目顺序
        Collections.shuffle(entryList);

        // 创建 AtomicInteger 来跟踪循环次数
        AtomicInteger num = new AtomicInteger(1);
        for (Map.Entry<String, String> entry : entryList) {
            if(CSDNUserInfos.userNewInfoNonvaild.containsKey(entry.getKey())){
                System.out.println("账号：" + entry.getKey() + "已经失效了，不在参与点赞、评论、收藏行为！！！");
                continue;
            }
            String currentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime());
            System.out.println("账号：" + entry.getKey() + " 在 " + currentTime +
                    " 进行登陆和评论数据, 该账号当前位于第" + num.getAndIncrement() + "位。");

            // 执行登录和提交操作
            doLoginAndFavorite(entry.getKey(), entry.getValue());
        }

        CSDNLoginAndSubmitTest.commitDealNew();
        //CsdnBlogFetcher.call();
        //FavoriteFinal.call();
        CSDNLoginTest.call();

        System.out.println("执行完成！！！！");
    }

    private static void doLoginAndFavorite(String userIdentification, String pwdOrVerifyCode) {
        String url = "https://passport.csdn.net/v1/register/pc/login/doLogin";
        String logoutUrl = "https://passport.csdn.net/account/logout";  // 登出 URL

        // 登录请求的 JSON 参数
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("agreedPrivacyPolicy", "0");
        jsonObject.addProperty("loginType", "1");
        jsonObject.addProperty("pwdOrVerifyCode", pwdOrVerifyCode);
        jsonObject.addProperty("uaToken", "");
        jsonObject.addProperty("userIdentification", userIdentification);
        jsonObject.addProperty("webUmidToken", "");

        String jsonPayload = jsonObject.toString();

        try (
                CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);

            // 设置请求头
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            post.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

            // 设置请求体
            StringEntity entity = new StringEntity(jsonPayload);
            post.setEntity(entity);

            // 发送请求
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                //System.out.println("Status Code: " + statusCode);
                //System.out.println("Response Body: " + responseBody);

                // 提取 Set-Cookie
                if (statusCode == 200) {
                    Header[] cookies = response.getHeaders("Set-Cookie");
                    //System.out.println("Cookies:");
                    //Arrays.stream(cookies).forEach(header -> System.out.println(header.getValue()));

                    // 将 Cookie 保存，用于后续请求
                    StringBuilder cookieString = new StringBuilder();
                    Arrays.stream(cookies).forEach(header -> {
                        // 提取每个 Cookie 的 key=value
                        String cookiePart = header.getValue().split(";")[0];
                        cookieString.append(cookiePart).append("; ");
                    });

                    //System.out.println("Saved Cookie: " + cookieString.toString().trim());

                    //执行业务逻辑
                    doFavorites(userIdentification, cookieString.toString());

                    // 进行登出请求
                    logout(userIdentification, httpClient, logoutUrl, cookieString.toString());

                    Thread.sleep(5000);

                } else {
                    System.out.println(userIdentification + "登录失败，请检查参数！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doFavorites(String userIdentification, String cookieString) {
        List<String> zyfUrl = Lists.newArrayList(CSDNArticles.getArticleLinks("https://blog.csdn.net/xiaofeng10330111/article/details/"));
        zyfUrl.forEach(zyArticlefUrl->{
            //如果是指定收藏则只处理指定收藏的内容
            List<String> zhiDingSc = getZhiDingSC();
            if(CollectionUtils.isNotEmpty(zhiDingSc)){
                if(zhiDingSc.contains(zyArticlefUrl)){
                    System.out.println(String.format("%s对指定文章%s进行对应的收藏开始",userIdentification,zyArticlefUrl));
                    doFavorite( userIdentification, zyArticlefUrl, cookieString);
                }
            } else {
                System.out.println(String.format("%s对全量文章%s进行对应的收藏开始",userIdentification,zyArticlefUrl));
                doFavorite( userIdentification, zyArticlefUrl, cookieString);
            }
        });
    }

    private static  List<String> getZhiDingSC(){
        List<String> zhiDingSc = Lists.newArrayList();
//        zhiDingSc.add("https://blog.csdn.net/xiaofeng10330111/article/details/");
//        zhiDingSc.add("https://blog.csdn.net/xiaofeng10330111/article/details/");
        return zhiDingSc;
    }

    private static void doFavorite(String userIdentification, String urlAr,String cookie){
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

                Favorite.Result urlResult = JSON.parseObject(responseBodyStr, Favorite.Result.class);
                if (urlResult == null || urlResult.getCode() != 200) {
                    System.out.println("返回数据并不合法");
                    return;
                }

                Favorite.FavoriteRes likeInfo = urlResult.getData();
                Favorite.FavoriteDetail favoriteDetail = likeInfo.getResult().get(0);
                if(favoriteDetail.getIsFavorite()){
                    //如果为true则收藏过了，直接返回
                    System.out.println("收藏过了====================");
                    return;
                }

                //没有收藏则收藏
                CSDNFavoriteAPI.call(userIdentification,cookie,urlAr,favoriteDetail.getID());

                Thread.sleep(200);

                //check( urlAr,  cookie);

            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void logout(String userIdentification, CloseableHttpClient httpClient, String logoutUrl, String cookies) {
        try {
            HttpPost logoutPost = new HttpPost(logoutUrl);

            // 设置登出请求的请求头
            logoutPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            logoutPost.setHeader("Cookie", cookies);
            logoutPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

            // 发送登出请求
            try (CloseableHttpResponse response = httpClient.execute(logoutPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println(userIdentification + "在" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 退出账号，基本状态码：" + statusCode + " 成功, 退出时回调打印：" + responseBody);
                //System.out.println("Logout Status Code: " + statusCode);
                //System.out.println("Logout Response Body: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
