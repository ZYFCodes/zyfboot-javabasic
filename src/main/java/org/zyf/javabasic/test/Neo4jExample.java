//package org.zyf.javabasic.test;
//
///**
// * @author yanfengzhang
// * @description
// * @date 2023/5/3  11:18
// */
//public class Neo4jExample {
//    public static void main(String[] args) {
//        // 连接到Neo4j数据库
//        String uri = "bolt://localhost:7687";
//        String user = "neo4j";
//        String password = "123456";
//        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
//        Session session = driver.session();
//
//        // 定义查询语句，查找“苹果”的描述
//        String query = "MATCH (c:Concept{name:'苹果'})-[:HasDescription]->(d:Description) RETURN d.content";
//
//        // 执行查询语句，返回结果
//        StatementResult result = session.run(query);
//
//        // 输出查询结果
//        while (result.hasNext()) {
//            Record record = result.next();
//            String content = record.get("d.content").asString();
//            System.out.println(content);
//        }
//
//        // 关闭数据库连接
//        session.close();
//        driver.close();
//    }
//}
