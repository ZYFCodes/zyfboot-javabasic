package org.zyf.javabasic.designpatterns.prototype;

import lombok.Data;

/**
 * 描述：对复制的理解分析
 *
 * @author yanfengzhang
 * @date 2020-05-26 11:06
 */
@Data
public class EmployeePrototypeTest {
    private String name;

    public static void main(String[] args) {
        EmployeePrototypeTest employeePrototypeTest1 = new EmployeePrototypeTest();
        employeePrototypeTest1.setName("zhangyanfeng");

        EmployeePrototypeTest employeePrototypeTest2 = employeePrototypeTest1;
        employeePrototypeTest2.setName("zhouyi");

        System.out.println("未使用clone方法实现的复制，只是对象引用的复制，测试结果如下：");
        System.out.println("原型模式测验，当前员工1姓名为：" + employeePrototypeTest1.getName());
        System.out.println("原型模式测验，当前员工2姓名为：" + employeePrototypeTest2.getName());

        EmployeePrototype employeePrototype1 = new EmployeePrototype();
        employeePrototype1.setName("zhangyanfeng");

        EmployeePrototype employeePrototype2 = employeePrototype1.clone();
        employeePrototype2.setName("zhouyi");

        System.out.println("使用clone方法实现的复制，测试结果如下：");
        System.out.println("原型模式测验，当前员工1姓名为：" + employeePrototype1.getName());
        System.out.println("原型模式测验，当前员工2姓名为：" + employeePrototype2.getName());
    }
}
