package org.zyf.javabasic.test.interfaceandabstract;

/**
 * 描述：
 *
 * @author yanfengzhang
 * @date 2020-04-17 18:52
 */
public abstract class Diet implements Life {
    @Override
    public String doSomething() {
        System.out.println("Diet====");
        return "饮食问题";
    }
}
