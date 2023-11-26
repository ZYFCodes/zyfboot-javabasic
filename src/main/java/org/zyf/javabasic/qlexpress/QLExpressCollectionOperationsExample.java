package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 集合操作
 * @author: zhangyanfeng
 * @create: 2023-11-19 19:04
 **/
public class QLExpressCollectionOperationsExample {
    public static void main(String[] args) {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 使用NewMap创建Map
            String expressMap = "abc = NewMap(1:1, 2:2); return abc.get(1) + abc.get(2);";
            Object resultMap = runner.execute(expressMap, context, null, false, false);
            System.out.println("NewMap Result: " + resultMap);

            // 使用NewList创建List
            String expressList = "abc = NewList(1, 2, 3); return abc.get(1) + abc.get(2);";
            Object resultList = runner.execute(expressList, context, null, false, false);
            System.out.println("NewList Result: " + resultList);

            // 使用方括号[]创建List
            String expressSquareBrackets = "abc = [1, 2, 3]; return abc[1] + abc[2];";
            Object resultSquareBrackets = runner.execute(expressSquareBrackets, context, null, false, false);
            System.out.println("Square Brackets Result: " + resultSquareBrackets);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
