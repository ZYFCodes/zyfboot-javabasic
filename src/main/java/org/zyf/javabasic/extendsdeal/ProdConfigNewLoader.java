package org.zyf.javabasic.extendsdeal;

/**
 * @program: zyfboot-javabasic
 * @description: ProdConfigLoader修正
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:59
 **/
public class ProdConfigNewLoader {
    private final String commonConfig;
    private final String prodCert;

    private ProdConfigNewLoader(String commonConfig, String prodCert) {
        this.commonConfig = commonConfig;
        this.prodCert = prodCert;
    }

    public static ProdConfigNewLoader create() {
        String common = loadCommonConfig();   // 显式先加载通用配置
        String cert = loadProdCerts(common); // 再加载依赖其的生产证书
        return new ProdConfigNewLoader(common, cert);
    }

    private static String loadCommonConfig() {
        return "COMMON_OK";
    }

    private static String loadProdCerts(String base) {
        if (base == null) throw new IllegalStateException("commonConfig 不可为 null！");
        return "CERT_FOR_" + base;
    }

    public String getCommonConfig() {
        return commonConfig;
    }

    public String getProdCert() {
        return prodCert;
    }
}
