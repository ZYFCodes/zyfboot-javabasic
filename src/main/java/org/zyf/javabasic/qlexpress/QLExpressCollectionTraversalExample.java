package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 使用foreach关键字结合索引遍历集合
 * @author: zhangyanfeng
 * @create: 2023-11-19 19:36
 **/
public class QLExpressCollectionTraversalExample {
    public static void main(String[] args) {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 创建一个Map
            Map<String, String> map = new HashMap<>();
            map.put("a", "a_value");
            map.put("b", "b_value");

            // 将Map放入上下文中
            context.put("map", map);

            // 遍历Map
            String express = "keySet = map.keySet();\n" +
                    "objArr = keySet.toArray();\n" +
                    "for (i = 0; i < objArr.length; i++) {\n" +
                    "    key = objArr[i];\n" +
                    "    System.out.println(map.get(key));\n" +
                    "}";
            runner.execute(express, context, null, false, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
