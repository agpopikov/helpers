package org.helpers.common.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    private ResponseStatus status;
    private T data;
    private ResponseError error;

    public static Response ok() {
        return new Response<>(ResponseStatus.OK, null, null);
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(ResponseStatus.OK, data, null);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<>(ResponseStatus.ERROR, null, new ResponseError(message));
    }
}
