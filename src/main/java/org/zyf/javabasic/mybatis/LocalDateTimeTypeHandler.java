//package org.zyf.javabasic.mybatis;
//
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//
///**
// * @author yanfengzhang
// * @description 自定义的 TypeHandler 来实现 LocalDateTime 类型与数据库列值之间的转换
// * @date 2023/5/14  10:28
// */
//public class LocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
//        // 将 LocalDateTime 转换为 Timestamp 并设置到 PreparedStatement 中
//        ps.setTimestamp(i, Timestamp.valueOf(parameter));
//    }
//
//    @Override
//    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        // 从 ResultSet 中获取 Timestamp 值
//        Timestamp timestamp = rs.getTimestamp(columnName);
//        // 将 Timestamp 转换为 LocalDateTime
//        return timestamp != null ? timestamp.toLocalDateTime() : null;
//    }
//
//    @Override
//    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        Timestamp timestamp = rs.getTimestamp(columnIndex);
//        return timestamp != null ? timestamp.toLocalDateTime() : null;
//    }
//
//    @Override
//    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        Timestamp timestamp = cs.getTimestamp(columnIndex);
//        return timestamp != null ? timestamp.toLocalDateTime() : null;
//    }
//}
//
