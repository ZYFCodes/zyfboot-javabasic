package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentAttr;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentInfoContext;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/21  18:12
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SensitivePipelineExecutorTest {

    @Autowired
    private SensitivePipelineExecutor sensitivePipelineExecutor;

    @Test
    public void test() {

        sensitivePipelineExecutor.getContentCleanRes(
                ContentInfoContext.builder()
                        .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                        .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                        .contentAttr(ContentAttr.builder().build()).build());
    }
}
