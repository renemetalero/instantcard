package com.orchestration.instantcard.components.rest;

import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.LoginTokenParams;
import com.orchestration.instantcard.models.generals.RestClientWithAuthParams;
import org.springframework.http.HttpMethod;

public interface RestClientComponent<T,R> {
    T sendMessage(R request, String serviceId,String resourceUrlSem,Class<T> responseObj,HttpMethod httpMethod);
    T sendMessageWithAuth(R requestObj, Class<T> responseType, RestClientWithAuthParams restClientWithAuthParams) throws BusinessException;
    T getLoginTokenRestClient(LoginTokenParams loginTokenParams, String resourceUrlSem, R requestObj, Class<T> responseType);

}
