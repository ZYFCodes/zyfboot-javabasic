package org.zyf.javabasic.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * @program: zyfboot-javabasic
 * @description: HttpPostRequest
 * @author: zhangyanfeng
 * @create: 2024-08-03 12:28
 **/
public class HttpPostRequest {
    public static void main(String[] args) {
        try {
            // API 接口的 URL
            URL url = new URL("https://passport.csdn.net/v1/api/check/userstatus");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 POST
            connection.setRequestMethod("POST");

            // 设置请求头
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            connection.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br, zstd");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Origin", "https://blog.csdn.net");
            connection.setRequestProperty("Referer", "https://blog.csdn.net/xiaofeng10330111/article/details/140759153?spm=1001.2014.3001.5501");
            connection.setRequestProperty("Sec-Ch-Ua", "\"Not/A)Brand\";v=\"8\", \"Chromium\";v=\"126\", \"Google Chrome\";v=\"126\"");
            connection.setRequestProperty("Sec-Ch-Ua-Mobile", "?0");
            connection.setRequestProperty("Sec-Ch-Ua-Platform", "\"macOS\"");
            connection.setRequestProperty("Sec-Fetch-Dest", "empty");
            connection.setRequestProperty("Sec-Fetch-Mode", "cors");
            connection.setRequestProperty("Sec-Fetch-Site", "same-site");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
            connection.setRequestProperty("Access-Control-Allow-Credentials", "true");
            connection.setRequestProperty("Access-Control-Allow-Headers", "Accept,Authorization,Cache-Control,Content-Type,DNT,If-Modified-Since,JWT-TOKEN,Keep-Alive,Origin,User-Agent,UserName,UserToken,X-Access-Token,X-App-ID,X-CustomHeader,X-Data-Type,X-Device-ID,X-Device-Signature,X-Mx-ReqToken,X-OS,X-Requested-With,body,uber-trace-id,x-csrf-token,x-log-apiversion,x-log-bodyrawsize,x-log-requestid,x-token,x-ca-signature-headers");
            connection.setRequestProperty("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
            connection.setRequestProperty("Access-Control-Allow-Origin", "https://blog.csdn.net");

            // 设置 Cookie
            connection.setRequestProperty("Cookie", "fpv=506ddb56c781170e780b971e8fbf59ec; __gpi=UID=000009b2dc650faa:T=1691850423:RT=1699192279:S=ALNI_MY9LBNZER5MIu2FziAVx45S1E6qsA; p_uid=U010000; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=6525*1*10_19405898120-1691850414206-685544!5744*1*xiaofeng10330111; c_ins_fpage=/index.html; c_ins_um=-; ins_first_time=1713084887297; Hm_up_6bcd52f51e9b3dce32bec4a3997715ac=%7B%22islogin%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isonline%22%3A%7B%22value%22%3A%221%22%2C%22scope%22%3A1%7D%2C%22isvip%22%3A%7B%22value%22%3A%220%22%2C%22scope%22%3A1%7D%2C%22uid_%22%3A%7B%22value%22%3A%22xiaofeng10330111%22%2C%22scope%22%3A1%7D%7D; cf_clearance=XMWU4xMPiaIdAetK9mRZQczpWrQw8sgp1E5ClpufgAM-1713717920-1.0.1.1-bA9M96DmKE67yyESCSVzwAQfKZSWOwaq0le8OyuGIbfOajiK0dLYliXHrYwy47_LyncUk6Z_A0CckkfHQ2IuUQ; FCCDCF=%5Bnull%2Cnull%2Cnull%2C%5B%22CP-FskAP-FskAEsACBENAzEoAP_gAEPgAARoINJB7D7FbSFCwH5zaLsAMAhHRsCAQoQAAASBAmABQAKQIAQCgkAQFASgBAACAAAAICZBIQIECAAACUAAQAAAAAAEAAAAAAAIIAAAgAEAAAAIAAACAAAAEAAIAAAAEAAAmAgAAIIACAAAhAAAAAAAAAAAAAAAAgAAAAAAAAAAAAAAAAAAAQOhQD2F2K2kKFkPCmQWYAQBCijYEAhQAAAAkCBIAAgAUgQAgFIIAgAIFAAAAAAAAAQEgCQAAQABAAAIACgAAAAAAIAAAAAAAQQAAAAAIAAAAAAAAEAAAAAAAQAAAAIAABEhCAAQQAEAAAAAAAQAAAAAAAAAAABAAA%22%2C%222~2072.70.89.93.108.122.149.196.2253.2299.259.2357.311.313.323.2373.358.2415.415.449.2506.2526.486.494.495.2568.2571.2575.540.574.2624.609.2677.827.864.981.1029.1048.1051.1095.1097.1126.1205.1211.1276.1301.1365.1415.1423.1449.1570.1577.1598.1651.1716.1735.1753.1765.1870.1878.1889.1958~dv.%22%2C%2293F3F4AF-7190-4BE3-8FDC-BFC6D18FD6EA%22%5D%5D; _ga=GA1.2.1003498981.1691850421; _ga_7W1N0GEY1P=GS1.1.1720369099.50.1.1720369103.56.0.0; c_segment=2; dc_sid=fe60fe74cdef56df4d538a1fa8834118; uuid_tt_dd=10_19405897440-1721924064334-906450; c_first_ref=csdn.s2.udesk.cn; c_first_page=https%3A//ask.csdn.net/; dp_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6NTMxLCJleHAiOjE3MjI3NTA2MTIsImlhdCI6MTcyMjE0NTgxMiwidXNlcm5hbWUiOiJ4aWFvZmVuZzEwMzMwMTExIn0.snv5Q-ePhcEMMvjB_1x45uzy1LjOvOglSz1BEaf4Okg; ssxmod_itna=Yq0x9DyiD=0QYDKe0LPYIEP=K=i==NizzdTN=ODlOg=xA5D8D6DQeGTTudeiTqiKBx4xefKIbTxFFn08qxaP5AfrWsDRO4GIDeKG2DmeDyDi5GRD0FIxTDen=D5xGoDPxDeDABKDClTsKDjC5CnhyFvaFHE15Db197Di9ODYHpDAqh/xB1k=01DDPpDl9GDWc7DQ5svZPDExGOUzFmyxGaaIfLwtKDEAbCwt3Dv2oOsxGdScysvcMoiWpN/W0ez0bqFiDFdDrqK7bTkqPeCvxxFY2DFmik7BuMGItKUdD===; ssxmod_itna2=Yq0x9DyiD=0QYDKe0LPYIEP=K=i==NizzdTN=ODlOg=xA5D8D6DQeGTTudeiTqiKBx4xefKIbTxFFn08qxaP5AfrWsDRO4GIDeKG2DmeDyDi5GRD0FIxTDen=D5xGoDPxDeDABKDClTsKDjC5CnhyFvaFHE15Db197Di9ODYHpDAqh/xB1k=01DDPpDl9GDWc7DQ5svZPDExGOUzFmyxGaaIfLwtKDEAbCwt3Dv2oOsxGdScysvcMoiWpN/W0ez0bqFiDFdDrqK7bTkqPeCvxxFY2DFmik7BuMGItKUdD===; SESSION=7fb34857-ec68-422e-931a-8965af420b87.1720283967.1720283967; USERNAME=AFenxianlin; roleType=VIP; level=100");

            // 添加身份验证令牌（假设令牌为 YourAccessToken）
            // connection.setRequestProperty("Authorization", "Bearer YourAccessToken");


            // 设置请求体
            String jsonInputString = "{\"key\":\"value\"}"; // 替换为实际的 JSON 输入
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // 读取响应
            int status = connection.getResponseCode();
            BufferedReader in;
            if ("gzip".equals(connection.getContentEncoding())) {
                in = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream()), "utf-8"));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            }
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // 打印响应
            System.out.println("Status Code: " + status);
            System.out.println("Response: " + content.toString());

            // 断开连接
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
