package org.zyf.javabasic.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/10/31  11:13
 */
public class BaseTest {
    public static void main(String[] args) {
//        System.out.println("编码问题处理");
//        String[] wordArray = new String[]{"奥", "澳"};
//        List<String> wordsList = Lists.newArrayList();
//        for (String item : wordArray) {
//            try {
//                wordsList.add(new String(new Base64().encode(item.getBytes())));
//            } catch (Exception e) {
//                System.out.println("编码失败" + item);
//                wordsList.add(item);
//            }
//        }
//        System.out.println(wordsList.toString());
//
//
//        System.out.println("编码问题处理");
//        String[] wordArrayNew = new String[]{"先"};
//        List<String> wordsNewList = Lists.newArrayList();
//        for (String item : wordArrayNew) {
//            try {
//                wordsNewList.add(new String(new Base64().decode(item.getBytes())));
//            } catch (Exception e) {
//                System.out.println("编码失败" + item);
//                wordsNewList.add(item);
//            }
//        }
//        System.out.println(wordsNewList.toString());



        List<Long> categoryIdList = Lists.newArrayList();
        Set<Long> dd= Sets.newHashSet();
        dd.add(3L);
        categoryIdList.addAll(null);
        System.out.println(categoryIdList);
    }
}
