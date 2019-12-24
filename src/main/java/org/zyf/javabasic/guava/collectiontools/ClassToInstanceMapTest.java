package org.zyf.javabasic.guava.collectiontools;

import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * 描述：ClassToInstanceMap系列接口：是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象
 * Guava提供了两种有用的实现：MutableClassToInstanceMap和 ImmutableClassToInstanceMap
 *
 * @author yanfengzhang
 * @date 2019-12-24 19:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClassToInstanceMapTest {
    @Test
    public void testClassToInstanceMap() {
        ClassToInstanceMap<User> numberDefaults = MutableClassToInstanceMap.create();
        User user = new User();
        numberDefaults.putInstance(User.class, user);
        System.out.println(numberDefaults);
    }

    class User {
        private String name;
        private String age;
    }
}
