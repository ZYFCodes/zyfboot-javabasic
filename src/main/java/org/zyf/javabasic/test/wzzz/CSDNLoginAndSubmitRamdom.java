package org.zyf.javabasic.test.wzzz;

import com.google.common.collect.Lists;
import org.zyf.javabasic.common.utils.HttpUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @program: zyfboot-javabasic
 * @description: 综合性质分散评论操作
 * @author: zhangyanfeng
 * @create: 2025-03-19 00:12
 **/
public class CSDNLoginAndSubmitRamdom {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                15,
                35,
                10800,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        // 记录程序开始时间
        long startTime = System.currentTimeMillis();
        Map<String, String> userInfo = CSDNUserInfos.getAllUserInfo();
        // 将 Map 的条目转换为 List
        List<Map.Entry<String, String>> entryList = new ArrayList<>(userInfo.entrySet());
        // 打乱 List 中的条目顺序
        Collections.shuffle(entryList);

        List<String> zyfUrl = Lists.newArrayList(CSDNArticles.getArticleLinks("https://blog.csdn.net/xiaofeng10330111/article/details/"));
        System.out.println("当前访问文章总数为" + zyfUrl.size());

        for (int time = 0; time < 3000; time++) {
            Calendar cal1 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Shanghai"));
            Date date1 = cal1.getTime();
            System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date1) +
                    " 执行访问全列表数据进行分析，当前次数：" + time);



            // 记录开始时间
            long startTimeForRead = System.currentTimeMillis();
            IntStream.range(0, zyfUrl.size()).forEach(idx -> {
                String urlTest = zyfUrl.get(idx);
                String res = HttpUtils.sendPost(urlTest, null); // 假设这是发送 POST 请求的方法
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                            " 访问网站序号：" + idx +
                            " 存在异常！");
                    ;
                }
                System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 访问网站序号：" + idx +
                        " 访问网站：" + urlTest);
            });

            // 记录结束时间
            long endTimeForRead = System.currentTimeMillis();

            // 计算耗时（毫秒转为分钟和秒）
            long totalMillis = endTimeForRead - startTimeForRead;
            long minutes = totalMillis / 1000 / 60;
            long seconds = (totalMillis / 1000) % 60;

            // 打印耗时
            System.out.println("本次访问全列表数据完成，总耗时：" + minutes + " 分 " + seconds + " 秒");
        }
    }
}
