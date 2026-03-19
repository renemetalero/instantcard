package com.orchestration.instantcard.service.vision;

import org.springframework.http.HttpMethod;

public interface FiservService<S,R> {
    S consumeFiservService(R fiservRequest, HttpMethod method, Class<S> responseClass, boolean sendError);
    void setResourceUrlFiserv(String resourceUrlFiserv);
    void setToken(String token);
}
