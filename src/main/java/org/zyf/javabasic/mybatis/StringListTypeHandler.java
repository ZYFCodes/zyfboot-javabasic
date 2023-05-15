//package org.zyf.javabasic.mybatis;
//
//import org.apache.ibatis.type.BaseTypeHandler;
//import org.apache.ibatis.type.JdbcType;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
///**
// * @author yanfengzhang
// * @description 自定义的 TypeHandler 来实现 List<String> 类型与数据库列值之间的转换
// * @date 2023/5/14  10:39
// */
//public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
//    private static final String DELIMITER = ",";
//
//    @Override
//    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
//        // 将 List<String> 转换为以逗号分隔的字符串
//        String rolesString = String.join(DELIMITER, parameter);
//        // 将字符串存储到数据库中
//        ps.setString(i, rolesString);
//    }
//
//    @Override
//    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
//        // 从 ResultSet 中获取字符串值
//        String rolesString = rs.getString(columnName);
//        // 将字符串转换为 List<String>
//        return parseRolesString(rolesString);
//    }
//
//    @Override
//    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
//        String rolesString = rs.getString(columnIndex);
//        return parseRolesString(rolesString);
//    }
//
//    @Override
//    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
//        String rolesString = cs.getString(columnIndex);
//        return parseRolesString(rolesString);
//    }
//
//    private List<String> parseRolesString(String rolesString) {
//        if (rolesString == null || rolesString.isEmpty()) {
//            return Collections.emptyList();
//        }
//        // 将字符串按逗号分隔转换为 List<String>
//        return Arrays.asList(rolesString.split(DELIMITER));
//    }
//}
//
