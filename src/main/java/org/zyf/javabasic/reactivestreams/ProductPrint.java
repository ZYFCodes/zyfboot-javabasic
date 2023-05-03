//package org.zyf.javabasic.reactivestreams;
//
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.List;
//
///**
// * @author yanfengzhang
// * @description 使用 Reactor 框架实现的电商场景应用举例代码，用于获取商品信息并将其按照指定条件进行排序并输出到控制台
// * @date 2023/5/1  19:50
// */
//public class ProductPrint {
//    public static void main(String[] args) throws Exception {
//        // 创建商品对象列表
//        List<Product> productList = Arrays.asList(
//                new Product("A001", "商品A", new BigDecimal("100.00")),
//                new Product("A002", "商品B", new BigDecimal("200.00")),
//                new Product("A003", "商品C", new BigDecimal("300.00")),
//                new Product("A004", "商品D", new BigDecimal("400.00")),
//                new Product("A005", "商品E", new BigDecimal("500.00"))
//        );
//
//        // 创建 Flux 对象并按照价格排序
//        Flux<Product> productFlux = Flux.fromIterable(productList)
//                .sort(Comparator.comparing(Product::getPrice));
//
//        // 订阅 Flux 对象并输出商品信息
//        productFlux.subscribe(product -> System.out.println(product.getId() + " - " + product.getName() + " - " + product.getPrice()));
//    }
//
//    // 商品对象类
//    static class Product {
//        private String id;
//        private String name;
//        private BigDecimal price;
//
//        public Product(String id, String name, BigDecimal price) {
//            this.id = id;
//            this.name = name;
//            this.price = price;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public BigDecimal getPrice() {
//            return price;
//        }
//
//        public void setPrice(BigDecimal price) {
//            this.price = price;
//        }
//    }
//}
