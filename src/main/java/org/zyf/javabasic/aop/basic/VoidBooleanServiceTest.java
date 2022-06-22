package org.zyf.javabasic.aop.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import javax.annotation.Resource;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/8  21:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class VoidBooleanServiceTest {

    @Resource
    private VoidBooleanService voidBooleanService;

    @Test
    public void test1(){
        System.out.println(voidBooleanService.getBoolean());
        System.out.println(voidBooleanService.getBoolean2());
        voidBooleanService.getVoid();
        System.out.println(voidBooleanService.getInt1());
        System.out.println(voidBooleanService.getInt2());
        System.out.println(voidBooleanService.getWordRegular());
        System.out.println(voidBooleanService.getFuture());
        System.out.println(voidBooleanService.getFuturebyte());
        System.out.println(voidBooleanService.getFutureResultMapByteArrayResultVoid());
        System.out.println(voidBooleanService.getFutureResultSetbyte());
        System.out.println(voidBooleanService.getResultMapStringResultVoid());
        System.out.println(voidBooleanService.getResultMapByteArrayResultMapByteArrayResultVoid());
    }
}
