package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 测试里氏替换等价行为
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:11
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LSPViolationTest {
    @Test
    public void testChildReturnsImmutableList() throws Exception{
        ListProcessor processor = new ImmutableListProcessor();
        List<String> list = processor.process();
        list.add("X"); // ❌ 此处抛异常，说明子类行为不兼容父类
    }
}
