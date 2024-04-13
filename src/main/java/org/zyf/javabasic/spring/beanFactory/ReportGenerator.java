package org.zyf.javabasic.spring.beanFactory;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @program: zyfboot-javabasic
 * @description: 调用其方法来生成报告
 * @author: zhangyanfeng
 * @create: 2024-04-13 13:52
 **/
public class ReportGenerator {
    private DataSource dataSource;

    // setter 方法用于接收 dataSource 属性的注入
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void generateReport() {
        // 使用 JdbcTemplate 连接数据库
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // 查询数据库并生成报告
//        String sql = "SELECT * FROM user20240413";
//        jdbcTemplate.query(sql, (rs, rowNum) -> {
//            String username = rs.getString("username");
//            String email = rs.getString("email");
//            System.out.println("Username: " + username + ", Email: " + email);
//            return null;
//        });

        System.out.println("Report generated successfully.");
    }
}
