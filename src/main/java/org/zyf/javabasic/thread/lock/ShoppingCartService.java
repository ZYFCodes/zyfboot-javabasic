package org.zyf.javabasic.thread.lock;

import org.springframework.stereotype.Service;
import org.zyf.javabasic.skills.reflection.dto.Product;
import org.zyf.javabasic.thread.test.CartDao;
import org.zyf.javabasic.thread.test.ProductDao;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanfengzhang
 * @description 简单的电商代码示例，用来演示悲观锁和乐观锁的应用
 * @date 2022/5/3  16:16
 */
@Service
public class ShoppingCartService {

    @Resource
    private ProductDao productDao;

    @Resource
    private CartDao cartDao;

    private Lock lock = new ReentrantLock();

    public void addToCartWithPessimisticLock(int productId, int quantity) {
        lock.lock();
        try {
            // 从数据库中查询商品库存
            Product product = productDao.getProduct(productId);
            if (product.getStock() >= quantity) {
                // 减少库存
                productDao.decreaseStock(productId, quantity);
                // 将商品添加到购物车
                cartDao.addToCart(productId, quantity);
            } else {
                throw new RuntimeException("库存不足");
            }
        } finally {
            lock.unlock();
        }
    }

    public synchronized void addToCartWithPessimisticSync(int productId, int quantity) {
        // 从数据库中查询商品库存
        Product product = productDao.getProduct(productId);
        if (product.getStock() >= quantity) {
            // 减少库存
            productDao.decreaseStock(productId, quantity);
            // 将商品添加到购物车
            cartDao.addToCart(productId, quantity);
        } else {
            throw new RuntimeException("库存不足");
        }
    }

    public void addToCartWithOptimisticLock(int productId, int quantity) {
        // 从数据库中查询商品库存
        Product product = productDao.getProduct(productId);
        if (product.getStock() >= quantity) {
            // 减少库存
            int result = productDao.decreaseStockWithVersion(productId, quantity, product.getVersion());
            if (result == 1) {
                // 将商品添加到购物车
                cartDao.addToCart(productId, quantity);
            } else {
                throw new RuntimeException("库存不足");
            }
        } else {
            throw new RuntimeException("库存不足");
        }
    }

    private AtomicInteger count = new AtomicInteger(0);

    public boolean addGoodsCas(int quantity) {
        int current;
        do {
            current = count.get();
            if (current + quantity > 10) {
                // 商品库存不足
                return false;
            }
        } while (!count.compareAndSet(current, current + quantity));
        return true;
    }

    int countnew = 0;

    public boolean addGoods(int quantity) {
        lock.lock();
        try {
            if (countnew + quantity > 10) {
                // 商品库存不足
                return false;
            }
            countnew += quantity;
            return true;
        } finally {
            lock.unlock();
        }
    }
}
