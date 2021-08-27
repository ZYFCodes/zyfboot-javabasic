package org.zyf.javabasic.test;

import com.google.common.base.Splitter;
import org.zyf.javabasic.common.utils.FileUtils;
import org.zyf.javabasic.common.utils.HttpUtils;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/8/26  12:11
 */
public class TestWhileTrue {

    public static void main(String[] args) throws InterruptedException {
        String zyfurl = "/Users/yanfengzhang/Downloads/zyfurl.txt";
        String zyfurls = FileUtils.readFileContent(zyfurl);
        List<String> urlList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(zyfurls);
        System.out.println("当前文章总数为：" + urlList.size());
        int time=1;
        while (true) {
            int finalTime = time;
            urlList.forEach(url -> {
                System.out.println("请求网站为：" + url + ",请求次数为：" + finalTime);
                HttpUtils.sendPost(url, null);
            });
            System.out.println("以上文章已全部访问完成"+time+"次");
            time++;
            Thread.sleep(60000);
        }
    }
}
