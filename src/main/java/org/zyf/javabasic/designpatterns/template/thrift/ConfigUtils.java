package org.zyf.javabasic.designpatterns.template.thrift;

/**
 * @author yanfengzhang
 * @description 实际项目中的配置基本实现类（此处只做测试使用）
 * @date 2022/3/18  23:19
 */
public class ConfigUtils {
    /**
     * thrift scroll 遍历最大页数, 默认10000页
     */
    public static int getThriftScrollMaxPageLimit() {
        /*配置项：thrift_scroll_max_page_limit，默认值：10000*/
        return 10000;
    }

    /**
     * thrift scroll 遍历最大重试次数
     */
    public static int getThriftMaxInvokeRetryCount() {
        /*配置项：thrift_max_invoke_retry_count，默认值：5*/
        return 5;
    }

    /**
     * thrift scroll 遍历每页需要返回的结果个数
     */
    public static int getThriftScrollPageSize() {
        /*配置项：thrift_scroll_page_size，默认值：50*/
        return 5;
    }

    /**
     * thrift scroll 遍历, 调用频率,单位毫秒ms
     */
    public static int getThriftScrollRate() {
        /*配置项：thrift_scroll_rate，默认值：0*/
        return 0;
    }
}
