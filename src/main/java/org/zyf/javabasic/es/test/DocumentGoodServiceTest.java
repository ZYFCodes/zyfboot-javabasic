package org.zyf.javabasic.es.test;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.rest.RestStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.es.model.Goods;
import org.zyf.javabasic.es.service.DocumentGoodService;
import org.zyf.javabasic.es.service.IndexGoodService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/7  23:31
 */
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DocumentGoodServiceTest {
    @Autowired
    private IndexGoodService indexGoodService;
    @Autowired
    private DocumentGoodService documentGoodService;

    private final String indexName = "goods";

    /**
     * 添加文档
     */
    @Test
    public void addDocument() {
        // 创建商品信息
        Goods goods = new Goods();
        goods.setId(1L);
        goods.setTitle("Apple iPhone 13 Pro (A2639) 256GB 远峰蓝色 支持移动联通电信5G 双卡双待手机");
        goods.setPrice(new BigDecimal("8799.00"));
        goods.setStock(1000);
        goods.setSaleNum(599);
        goods.setCategoryName("手机");
        goods.setBrandName("Apple");
        goods.setStatus(0);
        goods.setCreateTime(new Date());

        // 返回状态
        RestStatus restStatus = null;
        try {
            restStatus = documentGoodService.addDocument(indexName, "_doc", goods);
        } catch (Exception e) {
            log.error("添加文档失败，错误信息：", e);
        }
        log.info("添加文档响应状态：{}", restStatus);
    }

    @Test
    public void getDocument() {
        Goods goods = null;
        try {
            goods = documentGoodService.getDocument(indexName, "_doc", "1");
        } catch (Exception e) {
            log.error("查询文档失败，错误信息：", e);
        }
        log.info("查询的文档信息：{}", goods);
    }

    @Test
    public void updateDocument() {
        // 创建商品信息
        Goods goods = new Goods();
        goods.setTitle("Apple iPhone 13 Pro Max (A2644) 256GB 远峰蓝色 支持移动联通电信5G 双卡双待手机");
        goods.setPrice(new BigDecimal("9999"));
        goods.setId(1L);

        // 返回状态
        RestStatus restStatus = null;
        try {
            restStatus = documentGoodService.updateDocument(indexName, "_doc", goods);
        } catch (Exception e) {
            log.error("更新文档失败，错误信息：", e);
        }
        log.info("更新文档响应状态：{}", restStatus);
    }

    @Test
    public void deleteDocument() {
        // 返回状态
        RestStatus restStatus = null;
        try {
            restStatus = documentGoodService.deleteDocument(indexName, "_doc", "1");
        } catch (Exception e) {
            log.error("删除文档失败，错误信息：", e);
        }
        log.info("删除文档响应状态：{}", restStatus);
    }

    /**
     * 批量导入测试数据
     */
    @Test
    public void importDocument() {
        List<Goods> goodsList = Lists.newArrayList();
        Goods goods1 = new Goods();
        goods1.setId(2L);
        goods1.setTitle("Apple iPhone 13 Pro (A2639) 512GB 远峰蓝色 支持移动联通电信5G 双卡双待手机");
        goods1.setPrice(new BigDecimal("10099.00"));
        goods1.setStock(1000);
        goods1.setSaleNum(599);
        goods1.setCategoryName("手机");
        goods1.setBrandName("Apple");
        goods1.setStatus(0);
        goods1.setCreateTime(new Date());
        goodsList.add(goods1);

        Goods goods2 = new Goods();
        goods2.setId(3L);
        goods2.setTitle("Apple iPhone 13 Pro (A2639) 1024GB 远峰蓝色 支持移动联通电信5G 双卡双待手机");
        goods2.setPrice(new BigDecimal("11099.00"));
        goods2.setStock(1000);
        goods2.setSaleNum(599);
        goods2.setCategoryName("手机");
        goods2.setBrandName("Apple");
        goods2.setStatus(0);
        goods2.setCreateTime(new Date());
        goodsList.add(goods2);

        RestStatus restStatus = null;
        try {
            restStatus = documentGoodService.batchImportGoodsData(goodsList);
        } catch (Exception e) {
            log.error("批量导入数据失败，错误信息：", e);
        }
        log.info("批量导入数据响应状态：{}", restStatus);
    }
}
