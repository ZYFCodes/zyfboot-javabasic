package org.zyf.javabasic.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListTest {

    public static void main(String[] args) {
//        List<Long> activityUuidList = Lists.newArrayList();
//        activityUuidList.add(Long.parseLong("234"));
//        activityUuidList.add(Long.parseLong("234"));
//        activityUuidList.add(Long.parseLong("345"));
//        activityUuidList.add(Long.parseLong("234"));
//        activityUuidList.add(Long.parseLong("123"));
//        activityUuidList.add(Long.parseLong("654"));
//        activityUuidList.add(null);
//        activityUuidList.add(null);
//
//        /*只能去除 List 列表中的重复对象，不去除含有null的元素*/
//        List<Long> activityUuidList1 = Lists.newArrayList(Sets.newHashSet(activityUuidList));
//        /*允许去除 List 列表中 重复对象以及null对象*/
//        List<Long> activityUuidList2 = ImmutableSet.copyOf(Iterables.filter(activityUuidList, Predicates.not(Predicates.isNull()))).asList();
//
//        System.out.println(activityUuidList);
//        System.out.println(activityUuidList1);
//        System.out.println(activityUuidList2);
//
//        User zyf1 = new User("1", "ZYF1", "18");
//        User zyf2 = new User("1", "ZYF1", "34");
//        User zyf3 = new User("1", "ZYF3", "18");
//        User zyf4 = new User("1", "ZYF1", "18");
//        User zyf5 = new User("1", "ZYF1", "18");
//
//
//        List<User> zyfList = Lists.newArrayList();
//        zyfList.add(zyf1);
//        zyfList.add(zyf2);
//        zyfList.add(zyf3);
//        zyfList.add(zyf4);
//        zyfList.add(zyf5);
//        zyfList.add(null);
//
//        List<User> zyfList1 = Lists.newArrayList(Sets.newHashSet(zyfList));
//        List<User> zyfList2 = ImmutableSet.copyOf(Iterables.filter(zyfList, Predicates.not(Predicates.isNull()))).asList();
//
//        System.out.println(zyfList);
//        System.out.println(zyfList1);
//        System.out.println(zyfList2);
//
//
//        System.out.println("Lists.newArrayList(50, 40, 30, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)");
//        List<Integer> instances = Lists.newArrayList(50, 40, 30, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
//        Collections.sort(instances);
//        Collections.reverse(instances);
//        System.out.println(instances);
//
//        System.out.println(getRefuelPackages(22));
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.format(new Date()));
//
//        System.out.println("测试list addall");
//        List<Integer> list1 = Lists.newArrayList();
//        list1.add(1);
//        list1.add(2);
//        list1.add(3);
//        List<Integer> list2 = Lists.newArrayList();
//        list2.add(4);
//        list2.addAll(list1);
//        System.out.println(list2);
//
//        List<Long> wordIdListByRule = null;
////        wordIdListByRule.add(1L);
////        wordIdListByRule.add(2L);
////        wordIdListByRule.add(3L);
//        // wordMapper过滤出符合条件的wordId
//        List<Long> wordIdsByWordMapper = Lists.newArrayList();
//        wordIdsByWordMapper.add(1L);
//        wordIdsByWordMapper.add(2L);
//        wordIdsByWordMapper.add(3L);
//        System.out.println(wordIdsByWordMapper.stream().filter(wordIdListByRule::contains).collect(Collectors.toList()));

//
//        String content = "感冒了";
//        List<String> keyWords = Splitter.on("-").omitEmptyStrings().splitToList("感-冒-了");
//
//
//        String needContain = keyWords.get(0);
//        if (!content.contains(needContain)) {
//            return;
//        }
//
//        boolean hit =true;
//        for (int i = 1; i < keyWords.size(); i++) {
//            if (content.contains(keyWords.get(i))) {
//                hit=false;
//                System.out.println(hit+keyWords.get(i));
//            }
//        }
//        System.out.println(keyWords+""+hit);

//        AtomicBoolean allHit = new AtomicBoolean(true);
//        keyWords.remove(needContain);
//        List<String> keyWordsNew = org.assertj.core.util.Lists.newArrayList(keyWords);
//        keyWordsNew.forEach(keyWord -> {
//            if (content.contains(keyWord)) {
//                /*只要有一个匹配上就结束*/
//                allHit.set(false);
//                System.out.println("keyWord"+allHit);
//            }
//        });
//        System.out.println(keyWords);



        JSONObject resultConfigJson = new JSONObject();
        resultConfigJson.put("rr",0.1);
        System.out.println(resultConfigJson.getInteger("rr"));



    }

    private static Map<Integer, Integer> getRefuelPackages(int number) {
        List<Integer> instances = Lists.newArrayList(9, 7, 5, 3, 1);
        Map<Integer, Integer> packages = new HashMap<>();
        for (int i = 0; i < instances.size() && number != 0; i++) {
            int divideNumber = number / instances.get(i);
            int modNumber = number % instances.get(i);
            if (divideNumber > 0) {
                packages.put(instances.get(i), divideNumber);
            }
            number = modNumber;
        }
        return packages;
    }
}
