package ba.com.kengur.util;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

public class ResponseConverter {

    private static ObjectMapper mapperInstance;

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    private ResponseConverter() {
        super();
    }

    public static <T> T readValue(String content, Class<T> valueType) throws IOException {
        if (mapperInstance == null) {
            mapperInstance = new IpxObjectMapper();
        }
        return mapperInstance.readValue(content, valueType);
    }

    private static ObjectMapper getObjectMapperInstance() {
        if (mapperInstance == null) {
            mapperInstance = new IpxObjectMapper();
        }
        return mapperInstance;
    }

    private static boolean isValidDate(String value) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDateTime.parse(value.trim(), formatter);
            return true;
        } catch (DateTimeParseException pe) {
            return false;
        }
    }

    private static boolean isNumeric(String value) {
        return value.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isTimeZone(String propertyValue) {
        return propertyValue != null && propertyValue.startsWith("tz(");
    }

    private static class IpxObjectMapper extends ObjectMapper {

        private static final long serialVersionUID = 1L;
        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

        private IpxObjectMapper() {
            super();

            this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            this.setSerializationInclusion(Include.NON_NULL);

            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(new LocalDateTimeSerializer(FORMATTER));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(FORMATTER));

            this.registerModule(javaTimeModule);
        }
    }

    public static String convertToJson(final String message) {
        try {
            ObjectNode objectNode = readNode(message);
            return getObjectMapperInstance().writeValueAsString(objectNode);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static ObjectNode readNode(String message) {
        ObjectNode objectNode = getObjectMapperInstance().createObjectNode();
        boolean readingName = true;
        boolean readingValue = false;
        boolean readingStreamOpened = false;
        int nameBegin = 0;
        int nameEnd = 0;
        int valueBegin = 0;
        int valueEnd = 0;
        String propertyName = "";
        String propertyValue = "";

        // 0-undefined
        // 1-simple without quotation marks
        // 2-string with quotation marks
        // 3-complex
        int valueType = 0;
        for (int i = 0; i < message.length(); i++) {
            if (readingName && message.charAt(i) == '=') {
                readingName = false;
                readingValue = true;
                nameEnd = i;
                if (message.charAt(i + 1) == '"' || message.charAt(i + 1) == '\'') {
                    valueType = 2;
                } else {
                    valueType = 1;
                }
                valueBegin = i + 1;
                propertyName = message.substring(nameBegin, nameEnd);
            } else if (readingValue && valueType == 1 && (message.charAt(i) == ' ' || i + 1 == message.length())) {
                readingValue = false;
                if (i + 1 == message.length()) {
                    valueEnd = i + 1;
                } else {
                    valueEnd = i;
                }
                propertyValue = message.substring(valueBegin, valueEnd);
                addToNode(objectNode, propertyName, propertyValue, valueType);
                valueType = 0;
            } else if (readingValue && valueType == 2
                    && (message.charAt(i) == '"' || message.charAt(i) == '\'' || i + 1 == message.length())) {
                if (!readingStreamOpened) {
                    readingStreamOpened = true;
                } else {
                    readingValue = false;
                    valueEnd = i + 1;
                    propertyValue = message.substring(valueBegin, valueEnd);
                    addToNode(objectNode, propertyName, propertyValue, valueType);
                    valueType = 0;
                    readingStreamOpened = false;
                }
            } else if (!readingName && !readingValue && valueType != 3 && message.charAt(i) != ' ') {
                readingName = true;
                nameBegin = i;
            } else if (readingName && message.charAt(i) == ' ') {
                readingName = false;
                nameEnd = i;
                valueType = 3;
                propertyName = message.substring(nameBegin, nameEnd);
            } else if (!readingValue && valueType == 3 && message.charAt(i) == '{') {
                readingValue = true;
                valueBegin = i + 1;
            } else if (readingValue && valueType == 3 && message.charAt(i) == '}') {
                readingValue = false;
                valueType = 0;
                valueEnd = i;
                propertyValue = message.substring(valueBegin, valueEnd);
                ObjectNode tmpNode = readNode(propertyValue);
                objectNode.putPOJO(propertyName, tmpNode);
            }
        }

        return objectNode;
    }

    private static void addToNode(ObjectNode objectNode, String propertyName, String propertyValue, int valueType) {
        if (valueType == 2) {
            objectNode.put(propertyName, propertyValue.substring(1, propertyValue.length() - 1));
        } else if (valueType == 1) {
            if (propertyValue.contains("..")) {
                String[] values = propertyValue.split("\\.\\.");

                if (values.length == 2) {
                    ObjectNode tmp = getObjectMapperInstance().createObjectNode();
                    parsetTypeAddToNode(tmp, "from", values[0]);
                    parsetTypeAddToNode(tmp, "to", values[1]);
                    objectNode.putPOJO(propertyName, tmp);
                }
            } else {
                parsetTypeAddToNode(objectNode, propertyName, propertyValue);
            }
        }
    }

    private static void parsetTypeAddToNode(ObjectNode objectNode, String propertyName, String propertyValue) {
        if (isValidDate(propertyValue)) {
            objectNode.put(propertyName, propertyValue);
        } else if (isNumeric(propertyValue)) {
            objectNode.put(propertyName, Long.parseLong(propertyValue));
        } else if (isTimeZone(propertyValue)) {
            objectNode.put(propertyName, propertyValue);
        }
    }

}
