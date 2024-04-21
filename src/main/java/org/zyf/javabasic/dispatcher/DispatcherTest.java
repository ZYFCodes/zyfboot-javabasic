package org.zyf.javabasic.dispatcher;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.dispatcher.base.DispatcherInfo;
import org.zyf.javabasic.dispatcher.core.DistributeExecutor;

/**
 * @program: zyfboot-javabasic
 * @description: 测试分发器
 * @author: zhangyanfeng
 * @create: 2024-04-21 20:57
 **/
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DispatcherTest {

    @Autowired
    private DistributeExecutor distributedExecutor;

    @Test
    public void testDistributeExecutor() {
        //假设前端设置数据如下
        String dispatcherContents = "{\"IMAGE\":[{\"forum\":false,\"imgLink\":\"https://org.zyf-medie.com/Zyf/img/A*45NyRqGcWM8AAAAAAAAAAAAAAQAAAQ/original?bz=APM_68687674&tid=Zyf_traceId\",\"matchEnd\":80,\"matchStart\":73,\"param\":\"%7B%22imageBizType%22%3A%22image%22%2C%22isEdit%22%3A%22false%22%7D\",\"placeHolderKey\":\"G_0\",\"referString\":\"<img src=\\\"https://org.zyf-medie.com/Zyf/img/A*45NyRqGcWM8AAAAAAAAAAAAAAQAAAQ/original?bz=APM_68687674&tid=Zyf_traceId\\\" param=\\\"%7B%22imageBizType%22%3A%22image%22%2C%22isEdit%22%3A%22false%22%7D\\\" />\",\"valid\":true},{\"forum\":false,\"imgLink\":\"https://org.zyf-medie.com/Zyf/img/A*9mEOT5DLZiYAAAAAAAAAAAAAAQAAAQ/original?bz=APM_68687674&tid=Zyf_traceId\",\"matchEnd\":94,\"matchStart\":87,\"param\":\"%7B%22snapshotId%22%3A%22ss-2088342263008273-1713531774473-6ef77c%22%2C%22imageBizType%22%3A%22hold%22%2C%22holdCount%22%3A14%2C%22userId%22%3A%222088342263008273%22%2C%22shape%22%3A%22V%22%7D\",\"placeHolderKey\":\"G_1\",\"referString\":\"<img src=\\\"https://org.zyf-medie.com/Zyf/img/A*9mEOT5DLZiYAAAAAAAAAAAAAAQAAAQ/original?bz=APM_68687674&tid=Zyf_traceId\\\" param=\\\"%7B%22snapshotId%22%3A%22ss-2088342263008273-1713531774473-6ef77c%22%2C%22imageBizType%22%3A%22hold%22%2C%22holdCount%22%3A14%2C%22userId%22%3A%222088342263008273%22%2C%22shape%22%3A%22V%22%7D\\\" />\",\"valid\":true}],\"STOCK\":[{\"dataMarket\":\"SH\",\"dataType\":\"stock\",\"forum\":true,\"matchEnd\":570,\"matchStart\":563,\"name\":\"舍得酒业\",\"placeHolderKey\":\"S_0\",\"referString\":\"$舍得酒业$\",\"stockCodeMarket\":\"600702.SH\",\"stockId\":\"656\",\"stockName\":\"舍得酒业\",\"stockType\":\"ES\",\"topicId\":\"656\",\"topicTypeName\":\"STOCK\",\"valid\":true},{\"dataMarket\":\"SZ\",\"dataType\":\"stock\",\"forum\":true,\"matchEnd\":758,\"matchStart\":751,\"name\":\"比亚迪\",\"placeHolderKey\":\"S_1\",\"referString\":\"$比亚迪$\",\"stockCodeMarket\":\"002594.SZ\",\"stockId\":\"4055\",\"stockName\":\"比亚迪\",\"stockType\":\"ES\",\"topicId\":\"4055\",\"topicTypeName\":\"STOCK\",\"valid\":true},{\"dataMarket\":\"SH\",\"dataType\":\"stock\",\"forum\":true,\"matchEnd\":947,\"matchStart\":940,\"name\":\"长江投资\",\"placeHolderKey\":\"S_2\",\"referString\":\"$长江投资$\",\"stockCodeMarket\":\"600119.SH\",\"stockId\":\"1720\",\"stockName\":\"长江投资\",\"stockType\":\"ES\",\"topicId\":\"1720\",\"topicTypeName\":\"STOCK\",\"valid\":true}],\"FUND\":[{\"dataMarket\":\"OF\",\"dataType\":\"fund\",\"forum\":true,\"fundCode\":\"005911\",\"fundName\":\"广发双擎升级混合A\",\"fundType\":\"BLEND\",\"matchEnd\":193,\"matchStart\":186,\"name\":\"广发双擎升级混合A\",\"placeHolderKey\":\"F_0\",\"productId\":\"20180920000230030000000000015303\",\"referString\":\"$广发双擎升级混合A$\",\"topicId\":\"20180920000230030000000000015303\",\"topicTypeName\":\"FUND\",\"valid\":true},{\"dataMarket\":\"OF\",\"dataType\":\"fund\",\"forum\":true,\"fundCode\":\"206009\",\"fundName\":\"鹏华新兴产业混合\",\"fundType\":\"BLEND\",\"matchEnd\":381,\"matchStart\":374,\"name\":\"鹏华新兴产业混合\",\"placeHolderKey\":\"F_1\",\"productId\":\"20150718000230030000000000002549\",\"referString\":\"$鹏华新兴产业混合$\",\"topicId\":\"20150718000230030000000000002549\",\"topicTypeName\":\"FUND\",\"valid\":true},{\"forum\":true,\"fundCode\":\"162605\",\"fundName\":\"景顺长城鼎益混合型证券投资基金(LOF)A类\",\"fundType\":\"BLEND\",\"matchEnd\":0,\"matchStart\":0,\"name\":\"景顺长城鼎益混合型证券投资基金(LOF)A类\",\"placeHolderKey\":\"F_2\",\"productId\":\"20150718000230030000000000000201\",\"referString\":\"景顺长城鼎益混合型证券投资基金(LOF)A类\",\"topicId\":\"20150718000230030000000000000201\",\"topicTypeName\":\"FUND\",\"valid\":true}]}";
        DispatcherInfo dispatcherInfo = new DispatcherInfo();
        dispatcherInfo.setDispatcherId(2024);
        dispatcherInfo.setDispatcherName("测试我们设计的分发器20240421");
        dispatcherInfo.setDispatcherContents(dispatcherContents);

        distributedExecutor.executeDistribution(dispatcherInfo);
    }
}
