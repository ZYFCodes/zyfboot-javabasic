package org.zyf.javabasic.test;

import org.zyf.javabasic.common.utils.FileUtils;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/11/9  19:00
 */
public class TestHiddenChar {

    //    private static Map<Character, Integer> recordAll = Maps.newHashMap();
    private static String recordAllText = "/Users/yanfengzhang/Downloads/Hidden-character.txt";

    public static void main(String[] args) {
        System.out.println("---------------");
        StringBuilder sb = new StringBuilder();
        char a = '\u0000';
        for (int i = 0; i < 10000; i++) {
            char t = (char) ((int) a + i);
            sb.append(t).append("====").append((int) t).append("\n");
//            recordAll.put(t,(int)t);
        }
        char aq = '\u2065';
        System.out.println(aq);
        System.out.println((int) aq);

        FileUtils.writeToFile(sb.toString(), recordAllText);
        System.out.println("---------------");
    }


}
