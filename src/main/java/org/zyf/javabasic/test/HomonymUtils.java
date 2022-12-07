package org.zyf.javabasic.test;

import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import com.google.common.base.Splitter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

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

    /**
     * @param homonyWord   同音词拼音文本信息
     * @param contentValid 用户文本有效字段信息
     * @return true-需要被忽略；false不应该被忽略
     */
    public static boolean isIgnore(String homonyWord, String contentValid) {
        /*有效文本转换成对应的拼音*/
        String contentValidWords = PinyinHelper.toPinyin(contentValid, PinyinStyleEnum.NORMAL);
        /*转变成对应的拼音链表*/
        List<String> contentValidPys = Splitter.on(" ").omitEmptyStrings().splitToList(contentValidWords);
        if (CollectionUtils.isEmpty(contentValidPys)) {
            return false;
        }

        /*如果对应拼音链表中有对应的同音词且大小一致则一定是命中的，不应该被忽略*/
        for (String py : contentValidPys) {
            if (py.contains(homonyWord) && py.length() == homonyWord.length()) {
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

        String wordstr = "先";
        String homonyWord = PinyinHelper.toPinyin(wordstr, PinyinStyleEnum.NORMAL);
        String contentValid = "西安xian印象";
        System.out.println(isIgnore(homonyWord, contentValid));
    }
}

