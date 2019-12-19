package org.zyf.javabasic.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：方法引用通过方法的名字来指向一个方法。
 *      方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 *      方法引用使用一对冒号 ::
 *
 * @author yanfengzhang
 * @date 2019-11-26 16:59
 */
public class MethodReference {
    public static void main(String args[]){
        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}
