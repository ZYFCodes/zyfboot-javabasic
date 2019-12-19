package org.zyf.javabasic.guava.basictools;

import com.google.common.base.Objects;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * 描述：Objects方法提供了比较null的方法
 *
 * @author yanfengzhang
 * @date 2019-12-19 19:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ObjectsTest {
    @Test
    public void testObjects() {
        /**return true*/
        TestCase.assertEquals(true, Objects.equal("a", "a"));

        /**return false*/
        TestCase.assertEquals(false, Objects.equal(null, "a"));

        /**return false*/
        TestCase.assertEquals(false, Objects.equal("a", null));

        /**return true*/
        TestCase.assertEquals(true, Objects.equal(null, null));
    }
}
