package com.orchestration.instantcard.service.vision.token;

import com.orchestration.instantcard.models.generals.TokenRestInformation;

import java.util.Optional;

public interface TokenRestService <S,R>{
    Optional<S> getLoginToken(TokenRestInformation tokenInformation, R tokenRequest, Class<S> responseClass, boolean sendError);

}
