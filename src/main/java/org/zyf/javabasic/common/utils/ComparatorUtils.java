package org.zyf.javabasic.common.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/2/13  14:46
 */
public class ComparatorUtils {
    public static <T extends Comparable<T>> int compareList(List<T> list1, List<T> list2) {
        int minLength = Math.min(list1.size(), list2.size());

        for (int i = 0; i < minLength; i++) {
            final int compareValue = list1.get(i).compareTo(list2.get(i));
            if (compareValue != 0) {
                /*They are already not equal*/
                return compareValue;
            }
        }
        if (list1.size() == list2.size()) {
            /*They are equal*/
            return 0;
        } else if (list1.size() < list2.size()) {
            /*list 1 is smaller*/
            return -1;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(new Integer[]{1, 2, 3, 6}));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(new Integer[]{1, 3, 2, 6}));

        boolean result = CollectionUtils.isEqualCollection(list1, list2);
        System.out.println("result = " + result);

        List<String> list3 = new ArrayList<>(Arrays.asList(new String[]{"1", "2", "3", "6"}));
        List<String> list4 = new ArrayList<>(Arrays.asList(new String[]{"1", "3", "2", "6"}));
        System.out.println(CollectionUtils.isEqualCollection(list3, list4));
    }
}
