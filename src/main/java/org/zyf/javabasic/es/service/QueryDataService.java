package org.zyf.javabasic.es.service;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/8  23:25
 */
public interface QueryDataService {
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
    <T> List<T> termQuery(String indexName, String columnName, Object value, Class<T> classz);

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
    <T> List<T> termsQuery(String indexName, String columnName, Object[] dataArgs, Class<T> classz);

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
    <T> List<T> matchAllQuery(String indexName, Class<T> classz, int startIndex, int pageSize, List<String> orderList, String columnName, Object value);

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
    <T> List<T> matchPhraseQuery(String indexName, Class<T> classz, String columnName, Object value);

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
    <T> List<T> matchMultiQuery(String indexName, Class<T> classz, String[] fields, Object text);

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
    <T> List<T> wildcardQuery(String indexName, Class<T> classz, String field, String text);

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
    <T> List<T> fuzzyQuery(String indexName, Class<T> classz, String field, String text);

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
    <T> List<T> boolQuery(String indexName, Class<T> beanClass);

    /**
     * 聚合查询 : 聚合查询一定是【先查出结果】，然后对【结果使用聚合函数】做处理.
     * Metric 指标聚合分析。常用的操作有：avg：求平均、max：最大值、min：最小值、sum：求和等
     * 案例：分别获取最贵的商品和获取最便宜的商品
     *
     * @param indexName 索引名
     */
    void metricQuery(String indexName);

    /**
     * 聚合查询： 聚合查询一定是【先查出结果】，然后对【结果使用聚合函数】做处理
     * Bucket 分桶聚合分析 : 对查询出的数据进行分组group by，再在组上进行游标聚合
     * 案例：根据品牌进行聚合查询
     *
     * @param indexName        索引名
     * @param bucketField
     * @param bucketFieldAlias
     */
    void bucketQuery(String indexName, String bucketField, String bucketFieldAlias);

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
    void subBucketQuery(String indexName, String bucketField, String bucketFieldAlias, String avgFiled, String avgFiledAlias);

    /**
     * 综合聚合查询
     * <p>
     * 根据商品分类聚合，获取每个商品类的平均价格，并且在商品分类聚合之上子聚合每个品牌的平均价格
     *
     * @param indexName 索引名
     */
    void subSubAgg(String indexName);
}
