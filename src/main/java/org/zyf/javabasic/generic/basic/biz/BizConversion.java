package org.zyf.javabasic.generic.basic.biz;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.zyf.javabasic.common.utils.JSONUtil;
import org.zyf.javabasic.generic.contants.BizContants;
import org.zyf.javabasic.generic.enums.CheckBizType;
import org.zyf.javabasic.generic.model.CheckBasicInfo;
import org.zyf.javabasic.generic.model.TVSeries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/1/28  23:41
 */
public class BizConversion {

    /**
     * @param bizType 具体业务基本信息
     * @param tClass  业务需要的对象类型
     * @param <T>     业务需要的对象类型
     * @return 业务数据列表
     * @description 获取当前有效状态（审核中）副本状态对应的业务信息
     * @author yanfengzhang
     */
    public <T> List<T> getBizInfoInCheckOnline(String bizType, Class<T> tClass) {
        /*1.根据当前的业务类型查看对应的业务类型*/
        CheckBizType checkBizType = CheckBizType.getEnumByName(bizType);
        if (null == checkBizType) {
            throw new BigVideoBizException("业务类型异常, 请检查对应的传入类型值是否正确！");
        }
        /*2.按照对应的业务类型查询当前正在审核处理的在线审核记录，若没有则直接返回即可 todo 这里应该直接去查数据库*/
        List<CheckBasicInfo> inCheckOnline = testOnly(checkBizType.getType());
        if (CollectionUtils.isEmpty(inCheckOnline)) {
            return Collections.EMPTY_LIST;
        }

        /*3.对当前符合条件的审核工作流进行整合返回*/
        List<T> results = new LinkedList<>();
        inCheckOnline.stream().forEach(checkBasicInfo -> {
            String content = checkBasicInfo.getContent();
            if (StringUtils.isNotEmpty(content)) {
                T t = JSON.parseObject(content, tClass);
                results.add(t);
            }
        });

        return results;
    }

    class BigVideoBizException extends RuntimeException {

        private int code;

        public BigVideoBizException(String msg) {
            super(msg);
        }
    }


    /**
     * @param checkBizType 审核业务类型
     * @return 具体审核业务类型对应审核数据
     * @description 只作为基本的测试内容进行测试，这部分应该能是在对应的数据库中来操作的
     */
    private List<CheckBasicInfo> testOnly(int checkBizType) {
        List<CheckBasicInfo> checkBasicInfos = Lists.newArrayList();
        if (checkBizType == BizContants.TV_SERIES) {
            TVSeries tvSeries = TVSeries.builder()
                    .id(12)
                    .name("离别")
                    .count(66)
                    .onLineTime(1612166400)
                    .time(2400)
                    .content("请调整心态，努力做好自己！").build();
            CheckBasicInfo checkBasicInfo = CheckBasicInfo.builder()
                    .content(JSONUtil.toJSONString(tvSeries)).build();
            checkBasicInfos.add(checkBasicInfo);
        }

        return checkBasicInfos;
    }
}
