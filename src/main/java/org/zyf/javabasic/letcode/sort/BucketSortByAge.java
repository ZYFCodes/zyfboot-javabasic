package org.zyf.javabasic.letcode.sort;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author yanfengzhang
 * @description 按照年龄对一组人员进行排序：
 * 假设有一组人员信息，包括姓名、年龄等属性，需要按照年龄对这组人员进行排序。
 * 可以使用桶排序，以年龄作为排序的关键字，将人员信息分配到不同的年龄桶中，
 * 再对每个年龄桶内的人员信息进行排序，最后合并所有桶内的人员信息即可。
 * @date 2023/4/16  14:12
 */
public class BucketSortByAge {
    public static void bucketSortByAge(Person[] arr, int maxAge) {
        if (arr == null || arr.length <= 1 || maxAge <= 0) {
            return;
        }

        /*创建年龄桶*/
        ArrayList<Person>[] ageBuckets = new ArrayList[maxAge + 1];
        for (int i = 0; i <= maxAge; i++) {
            ageBuckets[i] = new ArrayList<>();
        }

        /*将人员信息分配到年龄桶中*/
        for (Person person : arr) {
            int age = person.getAge();
            ageBuckets[age].add(person);
        }

        /*对每个年龄桶内的人员信息进行排序*/
        int idx = 0;
        for (ArrayList<Person> bucket : ageBuckets) {
            if (bucket != null && !bucket.isEmpty()) {
                /*对每个年龄桶内的人员信息进行排序，这里可以使用任何合适的排序算法*/
                Collections.sort(bucket, new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        /*按照年龄升序排序*/
                        return o1.getAge() - o2.getAge();
                    }
                });

                /*将排序后的人员信息合并到原数组中*/
                for (Person person : bucket) {
                    arr[idx++] = person;
                }
            }
        }
    }

    @Data
    public static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        /*示例数据*/
        Person[] persons = new Person[6];
        persons[0] = new Person("Alice", 25);
        persons[1] = new Person("Bob", 30);
        persons[2] = new Person("Charlie", 22);
        persons[3] = new Person("Dave", 25);
        persons[4] = new Person("Eve", 22);
        persons[5] = new Person("Frank", 30);

        System.out.println("排序前：");
        for (Person person : persons) {
            System.out.println(person.getName() + " - " + person.getAge() + "岁");
        }

        /*调用桶排序算法进行排序*/
        bucketSortByAge(persons, 30);

        System.out.println("\n排序后：");
        for (Person person : persons) {
            System.out.println(person.getName() + " - " + person.getAge() + "岁");
        }
    }
}
