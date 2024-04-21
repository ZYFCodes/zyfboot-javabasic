package org.zyf.javabasic.dispatcher.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.content.type.ImageDistributionContent;
import org.zyf.javabasic.dispatcher.enums.DistributionAnalyzeType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 分发信息整合
 * @author: zhangyanfeng
 * @create: 2024-04-21 18:40
 **/
@Data
public class DispatcherInfo implements Serializable {

    private static final long serialVersionUID = -4091102401377210060L;
    /**
     * 分发配置ID
     */
    private long dispatcherId;

    /**
     * 分发数据配置
     */
    private String dispatcherName;

    /**
     * 原始文本内容
     */
    private String textContent;

    /**
     * 分发数据文本
     */
    private String dispatcherContents;

    /**
     * 解析出来的分发数据
     */
    private Map<DistributionAnalyzeType, List<DistributionContent>> dispatcherInfo = Maps.newHashMap();

    /**
     * 将分发信息恢复成分析结果的映射
     *
     * @param dispatcherContents 分发信息字符串
     * @return 分析结果的映射，键为分析类型，值为对应的分析内容列表
     */
    public static Map<DistributionAnalyzeType, List<DistributionContent>> restoreDispatcherMap(String dispatcherContents) {
        Map<DistributionAnalyzeType, List<DistributionContent>> distributionAnalyzeRes = Maps.newHashMap();
        if (StringUtils.isBlank(dispatcherContents)) {
            return distributionAnalyzeRes;
        }

        JSONObject DispatcherJsonMap = JSONObject.parseObject(dispatcherContents);
        for (DistributionAnalyzeType at : DistributionAnalyzeType.values()) {
            JSONArray jsonArray = DispatcherJsonMap.getJSONArray(at.name());
            if (jsonArray == null) {
                continue;
            }
            List<DistributionContent> list = Lists.newArrayList();
            DistributionContent data = null;
            for (int i = 0; i < jsonArray.size(); i++) {
                data = jsonArray.getObject(i, at.getDistributionContentClass());
                list.add(data);
            }

            distributionAnalyzeRes.put(at, list);
        }

        return distributionAnalyzeRes;
    }

    /**
     * 提取分发信息中的图片链接列表
     *
     * @param dispatcherContents 分发信息字符串
     * @return 图片链接列表
     */
    public static List<String> extractImages(String dispatcherContents) {
        if (StringUtils.isBlank(dispatcherContents)) {
            return Lists.newArrayList();
        }
        Map<DistributionAnalyzeType, List<DistributionContent>> dispatcherMap = restoreDispatcherMap(dispatcherContents);
        if (MapUtils.isEmpty(dispatcherMap)) {
            return Lists.newArrayList();
        }

        List<DistributionContent> imageInfos = dispatcherMap.get(DistributionAnalyzeType.IMAGE);
        if (CollectionUtils.isEmpty(imageInfos)) {
            return Lists.newArrayList();
        }


        List<String> images = Lists.newArrayList();
        imageInfos.forEach(distributionContent -> {
            ImageDistributionContent imageDispatcherData = (ImageDistributionContent) distributionContent;
            images.add(imageDispatcherData.getImgLink());
        });

        return images;
    }
}
