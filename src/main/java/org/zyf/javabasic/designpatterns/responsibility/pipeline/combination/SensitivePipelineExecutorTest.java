package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.BizType;
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

    /**
     * =====敏感词词库模拟设置=====
     * 企业合规管控校验 4  23 张彦峰
     * 4  24 肯德基
     * =======相关词库处理=======
     * 企业敏感词库（编号1）          1  1  外卖
     * 1  86  美团
     * 部门敏感词库（编号2）匹配       2  23  腾讯
     * 2  36  阿里
     * 其他敏感词库（编号3）          3  28  禁药
     * 3  376  毒品
     * =======正则校验词库处理=======
     * 与正则                       5  11 "酒精", "南京"
     * ======手机号身份证号处理======
     * 模拟电话                     18 453 18252066688
     * <p>
     * 生效模拟
     * 合规管控处理     外卖 + 闪购
     * 生效规则处理     只对改地区生效110010
     * 白名单处理       比方针对敏感词36加白
     */
    @Test
    public void testSensitivePipelineExecutor() {
        log.info(sensitivePipelineExecutor.getSensitiveDealRes(ContentInfoContext.builder()
                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(110010).build()).build()));
        log.info(sensitivePipelineExecutor.getSensitiveDealRes(ContentInfoContext.builder()
                .content("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                .cleanContent("中國张彦峰㎵㎶㎷㎸㎹㎺外卖⏳⌚⏰")
                .contentAttr(ContentAttr.builder().bizType(BizType.E_COMMERCE.getType()).cityCode(115510).build()).build()));
    }
}
