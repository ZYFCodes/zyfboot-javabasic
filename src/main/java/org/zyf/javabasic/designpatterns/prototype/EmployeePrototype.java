package org.zyf.javabasic.designpatterns.prototype;

import lombok.Data;

/**
 * 描述：原型模式具体案例
 *
 * @author yanfengzhang
 * @date 2020-05-26 10:39
 */
@Data
public class EmployeePrototype implements Cloneable {
    private String name;

    /**
     * 功能描述：原型抽象类需要实现Cloneable接口,重写clone方法
     *
     * @author yanfengzhang
     * @date 2020-05-26 10:49
     */
    @Override
    public EmployeePrototype clone() {
        EmployeePrototype employeePrototype = null;
        try {
            employeePrototype = (EmployeePrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return employeePrototype;
    }

    public static void main(String[] args) {
        EmployeePrototype employeePrototype1 = new EmployeePrototype();
        employeePrototype1.setName("zhangyanfeng");

        EmployeePrototype employeePrototype2 = employeePrototype1.clone();
        employeePrototype2.setName("zhouyi");

        System.out.println("原型模式测验，当前员工1姓名为：" + employeePrototype1.getName());
        System.out.println("原型模式测验，当前员工2姓名为：" + employeePrototype2.getName());
    }
}
