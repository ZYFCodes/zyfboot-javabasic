package org.zyf.javabasic.es.service.impl;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.es.service.IndexGoodService;

import java.util.Map;

/**
 * @author yanfengzhang
 * @description 索引服务类
 * @date 2022/12/7  17:00
 */
@Service
public class IndexGoodServiceImpl implements IndexGoodService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     *
     * @param indexName 索引名
     * @param mapping   映射结构配置
     * @return true-创建成功
     * @throws Exception 异常
     */
    @Override
    public boolean indexCreate(String indexName, String mapping) throws Exception {
        CreateIndexRequest indexRequest = new CreateIndexRequest(indexName);

        IndicesClient indicesClient = restHighLevelClient.indices();
        indexRequest.mapping(mapping, XContentType.JSON);

        // 请求服务器
        CreateIndexResponse response = indicesClient.create(indexRequest, RequestOptions.DEFAULT);

        return response.isAcknowledged();
    }

    /**
     * 获取索引结构
     *
     * @param indexName 索引名
     * @return 索引结构
     * @throws Exception 异常
     */
    @Override
    public Map<String, Object> getMapping(String indexName) throws Exception {
        IndicesClient indicesClient = restHighLevelClient.indices();

        // 创建get请求
        GetIndexRequest request = new GetIndexRequest(indexName);
        // 发送get请求
        GetIndexResponse response = indicesClient.get(request, RequestOptions.DEFAULT);
        // 获取表结构
        Map<String, MappingMetaData> mappings = response.getMappings();
        Map<String, Object> sourceAsMap = mappings.get(indexName).getSourceAsMap();
        return sourceAsMap;
    }

    /**
     * 删除索引库
     *
     * @param indexName 索引名
     * @return true-删除成功
     * @throws Exception 异常
     */
    @Override
    public boolean indexDelete(String indexName) throws Exception {
        IndicesClient indicesClient = restHighLevelClient.indices();
        // 创建delete请求方式
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
        // 发送delete请求
        AcknowledgedResponse response = indicesClient.delete(deleteIndexRequest, RequestOptions.DEFAULT);

        return response.isAcknowledged();
    }

    /**
     * 判断索引是否存在
     *
     * @param indexName 索引名
     * @return true-存在
     * @throws Exception 异常
     */
    @Override
    public boolean indexExists(String indexName) throws Exception {
        IndicesClient indicesClient = restHighLevelClient.indices();
        // 创建get请求
        GetIndexRequest request = new GetIndexRequest(indexName);
        // 判断索引库是否存在
        return indicesClient.exists(request, RequestOptions.DEFAULT);
    }
}
