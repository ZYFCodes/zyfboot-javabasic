package org.zyf.javabasic.common.utils;

import lombok.extern.log4j.Log4j2;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  19:40
 */
@Log4j2
public class PinyinUtils {
    private static final int LENGTH = 128;

    /**
     * 获得汉语拼音首字母
     *
     * @param chines 汉字
     * @return 结果
     */
    public static String getAlpha(String chines) {
        chines = cleanChar(chines);
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > LENGTH) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    log.error("getAlpha", e);
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }

    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     *
     * @param inputString 汉字
     * @return 结果
     */
    public static String getPingYin(String inputString) {
        inputString = cleanChar(inputString);
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String output = "";
        if (inputString != null && inputString.length() > 0 && !"null".equals(inputString)) {
            char[] input = inputString.trim().toCharArray();
            try {
                for (int i = 0; i < input.length; i++) {
                    if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                        output += temp[0];
                    } else {
                        output += java.lang.Character.toString(input[i]);
                    }
                }
            } catch (Exception e) {
                log.error("inputString : {} ,\n e: {}", inputString, e);
            }
        } else {
            return "*";
        }
        return output;
    }

    /**
     * 汉字转换位汉语拼音首字母，英文字符不变
     *
     * @param chines 汉字
     * @return 拼音
     */
    public static String converterToFirstSpell(String chines) {
        if (StringUtils.isEmpty(chines)) {
            return "";
        }
        chines = cleanChar(chines);
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > LENGTH) {
                try {
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat);
                    if (strs == null) {
                        pinyinName += nameChar[i];
                    } else {
                        pinyinName += strs[0].charAt(0);
                    }

                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    log.error("converterToFirstSpell", e);
                }
            } else {
                pinyinName += nameChar[i];
            }
        }
        return pinyinName;
    }


    /**
     * 汉字转换位汉语拼音首字母，英文字符不变,只取第一个中文*
     *
     * @param chines 汉字
     * @return 结果
     */
    public static String converterToOnlyFirstSpell(String chines) {
        chines = cleanChar(chines);
        String pinyinName = "";
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        if (nameChar.length > 0) {
            if (nameChar[0] > LENGTH) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[0], defaultFormat)[0].charAt(0);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    log.error("{}", e);
                }
            } else {
                pinyinName += nameChar[0];
            }
        }
        return pinyinName;
    }

    /**
     * 清理特殊字符以便得到
     *
     * @param chines 汉字
     * @return 结果
     */
    public static String cleanChar(String chines) {
        chines = chines.replaceAll("[\\p{Punct}\\p{Space}]+", ""); // 正则去掉所有字符操作
        // 正则表达式去掉所有中文的特殊符号
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}<>《》【】‘；：”“’。，、？φ＋]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(chines);
        chines = matcher.replaceAll("").trim();
        return chines;
    }
}
