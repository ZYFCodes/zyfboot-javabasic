package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * @program: zyfboot-javabasic
 * @description: 正确示例测试（组合方式）
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:23
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LSPCompliantTest {
    @Test
    public void testWrapperReturnsImmutableList() {
        ListProcessor processor = new ListProcessor();
        ImmutableListNewProcessor wrapper = new ImmutableListNewProcessor(processor);

        List<String> list = wrapper.process();
        try {
            list.add("X");
            fail("应抛出 UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            log.info("不可变集合校验通过，拦截了非法修改");
        }
    }
}
