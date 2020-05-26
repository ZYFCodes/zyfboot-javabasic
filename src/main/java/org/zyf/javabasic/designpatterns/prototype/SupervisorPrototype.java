package org.zyf.javabasic.designpatterns.prototype;

import lombok.Data;

/**
 * 描述：复合形势下的原型模式，对员工增加对应的上司
 *
 * @author yanfengzhang
 * @date 2020-05-26 11:20
 */
@Data
public class SupervisorPrototype implements Cloneable {
    private String name;

    /**
     * 功能描述：原型抽象类需要实现Cloneable接口,重写clone方法
     *
     * @author yanfengzhang
     * @date 2020-05-26 10:49
     */
    @Override
    public SupervisorPrototype clone() {
        SupervisorPrototype supervisorPrototype = null;
        try {
            supervisorPrototype = (SupervisorPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return supervisorPrototype;
    }
}
