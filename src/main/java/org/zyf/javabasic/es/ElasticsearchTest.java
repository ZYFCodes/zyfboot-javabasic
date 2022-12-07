package org.zyf.javabasic.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.es.service.IndexService;

import java.util.Map;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/7  17:31
 */
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ElasticsearchTest {
    @Autowired
    private IndexService indexService;

    private final String indexName = "goods";

    /**
     * 创建索引库和映射表结构  注意：索引一般不会这么创建
     */
    @Test
    public void indexCreate() {
        String mapping = "{\n" +
                "    \"properties\":{\n" +
                "        \"brandName\":{\n" +
                "            \"type\":\"keyword\"\n" +
                "        },\n" +
                "        \"categoryName\":{\n" +
                "            \"type\":\"keyword\"\n" +
                "        },\n" +
                "        \"createTime\":{\n" +
                "            \"type\":\"date\",\n" +
                "            \"format\":\"yyyy-MM-dd HH:mm:ss\"\n" +
                "        },\n" +
                "        \"id\":{\n" +
                "            \"type\":\"long\"\n" +
                "        },\n" +
                "        \"price\":{\n" +
                "            \"type\":\"double\"\n" +
                "        },\n" +
                "        \"saleNum\":{\n" +
                "            \"type\":\"integer\"\n" +
                "        },\n" +
                "        \"status\":{\n" +
                "            \"type\":\"integer\"\n" +
                "        },\n" +
                "        \"stock\":{\n" +
                "            \"type\":\"integer\"\n" +
                "        },\n" +
                "        \"spec\":{\n" +
                "            \"type\":\"text\"\n" +
                "        },\n" +
                "        \"title\":{\n" +
                "            \"type\":\"text\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        boolean flag = false;
        try {
            flag = indexService.indexCreate(indexName, mapping);
        } catch (Exception e) {
            log.error("创建索引失败，错误信息：", e);
        }
        log.info("创建索引是否成功：{}", flag);
    }

    /**
     * 获取索引表结构
     */
    @Test
    public void getMapping() {
        try {
            Map<String, Object> indexMap = indexService.getMapping(indexName);

            // 将bean 转化为格式化后的json字符串
            String pretty1 = JSON.toJSONString(indexMap, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteDateUseDateFormat);
            log.info("索引信息：{}", pretty1);

        } catch (Exception e) {
            log.error("获取索引失败，错误信息：", e);
        }
    }

    /**
     * 删除索引库
     */
    @Test
    public void deleteIndex() {
        boolean flag = false;
        try {
            flag = indexService.indexDelete(indexName);
        } catch (Exception e) {
            log.error("删除索引库失败，错误信息：", e);
        }
        log.info("删除索引库是否成功：：{}", flag);
    }

    /**
     * 校验索引库是否存在
     */
    @Test
    public void indexExists() {
        boolean flag = false;
        try {
            flag = indexService.indexExists(indexName);
        } catch (Exception e) {
            log.error("校验索引库是否存在，错误信息：", e);
        }
        log.info("索引库是否存在：：{}", flag);
    }

}
