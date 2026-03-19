package com.orchestration.instantcard.components.rest;


import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.LoginTokenParams;
import com.orchestration.instantcard.models.generals.RestClientWithAuthParams;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
public class RestClientComponentImpl<T, R> implements RestClientComponent<T, R> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private String token;
	private String apiKey;


	private RestTemplate restTemplate;

	public RestClientComponentImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public T sendMessage(R requestObj, String serviceId, String resourceUrlSem, Class<T> responseType, HttpMethod httpMethod) {
		logger.info("RestClient Enviando mensaje type request{}, serviceid{}", requestObj.getClass().getSimpleName(), serviceId);
		T insReplyRestClient = null;
		logger.info("RestClientRequest.{}", serviceId);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<R> request = new HttpEntity<>(requestObj, headers);
		ResponseEntity<T> response = this.restTemplate.exchange(resourceUrlSem, httpMethod, request, responseType);
		insReplyRestClient = response.getBody();
		return insReplyRestClient;
	}

	@Override
	public T sendMessageWithAuth(R requestObj, Class<T> responseType, RestClientWithAuthParams restClientWithAuthParams) throws BusinessException {
		logger.info("RestClient Enviando mensaje type request{}, serviceid{}", requestObj.getClass().getSimpleName(), restClientWithAuthParams.getServiceId());
		T insReplyRestClient = null;
		logger.info("RestClientRequest.{}", restClientWithAuthParams.getServiceId());

		if(Objects.isNull(this.token)) {
			throw new BusinessException(
					InstantCardConstants.INSTANT_CARD_ERROR_FISERV,
					InstantCardConstants.TOKEN_FISERV_ERROR_MESSAGE,
					List.of(new ErrorModel(
							InstantCardEnumError.BUSINESS_INSTANT_CARD,
							InstantCardConstants.TOKEN_FISERV_ERROR_MESSAGE
					))
			);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set("apikey",this.apiKey);
		headers.set("X-ClientID", InstantCardConstants.X_CLIENTID);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization","Bearer "+this.token);

		HttpEntity<R> request = new HttpEntity<>(requestObj, headers);

		ResponseEntity<T> response = this.restTemplate.exchange(
				restClientWithAuthParams.getResourceUrlSem(),
				restClientWithAuthParams.getHttpMethod(),
				request,
				responseType);

		insReplyRestClient = response.getBody();

		return insReplyRestClient;
	}

	@Override
	public T getLoginTokenRestClient(LoginTokenParams loginTokenParams, String resourceUrlSem, R requestObj, Class<T> responseType) {
		restTemplate.setInterceptors(List.of(new BasicAuthenticationInterceptor(loginTokenParams.getUsername(), loginTokenParams.getPassword())));
		T tokenResponse = null;

		logger.info("RestClientRequest. obteniendo Token");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		headers.set("apikey", this.apiKey);
		HttpEntity<R> entity = new HttpEntity<>(requestObj, headers);
		ResponseEntity<T> response = restTemplate.exchange(resourceUrlSem, HttpMethod.POST, entity, responseType);
		tokenResponse = response.getBody();
		return tokenResponse;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
