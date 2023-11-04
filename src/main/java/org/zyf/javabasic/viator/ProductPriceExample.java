package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: Aviator 的变量语法糖
 * @author: zhangyanfeng
 * @create: 2023-10-23 00:46
 **/
public class ProductPriceExample {
    public static void main(String[] args) {
        // 创建一个 Product 对象
        Product product = new Product("Laptop", 999.99, new Date());

        // 创建 Aviator 表达式环境
        Map<String, Object> env = new HashMap<>();
        env.put("product", product);

        // 使用变量语法糖访问对象的属性
        double price = (double) AviatorEvaluator.execute("product.price", env);
        String name = (String) AviatorEvaluator.execute("product.name", env);

        System.out.println("Product Name: " + name);  // 输出 "Product Name: Laptop"
        System.out.println("Product Price: $" + price);  // 输出 "Product Price: $999.99"
    }

    @Data
    static class Product {
        private String name;
        private double price;
        private Date productionDate;

        public Product(String name, double price, Date productionDate) {
            this.name = name;
            this.price = price;
            this.productionDate = productionDate;
        }
    }
}
