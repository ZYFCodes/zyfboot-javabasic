package org.zyf.javabasic.test.interfaceandabstract;

/**
 * 描述：
 *
 * @author yanfengzhang
 * @date 2020-04-17 19:00
 */
public class DietBase extends Diet implements Life {
    @Override
    public String doSomething() {
        System.out.println("DietBase====");
        return "基本吃喝问题";
    }
}
