package org.zyf.javabasic.test.wzzz.like;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import lombok.Data;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.zyf.javabasic.common.UserAndArticlesInfo;
import org.zyf.javabasic.common.UserAndArticlesLikeInfo;
import org.zyf.javabasic.test.wzzz.CSDNArticles;
import org.zyf.javabasic.test.wzzz.CSDNLoginAndSubmitTest;
import org.zyf.javabasic.test.wzzz.CSDNUserInfos;
import org.zyf.javabasic.test.wzzz.fetcher.CsdnBlogFetcher;
import org.zyf.javabasic.test.wzzz.login.CSDNLoginTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: CsdnLikeRequest
 * @author: zhangyanfeng
 * @create: 2025-03-22 16:59
 **/
public class CsdnLike {

    private static List<UserAndArticlesLikeInfo> userAndArticlesLikeInfos = Lists.newArrayList();
    public static void main(String[] args) throws IOException, IOException, InterruptedException {

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
            String currentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime());
            System.out.println("账号：" + entry.getKey() + " 在 " + currentTime +
                    " 进行登陆和评论数据, 该账号当前位于第" + num.getAndIncrement() + "位。");
            if(CSDNUserInfos.userNewInfoNonvaild.containsKey(entry.getKey())){
                System.out.println("账号：" + entry.getKey() + "已经失效了，不在参与点赞、评论、收藏行为！！！");
                continue;
            }

            // 执行登录和提交操作
            doLoginAndLikes(entry.getKey(), entry.getValue());
        }

        // 调用方法输出到日志文件
        try {
            outputLogToFile();
        } catch (IOException e) {
            System.err.println("日志文件写入失败：" + e.getMessage());
        }

        CSDNLoginAndSubmitTest.commitDealNew();
        //CsdnBlogFetcher.call();
        //FavoriteFinal.call();
        CSDNLoginTest.call();
        System.out.println("执行完成！！！！");
    }

    public static void call(){
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
            String currentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime());
            System.out.println("账号：" + entry.getKey() + " 在 " + currentTime +
                    " 进行登陆和评论数据, 该账号当前位于第" + num.getAndIncrement() + "位。");

            // 执行登录和提交操作
            doLoginAndLikes(entry.getKey(), entry.getValue());
        }

        // 调用方法输出到日志文件
        try {
            outputLogToFile();
        } catch (IOException e) {
            System.err.println("日志文件写入失败：" + e.getMessage());
        }

        System.out.println("执行完成！！！！");
    }

    private static void outputLogToFile() throws IOException {
        // 获取当前日期的文件名
        String currentDate = new SimpleDateFormat("yyyyMMdd-HHmmss-SSS").format(new Date());
        String logFilePath = "/Users/zyf/Downloads/csdn/" + currentDate + "-userAndArticleLikes.json";

        // 检查父目录是否存在，不存在则创建
        File logFile = new File(logFilePath);
        File parentDir = logFile.getParentFile();
        if (!parentDir.exists() && !parentDir.mkdirs()) {
            throw new IOException("无法创建目录：" + parentDir.getAbsolutePath());
        }

        // 创建文件（如果不存在）
        if (!logFile.exists() && !logFile.createNewFile()) {
            throw new IOException("无法创建日志文件：" + logFilePath);
        }

        // 将 userAndArticlesInfos 转为 JSON 字符串并写入文件
        String json = JSON.toJSONString(userAndArticlesLikeInfos, true); // true 表示格式化输出
        try (PrintStream logPrintStream = new PrintStream(new FileOutputStream(logFile, false), true, "UTF-8")) {
            logPrintStream.println(json);
        }

        System.out.println("数据已写入 JSON 文件：" + logFilePath);
    }

    private static void doLoginAndLikes(String userIdentification, String pwdOrVerifyCode) {

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
                    doLikes(userIdentification, cookieString.toString());

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

    // 执行登出请求
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

    private static void doLikes(String userIdentification, String cookie) {
        List<Integer> articleIds = new ArrayList<>(CSDNArticles.articleIds());
        AtomicInteger num = new AtomicInteger();
        List<String> articlesForLike = Lists.newArrayList();
        articlesForLike.addAll(AllUserInfoArticleLikeGetUtil.getLikeeds(userIdentification));
        for (int i = 0; i < articleIds.size(); i++) {
            num.getAndIncrement();
            Integer  articleId = articleIds.get(i);
            String currentDatePre = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            if(AllUserInfoArticleLikeGetUtil.isLike(userIdentification,articleId)){
                System.out.println(String.format("用户:%s在%s对文章%s（当前序号：%s）已经点赞通过，直接跳过！！！", userIdentification, currentDatePre, articleId, num.get()));
                continue;
            }
            // 获取当前日期的文件名
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            System.out.println(String.format("用户:%s在%s对文章%s（当前序号：%s）进行点赞操作！", userIdentification, currentDate, articleId, num.get()));
            String getInfo = doLike(cookie, articleId.toString());
            if (StringUtils.containsAny(getInfo, "次数已达上限") ||
                    StringUtils.equalsIgnoreCase(getInfo, "返回数据并不合法:今日点赞次数已达上限!")) {
                System.out.println("今日点赞次数已达上限!==============================================");
                break;
            }
            articlesForLike.add(articleId.toString());
            String currentDateEnd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            System.out.println(String.format("用户:%s在%s对文章%s（当前序号：%s）进行点赞操作,操作完成✅！", userIdentification, currentDateEnd, articleId, num.get()));
        }
        UserAndArticlesLikeInfo usersLikeInfo = new UserAndArticlesLikeInfo();
        usersLikeInfo.setUserInfo(userIdentification);
        usersLikeInfo.setArticlesForLike(articlesForLike);
        userAndArticlesLikeInfos.add(usersLikeInfo);
    }

    public static String doLike(String cookie, String articleId) {
        try {
            OkHttpClient client = new OkHttpClient();

            // 构造请求体（这里是 x-www-form-urlencoded，假设点赞参数是 articleId=144597752）
            RequestBody body = new FormBody.Builder()
                    .add("articleId", articleId)
                    .build();

            // 构造请求
            Request request = new Request.Builder()
                    .url("https://blog.csdn.net//phoenix/web/v1/article/like")
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

            String describtion = urlResult.getMessage();
            if (StringUtils.equalsIgnoreCase(describtion, "今日点赞次数已达上限!") && urlResult.getCode() == 400) {
                System.out.println(describtion);
                return describtion;
            }

            LikeInfo likeInfo = urlResult.getData();
            if (likeInfo.getStatus()) {
                System.out.println("点赞成功=====================VVVVVVVVVVVVVVVVV============================："+articleId);
            } else {
                Thread.sleep(300);
                System.out.println("未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，未点赞，现在开始点赞："+articleId);
                doLike(cookie, articleId);
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
    static class LikeInfo {
        private Integer like_num;
        private Boolean status = false;
    }

    @Data
    static class Result {
        private int code;
        private String message;
        private LikeInfo data;
    }
}
