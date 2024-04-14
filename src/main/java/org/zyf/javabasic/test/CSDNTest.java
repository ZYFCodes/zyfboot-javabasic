package org.zyf.javabasic.test;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.assertj.core.util.Sets;
import org.springframework.util.CollectionUtils;
import org.zyf.javabasic.common.Article;
import org.zyf.javabasic.common.Result;
import org.zyf.javabasic.common.utils.HttpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/4/6  10:50
 */
public class CSDNTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        //v1();
        v2();
        //getRes();
        //System.out.println(getRes());
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


    private static void v1() throws InterruptedException {
        String url = "https://blog.csdn.net/community/home-api/v1/get-business-list";
        String page = "page=";
        String sizeMore = "&size=50&businessType=blog&orderby=&noMore=false&username=xiaofeng10330111";

        List<String> zyfUrl = Arrays.asList("https://zyfcodes.blog.csdn.net/article/details/105360860"
                , "https://zyfcodes.blog.csdn.net/article/details/105124900"
                , "https://zyfcodes.blog.csdn.net/article/details/105631666");

        for (int time = 0; time < 1000; time++) {
            Calendar cal1 = Calendar.getInstance();
            Date date1 = cal1.getTime();
            System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date1) + "执行访问全列表数据进行分析，当前次数：" + time);
            AtomicInteger start = new AtomicInteger(1);
            for (int i = 1; i < 200; i++) {
                String result = sendGET(url, page + i + sizeMore);
                Result urlResult = JSON.parseObject(result, Result.class);
                if (urlResult == null || urlResult.getCode() != 200) {
                    return;
                }

                List<Article> list = urlResult.getData().getList();
                if (CollectionUtils.isEmpty(list)) {
                    break;
                }
                System.out.println("访问文章总数为:" + list.size() + ",其中访问数小于10000的有：" + list.stream().filter(x -> x.getViewCount() <= 10000).count());
                list.forEach(article -> {
                    if (article.getViewCount() > 15000) {
                        return;
                    }

                    Calendar cal = Calendar.getInstance();
                    String urlDetail = article.getUrl();
                    HttpUtils.sendPost(urlDetail, null);
                    System.out.println(new SimpleDateFormat("yyyy/MM/dd      HH:mm:ss:SSS").format(cal.getTime()) +
                            "访问网站请求网站为：" + start + "-" + urlDetail + "，文章访问次数当前为：" + article.getViewCount());
                    start.getAndIncrement();

                });
                Thread.sleep(40000);
                zyfUrl.forEach(urlTest -> {
                    HttpUtils.sendPost(urlTest, null);
                    System.out.println(new SimpleDateFormat("yyyy/MM/dd       HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                            "必须访问网站：" + urlTest);
                });
            }
        }
    }

    private static void v2() throws InterruptedException {
        int limitViewCount = 10000;
        List<String> zyfUrl = Lists.newArrayList(getRes(10000));
        System.out.println("当前访问次数少于" + limitViewCount + "的文章个数为" + zyfUrl.size());

        for (int time = 0; time < 1000; time++) {
            Calendar cal1 = Calendar.getInstance();
            Date date1 = cal1.getTime();
            System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date1) + "执行访问全列表数据进行分析，当前次数：" + time);
            IntStream.range(0, zyfUrl.size()).forEach(idx -> {
                String urlTest = zyfUrl.get(idx);
                String res = HttpUtils.sendPost(urlTest, null); // 假设这是发送 POST 请求的方法
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                            " 访问网站序号：" + idx +
                            " 存在异常！");
                    ;
                }
//                System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
//                        " 访问网站序号：" + idx +
//                        " 访问网站：" + urlTest);
            });
        }

    }

    private static Set<String> getRes(int limitViewCount) {
        Set<String> res = Sets.newHashSet();
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/135898629");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/135887474");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/136090309");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/136087636");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/136073769");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/136007584");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/130716074");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/136285506");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/136779677");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/136970813");
        res.add("https://blog.csdn.net/xiaofeng10330111/article/details/137670813");

        Properties prop = new Properties();
        // 使用当前线程的类加载器获取资源的输入流
        try (InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("urlzyf.properties");
             InputStreamReader reader = new InputStreamReader(input, StandardCharsets.UTF_8)) {

            if (input == null) {
                System.out.println("Sorry, unable to find urlzyf.properties");
                return Sets.newHashSet();
            }

            // 加载 properties 文件
            prop.load(reader);

            // 从 properties 文件中读取字符串并使用
            // 假设 longString 是你的属性名
            String longString = prop.getProperty("longString.part1");
            // 定义提取 "url" 字段的正则表达式
            //String regex = "\"url\":\"(https?://[^\"]+)\"";
            String regex = "\"url\":\"(https?://[^\"]+)\"[^}]*?\"viewCount\":(\\d+)";

            // 创建 Pattern 对象
            Pattern pattern = Pattern.compile(regex);

            // 创建 matcher 对象
            Matcher matcher = pattern.matcher(longString);

            // 查找匹配的 URL
            while (matcher.find()) {
                int viewCount = Integer.parseInt(matcher.group(2));
                if (viewCount < limitViewCount) {
                    res.add(matcher.group(1));
                }
            }
            return res;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }

}
