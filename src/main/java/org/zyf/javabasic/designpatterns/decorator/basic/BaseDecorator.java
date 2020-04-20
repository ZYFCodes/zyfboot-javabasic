package org.zyf.javabasic.designpatterns.decorator.basic;

/**
 * 描述：基本装饰类
 *
 * @author yanfengzhang
 * @date 2020-04-19 13:34
 */
public abstract class BaseDecorator implements IDecorator {
    private IDecorator decorator;

    public BaseDecorator(IDecorator decorator) {
        this.decorator = decorator;
    }

    /**
     * 调用装饰方法
     */
    @Override
    public void decorate() {
        if (decorator != null) {
            decorator.decorate();
        }
    }
}
