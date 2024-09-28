package org.zyf.javabasic.ipaddresses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;


/**
 * @program: zyfboot-javabasic
 * @description: 简单的Java程序示例，用于生成大量IPv4地址数据并插入到数据库表中
 * @author: zhangyanfeng
 * @create: 2024-05-02 23:29
 **/
public class IPAddressDataGenerator {
    public static void main(String[] args) {
        // 数据库连接信息
        String url = "jdbc:mysql://localhost:3306/zyf";
        String username = "root";
        String password = "Zyf2014";

        // 生成大量IPv4地址数据
        int numAddresses = 10000; // 要生成的IPv4地址数量
        String[] ipAddresses = generateIPv4Addresses(numAddresses);

        // 将数据插入到数据库表中
        insertData(url, username, password, ipAddresses);
    }

    // 生成大量IPv4地址数据
    private static String[] generateIPv4Addresses(int numAddresses) {
        String[] ipAddresses = new String[numAddresses];
        Random rand = new Random();

        for (int i = 0; i < numAddresses; i++) {
            // 生成随机的IPv4地址
            String ipAddress = rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256) + "." + rand.nextInt(256);
            ipAddresses[i] = ipAddress;
        }

        return ipAddresses;
    }

    // 将数据插入到数据库表中
    private static void insertData(String url, String username, String password, String[] ipAddresses) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // 插入字符串存储的IPv4地址数据
            insertIPv4Addresses(connection, "ip_addresses_string", ipAddresses);

            // 将IPv4地址转换为整数存储并插入数据
            long[] integerAddresses = convertToInteger(ipAddresses);
            insertIntegerAddresses(connection, "ip_addresses_integer", integerAddresses);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 插入字符串存储的IPv4地址数据
    private static void insertIPv4Addresses(Connection connection, String tableName, String[] ipAddresses) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (ip_address) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (String ipAddress : ipAddresses) {
                statement.setString(1, ipAddress);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    // 将IPv4地址转换为整数存储并插入数据
    private static void insertIntegerAddresses(Connection connection, String tableName, long[] integerAddresses) throws SQLException {
        String sql = "INSERT INTO " + tableName + " (ip_address) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (long ipAddress : integerAddresses) {
                statement.setLong(1, ipAddress);
                statement.addBatch();
            }
            statement.executeBatch();
        }
    }

    // 将IPv4地址转换为整数存储
    private static long[] convertToInteger(String[] ipAddresses) {
        long[] integerAddresses = new long[ipAddresses.length];
        for (int i = 0; i < ipAddresses.length; i++) {
            String[] parts = ipAddresses[i].split("\\.");
            long ipAddress = 0;
            for (int j = 0; j < 4; j++) {
                ipAddress += Long.parseLong(parts[j]) << (24 - (8 * j));
            }
            integerAddresses[i] = ipAddress;
        }
        return integerAddresses;
    }
}
