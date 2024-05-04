package org.zyf.javabasic.ipaddresses;

import java.sql.*;

/**
 * @program: zyfboot-javabasic
 * @description: 比较两种存储方式的查询时间，以确定哪种方式的查询效率更高。
 * @author: zhangyanfeng
 * @create: 2024-05-02 23:44
 **/
public class IPAddressStorageComparison {
    public static void main(String[] args) {
        // Connect to the database
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zyf", "root", "Zyf2014");

            // Query IPv4 addresses stored as strings
            long startTimeString = System.currentTimeMillis();
            Statement statement = connection.createStatement();
            ResultSet resultSetString = statement.executeQuery("SELECT * FROM ip_addresses_string WHERE ip_address = '192.168.1.1'");
            long endTimeString = System.currentTimeMillis();
            long durationString = endTimeString - startTimeString;
            System.out.println("Query time for string storage: " + durationString + " milliseconds");

            // Query IPv4 addresses stored as integers
            long startTimeInteger = System.currentTimeMillis();
            ResultSet resultSetInteger = statement.executeQuery("SELECT * FROM ip_addresses_integer WHERE ip_address = 3232235777");
            long endTimeInteger = System.currentTimeMillis();
            long durationInteger = endTimeInteger - startTimeInteger;
            System.out.println("Query time for integer storage: " + durationInteger + " milliseconds");

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
