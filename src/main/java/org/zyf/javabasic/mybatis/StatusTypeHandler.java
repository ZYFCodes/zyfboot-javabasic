//package org.zyf.javabasic.mybatis;
//
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * @author yanfengzhang
// * @description 自定义的 TypeHandler 来实现 Status 枚举类型与数据库列值之间的转换
// * @date 2023/5/13  23:36
// */
//public class StatusTypeHandler extends BaseTypeHandler<Status> {
//
//    @Override
//    public Status getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        // 从 ResultSet 中获取列值
//        int data = rs.getInt(columnName);
//        // 将列值转换为枚举对象
//        return Status.getEnumById(data);
//    }
//
//    @Override
//    public Status getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        int data = rs.getInt(columnIndex);
//        return Status.getEnumById(data);
//    }
//
//    @Override
//    public Status getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
//        int data = callableStatement.getInt(columnIndex);
//        return Status.getEnumById(data);
//    }
//
//    @Override
//    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Status status, JdbcType jdbcType) throws SQLException {
//        // 将枚举值的code存储到数据库中
//        preparedStatement.setInt(i, status.getCode());
//    }
//}
