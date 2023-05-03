package org.zyf.javabasic.thread.test;

import org.springframework.stereotype.Service;
import org.zyf.javabasic.skills.reflection.dto.Product;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  16:19
 */
@Service
public class ProductDao {
    public Product getProduct(int productId) {
        return new Product();
    }

    public void decreaseStock(int productId, int quantity) {
    }

    public int decreaseStockWithVersion(int productId, int quantity, int version) {
        return 1;
    }
}
