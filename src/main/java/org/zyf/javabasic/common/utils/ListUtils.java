package org.zyf.javabasic.common.utils;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/3/19  21:13
 */
public class ListUtils {
    /**
     * @param list     切割集合
     * @param pageSize 分页长度
     * @return List<List < T>>返回分页数据
     */
    public static <T> List<List<T>> splitList(List<T> list, int pageSize) {
        int listSize = list.size();
        int page = (listSize + (pageSize - 1)) / pageSize;
        List<List<T>> listArray = Lists.newArrayList();
        for (int i = 0; i < page; i++) {
            List<T> subList = Lists.newArrayList();
            for (int j = 0; j < listSize; j++) {
                int pageIndex = ((j + 1) + (pageSize - 1)) / pageSize;
                if (pageIndex == (i + 1)) {
                    subList.add(list.get(j));
                }
                if ((j + 1) == ((j + 1) * pageSize)) {
                    break;
                }
            }
            listArray.add(subList);
        }
        return listArray;

    }


    public static void main(String[] args) {
        List<String> testList = Lists.newArrayList();
        for (int i = 0; i < 1045; i++) {
            testList.add("asd\\x01dfff\\x01gfdd\\x01" + i);
        }

        StringBuilder testResult = new StringBuilder();
        List<List<Object>> dealNumList = Lists.newArrayList();
        for (int i = 0; i < testList.size(); i++) {
            /*显示行号*/
            List<Object> hiveQueryResult = Arrays.stream(testList.get(i).split("\\x01")).collect(Collectors.toList());
            /*将数据放入到结果处理列表中*/
            dealNumList.add(hiveQueryResult);
            if (dealNumList.size() > 99) {
                List<List<List<Object>>> splitList = ListUtils.splitList(dealNumList, 20);
                System.out.println("htfghjkhgbftgyhujhbg======" + splitList.size());
                for (int j = 0; j < splitList.size(); j++) {
                    List<List<Object>> needSaveResult = splitList.get(j);
                    testResult.append(j).append(needSaveResult).append("\n");
                }
                dealNumList.clear();
            }
        }

        if (!CollectionUtils.isEmpty(dealNumList)) {
            for (int j = 0; j < dealNumList.size(); j++) {
                testResult.append(j).append(dealNumList).append("\n");
            }
            dealNumList.clear();
        }
        System.out.println(dealNumList.size() + ":" + dealNumList);
        System.out.println(testResult.toString());
    }
}
