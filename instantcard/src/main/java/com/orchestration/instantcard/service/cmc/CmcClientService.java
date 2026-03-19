package com.orchestration.instantcard.service.cmc;

import org.springframework.http.HttpMethod;

public interface CmcClientService<S, R> {
    S comsumeCmcService(R cmcReq, HttpMethod method, Class<S> responseClass, boolean sendError);
    void setResourceUrlCmc(String resourceUrlCmc);
}
