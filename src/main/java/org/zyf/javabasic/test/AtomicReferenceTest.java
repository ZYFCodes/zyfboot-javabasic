package org.zyf.javabasic.test;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/15  11:55
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<AtomicReferenceArray<String>> row = new AtomicReference<>(new AtomicReferenceArray<>(new String[3]));
        StringBuilder stringBuilder = new StringBuilder("ceshi\n");
        for (int i = 0; i < 3; i++) {
            row.set(new AtomicReferenceArray<>(new String[3]));
            row.get().set(0, String.valueOf(7L));
            row.get().set(1, String.valueOf(i));
            row.get().set(2, String.valueOf(i));
            stringBuilder.append(StringUtils.join(row, ",").replaceAll("\n", "")).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
