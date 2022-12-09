package org.zyf.javabasic.es.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.max.ParsedMax;
import org.elasticsearch.search.aggregations.metrics.min.ParsedMin;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.es.service.QueryDataService;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/8  14:37
 */
@Service
@Log4j2
public class QueryDataServiceImpl implements QueryDataService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 精确查询（termQuery）
     *
     * @param indexName  索引名
     * @param columnName 列名或字段名
     * @param value      查询内容
     * @param classz     数据结构
     * @param <T>        数据结构
     * @return 精确查询内容数据
     */
    @Override
    public <T> List<T> termQuery(String indexName, String columnName, Object value, Class<T> classz) {
        /* 查询的数据列表 */
        List<T> list = new ArrayList<>();
        try {
            /*构建查询条件（注意：termQuery 支持多种格式查询，如 boolean、int、double、string 等，这里使用的是 string 的查询）*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termQuery(columnName, value));
            /*执行查询es数据*/
            queryEsData(indexName, classz, list, searchSourceBuilder);
        } catch (IOException e) {
            log.error("精确查询数据失败，错误信息：", e);
        }
        return list;
    }

    /**
     * terms:多个查询内容在一个字段中进行查询
     *
     * @param indexName  索引名
     * @param columnName 列名或字段名
     * @param dataArgs   查询内容集合
     * @param classz     数据结构
     * @param <T>        数据结构
     * @return 多个查询内容在一个字段中进行查询对应结果
     */
    @Override
    public <T> List<T> termsQuery(String indexName, String columnName, Object[] dataArgs, Class<T> classz) {
        /*查询的数据列表*/
        List<T> list = new ArrayList<>();
        try {
            /* 构建查询条件（注意：termsQuery 支持多种格式查询，如 boolean、int、double、string 等，这里使用的是 string 的查询）*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.termsQuery(columnName, dataArgs));
            /*展示100条,默认只展示10条记录*/
            searchSourceBuilder.size(100);
            /*执行查询es数据*/
            queryEsData(indexName, classz, list, searchSourceBuilder);
        } catch (IOException e) {
            log.error("单字段多内容查询数据失败，错误信息：", e);
        }
        return list;
    }

    /**
     * 匹配查询符合条件的所有数据，并设置分页
     *
     * @param indexName  索引名
     * @param classz     数据结构
     * @param startIndex 起始下标
     * @param pageSize   页大小
     * @param orderList  设置排序
     * @param columnName 列名或字段名
     * @param value      列名或字段名指定内容
     * @param <T>        数据结构
     * @return 符合条件的所有数据
     */
    @Override
    public <T> List<T> matchAllQuery(String indexName, Class<T> classz, int startIndex, int pageSize, List<String> orderList, String columnName, Object value) {
        /*查询的数据列表*/
        List<T> list = new ArrayList<>();
        try {
            /*创建查询源构造器*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

            /*构建查询条件*/
            if (StringUtils.isNotBlank(columnName) && Objects.nonNull(value)) {
                MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery(columnName, value);
                searchSourceBuilder.query(matchQueryBuilder);
            } else {
                MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
                searchSourceBuilder.query(matchAllQueryBuilder);
            }

            /*设置分页*/
            searchSourceBuilder.from(startIndex);
            searchSourceBuilder.size(pageSize);

            /*设置排序*/
            if (orderList != null) {
                for (String order : orderList) {
                    /*开头代表：倒序*/
                    boolean flag = order.startsWith("-");
                    SortOrder sort = flag ? SortOrder.DESC : SortOrder.ASC;
                    order = flag ? order.substring(1) : order;
                    searchSourceBuilder.sort(order, sort);
                }
            }
            /*执行查询es数据*/
            queryEsData(indexName, classz, list, searchSourceBuilder);
        } catch (IOException e) {
            log.error("查询所有数据失败，错误信息：", e);
        }
        return list;
    }

    /**
     * 词语匹配查询
     *
     * @param indexName  索引名
     * @param classz     数据结构
     * @param columnName 列名或字段名
     * @param value      指定内容
     * @param <T>        数据结构
     * @return 词语匹配查询结果
     */
    @Override
    public <T> List<T> matchPhraseQuery(String indexName, Class<T> classz, String columnName, Object value) {
        /*查询的数据列表*/
        List<T> list = new ArrayList<>();
        try {
            /*构建查询条件*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchPhraseQuery(columnName, value));
            /*执行查询es数据*/
            queryEsData(indexName, classz, list, searchSourceBuilder);
        } catch (IOException e) {
            log.error("词语匹配查询失败，错误信息：", e);
        }
        return list;
    }

    /**
     * 内容在多字段中进行查询
     *
     * @param indexName 索引名
     * @param classz    数据结构
     * @param fields    列名或字段名集合
     * @param text      指定内容
     * @param <T>       数据结构
     * @return 查询结果
     */
    @Override
    public <T> List<T> matchMultiQuery(String indexName, Class<T> classz, String[] fields, Object text) {
        /*查询的数据列表*/
        List<T> list = new ArrayList<>();
        try {
            /*构建查询条件*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            /*设置查询条件*/
            searchSourceBuilder.query(QueryBuilders.multiMatchQuery(text, fields));
            /*执行查询es数据*/
            queryEsData(indexName, classz, list, searchSourceBuilder);
        } catch (IOException e) {
            log.error("词语匹配查询失败，错误信息：", e);
        }
        return list;
    }

    /**
     * 通配符查询(wildcard)：会对查询条件进行分词。还可以使用通配符 ?（任意单个字符） 和 * （0个或多个字符）
     *
     * @param indexName 索引名
     * @param classz    数据结构
     * @param field     列名或字段名集合
     * @param text      指定内容
     * @param <T>       数据结构
     * @return 查询结果
     */
    @Override
    public <T> List<T> wildcardQuery(String indexName, Class<T> classz, String field, String text) {
        /*查询的数据列表*/
        List<T> list = new ArrayList<>();
        try {
            /*构建查询条件*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.wildcardQuery(field, text));
            /*执行查询es数据*/
            queryEsData(indexName, classz, list, searchSourceBuilder);
        } catch (IOException e) {
            log.error("通配符查询失败，错误信息：", e);
        }
        return list;
    }

    /**
     * 模糊查询商品信息
     *
     * @param indexName 索引名
     * @param classz    数据结构
     * @param field     列名或字段名集合
     * @param text      指定内容
     * @param <T>       数据结构
     * @return 查询结果
     */
    @Override
    public <T> List<T> fuzzyQuery(String indexName, Class<T> classz, String field, String text) {
        /*查询的数据列表*/
        List<T> list = new ArrayList<>();
        try {
            /*构建查询条件*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.fuzzyQuery(field, text).fuzziness(Fuzziness.AUTO));
            /*执行查询es数据*/
            queryEsData(indexName, classz, list, searchSourceBuilder);
        } catch (IOException e) {
            log.error("通配符查询失败，错误信息：", e);
        }
        return list;
    }

    /**
     * boolQuery 查询
     * 高亮展示标题搜索字段
     * 设置出参返回字段
     * 案例：查询从2018-2022年间标题含 三星 的商品信息
     *
     * @param indexName 索引名
     * @param beanClass 数据结构
     * @param <T>       数据结构
     * @return 查询结果
     */
    @Override
    public <T> List<T> boolQuery(String indexName, Class<T> beanClass) {
        /*查询的数据列表*/
        List<T> list = new ArrayList<>();
        try {
            /*创建 Bool 查询构建器*/
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            /*构建查询条件*/
            boolQueryBuilder.must(QueryBuilders.matchQuery("title", "三星"));
            boolQueryBuilder.must(QueryBuilders.matchQuery("spec", "联通3G"));
            boolQueryBuilder.filter().add(QueryBuilders.rangeQuery("createTime").format("yyyy").gte("2018").lte("2022"));
            /*构建查询源构建器*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(boolQueryBuilder);
            searchSourceBuilder.size(100);
            /*甚至返回字段
            如果查询的属性很少，那就使用includes，而excludes设置为空数组
            如果排序的属性很少，那就使用excludes，而includes设置为空数组*/
            String[] includes = {"title", "categoryName", "price"};
            String[] excludes = {};
            searchSourceBuilder.fetchSource(includes, excludes);
            /*高亮设置
            设置高亮三要素:  field: 你的高亮字段 , preTags ：前缀    , postTags：后缀*/
            HighlightBuilder highlightBuilder = new HighlightBuilder().field("title").preTags("<font color='red'>").postTags("</font>");
            highlightBuilder.field("spec").preTags("<font color='red'>").postTags("</font>");
            searchSourceBuilder.highlighter(highlightBuilder);
            /*创建查询请求对象，将查询对象配置到其中*/
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(searchSourceBuilder);
            /*执行查询，然后处理响应结果*/
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            /*根据状态和数据条数验证是否返回了数据*/
            if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().getTotalHits() > 0) {
                SearchHits hits = searchResponse.getHits();
                for (SearchHit hit : hits) {
                    /* 将 JSON 转换成对象*/
                    T bean = JSON.parseObject(hit.getSourceAsString(), beanClass);
                    /*获取高亮的数据*/
                    HighlightField highlightField = hit.getHighlightFields().get("title");
                    log.info("高亮名称：{}", highlightField.getFragments()[0].string());
                    /*替换掉原来的数据*/
                    Text[] fragments = highlightField.getFragments();
                    if (fragments != null && fragments.length > 0) {
                        StringBuilder title = new StringBuilder();
                        for (Text fragment : fragments) {
                            title.append(fragment);
                        }
                        /* 获取method对象，其中包含方法名称和参数列表*/
                        Method setTitle = beanClass.getMethod("setTitle", String.class);
                        if (setTitle != null) {
                            /*执行method，bean为实例对象，后面是方法参数列表；setTitle没有返回值*/
                            setTitle.invoke(bean, title.toString());
                        }
                    }
                    list.add(bean);
                }
            }
        } catch (Exception e) {
            log.error("布尔查询失败，错误信息：", e);
        }
        return list;
    }

    /**
     * 聚合查询 : 聚合查询一定是【先查出结果】，然后对【结果使用聚合函数】做处理.
     * Metric 指标聚合分析。常用的操作有：avg：求平均、max：最大值、min：最小值、sum：求和等
     * 案例：分别获取最贵的商品和获取最便宜的商品
     *
     * @param indexName 索引名
     */
    @Override
    public void metricQuery(String indexName) {
        try {
            /* 构建查询条件*/
            MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
            /*创建查询源构造器*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchAllQueryBuilder);

            /*获取最贵的商品*/
            AggregationBuilder maxPrice = AggregationBuilders.max("maxPrice").field("price");
            searchSourceBuilder.aggregation(maxPrice);
            /*获取最便宜的商品*/
            AggregationBuilder minPrice = AggregationBuilders.min("minPrice").field("price");
            searchSourceBuilder.aggregation(minPrice);

            /*创建查询请求对象，将查询对象配置到其中*/
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(searchSourceBuilder);
            /*执行查询，然后处理响应结果*/
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = searchResponse.getAggregations();
            ParsedMax max = aggregations.get("maxPrice");
            log.info("最贵的价格：" + max.getValue());
            ParsedMin min = aggregations.get("minPrice");
            log.info("最便宜的价格：" + min.getValue());
        } catch (Exception e) {
            log.error("指标聚合分析查询失败，错误信息：", e);
        }
    }

    /**
     * 聚合查询： 聚合查询一定是【先查出结果】，然后对【结果使用聚合函数】做处理
     * Bucket 分桶聚合分析 : 对查询出的数据进行分组group by，再在组上进行游标聚合
     * 案例：根据品牌进行聚合查询
     *
     * @param indexName        索引名
     * @param bucketField
     * @param bucketFieldAlias
     */
    @Override
    public void bucketQuery(String indexName, String bucketField, String bucketFieldAlias) {
        try {
            /*构建查询条件*/
            MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
            /*创建查询源构造器*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchAllQueryBuilder);

            /*根据bucketField进行分组查询*/
            TermsAggregationBuilder aggBrandName = AggregationBuilders.terms(bucketFieldAlias).field(bucketField);
            searchSourceBuilder.aggregation(aggBrandName);

            /*创建查询请求对象，将查询对象配置到其中*/
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(searchSourceBuilder);
            /*执行查询，然后处理响应结果*/
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = searchResponse.getAggregations();
            /*分组结果数据*/
            ParsedStringTerms aggBrandName1 = aggregations.get(bucketFieldAlias);
            for (Terms.Bucket bucket : aggBrandName1.getBuckets()) {
                log.info(bucket.getKeyAsString() + "====" + bucket.getDocCount());
            }
        } catch (IOException e) {
            log.error("分桶聚合分析查询失败，错误信息：", e);
        }
    }

    /**
     * 子聚合聚合查询  Bucket 分桶聚合分析
     * <p>
     * 案例：根据商品分类进行分组查询,并且获取分类商品中的平均价格
     *
     * @param indexName        索引名
     * @param bucketField
     * @param bucketFieldAlias
     * @param avgFiled
     * @param avgFiledAlias
     */
    @Override
    public void subBucketQuery(String indexName, String bucketField, String bucketFieldAlias, String avgFiled, String avgFiledAlias) {
        try {
            /*构建查询条件*/
            MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
            /*创建查询源构造器*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchAllQueryBuilder);

            /* 根据 bucketField进行分组查询,并且获取分类信息中 指定字段的平均值*/
            TermsAggregationBuilder subAggregation = AggregationBuilders.terms(bucketFieldAlias).field(bucketField)
                    .subAggregation(AggregationBuilders.avg(avgFiledAlias).field(avgFiled));
            searchSourceBuilder.aggregation(subAggregation);

            /* 创建查询请求对象，将查询对象配置到其中*/
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(searchSourceBuilder);
            /*执行查询，然后处理响应结果*/
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            Aggregations aggregations = searchResponse.getAggregations();
            ParsedStringTerms aggBrandName1 = aggregations.get(bucketFieldAlias);
            for (Terms.Bucket bucket : aggBrandName1.getBuckets()) {
                /*获取聚合后的 组内字段平均值,注意返回值不是Aggregation对象,而是指定的ParsedAvg对象*/
                ParsedAvg avgPrice = bucket.getAggregations().get(avgFiledAlias);
                log.info(bucket.getKeyAsString() + "====" + avgPrice.getValueAsString());
            }
        } catch (IOException e) {
            log.error("分桶聚合分析查询失败，错误信息：", e);
        }
    }

    /**
     * 综合聚合查询
     * <p>
     * 根据商品分类聚合，获取每个商品类的平均价格，并且在商品分类聚合之上子聚合每个品牌的平均价格
     *
     * @param indexName 索引名
     */
    @Override
    public void subSubAgg(String indexName) {
        try {
            /*构建查询条件*/
            MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
            /*创建查询源构造器*/
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(matchAllQueryBuilder);

            /*注意这里聚合写的位置不要写错,很容易搞混,错一个括号就不对了*/
            TermsAggregationBuilder subAggregation = AggregationBuilders.terms("categoryNameAgg").field("categoryName")
                    .subAggregation(AggregationBuilders.avg("categoryNameAvgPrice").field("price"))
                    .subAggregation(AggregationBuilders.terms("brandNameAgg").field("brandName")
                            .subAggregation(AggregationBuilders.avg("brandNameAvgPrice").field("price")));
            searchSourceBuilder.aggregation(subAggregation);

            /*创建查询请求对象，将查询对象配置到其中*/
            SearchRequest searchRequest = new SearchRequest(indexName);
            searchRequest.source(searchSourceBuilder);
            /*执行查询，然后处理响应结果*/
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            /*获取总记录数*/
            log.info("totalHits = " + searchResponse.getHits().getTotalHits());
            /*获取聚合信息*/
            Aggregations aggregations = searchResponse.getAggregations();
            ParsedStringTerms categoryNameAgg = aggregations.get("categoryNameAgg");

            /*获取值返回*/
            for (Terms.Bucket bucket : categoryNameAgg.getBuckets()) {
                /*获取聚合后的分类名称*/
                String categoryName = bucket.getKeyAsString();
                /*获取聚合命中的文档数量*/
                long docCount = bucket.getDocCount();
                /*获取聚合后的分类的平均价格,注意返回值不是Aggregation对象,而是指定的ParsedAvg对象*/
                ParsedAvg avgPrice = bucket.getAggregations().get("categoryNameAvgPrice");
                log.info(categoryName + "======平均价:" + avgPrice.getValue() + "======数量:" + docCount);

                ParsedStringTerms brandNameAgg = bucket.getAggregations().get("brandNameAgg");
                for (Terms.Bucket brandeNameAggBucket : brandNameAgg.getBuckets()) {
                    /*获取聚合后的品牌名称*/
                    String brandName = brandeNameAggBucket.getKeyAsString();
                    /*获取聚合后的品牌的平均价格,注意返回值不是Aggregation对象,而是指定的ParsedAvg对象*/
                    ParsedAvg brandNameAvgPrice = brandeNameAggBucket.getAggregations().get("brandNameAvgPrice");
                    log.info("     " + brandName + "======" + brandNameAvgPrice.getValue());
                }
            }
        } catch (IOException e) {
            log.error("综合聚合查询失败，错误信息：", e);
        }
    }


    /**
     * 执行es查询
     *
     * @param indexName
     * @param beanClass
     * @param list
     * @param searchSourceBuilder
     * @param <T>
     * @throws IOException
     */
    private <T> void queryEsData(String indexName, Class<T> beanClass, List<T> list, SearchSourceBuilder searchSourceBuilder) throws IOException {
        /*创建查询请求对象，将查询对象配置到其中*/
        SearchRequest searchRequest = new SearchRequest(indexName);
        searchRequest.source(searchSourceBuilder);
        /*执行查询，然后处理响应结果*/
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        /*根据状态和数据条数验证是否返回了数据*/
        if (RestStatus.OK.equals(searchResponse.status()) && searchResponse.getHits().getTotalHits() > 0) {
            SearchHits hits = searchResponse.getHits();
            for (SearchHit hit : hits) {
                /*将 JSON 转换成对象*/
                T bean = JSON.parseObject(hit.getSourceAsString(), beanClass);
                list.add(bean);
            }
        }
    }
}
