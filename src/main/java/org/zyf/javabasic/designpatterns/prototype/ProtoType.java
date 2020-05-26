package org.zyf.javabasic.designpatterns.prototype;

/**
 * 描述：原型抽象类需要实现Cloneable接口
 *
 * @author yanfengzhang
 * @date 2020-05-26 10:07
 */
public class ProtoType implements Cloneable {
    /*重写clone方法*/
    @Override
    public ProtoType clone() {
        ProtoType protoType = null;
        try {
            protoType = (ProtoType) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return protoType;
    }
}
