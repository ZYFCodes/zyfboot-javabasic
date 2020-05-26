package org.zyf.javabasic.designpatterns.prototype;

/**
 * 描述：具体使用
 *
 * @author yanfengzhang
 * @date 2020-05-26 10:16
 */
public class Client {
    public static void main(String[] args) {
        ConcreteProtoType concreteProtoType = new ConcreteProtoType();
        for (int i = 0; i < 10; i++) {
            ConcreteProtoType cloneConcreteProtoType = (ConcreteProtoType) concreteProtoType.clone();
            cloneConcreteProtoType.proto();
        }
    }
}
