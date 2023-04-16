package org.zyf.javabasic.letcode.sort;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author yanfengzhang
 * @description 对一组考试成绩进行排序
 * 假设有一组学生的考试成绩，需要按照成绩进行排序。
 * 可以使用桶排序，以考试成绩作为排序的关键字，将学生信息分配到不同的成绩桶中，
 * 再对每个成绩桶内的学生信息进行排序，最后合并所有桶内的学生信息即可。
 * @date 2023/4/16  14:18
 */
public class BucketSortByScore {
    public static void bucketSortByScore(Student[] arr, int maxScore) {
        if (arr == null || arr.length <= 1 || maxScore <= 0) {
            return;
        }

        /*创建成绩桶*/
        ArrayList<Student>[] scoreBuckets = new ArrayList[maxScore + 1];
        for (int i = 0; i <= maxScore; i++) {
            scoreBuckets[i] = new ArrayList<>();
        }

        /*将学生信息分配到成绩桶中*/
        for (Student student : arr) {
            int score = student.getScore();
            scoreBuckets[score].add(student);
        }

        /*对每个成绩桶内的学生信息进行排序*/
        int idx = 0;
        for (ArrayList<Student> bucket : scoreBuckets) {
            if (bucket != null && !bucket.isEmpty()) {
                /*对每个成绩桶内的学生信息进行排序，这里可以使用任何合适的排序算法*/
                Collections.sort(bucket, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        /*按照成绩降序排序*/
                        return o2.getScore() - o1.getScore();
                    }
                });

                /*将排序后的学生信息合并到原数组中*/
                for (Student student : bucket) {
                    arr[idx++] = student;
                }
            }
        }
    }

    @Data
    public static class Student {
        private String name;
        private int score;

        /*构造函数*/
        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) {
        /*示例数据*/
        Student[] students = new Student[6];
        students[0] = new Student("Alice", 90);
        students[1] = new Student("Bob", 78);
        students[2] = new Student("Charlie", 88);
        students[3] = new Student("Dave", 95);
        students[4] = new Student("Eve", 82);
        students[5] = new Student("Frank", 88);

        System.out.println("排序前：");
        for (Student student : students) {
            System.out.println(student.getName() + " - 成绩：" + student.getScore());
        }

        /*调用桶排序算法进行排序*/
        bucketSortByScore(students, 100);

        System.out.println("\n排序后：");
        for (Student student : students) {
            System.out.println(student.getName() + " - 成绩：" + student.getScore());
        }
    }
}
