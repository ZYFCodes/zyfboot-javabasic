package org.zyf.javabasic.skills.reflection.test;

import com.google.common.collect.Lists;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.skills.reflection.dto.Order;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 描述：
 *
 * @author yanfengzhang
 * @date 2020-01-02 17:16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReflectionTest {
    @Test
    public void inspectOrderFields() {
        Object order = new Order();
        /*getDeclaredFields方法来获取到order对象所有成员变量的数据结构*/
        Field[] fields = order.getClass().getDeclaredFields();
        List<String> actualFieldNames = getFieldNames(fields);
        TestCase.assertTrue(Arrays.asList(
                "ctime", "uuid", "productId", "price", "amount", "sellerId", "buyerId"
        ).containsAll(actualFieldNames));

    }

    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = Lists.newArrayList();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        System.out.println(fieldNames);
        return fieldNames;
    }
}
