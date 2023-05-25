package org.zyf.javabasic.sensitive.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.sensitive.base.BusinessIdentity;
import org.zyf.javabasic.sensitive.base.HitWordResultForBusiness;
import org.zyf.javabasic.sensitive.base.SensitiveInfo;
import org.zyf.javabasic.sensitive.base.WordDomain;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  21:09
 */
@Service
@Slf4j
public class BusinessIdentityServiceImpl implements BusinessIdentityService {

    /**
     * 默认的业务身份
     */
    public static final String DEFAULT_BUSINESS_IDENTITY_NAME = "default";

    /**
     * bizIdentity -> SensitiveInfo
     */
    private static Map<String, SensitiveInfo> bizSensitiveInfoMap = Maps.newHashMap();

//    @Resource
//    private BusinessIdentityThriftService.Iface businessIdentityThriftService;
//    @Autowired
//    private WordService wordService;

    @PostConstruct
    public void init() {
        for (String businessIdentity : getHandleBusinessIdentitiesName()) {
            bizSensitiveInfoMap.put(businessIdentity, new SensitiveInfo(businessIdentity));
        }
    }

    /**
     * 获取全部业务身份
     */
    private List<BusinessIdentity> getAllBusinessIdentities() {
        try {
//            BusinessIdentitySearchParams params = new BusinessIdentitySearchParams();
//            BusinessIdentitySearchResult businessIdentitySearchResult = businessIdentityThriftService.searchBusinessIdentity(params, getPagination());
//            if (businessIdentitySearchResult != null) {
//                List<BusinessIdentity> dataList = businessIdentitySearchResult.getDataList();
//                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(dataList)) {
//                    return dataList;
//                }
//            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return Lists.newArrayList();
    }

    private Set<String> getHandleBusinessIdentitiesName() {
//        Set<String> result = getAllBusinessIdentities().stream().filter(businessIdentity -> ConfigUtil.getMccBusinessIdentities().contains(businessIdentity.getName())).map(BusinessIdentity::getName).collect(Collectors.toSet());
//        // 外加一个默认
//        result.add(DEFAULT_BUSINESS_IDENTITY_NAME);
//        return result;
        return Sets.newHashSet();
    }

    /**
     * 根据业务身份获取 SensitiveInfo
     */
    private Optional<SensitiveInfo> getSensitiveInfo(String businessIdentity) {
        if (null != bizSensitiveInfoMap.get(businessIdentity)) {
            return Optional.of(bizSensitiveInfoMap.get(businessIdentity));
        } else {
            // 这主要解决一个问题，就是在不重启的情况下，也防止外界乱传递业务身份
            if (getHandleBusinessIdentitiesName().contains(businessIdentity)) {
                bizSensitiveInfoMap.put(businessIdentity, new SensitiveInfo(businessIdentity));
                return Optional.of(bizSensitiveInfoMap.get(businessIdentity));
            }
        }
        return Optional.empty();
    }

    @Override
    public Map<String, SensitiveInfo> getSensitiveInfoMap() {
        Map<String, SensitiveInfo> result = Maps.newHashMap();
        if (CollectionUtils.isEmpty(getHandleBusinessIdentitiesName())) {
            return result;
        }
        for (String businessIdentity : getHandleBusinessIdentitiesName()) {
            result.put(businessIdentity, getSensitiveInfo(businessIdentity).get());
        }
        return result;
    }

    @Override
    public void scrollHandleByAllBizIdentity(Consumer<SensitiveInfo> consumer) {
        if (MapUtils.isNotEmpty(getSensitiveInfoMap())) {
            for (SensitiveInfo sensitiveInfo : getSensitiveInfoMap().values()) {
                Instant begin = Instant.now();
                if (log.isInfoEnabled()) {
                    log.info(" ======【BEGIN】====== handleByAllBizIdentity consume businessIdentity: {}  ", sensitiveInfo.getBusinessIdentity());
                }
                // 埋下业务身份
//                BusinessIdentityUtil.newBusinessIdentityBuilder()
//                        .putBusinessIdentity(sensitiveInfo.getBusinessIdentity())
//                        .putPoiTagId(Constants.ZERO)
//                        .putRole(Constants.ALL)
//                        .putClient(Constants.ALL)
//                        .buildBusinessIdentityInfo();
                // 真正的处理逻辑
                consumer.accept(sensitiveInfo);
                // 清除业务身份
//                BusinessIdentityUtil.cleanBusinessIdentity();
                if (log.isInfoEnabled()) {
                    log.info("======【END】====== handleByAllBizIdentity consume OK ，businessIdentity:{}, it cost: {} ms ", sensitiveInfo.getBusinessIdentity(), Duration.between(begin, Instant.now()).toMillis());
                }
            }
        }
    }

    @Override
    public Optional<SensitiveInfo> getSensitiveInfoByTracer() {
        return getBizIdentityByTracer().isPresent() ? getSensitiveInfo(getBizIdentityByTracer().get()) : Optional.empty();
    }

    @Override
    public Optional<String> getBizIdentityByTracer() {
//        String bizIdentity = BusinessIdentityUtil.getBusinessIdentity();
        // 当前如果没有埋点业务身份，或者埋点的业务身份在mcc中没有处理，也就是可能没有db集群，那么还是使用默认的
//        if (StringUtils.isBlank(bizIdentity) || !ConfigUtil.getMccBusinessIdentities().contains(bizIdentity)) {
//            bizIdentity = DEFAULT_BUSINESS_IDENTITY_NAME;
//        }
//        return Optional.of(bizIdentity);
        return null;
    }

    /**
     * 业务身份是否命中对应敏感词
     *
     * @param word 敏感词
     * @return true-命中敏感词；false-没有命中敏感词
     */
    @Override
    public boolean isHitWordForBusinessIdetity(String word) {
        /*如果有业务身份需根据当前的业务身份判断是否命中敏感词*/
        if (this.getBizIdentityByTracer().isPresent()) {
//            WordDomain hitWord = wordService.getFromTairByWords(word);
            WordDomain hitWord = null;
            /*如果符合业务身份，但又没有查到敏感词信息，则针对当前业务身份认为没有命中敏感词*/
            return null != hitWord;
        }

        /*没有业务身份时，默认认为命中*/
        return true;
    }

    /**
     * 业务身份是否命中对应敏感词结果（优化：减少反复查询tair,提高性能）
     *
     * @param word 敏感词
     * @return true-命中敏感词；false-没有命中敏感词
     */
    @Override
    public HitWordResultForBusiness hitWordResultForBusinessIdetity(String word) {
        /*如果有业务身份需根据当前的业务身份判断是否命中敏感词*/
        if (this.getBizIdentityByTracer().isPresent()) {
//            WordDomain hitWord = wordService.getFromTairByWords(word);
            WordDomain hitWord = null;
            /*如果符合业务身份，但又没有查到敏感词信息，则针对当前业务身份认为没有命中敏感词*/
            return HitWordResultForBusiness.builder().isHit(null != hitWord).hitWord(hitWord).build();
        }

        /*没有业务身份时，从默认库查询看是否命中*/
//        WordDomain hitWord = wordService.getFromTairByWords(word);
        WordDomain hitWord = null;
        return HitWordResultForBusiness.builder().isHit(null != hitWord).hitWord(hitWord).build();

    }

    private Pagination getPagination() {
        Pagination pagination = new Pagination();
        pagination.setPageNum(0);
        pagination.setPageSize(1000);
        return pagination;
    }

    @Data
    class Pagination {
        public int total;
        public int pageNum;
        public int pageSize;
    }
}

