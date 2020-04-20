package org.zyf.javabasic.designpatterns.decorator.basic;

/**
 * 描述：窗帘装饰类
 *
 * @author yanfengzhang
 * @date 2020-04-19 13:35
 */
public class CurtainDecorator extends BaseDecorator {
    public CurtainDecorator(IDecorator decorator) {
        super(decorator);
    }

    /**
     * 窗帘具体装饰方法
     */
    @Override
    public void decorate() {
        System.out.println("窗帘装饰。。。");
        super.decorate();
    }
}
