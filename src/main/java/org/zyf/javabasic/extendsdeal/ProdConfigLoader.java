package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: zyfboot-javabasic
 * @description: ProdConfigLoader
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:37
 **/
@Slf4j
public class ProdConfigLoader extends BaseConfigLoader{
    public static String prodCert;

    static {
        log.info("ProdConfigLoader: loading prod cert using commonCert...");
        // 错误依赖：假如 commonCert 尚未初始化，报 NPE
        prodCert = "prod-cert-" + commonCert.toUpperCase(); // ⚠️潜在 NPE
    }
}
