package org.zyf.javabasic.test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.zyf.javabasic.common.utils.FileUtils;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/10/29  17:18
 */
public class TestCountSensitiveId2 {

    private static String result = "/Users/yanfengzhang/Downloads/第1批次查询结果ZYF.csv";

    public static void main(String[] args) {
        List<String> sensitiveIds = Lists.newArrayList();
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第1批次查询结果ZYF.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第2批次查询结果-20211029.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第3批次查询结果.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第4批次查询结果.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第5批次查询结果.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第6批次查询结果.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第7批次查询结果.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第8批次查询结果.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第9批次查询结果.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第10批次查询结果.csv"));
        sensitiveIds.addAll(getSensitives("/Users/yanfengzhang/Downloads/第11批次查询结果.csv"));

        System.out.println(sensitiveIds.size());
        System.out.println(sensitiveIds);

    }


    private static List<String> getSensitives(String result) {
        String resultTemp = FileUtils.readFileContent2(result);
        String[] resultTempArray = resultTemp.split("@@@ZYF@@@");

        System.out.println(resultTempArray.length);

        List<String> sensitiveIds = Lists.newArrayList();
        for (int i = 1; i < resultTempArray.length; i++) {
            List<String> result1 = Splitter.on(",").trimResults().splitToList(resultTempArray[i]);
            if (result1.size() < 2) {
                continue;
            }
            sensitiveIds.add(result1.get(1));
        }

        return sensitiveIds;
    }
}
