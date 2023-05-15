//package org.zyf.javabasic.mybatis;
//
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.TypeHandler;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.sql.Types;
//import java.time.LocalDateTime;
//
///**
// * @author yanfengzhang
// * @description 自定义 TypeHandler 中将数据库中的时间段（以秒为单位）与 Java 对象中的 TimePeriod 类型进行转换
// * @date 2023/5/14  10:52
// */
//public class TimePeriodTypeHandler implements TypeHandler<TimePeriod> {
//    @Override
//    public void setParameter(PreparedStatement ps, int i, TimePeriod parameter, JdbcType jdbcType) throws SQLException {
//        // 将 Java 对象转换为数据库类型，并设置到 PreparedStatement 中的指定参数位置
//        if (parameter != null) {
//            ps.setTimestamp(i, Timestamp.valueOf(parameter.getStartTime()));
//            ps.setTimestamp(i + 1, Timestamp.valueOf(parameter.getEndTime()));
//        } else {
//            ps.setNull(i, Types.TIMESTAMP);
//            ps.setNull(i + 1, Types.TIMESTAMP);
//        }
//    }
//
//    @Override
//    public TimePeriod getResult(ResultSet rs, String columnName) throws SQLException {
//        // 从 ResultSet 中获取指定列名的数据库值，并将其转换为对应的 Java 对象
//        LocalDateTime startTime = rs.getTimestamp(columnName).toLocalDateTime();
//        LocalDateTime endTime = rs.getTimestamp(columnName + "_end").toLocalDateTime();
//        return new TimePeriod(startTime, endTime);
//    }
//
//    @Override
//    public TimePeriod getResult(ResultSet rs, int columnIndex) throws SQLException {
//        // 从 ResultSet 中获取指定列索引的数据库值，并将其转换为对应的 Java 对象
//        LocalDateTime startTime = rs.getTimestamp(columnIndex).toLocalDateTime();
//        LocalDateTime endTime = rs.getTimestamp(columnIndex + 1).toLocalDateTime();
//        return new TimePeriod(startTime, endTime);
//    }
//
//    @Override
//    public TimePeriod getResult(CallableStatement cs, int columnIndex) throws SQLException {
//        // 从 CallableStatement 中获取指定列索引的数据库值，并将其转换为对应的 Java 对象
//        LocalDateTime startTime = cs.getTimestamp(columnIndex).toLocalDateTime();
//        LocalDateTime endTime = cs.getTimestamp(columnIndex + 1).toLocalDateTime();
//        return new TimePeriod(startTime, endTime);
//    }
//}