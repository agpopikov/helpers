package org.helpers.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.helpers.common.api.response.Response;
import org.helpers.common.api.response.ResponseError;
import org.helpers.common.api.response.ResponseStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Simple wrap around Jackson's ObjectMapper.
 */
public class JsonUtils {

    /**
     * Serializes specified value of type T to JSON.
     *
     * @param mapper - mapper to use.
     * @param object - value to serialize.
     * @param <T>    - type of value to serialize.
     * @return - string with JSON.
     */
    public static <T> String toJson(ObjectMapper mapper, T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error occurred while serializing object.", e);
        }
    }

    /**
     * Deserialize specified JSON-string to object of type T,
     * if it have any problem with deserialization - throws runtime exception.
     *
     * @param mapper - mapper to use.
     * @param value  - value to deserialize.
     * @param clazz  - class of T (mitigating Java generics).
     * @param <T>    - type of value to deserialize.
     * @return - deserialized object.
     */
    public static <T> T fromJson(ObjectMapper mapper, String value, Class<T> clazz) {
        try {
            return mapper.readValue(value, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while deserializing object.", e);
        }
    }

    public static Collection<String> fromJsonCollection(ObjectMapper mapper, String value) {
        try {
            return mapper.readValue(value, new TypeReference<Collection<String>>() {
                // stub
            });
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while deserializing object.", e);
        }
    }

    public static <T> Collection<T> fromJsonCollection(ObjectMapper mapper, String value, Class<T> clazz) {
        try {
            CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            return mapper.readValue(value, type);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while deserializing object.", e);
        }
    }

    public static <T> Response<T> fromJsonResponse(ObjectMapper mapper, String value, Class<T> clazz) {
        try {
            JsonNode root = mapper.readTree(value);
            ResponseStatus status = ResponseStatus.valueOf(root.get("status").asText());
            ResponseError error = mapper.treeToValue(root.get("error"), ResponseError.class);
            T data = mapper.treeToValue(root.get("data"), clazz);
            return new Response<>(status, data, error);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while deserializing object.", e);
        }
    }
}
