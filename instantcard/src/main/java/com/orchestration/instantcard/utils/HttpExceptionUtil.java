package com.orchestration.instantcard.utils;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Objects;
import java.util.Optional;

@Component
public class HttpExceptionUtil {
	private final Gson gson = new Gson();

	public <T> Optional<T> checkException(Exception e, Class<T> classType) {

		if (e instanceof HttpStatusCodeException httpServerError) {
			return processException(httpServerError,  classType);
		}
		return Optional.empty();
	}

	private <T> Optional<T> processException(HttpStatusCodeException httpServerError,
			Class<T> classType) {
		T objError = null;
		objError = gson.fromJson(httpServerError.getResponseBodyAsString(), classType);
		if (Objects.nonNull(objError)) {
			return Optional.ofNullable(objError);
		}
		return Optional.empty();
	}

}
