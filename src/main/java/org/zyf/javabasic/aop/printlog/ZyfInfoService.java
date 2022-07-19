package org.zyf.javabasic.aop.printlog;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/13  11:55
 */
@Service
public class ZyfInfoService {
    @PrintLogInfo(description = "获取张彦峰的相关信息")
    public ZyfInfoResponse getZyfInfo(ZyfInfoRequest zyfInfoRequest) {
        if (zyfInfoRequest.isNeedBasicInfo() && !zyfInfoRequest.isNeedBlogInfo()) {
            return ZyfInfoResponse.builder()
                    .name("张彦峰")
                    .age(29)
                    .height(185)
                    .weight(83).build();
        }

        List<String> blogShowcases = Stream.of("https://blog.csdn.net/xiaofeng10330111/article/details/106086200",
                "https://blog.csdn.net/xiaofeng10330111/article/details/106086035").collect(Collectors.toList());

        return ZyfInfoResponse.builder()
                .name("张彦峰")
                .age(29)
                .height(185)
                .weight(83)
                .blogShowcases(blogShowcases).build();
    }
}
