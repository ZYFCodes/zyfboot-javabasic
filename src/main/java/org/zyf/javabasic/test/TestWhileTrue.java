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
    private static String recordAllText = "/Users/yanfengzhang/Downloads/zyfurlTotal.txt";
    private static String zyfurl = "/Users/yanfengzhang/Downloads/zyfurl.txt";

    public static void main(String[] args) throws InterruptedException, IOException {
        String zyfurls = FileUtils.readFileContent(zyfurl);
        List<String> urlList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(zyfurls);
        List<String> urlDistinctList = urlList.stream().distinct().collect(Collectors.toList());
        int total = urlDistinctList.size();
        System.out.println("当前文章总数为：" + total);
        int time = 1;
        while (true) {
            int randomUrlNum = total / 2;
            List<Integer> randomIntList = getRandomInt(4, 7, 0);
            System.out.println("第" + time + "次随机访问文章编号：" + randomIntList);
            System.out.println("对应随机访问" + randomUrlNum + "个网站如下");
            randomIntList.forEach(randomInt -> {
                HttpUtils.sendPost(urlDistinctList.get(randomInt), null);
                System.out.println("随机访问网站请求网站为：" + urlDistinctList.get(randomInt));
            });

            time++;
            System.out.println("本次随机访问结束！");
            FileUtils.writeToFile(recordAll.toString(), recordAllText);
            Thread.sleep(60000);
        }

    }

    /**
     * 获取指定范围的指定返回个数的随机结果集合
     *
     * @param num 需要返回的总个数
     * @param max 随机最大值
     * @param min 随机最小值
     * @return 需要返回的总个数集合
     */
    public static List<Integer> getRandomInt(int num, int max, int min) {
        List<Integer> result = Lists.newArrayList();

        Map<Integer, Integer> record = Maps.newHashMap();
        recordAll = beforeDeal();
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

    public static Map<Integer, Integer> beforeDeal() {
        String fileContent = FileUtils.readFileContent(recordAllText);
        String fileContentDeal = fileContent.substring(0, fileContent.length() - 1).replaceAll("\\{", "");
        String fileContentDealFinal = fileContentDeal.replaceAll("\\}", "");

        Map<String, String> result = Splitter.on(",").withKeyValueSeparator("=").split(fileContentDealFinal);
        Map<Integer, Integer> initMap = Maps.newHashMap();
        for (String element : result.keySet()) {
            int time = Integer.valueOf(result.get(element));
            element = element.trim();
            initMap.put(Integer.valueOf(element), time);
        }
        return initMap;
    }
}
