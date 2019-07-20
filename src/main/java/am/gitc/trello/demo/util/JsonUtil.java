package am.gitc.trello.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

  public static String getStringValue(String jsonData, String key) throws IOException {
    JsonNode jsonNode = objectMapper.readTree(jsonData).get(key);
    return jsonNode == null ? null : jsonNode.asText();
  }

  public static <T> T deserialize(String payload, TypeReference<T> type) throws IOException {
    return objectMapper.readValue(payload, type);
  }

  public static <T> T deserialize(InputStream payload, TypeReference<T> type) throws IOException {
    return objectMapper.readValue(payload, type);
  }

  public static String serialize(Object obj) {
    String value = "";
    try {
      value = objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      logger.error("ERROR:", e);
    }
    return value;
  }
}