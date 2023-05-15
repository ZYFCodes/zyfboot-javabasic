//package org.zyf.javabasic.mybatis;
//
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * @author yanfengzhang
// * @description 用于处理 InetAddress 类型与数据库列值之间的转换
// * @date 2023/5/14  11:20
// */
//public class InetAddressTypeHandler extends BaseTypeHandler<InetAddress> {
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, InetAddress parameter, JdbcType jdbcType) throws SQLException {
//        // 获取 IP 地址的字符串表示
//        String ipAddressString = parameter.getHostAddress();
//        // 将字符串存储到数据库中
//        ps.setString(i, ipAddressString);
//    }
//
//    @Override
//    public InetAddress getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        // 从 ResultSet 中获取字符串值
//        String ipAddressString = rs.getString(columnName);
//        // 将字符串转换为 InetAddress
//        return parseIpAddressString(ipAddressString);
//    }
//
//    @Override
//    public InetAddress getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        String ipAddressString = rs.getString(columnIndex);
//        return parseIpAddressString(ipAddressString);
//    }
//
//    @Override
//    public InetAddress getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        String ipAddressString = cs.getString(columnIndex);
//        return parseIpAddressString(ipAddressString);
//    }
//
//    private InetAddress parseIpAddressString(String ipAddressString) {
//        try {
//            // 将字符串解析为 InetAddress
//            return InetAddress.getByName(ipAddressString);
//        } catch (UnknownHostException e) {
//            // 处理解析错误，例如返回默认的 IP 地址或抛出异常
//            return null;
//        }
//    }
//}
