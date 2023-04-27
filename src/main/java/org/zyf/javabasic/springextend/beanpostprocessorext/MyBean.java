package org.zyf.javabasic.springextend.beanpostprocessorext;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/16  23:54
 */
//@Data
public class MyBean {
    private String myProperty;

    public void doSomething() {
        System.out.println("MyBean.doSomething()执行，myProperty=" + myProperty);
    }

    public void setMyProperty(String myProperty) {
        this.myProperty = myProperty;
    }
}
