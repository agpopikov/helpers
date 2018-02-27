package org.helpers.common.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseError {

    @JsonProperty("message")
    private final String message;
    @JsonProperty("localized_message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String localizedMessage;
    @JsonProperty("code")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String code;

    public ResponseError(String message) {
        this(message, message, null);
    }
}
