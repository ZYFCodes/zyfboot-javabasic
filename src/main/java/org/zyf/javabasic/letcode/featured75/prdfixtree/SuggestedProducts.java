package org.zyf.javabasic.letcode.featured75.prdfixtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 搜索推荐系统
 * @author: zhangyanfeng
 * @create: 2024-08-24 13:52
 **/
public class SuggestedProducts {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> result = new ArrayList<>();
        // 对产品进行字典序排序
        Arrays.sort(products);

        // 从第一个字符开始，逐步增加前缀
        String prefix = "";
        for (char c : searchWord.toCharArray()) {
            prefix += c; // 添加当前字符到前缀
            List<String> suggestions = new ArrayList<>();
            // 遍历产品列表，找到以当前前缀开始的产品
            for (String product : products) {
                if (product.startsWith(prefix)) {
                    suggestions.add(product);
                    if (suggestions.size() == 3) {
                        break; // 只需要前 3 个匹配的产品
                    }
                }
            }
            result.add(suggestions); // 将当前前缀的推荐列表添加到结果中
        }

        return result;
    }

    public static void main(String[] args) {
        SuggestedProducts solution = new SuggestedProducts();

        // 示例测试用例
        String[] products1 = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord1 = "mouse";
        System.out.println(solution.suggestedProducts(products1, searchWord1));
        // 输出: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]

        String[] products2 = {"havana"};
        String searchWord2 = "havana";
        System.out.println(solution.suggestedProducts(products2, searchWord2));
        // 输出: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]

        String[] products3 = {"bags", "baggage", "banner", "box", "cloths"};
        String searchWord3 = "bags";
        System.out.println(solution.suggestedProducts(products3, searchWord3));
        // 输出: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]

        String[] products4 = {"havana"};
        String searchWord4 = "tatiana";
        System.out.println(solution.suggestedProducts(products4, searchWord4));
        // 输出: [[],[],[],[],[],[],[]]
    }
}
