package org.zyf.javabasic.designpatterns.decorator.basic;

/**
 * 描述：具体使用测试
 *
 * @author yanfengzhang
 * @date 2020-04-19 13:36
 */
public class Test {
    public static void main(String[] args) {
        IDecorator decorator = new Decorator();
        IDecorator curtainDecorator = new CurtainDecorator(decorator);
        curtainDecorator.decorate();
    }
}
