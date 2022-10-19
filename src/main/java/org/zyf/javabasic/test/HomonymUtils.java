package org.zyf.javabasic.test;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/10/14  15:07
 */
public class HomonymUtils {
    /*
     * 是否是英文字符串
     */
    public static boolean isEnglishStr(String charaString) {
        return charaString.matches("^[a-zA-Z]*");
    }

    /*
     *  是否是中文字符串
     */
    public static boolean isChineseStr(String str) {
        return str.matches("[\u4e00-\u9fa5]+");
    }

    public static boolean isChineseChar(char c) {

        return String.valueOf(c).matches("[\u4e00-\u9fa5]");

    }

    public static boolean isEnglishChar(char c) {

        return String.valueOf(c).matches("^[a-zA-Z]*");

    }

    /**
     * 判断是否是中文或英文字母
     *
     * @param
     * @return
     */
    public static boolean conValidate(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        for (char item : str.toCharArray()) {
            if (!isEnglishChar(item) && !isChineseChar(item)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String test1 = "zhha张";
        String test2 = "zhha";
        String test3 = "张";
        String test4 = "张45t%";

        System.out.println("是否是英文字符串:" + isEnglishStr(test1) + "_" + isEnglishStr(test2) + "_" + isEnglishStr(test3) + "_" + isEnglishStr(test4));
        System.out.println("是否是中文字符串:" + isChineseStr(test1) + "_" + isChineseStr(test2) + "_" + isChineseStr(test3) + "_" + isChineseStr(test4));
        System.out.println("判断是否是中文或英文字母:" + conValidate(test1) + "_" + conValidate(test2) + "_" + conValidate(test3) + "_" + conValidate(test4));
    }
}

