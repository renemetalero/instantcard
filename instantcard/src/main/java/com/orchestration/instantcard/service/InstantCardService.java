package com.orchestration.instantcard.service;

import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import org.springframework.http.ResponseEntity;

public interface InstantCardService {
    ResponseEntity<InstantCardResponse> processInstantCard(InstantCardRequest request);

}
