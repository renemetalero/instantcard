package com.orchestration.instantcard.controladvice;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.ApplicationException;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ValidationsException;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import com.orchestration.instantcard.utils.Authorization;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;

import com.orchestration.instantcard.utils.constants.LogLevelConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


@RestControllerAdvice
public class RestExceptionHandler {


    private final Logger loggerRep = LoggerFactory.getLogger(getClass());

    private Authorization authorization;

    private static final String TRACE_ID_HEADER = "X-Trace-Id";

    public RestExceptionHandler(Authorization authorization) {
        this.authorization = authorization;
    }

    private String generateLogTrace(String typeException, String message) {
        String traceId = UUID.randomUUID().toString();
        loggerRep.info("[{}] - Trace ID: {} - {} - Message: {}", LogLevelConstants.CRITICAL.getText(), traceId, typeException, message);
        return traceId;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<InstantCardResponse> handleBusinesException(BusinessException bex) {

        String traceId = this.generateLogTrace("RestExceptionHandler.handleBusinesException", bex.getMessage());

        InstantCardEnumError enums = InstantCardEnumError.findMessageByCode(bex.getCode().toString());

        ResponseEntity<InstantCardResponse> responseEntity = authorization.getResponseBusinessService(
                Integer.parseInt(enums.getCode()),
                bex.getMessage().isEmpty() ? enums.getTitle() : bex.getMessage(),
                bex.getErrors());

        InstantCardResponse response = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.add(TRACE_ID_HEADER, traceId);

        return new ResponseEntity<>(response, headers, Integer.parseInt(enums.getCode()));
    }

    @ExceptionHandler(ValidationsException.class)
    public ResponseEntity<InstantCardResponse> handleValidationException(ValidationsException vex) {

        String traceId = this.generateLogTrace("RestExceptionHandler.handleValidationException", vex.getMessage());

        ResponseEntity<InstantCardResponse> responseEntity = authorization.getResponseValidationsGenerals(
                HttpStatus.BAD_REQUEST,
                vex.getTitle().isEmpty()
                        ? InstantCardConstants.DATA_VALIDATE
                        : vex.getTitle(),
                vex.getErrorsModel());

        InstantCardResponse response = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.add(TRACE_ID_HEADER, traceId);

        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<InstantCardResponse> handleApplicationException(ApplicationException aex){

        String traceId = this.generateLogTrace("RestExceptionHandler.handleApplicationException", aex.getMessage());

        ResponseEntity<InstantCardResponse> responseEntity = authorization.getResponseApplicationService(HttpStatus.INTERNAL_SERVER_ERROR, aex.getMessage(), aex.getErrors());
        InstantCardResponse response = responseEntity.getBody();

        HttpHeaders headers = new HttpHeaders();
        headers.add(TRACE_ID_HEADER, traceId);

        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }
}
