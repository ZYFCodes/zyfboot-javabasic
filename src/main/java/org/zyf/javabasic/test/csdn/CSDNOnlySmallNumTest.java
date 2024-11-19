package org.zyf.javabasic.test.csdn;

import com.google.common.collect.Lists;
import org.zyf.javabasic.common.utils.HttpUtils;
import org.zyf.javabasic.test.csdn.CSDNArticles;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @program: zyfboot-javabasic
 * @description: 任务：给指定的其他账号开始刷浏览量
 * @author: zhangyanfeng
 * @create: 2024-10-16 23:44
 **/
public class CSDNOnlySmallNumTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        int limitViewCount = 10000;
        List<String> zyfUrl = Lists.newArrayList(CSDNArticles.articlesForOnly());
        System.out.println("当前访问次数少于" + limitViewCount + "的文章个数为" + zyfUrl.size());

        for (int time = 0; time < 3000; time++) {
            Calendar cal1 = Calendar.getInstance();
            Date date1 = cal1.getTime();
            System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date1) + "执行访问全列表数据进行分析，当前次数：" + time);
            IntStream.range(0, zyfUrl.size()).forEach(idx -> {
                String urlTest = zyfUrl.get(idx);
                String res = HttpUtils.sendPost(urlTest, null); // 假设这是发送 POST 请求的方法
                try {
                    Thread.sleep(6);
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
        }
    }
}
