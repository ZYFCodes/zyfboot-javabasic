package org.zyf.javabasic.test.interfaceandabstract;

/**
 * 描述：
 *
 * @author yanfengzhang
 * @date 2020-04-17 18:58
 */
public abstract class Travel implements Life {
    @Override
    public String doSomething() {
        System.out.println("Travel====");
        return "出行问题";
    }
}
