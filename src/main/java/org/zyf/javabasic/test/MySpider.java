package org.zyf.javabasic.test;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/9/6  14:19
 */
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MySpider {
    public static void main(String[] args) throws IOException {
        MySpider spider = new MySpider();

        String author = "xiaofeng10330111";
        function(author);
    }

    public static void function(String author) throws IOException {
        final String address = "https://blog.csdn.net/" + author + "/article/list/";

        StringBuffer currentAd = null;

        String regex = "(?<=href=\")https://blog.csdn.net/" + author + "/article/details/[0-9]{8,9}(?=\")";

        Set<String> urlSet = new HashSet<String>();
        String content = null;
        for (int i = 1; i < 10; i++) {
            currentAd = new StringBuffer(address);
            currentAd.append(i);
            content = getPageContent(currentAd.toString());
            List<String> list = getMatcher(content, regex);
            urlSet.addAll(list);
            //判断是否到头
            if (content.indexOf("空空如也") != -1){
                break;
            }
        }
        //打印链接
        for(String s : urlSet){
            getInputStream(s);
            System.out.println(s);
        }
        System.out.println("Running Succeed");
    }

    //获取页面内容
    public static String getPageContent(String url) {
        HttpClientBuilder builder = HttpClients.custom();
        CloseableHttpClient client = builder.build();
        String content = null;
        //httpGet作为获取
        HttpGet request = new HttpGet(url);

        //修改cookie策略，避免红色警告
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        request.setConfig(defaultConfig);
        try {
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            content = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    //URL开始连接
    public static void getInputStream(String strUrl) throws IOException {
        //url->openConnection->getInputStream
        URL url = new URL(strUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        InputStream in = con.getInputStream();
        in.close();
    }

    //正则匹配获取博文列表
    public static List<String> getMatcher(String str, String regex) {
        List<String> list = new ArrayList<String>();
        //正则匹配
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        //寻找添加
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }
}
