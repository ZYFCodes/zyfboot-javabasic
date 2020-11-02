package org.zyf.javabasic.test;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.zyf.javabasic.common.RightsInfo;

import java.util.List;
import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/11/2  20:08
 */
public class MapsTest {

    public static void main(String[] args) {
        RightsInfo rightsInfo1 = getRightsInfo(1);
        RightsInfo rightsInfo2 = getRightsInfo(2);
        RightsInfo rightsInfo3 = getRightsInfo(3);
        RightsInfo rightsInfo4 = getRightsInfo(4);
        RightsInfo rightsInfo5 = getRightsInfo(5);
        List<RightsInfo> rightsInfos = Lists.newArrayList();
        rightsInfos.add(rightsInfo1);
        rightsInfos.add(rightsInfo2);
        rightsInfos.add(rightsInfo3);
        rightsInfos.add(rightsInfo4);
        rightsInfos.add(rightsInfo5);

        Map<Integer, RightsInfo> rightsInfoMap = Maps.uniqueIndex(rightsInfos, new Function<RightsInfo, Integer>() {
            @Override
            public Integer apply(RightsInfo rightsInfo) {
                return rightsInfo.getId();
            }
        });

        System.out.println(rightsInfoMap);
    }

    public static RightsInfo getRightsInfo(Integer id) {
        RightsInfo rightsInfo = new RightsInfo();
        rightsInfo.setCanGiven(2);
        rightsInfo.setCanSale(34);
        rightsInfo.setDescription("zyf");
        rightsInfo.setId(id);

        return rightsInfo;
    }

}
