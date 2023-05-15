//package org.zyf.javabasic.mybatis;
//
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author yanfengzhang
// * @description 自定义 TypeHandler 中将 VARCHAR 类型的数据库列与 List<Long> 类型的 Java 对象进行转换
// * @date 2023/5/14  10:48
// */
//public class LongListTypeHandler extends BaseTypeHandler<List<Long>> {
//
//    private static final String DELIMITER = ",";
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, List<Long> parameter, JdbcType jdbcType) throws SQLException {
//        String joinedString = parameter.stream().map(String::valueOf).collect(Collectors.joining(DELIMITER));
//        ps.setString(i, joinedString);
//    }
//
//    @Override
//    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        String columnValue = rs.getString(columnName);
//        return convertStringToList(columnValue);
//    }
//
//    @Override
//    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        String columnValue = rs.getString(columnIndex);
//        return convertStringToList(columnValue);
//    }
//
//    @Override
//    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        String columnValue = cs.getString(columnIndex);
//        return convertStringToList(columnValue);
//    }
//
//    private List<Long> convertStringToList(String columnValue) {
//        if (columnValue == null || columnValue.isEmpty()) {
//            return null;
//        }
//        List<Long> resultList = new ArrayList<>();
//        String[] values = columnValue.split(DELIMITER);
//        for (String value : values) {
//            resultList.add(Long.valueOf(value));
//        }
//        return resultList;
//    }
//}
