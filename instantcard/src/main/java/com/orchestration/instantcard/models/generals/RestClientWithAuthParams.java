package com.orchestration.instantcard.models.generals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpMethod;

@Getter
@AllArgsConstructor
public class RestClientWithAuthParams {
    private String serviceId;
    private String resourceUrlSem;
    private HttpMethod httpMethod;
}
