package org.zyf.javabasic.designpatterns.decorator.basic;

/**
 * 描述：装修基本类
 *
 * @author yanfengzhang
 * @date 2020-04-19 13:32
 */
public class Decorator implements IDecorator {
    /**
     * 基本实现方法
     */
    @Override
    public void decorate() {
        System.out.println("水电装修、天花板以及粉刷墙.");
    }
}
