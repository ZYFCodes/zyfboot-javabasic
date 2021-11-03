package org.zyf.javabasic.test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.zyf.javabasic.common.utils.FileUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/10/29  15:13
 */
public class TestCountSensitiveId {

    private static String result = "/Users/yanfengzhang/Downloads/记录指定分类的敏感词id-295037529-1635492140569.txt";

    public static void main(String[] args) {
        String resultTemp = FileUtils.readFileContent(result);
        List<String> resultTempList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(resultTemp);
        List<String> resultTempNewList = resultTempList.subList(1, resultTempList.size());

        System.out.println(resultTempNewList.size());
        Set<String> set = new HashSet<>(resultTempNewList);
        System.out.println(set.size());

        List<String> dd = Lists.newArrayList();
        dd.addAll(set);
        System.out.println(dd.size());

        List<List<String>> subSets = Lists.partition(dd, 2000);
        for (List<String> subs : subSets) {
            System.out.println(Joiner.on(",").skipNulls().join(subs));
        }
    }
}
