package com.bill.common.util;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

/**
 * json工具
 */
public class JsonUtil {
    private static final ObjectMapper snakeCaseObjectMapper =
            new ObjectMapper()
                    .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                    .configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

    private static final ObjectMapper camelCaseObjectMapper =
            new ObjectMapper()
                    .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
                    .configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

    public static ObjectMapper getSnakeCaseObjectMapper() {
        return snakeCaseObjectMapper;
    }

    public static ObjectMapper getCamelCaseObjectMapper() {
        return camelCaseObjectMapper;
    }

    public static <T> T parse(String content, Class<T> valueType, ObjectMapper objectMapper) {
        if (content == null) {
            return null;
        }
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Failed to parse to %s with json %s", valueType, content), e);
        }
    }

    public static <T> T parse(
            String content, TypeReference<T> valueTypeRef, ObjectMapper objectMapper) {
        if (content == null) {
            return null;
        }
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(
                    String.format("Failed to parse to %s with json %s", valueTypeRef, content), e);
        }
    }

    public static <T> String format(T value, ObjectMapper objectMapper) {
        // TODO (zhangrui.ryan): may not right, should not be null<null>, should be
        // java.lang.String<null>
        if (value == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to format %s to json", value), e);
        }
    }

    public static <T> T parseSnakeCase(String content, Class<T> valueType) {
        return parse(content, valueType, snakeCaseObjectMapper);
    }

    public static <T> T parseCamelCase(String content, Class<T> valueType) {
        return parse(content, valueType, camelCaseObjectMapper);
    }

    public static <T> T parseSnakeCase(String content, TypeReference<T> valueTypeRef) {
        return parse(content, valueTypeRef, snakeCaseObjectMapper);
    }

    public static <T> T parseCamelCase(String content, TypeReference<T> valueTypeRef) {
        return parse(content, valueTypeRef, camelCaseObjectMapper);
    }

    public static <T> String formatSnakeCase(T value) {
        return format(value, snakeCaseObjectMapper);
    }

    public static <T> String formatCamelCase(T value) {
        return format(value, camelCaseObjectMapper);
    }
}
