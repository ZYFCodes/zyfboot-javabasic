package org.zyf.javabasic.es.service;

import org.elasticsearch.rest.RestStatus;
import org.zyf.javabasic.es.model.Goods;

import java.io.IOException;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 文档操作service
 * @date 2022/12/7  20:45
 */
public interface DocumentGoodService {
    /**
     * 增加文档信息
     *
     * @param indexName 索引名
     * @param type      文档类型
     * @param goods     商品数据
     * @return 增加文档信息状态
     * @throws IOException 异常信息
     */
    RestStatus addDocument(String indexName, String type, Goods goods) throws IOException;

    /**
     * 获取文档信息
     *
     * @param indexName 索引名
     * @param type      文档类型
     * @param id        文档ID
     * @return 商品数据
     * @throws Exception 异常信息
     */
    Goods getDocument(String indexName, String type, String id) throws Exception;

    /**
     * 更新文档信息
     *
     * @param indexName 索引名
     * @param type      文档类型
     * @param goods     商品数据
     * @return 更新文档信息状态
     * @throws IOException 异常信息
     */
    RestStatus updateDocument(String indexName, String type, Goods goods) throws IOException;

    /**
     * 删除文档信息
     *
     * @param indexName 索引名
     * @param type      文档类型
     * @param id        文档ID
     * @return 删除文档信息状态
     * @throws IOException 异常信息
     */
    RestStatus deleteDocument(String indexName, String type, String id) throws IOException;

    /**
     * 批量导入
     *
     * @param goodsList 商品列表
     * @return 批量导入状态
     * @throws IOException 异常信息
     */
    RestStatus batchImportGoodsData(List<Goods> goodsList) throws IOException;
}
