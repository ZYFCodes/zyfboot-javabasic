package org.zyf.javabasic.test.truncate;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.assertj.core.util.Lists;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @program: zyfboot-javabasic
 * @description: 优化对象打印的工具类。 强调在对象打印过程中减少冗余数据的目标。
 * @author: zhangyanfeng
 * @create: 2022-12-30 22:10
 **/
public class ObjectPrintOptimizer {
    /**
     * 修改JSON对象指定路径对应的值为随机UUID
     */
    public static String modifyJsonByPaths(String jsonString, List<String> paths) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);

        if (!rootNode.isObject()) {
            throw new IllegalArgumentException("JSON数据必须是一个对象");
        }

        ObjectNode rootObjectNode = (ObjectNode) rootNode;
        for (String path : paths) {
            modifySinglePath(rootObjectNode, path);
        }
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }

    private static void modifySinglePath(ObjectNode rootNode, String path) throws Exception {
        String[] keys = path.split("\\.");
        ObjectNode currentNode = rootNode;

        for (int i = 0; i < keys.length - 1; i++) {
            currentNode = (ObjectNode) currentNode.get(keys[i]);
            if (currentNode == null || !currentNode.isObject()) {
                throw new IllegalArgumentException("无效的路径: " + path);
            }
        }

        String lastKey = keys[keys.length - 1];
        if (currentNode.has(lastKey)) {
            currentNode.put(lastKey, UUID.randomUUID().toString());
        } else {
            throw new IllegalArgumentException("路径中字段不存在: " + lastKey);
        }
    }

    /**
     * 修改JSON对象中所有字符串长度大于指定阈值的字段值
     *
     * @param jsonString JSON字符串
     * @param maxLength  字符串的长度阈值
     * @param replacement 替换内容
     * @return 修改后的JSON字符串
     * @throws Exception 如果JSON格式错误
     */
    public static String modifyLongStrings(String jsonString, int maxLength, String replacement) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonString);

        if (!rootNode.isObject()) {
            throw new IllegalArgumentException("JSON数据必须是一个对象");
        }

        // 递归修改长字符串
        modifyNode(null, rootNode, maxLength, replacement);

        // 返回修改后的JSON字符串
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    }

    /**
     * 递归修改JSON节点中的长字符串或特定类型的字段值
     *
     * @param parentNode 父节点
     * @param currentNode 当前节点
     * @param maxLength  字符串的长度阈值
     * @param replacement 替换内容
     */
    private static void modifyNode(ObjectNode parentNode, JsonNode currentNode, int maxLength, String replacement) {
        if (currentNode.isObject()) {
            // 如果是对象，递归处理每个字段
            ObjectNode objectNode = (ObjectNode) currentNode;
            List<String> fieldNames = Lists.newArrayList(objectNode.fieldNames());
            for (String fieldName : fieldNames) {
                modifyNode(objectNode, objectNode.get(fieldName), maxLength, replacement);
            }
        } else if (currentNode.isArray()) {
            // 如果是数组，递归处理每个元素
            for (JsonNode arrayNode : currentNode) {
                modifyNode(null, arrayNode, maxLength, replacement);
            }
        } else if (currentNode.isTextual()) {
            // 如果是字符串节点，检查长度并替换
            if (currentNode.asText().length() > maxLength && parentNode != null) {
                parentNode.put(currentNode.asText(), replacement);  // 替换长字符串
            }
        } else if (currentNode.isNumber()) {
            // 如果是数字类型节点，可以执行替换或其他逻辑
            if (parentNode != null) {
                parentNode.put(currentNode.asText(), UUID.randomUUID().toString());  // 示例：用UUID替换数字
            }
        } else if (currentNode.isBoolean()) {
            // 如果是布尔类型节点，可以执行其他逻辑
            if (parentNode != null) {
                parentNode.put(currentNode.asText(), currentNode.asBoolean() ? "false" : "true");  // 示例：将布尔值反转
            }
        }
    }

    /**
     * 修改JSON对象中指定路径列表对应的值为随机UUID
     */
    public static String modifyJsonByPath(String jsonString, String path) throws Exception {
        return modifyJsonByPaths(jsonString, Lists.newArrayList(path));
    }

    public static void main(String[] args) throws Exception {
        // 测试数据准备
        ComplexObject complexObject = createComplexObject();

        // 将复杂对象序列化为JSON字符串
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(complexObject);

        System.out.println("Original JSON:");
        System.out.println(jsonString);

        // 单独测试每个功能
        testModifyJsonByPaths(jsonString);
        testModifyLongStrings(jsonString);
        testModifyJsonBySinglePath(jsonString);
    }

    /**
     * 创建复杂对象的测试数据
     */
    private static ComplexObject createComplexObject() {
        ComplexObject complexObject = new ComplexObject();
        complexObject.user = new ComplexObject.User();
        complexObject.user.id = "123";
        complexObject.user.profile = new ComplexObject.Profile();
        complexObject.user.profile.bio = "This is a very long bio text that exceeds the limit.";
        complexObject.user.profile.preferences = new ComplexObject.Preferences();
        complexObject.user.profile.preferences.theme = "dark";
        complexObject.user.profile.preferences.language = "English";

        complexObject.metadata = new ComplexObject.Metadata();
        complexObject.metadata.requestId = UUID.randomUUID().toString();
        complexObject.metadata.timestamp = "2024-11-30T12:00:00Z";
        complexObject.metadata.tags = Arrays.asList("tag1", "tag2", "very-long-tag-that-needs-replacement");

        return complexObject;
    }

    /**
     * 测试按路径列表修改JSON值
     */
    private static void testModifyJsonByPaths(String jsonString) throws Exception {
        List<String> paths = Lists.newArrayList("user.profile.bio", "metadata.requestId");
        String modifiedJson = ObjectPrintOptimizer.modifyJsonByPaths(jsonString, paths);

        System.out.println("Modified JSON by paths:");
        System.out.println(modifiedJson);
    }

    /**
     * 测试按字符串长度修改JSON值
     */
    private static void testModifyLongStrings(String jsonString) throws Exception {
        int maxLength = 20;
        String replacement = "[TRUNCATED]";
        String modifiedJson = ObjectPrintOptimizer.modifyLongStrings(jsonString, maxLength, replacement);

        System.out.println("Modified JSON by long strings:");
        System.out.println(modifiedJson);
    }

    /**
     * 测试按单个路径修改JSON值
     */
    private static void testModifyJsonBySinglePath(String jsonString) throws Exception {
        String singlePath = "metadata.timestamp";
        String modifiedJson = ObjectPrintOptimizer.modifyJsonByPath(jsonString, singlePath);

        System.out.println("Modified JSON by single path:");
        System.out.println(modifiedJson);
    }
}
