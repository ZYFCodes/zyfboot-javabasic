package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: zyfboot-javabasic
 * @description: BaseConfigLoader
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:36
 **/
@Slf4j
public class BaseConfigLoader {
    protected static String commonCert;

    static {
        log.info("BaseConfigLoader: loading common config...");
        commonCert = loadCommonConfig();
    }

    protected static String loadCommonConfig() {
        return "common-cert";
    }
}
