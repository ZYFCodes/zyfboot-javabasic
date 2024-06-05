package org.zyf.javabasic.thread.threadLocal;

import org.zyf.javabasic.skills.reflection.dto.Product;

/**
 * @program: zyfboot-javabasic
 * @description: 用户在进行购物时需要进行身份认证，并且在用户进行购物操作时，需要记录用户的购物车信息。
 * @author: zhangyanfeng
 * @create: 2024-06-02 14:02
 **/
public class ShoppingCartService {
    public void addToCart(Product product, int quantity) {
        // 开启一个新的上下文
        ContextManager.beginContext();
        try {
            // 将用户ID和商品信息设置到当前上下文中
            ContextManager.getCurrentContext().set("userId", getCurrentUserId());
            ContextManager.getCurrentContext().set("product", product);
            ContextManager.getCurrentContext().set("quantity", quantity);

            // 执行添加到购物车的逻辑
            // 这里可以调用其他方法，或者执行其他操作
            System.out.println("Adding product to cart...");

            checkout();

        } finally {
            // 关闭当前上下文
            ContextManager.endContext();
        }
    }

    public void checkout() {
        // 从当前上下文中读取用户ID和购物车信息
        String userId = (String) ContextManager.getCurrentContext().get("userId");
        Product product = (Product) ContextManager.getCurrentContext().get("product");
        int quantity = (int) ContextManager.getCurrentContext().get("quantity");

        // 执行结账逻辑
        // 这里可以根据购物车信息进行结账操作
        System.out.println("Checking out...");
        System.out.println("User ID: " + userId);
        System.out.println("Product: " + product.getName());
        System.out.println("Quantity: " + quantity);
    }

    private String getCurrentUserId() {
        // 模拟获取当前用户ID的方法
        return "user123";
    }

    public static void main(String[] args) {
        ShoppingCartService shoppingCartService = new ShoppingCartService();
        Product product = new Product();
        product.setName("iPhone");
        product.setId(1000);

        shoppingCartService.addToCart(product, 1);
    }
}
