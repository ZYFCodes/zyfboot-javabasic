package org.zyf.javabasic.test;


import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;
import org.zyf.javabasic.common.Article;
import org.zyf.javabasic.common.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/4/6  10:50
 */
public class CSDNTest {

    public static void main(String[] args) throws IOException {
//        String url = "https://blog.csdn.net/community/home-api/v1/get-business-list?page=100&size=100&businessType=blog&orderby=&noMore=false&username=xiaofeng10330111";
//
//        Document document = Jsoup.connect(url)
//                .userAgent("ozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.86 Safari/537.36")
//                .get();
//
//        Elements items = document.select("#article_list > div");
//        System.out.println(items.size());
        String url = "https://blog.csdn.net/community/home-api/v1/get-business-list";
        String page = "page=";
        String sizeMore = "&size=20&businessType=blog&orderby=&noMore=false&username=xiaofeng10330111";
        AtomicInteger start = new AtomicInteger(1);
        for (int i = 1; i < 100; i++) {
            String result = sendGET(url, page + i + sizeMore);
            Result urlResult = JSON.parseObject(result, Result.class);
            if (urlResult == null || urlResult.getCode() != 200) {
                return;
            }

            List<Article> list = urlResult.getData().getList();
            if (CollectionUtils.isEmpty(list)) {
                break;
            }

            list.forEach(article -> {
                String urlDetail = article.getUrl();
                System.out.println(start + ":" + urlDetail);
                start.getAndIncrement();
            });
        }

    }

    public static String sendGET(String url, String param) {
        String result = "";//访问返回结果
        BufferedReader read = null;//读取访问结果

        try {
            //创建url
            URL realurl = new URL(url + "?" + param);
            //打开连接
            URLConnection connection = realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
