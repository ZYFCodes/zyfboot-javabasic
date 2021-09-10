package org.zyf.javabasic.test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.zyf.javabasic.common.utils.FileUtils;
import org.zyf.javabasic.common.utils.HttpUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/8/26  12:11
 */
public class TestWhileTrue {

    private static Map<Integer, Integer> recordAll = Maps.newHashMap();

    public static void main(String[] args) throws InterruptedException, IOException {
        String zyfurl = "/Users/yanfengzhang/Downloads/zyfurl.txt";

        String recordAllText = "/Users/yanfengzhang/Downloads/zyfurlTotal.txt";
        String zyfurls = FileUtils.readFileContent(zyfurl);
        List<String> urlList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(zyfurls);
        List<String> urlDistinctList = urlList.stream().distinct().collect(Collectors.toList());
        int total = urlDistinctList.size();
        System.out.println("当前文章总数为：" + total);
        int time = 1;
        while (true) {
            int randomUrlNum = total / 2;
            List<Integer> randomIntList = getRandomInt(randomUrlNum, total, 0);
            System.out.println("第" + time + "次随机访问文章编号：" + randomIntList);
            System.out.println("对应随机访问" + randomUrlNum + "个网站如下");
            randomIntList.forEach(randomInt -> {
                HttpUtils.sendPost(urlDistinctList.get(randomInt), null);
                System.out.println("随机访问网站请求网站为：" + urlDistinctList.get(randomInt));
            });

            time++;
            System.out.println("本次随机访问结束！");
            FileUtils.writeToFile("基本访问次数统计如下：" + recordAll, recordAllText);
            Thread.sleep(60000);
        }

    }

    public static List<Integer> getRandomInt(int num, int max, int min) {
        List<Integer> result = Lists.newArrayList();

        Map<Integer, Integer> record = Maps.newHashMap();
        while (result.size() != num) {
            Random random = new Random();
            int randomNum = random.nextInt(max) % (max - min + 1) + min;
            if (null == record.get(randomNum)) {
                result.add(randomNum);
            }
            record.merge(randomNum, 1, Integer::sum);
            recordAll.merge(randomNum, 1, Integer::sum);
        }

        return result;
    }

    public static void beforeDeal(){
        String beafore=FileUtils.readFileContent("/Users/yanfengzhang/Downloads/zyfurlTotal.txt");
        String beafore1=beafore.substring(0,beafore.length()-1).replaceAll("\\{","");
        String beafore2=beafore1.replaceAll("\\}","");
        System.out.println(beafore2);

        Map<String, String> result = Splitter.on(",").withKeyValueSeparator("=").split(beafore2);
    }
}
