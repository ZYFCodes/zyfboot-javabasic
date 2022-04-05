package org.zyf.javabasic.designpatterns.strategy.base;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/4  23:42
 */
@Service
public class ProductCategoryService {
    /**
     * 根据当前的类目信息获取对应全链路路径信息 （做一个简单模拟）
     *
     * @param categoryIds 当前类目信息
     * @return
     */
    public Collection<Long> fetchAllIdPathByCategoryId(Collection<Long> categoryIds) {
        Collection<Long> allIdPath = new ArrayList<Long>(Collections.EMPTY_SET);
        if (CollectionUtils.isEmpty(categoryIds)) {
            return allIdPath;
        }

        categoryIds.forEach(categoryId -> {
            if (categoryId == 1) {
                allIdPath.add(1L);
                return;
            }
            if (categoryId == 2) {
                allIdPath.add(2L);
                return;
            }
            if (categoryId == 3) {
                allIdPath.add(3L);
                return;
            }
            if (categoryId == 4) {
                allIdPath.add(4L);
                return;
            }
            if (categoryId == 5) {
                allIdPath.add(5L);
            }
        });
        return allIdPath;
    }
}
