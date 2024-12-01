package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * @program: zyfboot-javabasic
 * @description: Guava Table 详细讲解与示例
 * @author: zhangyanfeng
 * @create: 2018-12-01 19:20
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuavaTableTest {
    // 1. 基本的 Table 操作：存储和获取值
    @Test
    public void testBasicTableOperations() {
        Table<String, String, Integer> table = HashBasedTable.create();

        // 添加数据
        table.put("Row1", "Col1", 10);
        table.put("Row1", "Col2", 20);
        table.put("Row2", "Col1", 30);

        // 获取数据并打印
        System.out.println("Row1, Col1: " + table.get("Row1", "Col1"));
        System.out.println("Row1, Col2: " + table.get("Row1", "Col2"));
        System.out.println("Row2, Col1: " + table.get("Row2", "Col1"));

        // 获取行、列、值并打印
        System.out.println("Row1 contains value 10: " + table.row("Row1").containsValue(10));
        System.out.println("Column Col1 contains value 30: " + table.column("Col1").containsValue(30));
    }

    // 2. 嵌套 Table 示例：三维数据结构
    @Test
    public void testNestedTable() {
        Table<String, String, Table<String, String, Integer>> nestedTable = HashBasedTable.create();

        // 创建一个二维 Table
        Table<String, String, Integer> studentScores = HashBasedTable.create();
        studentScores.put("Math", "John", 85);
        studentScores.put("English", "John", 90);
        studentScores.put("Math", "Alice", 88);

        // 将二维 Table 放入三维 Table
        nestedTable.put("Class1", "GradeA", studentScores);

        // 获取数据并打印
        System.out.println("Class1, GradeA, Math, John: " + nestedTable.get("Class1", "GradeA").get("Math", "John"));
        System.out.println("Class1, GradeA, English, John: " + nestedTable.get("Class1", "GradeA").get("English", "John"));
        System.out.println("Class1, GradeA, Math, Alice: " + nestedTable.get("Class1", "GradeA").get("Math", "Alice"));
    }

    // 3. 删除操作：删除行、列以及单元格
    @Test
    public void testRemoveOperations() {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("Row1", "Col1", 10);
        table.put("Row1", "Col2", 20);

        // 删除单元格
        table.remove("Row1", "Col1");
        System.out.println("After removing Row1, Col1: " + table.get("Row1", "Col1"));

        // 删除一整行
        table.rowMap().remove("Row1");
        System.out.println("After removing Row1: " + table.rowMap().isEmpty());

        // 添加数据后验证
        table.put("Row2", "Col1", 50);
        System.out.println("Row2, Col1 after adding data: " + table.get("Row2", "Col1"));
    }

    // 4. 处理空值：如何通过 putAll 处理一批数据
    @Test
    public void testPutAllWithNulls() {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("Row1", "Col1", 10);
        table.put("Row2", "Col2", null); // 插入空值

        // 获取空值单元格并打印
        System.out.println("Row2, Col2 (null value): " + table.get("Row2", "Col2"));

        // 使用 putAll 方法批量插入数据
        table.putAll(HashBasedTable.create());
        System.out.println("After putAll (empty): " + table.isEmpty());
    }

    // 5. 判断一个 Table 是否包含某个值
    @Test
    public void testContainsValue() {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("Row1", "Col1", 10);
        table.put("Row2", "Col1", 20);

        // 判断是否包含某个值并打印
        System.out.println("Table contains value 10: " + table.containsValue(10));
        System.out.println("Table contains value 100: " + table.containsValue(100));
    }

    // 6. 空值处理：从 Table 中获取 Null
    @Test
    public void testNullHandlingInTable() {
        Table<String, String, String> table = HashBasedTable.create();

        // 添加一个空值
        table.put("Row1", "Col1", null);

        // 确保获取到的是 null 并打印
        System.out.println("Row1, Col1 (null value): " + table.get("Row1", "Col1"));
    }

    // 7. 获取所有行或列
    @Test
    public void testRowAndColumnMethods() {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("Row1", "Col1", 10);
        table.put("Row1", "Col2", 20);
        table.put("Row2", "Col1", 30);

        // 获取行并打印
        System.out.println("Row1 contains 10: " + table.row("Row1").containsValue(10));
        System.out.println("Row1 contains 20: " + table.row("Row1").containsValue(20));

        // 获取列并打印
        System.out.println("Col1 contains 10: " + table.column("Col1").containsValue(10));
        System.out.println("Col1 contains 30: " + table.column("Col1").containsValue(30));
    }
}
