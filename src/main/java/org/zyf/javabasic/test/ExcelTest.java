package org.zyf.javabasic.test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.zyf.javabasic.common.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/28  14:39
 */
public class ExcelTest {

    private static String zyfurl = "/Users/yanfengzhang/Downloads/团餐子门店最新.txt";

    public static void main(String[] args) throws InterruptedException, IOException {
        String tuancanPoiInfoStr = FileUtils.readFileContent(zyfurl);
        List<String> tuancanPoiInfos = Splitter.on(",").omitEmptyStrings().splitToList(tuancanPoiInfoStr);
        StringBuilder sb = new StringBuilder();
        List<Integer> poiNum = Lists.newArrayList();
        tuancanPoiInfos.forEach(tuancanInfo -> {
            List<Integer> number = parseInt(tuancanInfo);
            if (CollectionUtils.isNotEmpty(number)) {
                sb.append(number.get(0)).append(",");
                poiNum.add(number.get(0));
            }
            System.out.println(poiNum.size());

        });

        System.out.println(sb);

    }

    /**
     * 解析一个字符串，获取所有连续的数字，用集合接收
     */
    public static List<Integer> parseInt(String str) {
        // 解析数字
        ArrayList<Integer> list = new ArrayList<>();
        int start = 0, end, len = str.length();
        for (int i = 0; i < len; i++) {
            char cTop = 0;
            char cRear = 0;
            char c = str.charAt(i);
            if (i > 0) {
                cTop = str.charAt(i - 1);
            }
            if (i < len - 1) {
                cRear = str.charAt(i + 1);
            }
            // 如果 c 是数字 且 它前一个数不是数字 或者 它是第 0 个字符时，获得 start
            if (isNumber(c) && (!isNumber(cTop) || i == 0)) {
                start = i;
            }
            // 如果 c 是数字 且 它后一个数不是数字 或者 它是最后一个字符时，获得 end
            if (isNumber(c) && (!isNumber(cRear) || i == len - 1)) {
                end = i + 1;
                list.add(Integer.parseInt(str.substring(start, end)));
            }
        }
        return list;
    }

    /**
     * 判断是否是数字
     */
    public static boolean isNumber(char c) {
        // ascii 编码
        return c >= 48 && c <= 57;
    }
}
