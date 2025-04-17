package org.zyf.javabasic.test.wzzz;

import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * @program: zyfboot-javabasic
 * @description: 账号登录登出操作
 * @author: zhangyanfeng
 * @create: 2025-03-22 14:36
 **/
@Service
@Log4j2
public class CSDNLoginDealService {
    public String getcookieByLogin(String userIdentification, String pwdOrVerifyCode) {
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

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
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

                    return cookieString.toString();
                } else {
                    log.info("账号:【{}】登录失败，请检查参数！",userIdentification);
                }
            }
        } catch (Exception e) {
            log.error("账号:【{}】登录失败，异常如下：{}",userIdentification,e.getMessage(), e);
        }
        return null;
    }

    // 执行登出请求
    public void logout(String userIdentification, String logoutUrl, String cookies) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost logoutPost = new HttpPost(logoutUrl);

            // 设置登出请求的请求头
            logoutPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            logoutPost.setHeader("Cookie", cookies);
            logoutPost.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");

            // 发送登出请求
            try (CloseableHttpResponse response = httpClient.execute(logoutPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());
                log.info("账号:【{}】在【{}】退出了账号，基本状态码为:{}，退出时回调打印：{}",
                        userIdentification,
                        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime())
                ,statusCode,responseBody);
                log.info(userIdentification + "在" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 退出账号，基本状态码：" + statusCode + " 成功, 退出时回调打印：" + responseBody);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        CSDNLoginDealService service = new CSDNLoginDealService();
        String userIdentification="13370136591";
        String pwdOrVerifyCode="csdn20142014";
        String cookies = service.getcookieByLogin(userIdentification,pwdOrVerifyCode);
        System.out.println("cookies============================"+cookies);
        service.logout(userIdentification,pwdOrVerifyCode,cookies);
        System.out.println(cookies);
    }
}
