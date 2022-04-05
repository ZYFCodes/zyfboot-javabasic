package org.zyf.javabasic.test.exception;

import org.zyf.javabasic.common.ActivityBizException;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/2/25  20:17
 */
public class ExceptionTest {

    public static void main(String[] args) {
        System.out.println("测试开始！");
        try {
            throw new Exception();
        } catch (ActivityBizException e) {
            System.out.println("ActivityBizException");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("测试结束！");
    }

    public void testThrowException() throws Exception {
        throw new Exception();
    }

    public void testThrowActivityBizException() throws Exception {
        throw new ActivityBizException("123");
    }
}
