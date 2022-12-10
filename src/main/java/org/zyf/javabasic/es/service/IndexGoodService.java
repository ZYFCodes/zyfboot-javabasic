package org.zyf.javabasic.es.service;

import java.util.Map;

/**
 * @author yanfengzhang
 * @description 索引操作service
 * @date 2022/12/7  23:01
 */
public interface IndexGoodService {

    /**
     * 创建索引
     *
     * @param indexName 索引名
     * @param mapping   映射结构配置
     * @return true-创建成功
     * @throws Exception 异常
     */
    boolean indexCreate(String indexName, String mapping) throws Exception;

    /**
     * 获取索引结构
     *
     * @param indexName 索引名
     * @return 索引结构
     * @throws Exception 异常
     */
    Map<String, Object> getMapping(String indexName) throws Exception;

    /**
     * 删除索引库
     *
     * @param indexName 索引名
     * @return true-删除成功
     * @throws Exception 异常
     */
    boolean indexDelete(String indexName) throws Exception;

    /**
     * 判断索引是否存在
     *
     * @param indexName 索引名
     * @return true-存在
     * @throws Exception 异常
     */
    boolean indexExists(String indexName) throws Exception;
}
