package org.zyf.javabasic.test;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/1  11:29
 */
public class TestInfo {
    public static void main(String[] args) {
        List<Long> activityUuidList = Lists.newArrayList();
        activityUuidList.add(Long.parseLong("234"));
        activityUuidList.add(Long.parseLong("234"));
        activityUuidList.add(Long.parseLong("345"));
        activityUuidList.add(Long.parseLong("234"));
        activityUuidList.add(Long.parseLong("123"));
        activityUuidList.add(Long.parseLong("654"));

        for (int i = 0; i < activityUuidList.size(); i++) {
            System.out.println(i + 1 + ":" + activityUuidList.get(i));
        }
    }
}
