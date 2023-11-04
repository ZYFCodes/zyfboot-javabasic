package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 计算员工年龄平均值
 * @author: zhangyanfeng
 * @create: 2023-10-23 00:37
 **/
public class EmployeeAverageAgeExample {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 30));
        employees.add(new Employee("Alice", 25));
        employees.add(new Employee("Bob", 35));

        // 创建一个环境 Map 并将员工列表放入其中
        Map<String, Object> env = new HashMap<>();
        env.put("employees", employees);

        // 计算平均年龄
        Double totalAge = 0.0;
        for (Employee employee : employees) {
            totalAge += employee.getAge();
        }
        int numEmployees = employees.size();
        Double avgAge = totalAge / numEmployees;

        // 将平均年龄传递给 Aviator 表达式
        env.put("avgAge", avgAge);

        // 使用变量语法糖访问平均年龄
        Double result = (Double) AviatorEvaluator.execute("avgAge", env);

        System.out.println("Average Age: " + result);  // 输出平均年龄
    }

    @Data
    static class Employee {
        private String name;
        private int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
