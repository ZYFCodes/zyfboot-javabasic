package org.zyf.javabasic.es.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.es.model.Goods;
import org.zyf.javabasic.es.service.DocumentGoodService;

import java.io.IOException;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/7  23:58
 */
@Service
@Log4j2
public class DocumentGoodServiceImpl implements DocumentGoodService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 增加文档信息
     *
     * @param indexName 索引名
     * @param type      文档类型
     * @param goods     商品数据
     * @return 增加文档信息状态
     * @throws IOException 异常信息
     */
    @Override
    public RestStatus addDocument(String indexName, String type, Goods goods) throws IOException {
        /*1.默认类型为_doc*/
        if (StringUtils.isBlank(type)) {
            type = "_doc";
        }
        /*2.将对象转为json*/
        String data = JSON.toJSONString(goods);
        /*3.创建索引请求对象*/
        IndexRequest indexRequest = new IndexRequest(indexName, type).id(goods.getId() + "").source(data, XContentType.JSON);
        /*4.执行增加文档*/
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        return response.status();
    }

    /**
     * 获取文档信息
     *
     * @param indexName 索引名
     * @param type      文档类型
     * @param id        文档ID
     * @return 商品数据
     * @throws Exception 异常信息
     */
    @Override
    public Goods getDocument(String indexName, String type, String id) throws Exception {
        /*1.默认类型为_doc*/
        if (StringUtils.isBlank(type)) {
            type = "_doc";
        }
        /*2.创建获取请求对象*/
        GetRequest getRequest = new GetRequest(indexName, type, id);
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

        return JSON.parseObject(response.getSourceAsString(), Goods.class);
    }

    /**
     * 更新文档信息
     *
     * @param indexName 索引名
     * @param type      文档类型
     * @param goods     商品数据
     * @return 更新文档信息状态
     * @throws IOException 异常信息
     */
    @Override
    public RestStatus updateDocument(String indexName, String type, Goods goods) throws IOException {
        /*1.默认类型为_doc*/
        if (StringUtils.isBlank(type)) {
            type = "_doc";
        }

        /*2.创建索引请求对象*/
        UpdateRequest updateRequest = new UpdateRequest(indexName, type, String.valueOf(goods.getId()));
        /*3.设置更新文档内容*/
        updateRequest.doc(JSON.toJSONString(goods), XContentType.JSON);
        /*4.执行更新文档*/
        UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

        return response.status();
    }

    /**
     * 删除文档信息
     *
     * @param indexName 索引名
     * @param type      文档类型
     * @param id        文档ID
     * @return 删除文档信息状态
     * @throws IOException 异常信息
     */
    @Override
    public RestStatus deleteDocument(String indexName, String type, String id) throws IOException {
        /*1.默认类型为_doc*/
        if (StringUtils.isBlank(type)) {
            type = "_doc";
        }

        /*2.创建删除请求对象*/
        DeleteRequest deleteRequest = new DeleteRequest(indexName, type, id);
        /*3.执行删除文档*/
        DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);

        return response.status();
    }

    /**
     * 批量导入
     *
     * @param goodsList 商品列表
     * @return 批量导入状态
     * @throws IOException 异常信息
     */
    @Override
    public RestStatus batchImportGoodsData(List<Goods> goodsList) throws IOException {
        if (CollectionUtils.isEmpty(goodsList)) {
            return RestStatus.CREATED;
        }

        /*bulk导入 循环goodsList，创建IndexRequest添加数据*/
        BulkRequest bulkRequest = new BulkRequest();
        for (Goods goods : goodsList) {
            //将goods对象转换为json字符串
            String data = JSON.toJSONString(goods);
            IndexRequest indexRequest = new IndexRequest("goods", "_doc");
            indexRequest.id(goods.getId() + "").source(data, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return response.status();
    }
}
