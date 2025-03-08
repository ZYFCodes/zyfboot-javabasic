package org.zyf.javabasic.test.wzzz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: CurrentMomentWords
 * @author: zhangyanfeng
 * @create: 2025-01-11 20:00
 **/
public class CurrentMomentWords {

    // 初始化“此刻”词汇库
    private static final List<String> currentMomentWords = new ArrayList<>();

    static {
        // 添加“此刻”相关词汇
        String[] words = {
                // 表达时间的词汇
                "此时", "此刻", "现在", "眼下", "此情此景", "当下", "此时此地", "此分此秒", "这一瞬间", "眼前",
                "当前时刻", "此刻此地", "此分此秒", "眼前光景", "现如今", "如今", "当此之际", "这会儿", "这时候",
                "此时此景", "此间", "当此刻", "目前", "眼前的瞬间", "当前", "现时", "现在这一刻", "眼下时分",

                // 特殊情感的表达
                "这一秒", "这一刻", "这一刹那", "现在的光景", "当前的情景", "这一时刻", "这一会儿", "此刻的心情",
                "现在的场景", "当下的瞬间", "这一秒钟", "这一时间点", "这短暂的时光", "当下的气氛", "眼下的情景",
                "这一分一秒", "此刻的情怀", "眼前的画面", "当前的瞬间", "这独特的瞬间", "眼前的时光",
                "当下的感受", "这一会儿的情景", "此刻的气氛"
        };

        for (String word : words) {
            currentMomentWords.add(word);
        }
    }

    // 随机返回一个“此刻”相关词汇
    public static String getRandomCurrentMomentWord() {
        Random random = new Random();
        return currentMomentWords.get(random.nextInt(currentMomentWords.size()));
    }

    public static void main(String[] args) {
        // 测试随机“此刻”词汇
        for (int i = 0; i < 10; i++) {
            System.out.println(getRandomCurrentMomentWord());
        }
    }
}

