//package cn.net.yzl.common.util;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.type.TypeFactory;
//import com.google.common.collect.Lists;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.math.NumberUtils;
//
//import javax.validation.constraints.NotNull;
//import java.util.List;
//
///**
// * json util
// **/
//public class JacksonUtil {
//    public static char PATH_SPLITER = '/';
//
//    public static ObjectMapper objectMapper = new ObjectMapper();
//    private static TypeFactory typeFactory;
//
//    static {
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true) ;
//        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
//        typeFactory = objectMapper.getTypeFactory();
//    }
//
//    /**
//     * 根据传入的json path获取单个节点的值，json path通过'/'分隔
//     * 查询不到时返回MissingNode
//     * eg:
//     *      node = {"a": {b: ["1", "2"]}}
//     *      getByPath(node, "a/b/0") 返回 "1"
//     */
//    public static JsonNode getValueByPath(JsonNode definitionNodes, @NotNull String path) {
//        String[] pathNodes = StringUtils.split(path, PATH_SPLITER);
//        JsonNode currNode = definitionNodes;
//        for (String pathNode : pathNodes) {
//            if (currNode.isMissingNode()) {
//                return currNode;
//            }
//            if (currNode.isArray() && NumberUtils.isDigits(pathNode)) {
//                int index = Integer.parseInt(pathNode);
//                currNode = currNode.path(index);
//            } else {
//                currNode = currNode.path(pathNode);
//            }
//        }
//        return currNode;
//    }
//
//    /**
//     *
//     * @param value json格式字符串,在转换基础类型时，要将value 先toJsonString一下
//     * @param valueClass
//     * @return
//     */
//    public static <T> T toObject(String value, Class<T> valueClass) {
//        if (StringUtils.isBlank(value)) {
//            return null;
//        }
//
//        try {
//            return objectMapper.readValue(value, valueClass);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <T> T toObject(Object value, Class<T> valueClass) {
//        if (value == null) {
//            return null;
//        }
//
//        try {
//            if (value instanceof String && !"java.lang.String".equals(valueClass.getName())){
//                return toObject(String.valueOf(value) , valueClass);
//            }
//            return objectMapper.readValue(toJsonString(value), valueClass);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <T> T toObject(JsonNode value, Class<T> valueClass) {
//        if (value == null) {
//            return null;
//        }
//        try {
//            return objectMapper.readValue(value.toString(), valueClass);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <T> List<T> toObjectList(Object value, Class<T> valueClass) {
//        if (value == null) {
//            return Lists.newArrayList();
//        }
//
//        try {
//            String v = toJsonString(value);
//            return objectMapper.readValue(v,
//                    typeFactory.constructCollectionType(List.class, valueClass));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <T> List<T> toObjectList(String value, Class<T> valueClass) {
//        if (StringUtils.isBlank(value)) {
//            return Lists.newArrayList();
//        }
//        try {
//            return objectMapper.readValue(value, typeFactory.constructCollectionType(List.class, valueClass));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static <T> List<T> toObjectList(JsonNode value, Class<T> valueClass) {
//        if (value == null) {
//            return Lists.newArrayList();
//        }
//        try {
//            return objectMapper.readValue(value.toString(),
//                    typeFactory.constructCollectionType(List.class, valueClass));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static String toJsonString(Object o) {
//        if (o == null) {
//            return null;
//        }
//        try {
//            return objectMapper.writeValueAsString(o);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static JsonNode parse(String value) {
//        if (StringUtils.isBlank(value)) {
//            return null;
//        }
//        try {
//            return objectMapper.readTree(value);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static JsonNode parse(Object value) {
//        if (value == null) {
//            return null;
//        }
//        try {
//            return objectMapper.readTree(toJsonString(value));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static JsonNode newJsonNode() {
//        return JacksonUtil.parse("{}");
//    }
//
//    public static String getString(JsonNode jsonNode, String key) {
//        if (jsonNode == null) {
//            return null;
//        }
//        if (!jsonNode.has(key)) {
//            return null;
//        }
//        if (jsonNode.get(key) == null) {
//            return null;
//        }
//        return jsonNode.get(key).asText();
//    }
//
//    public static Integer getInteger(JsonNode jsonNode, String key) {
//        if (jsonNode == null) {
//            return null;
//        }
//        if (!jsonNode.has(key)) {
//            return null;
//        }
//        if (jsonNode.get(key) == null) {
//            return null;
//        }
//        return jsonNode.get(key).asInt();
//    }
//
//    public static Integer getInteger(JsonNode jsonNode, String key, int defaultValue) {
//        Integer value = getInteger(jsonNode, key);
//        if (value != null) {
//            return value;
//        }
//        return defaultValue;
//    }
//
//    public static Long getLong(JsonNode jsonNode, String key) {
//        if (jsonNode == null) {
//            return null;
//        }
//        if (!jsonNode.has(key)) {
//            return null;
//        }
//        if (jsonNode.get(key) == null) {
//            return null;
//        }
//        return jsonNode.get(key).asLong();
//    }
//
//    public static Long getLong(JsonNode jsonNode, String key, long defaultValue) {
//        Long value = getLong(jsonNode, key);
//        if (value != null) {
//            return value;
//        }
//        return defaultValue;
//    }
//}
