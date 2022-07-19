package org.zyf.javabasic.aop.redissync;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.SensitiveWord;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/18  23:55
 */
@Service
@Slf4j
public class SensitiveOperationService {
    @OperationRedisSync(dealBizType = OperationRedisSyncCons.BizType.SENSITIVE,
            dealInfoType = OperationRedisSyncCons.DealType.CREATE,
            dealInfo = "sensitiveWord")
    public void createSensitive(SensitiveWord sensitiveWord, String opName) {
        log.info("敏感词业务，新建操作");
        log.info("敏感词业务，DB新建操作完成");
    }

    @OperationRedisSync(dealBizType = OperationRedisSyncCons.BizType.SENSITIVE,
            dealInfoType = OperationRedisSyncCons.DealType.DELETE,
            dealInfo = "id")
    public void deleteSensitive(Long id, String opName) {
        log.info("敏感词业务，删除操作");
        log.info("敏感词业务，DB删除操作完成");
    }
}
