package org.zyf.javabasic.guava.basictools;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.List;

/**
 * 描述：Guava提供的Preconditions类中有一些基础前置检查方法，当检查不通过时迅速抛出错误。
 *
 * @author yanfengzhang
 * @date 2019-12-19 17:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PreconditionsTest {
    @Test
    public void checkArgument() {
        int i = 1;

        /**前置检查方法--条件检查:不通过抛出IllegalArgumentException及自定义描述 自定义描述支持类似于String.format()的字符串拼接但是只能用%s */
        Preconditions.checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);
    }

    @Test
    public void checkState() {
        int i = 1;

        /**状态检查 不通过抛出IllegalStateException及自定义描述*/
        Preconditions.checkState(i >= 0, "Argument was %s but expected nonnegative", i);
    }

    @Test
    public void checkNotNull() {
        String a = "";

        /**空值检查 不通过抛出NullPointerException及自定义描述*/
        Preconditions.checkNotNull(a, "Argument was null");
    }

    @Test
    public void checkElementIndex() {
        int j[] = {4, 1, 2, 3};
        List l = Ints.asList(j);

        /**检查列表、字符串或数组某一索引是否有效 不通过抛出IndexOutOfBoundsException*/
        Preconditions.checkElementIndex(2, l.size());
    }

    @Test
    public void checkPositionIndex() {
        int j[] = {4, 1, 2, 3};
        List l = Ints.asList(j);

        /**检查列表、字符串或数组某一位置是否有效 不通过抛出IndexOutOfBoundsException*/
        Preconditions.checkPositionIndex(2, l.size());
    }

    @Test
    public void checkPositionIndexes() {
        int j[] = {4, 1, 2, 3};
        List l = Ints.asList(j);

        /**检查列表、字符串或数组某一范围是否有效 不通过抛出IndexOutOfBoundsException*/
        Preconditions.checkPositionIndexes(1, 2, l.size());
    }
}
