package org.zyf.javabasic.test;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.zyf.javabasic.common.User;

import java.util.List;

public class ListTest {

    public static void main(String[] args) {
        List<Long> activityUuidList = Lists.newArrayList();
        activityUuidList.add(Long.parseLong("234"));
        activityUuidList.add(Long.parseLong("234"));
        activityUuidList.add(Long.parseLong("345"));
        activityUuidList.add(Long.parseLong("234"));
        activityUuidList.add(Long.parseLong("123"));
        activityUuidList.add(Long.parseLong("654"));
        activityUuidList.add(null);
        activityUuidList.add(null);

        /*只能去除 List 列表中的重复对象，不去除含有null的元素*/
        List<Long> activityUuidList1 = Lists.newArrayList(Sets.newHashSet(activityUuidList));
        /*允许去除 List 列表中 重复对象以及null对象*/
        List<Long> activityUuidList2 = ImmutableSet.copyOf(Iterables.filter(activityUuidList, Predicates.not(Predicates.isNull()))).asList();

        System.out.println(activityUuidList);
        System.out.println(activityUuidList1);
        System.out.println(activityUuidList2);

        User zyf1 = new User("1", "ZYF1", "18");
        User zyf2 = new User("1", "ZYF1", "34");
        User zyf3 = new User("1", "ZYF3", "18");
        User zyf4 = new User("1", "ZYF1", "18");
        User zyf5 = new User("1", "ZYF1", "18");


        List<User> zyfList = Lists.newArrayList();
        zyfList.add(zyf1);
        zyfList.add(zyf2);
        zyfList.add(zyf3);
        zyfList.add(zyf4);
        zyfList.add(zyf5);
        zyfList.add(null);

        List<User> zyfList1 = Lists.newArrayList(Sets.newHashSet(zyfList));
        List<User> zyfList2 = ImmutableSet.copyOf(Iterables.filter(zyfList, Predicates.not(Predicates.isNull()))).asList();

        System.out.println(zyfList);
        System.out.println(zyfList1);
        System.out.println(zyfList2);
    }
}
