package org.zyf.javabasic.sensitive.service;

import org.zyf.javabasic.sensitive.base.HitWordResultForBusiness;
import org.zyf.javabasic.sensitive.base.SensitiveInfo;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:23
 */
public interface BusinessIdentityService {

    /**
     * 获取全部SensitiveInfo
     * businessidentity  -> SensitiveInfo
     */
    Map<String, SensitiveInfo> getSensitiveInfoMap();

    /**
     * 滚动挨个处理：
     * 根据所有业务身份，处理相应逻辑
     */
    void scrollHandleByAllBizIdentity(Consumer<SensitiveInfo> consumer);

    /**
     * 通过 Trace 拿到业务身份，再拿到info
     */
    Optional<SensitiveInfo> getSensitiveInfoByTracer();


    /**
     * 通过 Trace 获取业务身份
     */
    Optional<String> getBizIdentityByTracer();

    /**
     * 业务身份是否命中对应敏感词
     *
     * @param word 敏感词
     * @return true-命中敏感词；false-没有命中敏感词
     */
    boolean isHitWordForBusinessIdetity(String word);

    /**
     * 业务身份是否命中对应敏感词结果（优化：减少反复查询tair,提高性能）
     *
     * @param word 敏感词
     * @return true-命中敏感词；false-没有命中敏感词
     */
    HitWordResultForBusiness hitWordResultForBusinessIdetity(String word);
}

