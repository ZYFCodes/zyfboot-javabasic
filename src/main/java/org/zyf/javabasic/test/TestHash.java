package org.zyf.javabasic.test;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/3/11  21:51
 */
public class TestHash {

    public static void main(String[] args) {
        for (Integer i = 1; i < 45; i++) {
            int hash = i.hashCode();
            int index = (hash & 0x7FFFFFFF) % 16;
            System.out.println(index);
        }

    }

}
