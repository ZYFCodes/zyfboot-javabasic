package org.zyf.javabasic.es.service;

import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/7  16:58
 */
public interface IndexService {

    /**
     * 创建索引
     *
     * @param indexName 索引名
     * @param mapping   映射结构配置
     * @return true-创建成功
     * @throws Exception 异常
     */
    public boolean indexCreate(String indexName, String mapping) throws Exception;

    /**
     * 获取索引结构
     *
     * @param indexName 索引名
     * @return 索引结构
     * @throws Exception 异常
     */
    public Map<String, Object> getMapping(String indexName) throws Exception;

    /**
     * 删除索引库
     *
     * @param indexName 索引名
     * @return true-删除成功
     * @throws Exception 异常
     */
    public boolean indexDelete(String indexName) throws Exception;

    /**
     * 判断索引是否存在
     *
     * @param indexName 索引名
     * @return true-存在
     * @throws Exception 异常
     */
    public boolean indexExists(String indexName) throws Exception;
}
