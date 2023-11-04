package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义对象比较
 * @author: zhangyanfeng
 * @create: 2023-10-21 16:46
 **/
public class CustomObjectComparisonExample {
    public static void main(String[] args) {
        // 自定义对象实现 java.lang.Comparable 接口
        class MyObject implements Comparable<MyObject> {
            private int value;

            public MyObject(int value) {
                this.value = value;
            }

            @Override
            public int compareTo(MyObject other) {
                return Integer.compare(this.value, other.value);
            }
        }

        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile("obj1 > obj2");

        Map<String, Object> env = new HashMap<String, Object>();
        env.put("obj1", new MyObject(5));
        env.put("obj2", new MyObject(3));

        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);  // 输出 true
    }
}
