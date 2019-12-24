package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Map;

/**
 * 描述：对应集合类后面加上s
 *
 * @author yanfengzhang
 * @date 2019-12-24 14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TableTest {

    @Test
    public void testTransposeTable() {
        Table<Integer, Integer, Integer> table = HashBasedTable.create();
        table.put(1, 1, 3);
        table.put(1, 2, 4);
        table.put(1, 3, 5);
        table.put(1, 4, 6);

        /**把Table<C, R, V>转置成Table<R, C, V> 也就是行列互换*/
        Table<Integer, Integer, Integer> newTable = Tables.transpose(table);

        System.out.println("原table为：" + table);
        System.out.println("把Table<C, R, V>转置成Table<R, C, V> 也就是行列互换：");
        System.out.println("转换table为：" + newTable);
    }

    @Test
    public void testRowOrColumn() {
        Table<Integer, Integer, Integer> weightedGraph = HashBasedTable.create();
        weightedGraph.put(1, 2, 4);
        weightedGraph.put(1, 3, 20);
        weightedGraph.put(2, 3, 5);

        /**返回某一行的列对应值的map  returns a Map mapping 2 to 4, 3 to 20*/
        Map rowMap = weightedGraph.row(1);
        /**返回某一列的行对应值的map returns a Map mapping 1 to 20, 2 to 5*/
        Map columnMap = weightedGraph.column(3);

        System.out.println("原table为：" + weightedGraph);
        System.out.println("返回某一行的列对应值的map：" + rowMap);
        System.out.println("返回某一列的行对应值的map：" + columnMap);
    }
}
