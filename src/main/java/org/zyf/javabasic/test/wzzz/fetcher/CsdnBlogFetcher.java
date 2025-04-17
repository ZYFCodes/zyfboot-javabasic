package org.zyf.javabasic.test.wzzz.fetcher;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.zyf.javabasic.common.Article;
import org.zyf.javabasic.common.DataCsdn;
import org.zyf.javabasic.common.Result;
import org.zyf.javabasic.common.UserAndArticlesInfo;
import org.zyf.javabasic.test.wzzz.CSDNUserInfos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: CsdnBlogFetcher
 * @author: zhangyanfeng
 * @create: 2025-03-22 15:41
 **/
public class CsdnBlogFetcher {

    private static List<UserAndArticlesInfo> userAndArticlesInfos = Lists.newArrayList();

    public static void main(String[] args) throws IOException {
//        String username = "2401_86609655";
//        Integer page = 2;
//        String cookie = "csdn_newcert_2401_86608273=1; HMACCOUNT=2B5DA67F120A89C5; uuid_tt_dd=10_20886653410-1736662831923-179767; fid=20_97458744433-1736663253424-121458; c_segment=7; csdn_newcert_2401_86608357=1; csdn_newcert_weixin_48861542=1; csdn_newcert_xiaofeng10330111=1; csdn_newcert_m0_74022498=1; csdn_newcert_ZWL53223456789=1; csdn_newcert_qq_42878414=1; csdn_newcert_STC91s=1; csdn_newcert_weixin_48602413=1; csdn_newcert_2401_89508257=1; csdn_newcert_2402_89486958=1; csdn_newcert_xingyi51=1; csdn_newcert_2401_89487402=1; csdn_newcert_2401_89488159=1; csdn_newcert_2401_89508915=1; csdn_newcert_2401_89509302=1; csdn_newcert_2401_89509544=1; csdn_newcert_2401_89509701=1; csdn_newcert_2401_89509744=1; csdn_newcert_2401_89510128=1; csdn_newcert_2401_89510197=1; csdn_newcert_2401_89510257=1; csdn_newcert_ZXMXMXM66666=1; csdn_newcert_2401_89510271=1; csdn_newcert_QQQ777111_=1; csdn_newcert_2402_89510315=1; csdn_newcert_SVQ20241216=1; csdn_newcert_H2013212994=1; csdn_newcert_2501_90647030=1; csdn_newcert_lunyelancha=1; historyList-new=%5B%5D; csdn_newcert_2501_90648645=1; csdn_newcert_2501_90648487=1; csdn_newcert_pingcao185=1; csdn_newcert_gujian1014010101=1; csdn_newcert_QxiaoQQ=1; csdn_newcert_2501_90655610=1; csdn_newcert_qq_33518830=1; _ga=GA1.2.881309776.1737198515; _ga_7W1N0GEY1P=GS1.1.1740286121.4.0.1740286146.35.0.0; c_ins_prid=1735865600518_372279; c_ins_rid=1740305260785_314869; c_ins_fref=https://blog.csdn.net/2501_90655610; x_inscode_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjcmVkZW50aWFsIjoiIiwiY3NkblVzZXJuYW1lIjoiMjUwMV85MDY1NTYxMCIsInVzZXJJZCI6IjY3YmFmMzZkNDNlNWMyN2U3MTZlMDFjZSIsInVzZXJuYW1lIjoiMjUwMV85MDY1NTYxMCJ9.jFiE2okDjfYBl37_p6rs5nNS_hxG4ONw55hhRMlyXHs; csdn_newcert_m0_73933556=1; dc_sid=57be7336334935765f246155a0bc280e; c_ab_test=1; csdn_newcert_DJV29807654=1; csdn_newcert_wdfdgygg77=1; csdn_newcert_kjj987=1; csdn_newcert_wire290=1; csdn_newcert_shark094=1; csdn_newcert_jarfg35=1; csdn_newcert_cloudman08=1; csdn_newcert_wengang345=1; csdn_newcert_junhui469=1; csdn_newcert_yaxin0765=1; csdn_newcert_xiayan827=1; csdn_newcert_hy098543=1; csdn_newcert_wenlong5o02=1; csdn_newcert_haicha028765=1; csdn_newcert_kai176567=1; csdn_newcert_deying0865423=1; csdn_newcert_chendong52329=1; csdn_newcert_suyang199312=1; csdn_newcert_kunming19850325=1; csdn_newcert_xuanyan19920827=1; csdn_newcert_wuyou199624=1; cknowvip-pc-close=1742018684778; csdn_newcert_hanyu5201314=1; csdn_newcert_sigen520520=1; csdn_newcert_ERG2oKns=1; csdn_newcert_chenzheng52029=1; csdn_newcert_WTYlaoda34=1; csdn_newcert_BaoLong543235=1; csdn_newcert_BXia47765432=1; csdn_newcert_GAoS97654=1; csdn_newcert_LIKai287656=1; csdn_newcert_RFS2jhj=1; csdn_newcert_DM19990205=1; csdn_newcert_EAJ20020527=1; csdn_newcert_YOW20011108=1; csdn_newcert_XSY19850824=1; csdn_newcert_ZHAO19911023=1; csdn_newcert_WGY1990730=1; csdn_newcert_TSJ19981027=1; csdn_newcert_XSK19930822=1; csdn_newcert_CHE19960919=1; csdn_newcert_ZGR20041209=1; csdn_newcert_ZJR20050718=1; csdn_newcert_2501_91205736=1; csdn_newcert_2501_91205858=1; csdn_newcert_2501_91205894=1; csdn_newcert_2501_91205926=1; csdn_newcert_2501_91206193=1; csdn_newcert_2501_91206293=1; csdn_newcert_2501_91206315=1; csdn_newcert_2501_91206613=1; csdn_newcert_2501_91206635=1; csdn_newcert_2501_91206658=1; csdn_newcert_2501_91206682=1; csdn_newcert_2501_91206720=1; csdn_newcert_2501_91206746=1; csdn_newcert_2501_91206763=1; csdn_newcert_2501_91206784=1; csdn_newcert_2501_91206810=1; csdn_newcert_2501_91206840=1; csdn_newcert_ZL5205201314=1; c_first_ref=default; c_first_page=https%3A//blog.csdn.net/FrenzyTechAI/article/details/136066011; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_20886653410-1736662831923-179767!5744*1*xiaofeng10330111; ssxmod_itna=eqjxcDnDgDB7t4BP8e5GkiDOnIKk0iTo47IddD/4wDnqD=GFDK40EA7eKD7+n9Mjw2pWK9aRRP=QF=Du3pQ5F3aoIaQKxiTD4q07Db4GkDAqiOD7u+IYD4fKGwD0eG+DD4DW8qDUZA=qDd9Z9CyEpOm2P89xGWIoKDmOKDRroDSQ7sVOvF875Di=oDXoYDaoKDu6vOzxi8D7FLIxE1D7PpA9fA+xi3EDvA+40OktIHDBbzlgvOl8RtacTNdPhxLWRDQAD45rGetE1WMj6ozXio4ODbanqMsF4xrnUDD=; ssxmod_itna2=eqjxcDnDgDB7t4BP8e5GkiDOnIKk0iTo47IdG9i=3aDBMEe7P6LO9HD7mx8OziHXxRiB85N=R7B+y0IR4STFxwR9ExTaEdNImkXmrvZp5qp8rCKONuXzUpgQzeWvCtnBZUAF/zy9EkEcB2GSSEiyC3ExKGovU2r0If2wK8DKniL4XGoD2wvnBrhoOf0IbuDI41GsR22UK1DHkSAbK328XaahFjr4xqaWD7QHx7=Ded5xD===; tfstk=gUbsZhsZDtQEr5khsRFeO7GoIV8batazljOAZs3ZMFLtlI1yLNuansSCcT5JBIPgnrtAUt02B1PisMXPwV7ajEuXSEYYU8zzz5fMoEBikYdJI6CHMWRTpAiOkEYYUJRxpakJoTDlqTIO9WO2gChOHhCp9pArWEp9DvnpwppvktpvJXdyixHvHC3LOIvpHELAH6h9fodjfd1_6TyF7jhvg1pIkqQToh95vmuxkwO6fOf9d111RCt6eHcWzJb5w1QkGZV-cK1lXORhhzwBJN61WHTbl2YNNMB6AGwjRHjdZZKCx8ilZ1B123QTRWxCS_89dZVmodINMZ-fW8h2BNXNR3bmCJJV4679ATeERtxfXgLOl8ad4auyFnCscXtolB9zOWimmq1ezEmAups9XBA1sWNICnx9tBTYOWim0hdHT4FQOAtc.; csdn_newcert_2501_91206257=1; _clck=6feo0j%7C2%7Cfuf%7C0%7C1829; creative_btn_mp=3; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1742616568; hide_login=1; p_uid=U010000; loginbox_strategy=%7B%22taskId%22%3A317%2C%22abCheckTime%22%3A1742616736937%2C%22version%22%3A%22ExpA%22%2C%22nickName%22%3A%22%E5%BC%A0%E5%BD%A6%E5%B3%B0ZYF%22%2C%22blog-threeH-dialog-expa%22%3A1742620596269%7D; csdn_newcert_tianshu11tianshu=1; csdn_newcert_2401_86609655=1; csdn_newcert_macbookpro11=1; csdn_newcert_2301_76981999=1; UN=2401_86609655; __gads=ID=14d0d494b3ce1f4b:T=1729211486:RT=1742625088:S=ALNI_MboS8tOkr7t1-xMUipNO7TTf-NOsg; __gpi=UID=00000f2b431c7bcf:T=1729211486:RT=1742625088:S=ALNI_MbJfxC_gq6apcum1nbhxrIFNNKl2A; __eoi=ID=a196d5e2b4979ec0:T=1729211486:RT=1742625088:S=AA-AfjbE9fL1C-ymryWPWJIEyM0x; SESSION=f81421a4-87e9-4b3e-bcdb-07a3bfa1dd4c; c_pref=https%3A//blog.csdn.net/2401_86609655%3Ftype%3Dblog; c_ref=https%3A//i.csdn.net/; UserName=2401_86609655; UserInfo=9be802d743814ab38797c4d222716d88; UserToken=9be802d743814ab38797c4d222716d88; UserNick=CloudZzzzzzz; AU=D44; BT=1742628034583; dc_session_id=10_1742633378116.591168; c_dsid=11_1742633379987.246010; c_page_id=default; log_Id_pv=1; dc_tos=stiois; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1742633380; creativeSetApiNew=%7B%22toolbarImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20230921102607.png%22%2C%22publishSuccessImg%22%3A%22https%3A//img-home.csdnimg.cn/images/20240229024608.png%22%2C%22articleNum%22%3A280%2C%22type%22%3A2%2C%22oldUser%22%3Atrue%2C%22useSeven%22%3Afalse%2C%22oldFullVersion%22%3Atrue%2C%22userName%22%3A%222401_86609655%22%7D; log_Id_view=10";
//
//        getArticleListForOne(username, page, cookie);


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
            doLoginAndFetcher(entry.getKey(), entry.getValue());
        }

        // 调用方法输出到日志文件
        try {
            outputLogToFile();
        } catch (IOException e) {
            System.err.println("日志文件写入失败：" + e.getMessage());
        }

        System.out.println("执行完成！！！！");
    }

    public static void call() throws IOException {

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
            doLoginAndFetcher(entry.getKey(), entry.getValue());
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
        String logFilePath = "/Users/zyf/Downloads/csdn/" + currentDate + "-userAndArticles.json";

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
        String json = JSON.toJSONString(userAndArticlesInfos, true); // true 表示格式化输出
        try (PrintStream logPrintStream = new PrintStream(new FileOutputStream(logFile, false), true, "UTF-8")) {
            logPrintStream.println(json);
        }

        System.out.println("数据已写入 JSON 文件：" + logFilePath);
    }

    private static void doLoginAndFetcher(String userIdentification, String pwdOrVerifyCode) {

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
                    doFetcher(userIdentification, cookieString.toString());

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

    private static void doFetcher(String userIdentification, String cookieString) {
        String userName = AllUserInfoGetUtil.getUserName(userIdentification);

        List<Article> articlesForIdentification = Lists.newArrayList();
        //只提取100篇 20*5=100
        for (int i = 1; i < 7; i++) {
            List<Article> articles = getArticleListForOne(userName, i, cookieString);
            if (CollectionUtils.isEmpty(articles)) {
                break;
            }
            articlesForIdentification.addAll(articles);
        }

        UserAndArticlesInfo userAndArticlesInfo = new UserAndArticlesInfo();
        userAndArticlesInfo.setUserInfo(userIdentification);
        userAndArticlesInfo.setArticles(articlesForIdentification);
        userAndArticlesInfos.add(userAndArticlesInfo);
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

    public static List<Article> getArticleListForOne(String username, Integer page, String cookie) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://blog.csdn.net/community/home-api/v1/get-business-list?page=1&size=20&businessType=blog&orderby=&noMore=false&year=&month=&username=2401_86609655";
        if (StringUtils.isNotBlank(username)) {
            url = url.replace("2401_86609655", username);
        }
        if (Objects.nonNull(page)) {
            url = url.replace("page=1", "page=" + page);
        }
        System.out.println("现在获取用户：" + username + "对应页码-" + page + "的最大20篇文章开始==================");

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json, text/plain, */*")
                //.addHeader("accept-encoding", "gzip, deflate, br, zstd")
                .addHeader("accept-language", "zh-CN,zh;q=0.9")
                .addHeader("referer", "https://blog.csdn.net/")
                .addHeader("cookie", cookie) // 替换为你自己浏览器复制的 Cookie
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36")
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            // 尝试从响应头获取编码
            String charset = "UTF-8";
            MediaType contentType = responseBody.contentType();
            if (contentType != null && contentType.charset() != null) {
                charset = contentType.charset().name();
            }

            byte[] responseBytes = responseBody.bytes();
            String body = new String(responseBytes, charset);
            //System.out.println(body);
            Result urlResult = JSON.parseObject(body, Result.class);
            if (urlResult == null || urlResult.getCode() != 200) {
                System.out.println("返回数据并不合法");
                return Lists.newArrayList();
            }

            DataCsdn dataCsdn = urlResult.getData();
            List<Article> list = dataCsdn.getList();
            if (CollectionUtils.isEmpty(list)) {
                System.out.println("返回数据并不合法");
                return Lists.newArrayList();
            }

            System.out.println("获取用户：" + username + "对应页码-" + page + "的最大20篇文章中第一篇文章信息为：" + JSON.toJSON(list.get(0)));

            Thread.sleep(3000);

            return list;

        } catch (Exception e) {
            System.out.println("现在获取用户：" + username + "对应页码-" + page + "的最大20篇文章返回数据异常：" + e.getMessage());
            return Lists.newArrayList();
        }
    }
}
