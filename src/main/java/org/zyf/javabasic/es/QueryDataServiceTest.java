package org.zyf.javabasic.es;

import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.es.model.Goods;
import org.zyf.javabasic.es.service.QueryDataService;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/8  20:28
 */
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QueryDataServiceTest {

    @Autowired
    private QueryDataService queryDataService;

    /**
     * 单字段精确查询
     */
    @Test
    public void termQuery() {
        List<Goods> goodsList = null;
        try {
            goodsList = queryDataService.termQuery("goods", "title", "华为", Goods.class);
        } catch (Exception e) {
            log.error("单字段精确查询失败，错误信息：", e);
        }
        log.info("单字段精确查询结果：{}", goodsList);
    }

    /**
     * 单字段多内容精确查询
     */
    @Test
    public void termsQuery() {
        List<Goods> goodsList = null;
        try {
            String[] args = {"华为", "OPPO", "TCL"};
            goodsList = queryDataService.termsQuery("goods", "title", args, Goods.class);
        } catch (Exception e) {
            log.error("单字段多内容精确查询失败，错误信息：", e);
        }
        log.info("单字段多内容精确查询结果：{}", goodsList);
    }

    /**
     * 单字段匹配分页查询
     */
    @Test
    public void matchQuery() {
        List<Goods> goodsList = null;
        try {
            List<String> orderList = Lists.newArrayList("-price", "-saleNum");
            goodsList = queryDataService.matchAllQuery("goods", Goods.class, 0, 3, orderList, "title", "华为");
        } catch (Exception e) {
            log.error("匹配查询失败，错误信息：", e);
        }
        log.info("匹配查询结果：{}", goodsList);
    }

    /**
     * 单字段多内容精确查询
     */
    @Test
    public void matchPhraseQuery() {
        List<Goods> goodsList = null;
        try {
            goodsList = queryDataService.matchPhraseQuery("goods", Goods.class, "title", "华为");
        } catch (Exception e) {
            log.error("词语匹配查询失败，错误信息：", e);
        }
        log.info("词语匹配查询结果：{}", goodsList);
    }

    /**
     * 内容在多字段中进行查询
     */
    @Test
    public void matchMultiQuery() {
        List<Goods> goodsList = null;
        try {
            String[] fields = {"title", "categoryName"};
            goodsList = queryDataService.matchMultiQuery("goods", Goods.class, fields, "手机");
        } catch (Exception e) {
            log.error("内容在多字段中进行查询失败，错误信息：", e);
        }
        log.info("内容在多字段中进行查询结果：{}", goodsList);
    }

    /**
     * 通配符查询
     * <p>
     * 查询所有以 “三” 结尾的商品信息
     */
    @Test
    public void wildcardQuery() {
        List<Goods> goodsList = null;
        try {
            goodsList = queryDataService.wildcardQuery("goods", Goods.class, "title", "*三");
        } catch (Exception e) {
            log.error("通配符查询查询失败，错误信息：", e);
        }
        log.info("通配符查询结果：{}", goodsList);
    }

    /**
     * 模糊查询
     * <p>
     * 模糊查询所有以 “三” 结尾的商品信息
     */
    @Test
    public void fuzzyQuery() {
        List<Goods> goodsList = null;
        try {
            goodsList = queryDataService.fuzzyQuery("goods", Goods.class, "title", "三");
        } catch (Exception e) {
            log.error("模糊查询失败，错误信息：", e);
        }
        log.info("模糊查询结果：{}", goodsList);
    }

    @Test
    public void boolQuery() {
        List<Goods> goodsList = null;
        try {
            goodsList = queryDataService.boolQuery("goods", Goods.class);
        } catch (Exception e) {
            log.error("布尔查询失败，错误信息：", e);
        }
        log.info("布尔查询结果：{}", goodsList);
    }

    /**
     * Metric 指标聚合分析
     */
    @Test
    public void metricQuery() {
        queryDataService.metricQuery("goods");
    }

    /**
     * Bucket 分桶聚合分析
     */
    @Test
    public void bucketQuery() {
        queryDataService.bucketQuery("goods", "brandName", "brandNameName");
    }

    /**
     * 子聚合聚合查询
     */
    @Test
    public void subBucketQuery() {
        queryDataService.subBucketQuery("goods", "brandName", "brandNameName", "price", "avgPrice");
    }

    /**
     * 综合聚合查询
     */
    @Test
    public void subSubAgg() {
        queryDataService.subSubAgg("goods");
    }
}
