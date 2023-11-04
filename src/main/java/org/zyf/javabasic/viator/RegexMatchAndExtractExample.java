package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 执行正则表达式匹配并提取匹配的内容
 * @author: zhangyanfeng
 * @create: 2023-10-23 00:30
 **/
public class RegexMatchAndExtractExample {
    public static void main(String[] args) {
        String email = "killme2008@gmail.com";
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("email", email);
        String username = (String) AviatorEvaluator.execute("email=~/([\\w0-8]+)@\\w+[\\.\\w+]+/ ? $1 : 'unknow' ", env);
        System.out.println(username); // killme2008
    }
}
