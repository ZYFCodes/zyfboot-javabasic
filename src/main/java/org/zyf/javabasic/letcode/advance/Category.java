package org.zyf.javabasic.letcode.advance;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/6/15  23:14
 */
public class Category {
    private int id;
    private String name;
    private int parentId;
    private List<Category> subcategories;

    public Category(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.subcategories = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParentId() {
        return parentId;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

    public void addSubcategory(Category subcategory) {
        subcategories.add(subcategory);
    }

    public static List<Category> buildCategoryTree(List<Category> categories) {
        Map<Integer, Category> categoryMap = new HashMap<>();
        List<Category> rootCategories = new ArrayList<>();

        // 构建分类映射表，并找到根级分类
        for (Category category : categories) {
            categoryMap.put(category.getId(), category);
            if (category.getParentId() == 0) {
                rootCategories.add(category);
            }
        }

        // 组装分类层级关系
        for (Category category : categories) {
            if (category.getParentId() != 0) {
                Category parent = categoryMap.get(category.getParentId());
                if (parent != null) {
                    parent.addSubcategory(category);
                }
            }
        }

        return rootCategories;
    }

    public static void main(String[] args) {
        // 模拟数据库查询得到的分类数据
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, "Category A", 0));
        categories.add(new Category(2, "Category B", 0));
        categories.add(new Category(3, "Subcategory A1", 1));
        categories.add(new Category(4, "Subcategory A2", 1));
        categories.add(new Category(5, "Subcategory B1", 2));

        // 构建分类层级树
        List<Category> categoryTree = Category.buildCategoryTree(categories);

        // 将组装后的数据转换为 JSON 格式（示例中省略了 JSON 转换的具体代码）
        //String jsonResponse = convertToJSON(categoryTree);

        // 返回数据给前端
        System.out.println(JSON.toJSONString(categoryTree));
    }

    // 辅助方法：将对象转换为 JSON 格式（示例中省略了具体实现）
    private static String convertToJSON(Object object) {
        // 实现省略
        return "";
    }
}
