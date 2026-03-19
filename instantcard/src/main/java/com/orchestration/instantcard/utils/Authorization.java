package com.orchestration.instantcard.utils;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.response.InstantCardResponseData;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Authorization {

    private ApiContext apiContext;

    public Authorization(ApiContext apiContext) {
        this.apiContext = apiContext;
    }

    public ResponseEntity<InstantCardResponse> getResponseBusinessService(Integer status, String message, List<ErrorModel> errors) {
        return ResponseEntity.status(status).body(new InstantCardResponse(status, message,
                apiContext.getMetadata().cloneInfo(), errors));
    }

    public ResponseEntity<InstantCardResponse> getResponseValidationsGenerals(HttpStatus http, String message, List<ErrorModel> errors) {
        return new ResponseEntity<>(new InstantCardResponse(http.value(), message,
                apiContext.getMetadata().cloneInfo(), errors), http);
    }

    public ResponseEntity<InstantCardResponse> getSuccessResponse(InstantCardResponseData data) {
        HttpStatus http = HttpStatus.OK;
        return new ResponseEntity<>(new InstantCardResponse(http.value(), InstantCardConstants.SUCCESS,
                apiContext.getMetadata().cloneInfo(), data), http);
    }

    public ResponseEntity<InstantCardResponse> getResponseApplicationService(HttpStatus http, String message, List<ErrorModel> errors){
        return new ResponseEntity<>(new InstantCardResponse(http.value(), message,
                apiContext.getMetadata().cloneInfo(), errors), http);
    }

}
