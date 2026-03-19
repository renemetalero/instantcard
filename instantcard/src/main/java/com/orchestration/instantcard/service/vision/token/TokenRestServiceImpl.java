package com.orchestration.instantcard.service.vision.token;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.LoginTokenParams;
import com.orchestration.instantcard.models.generals.TokenRestInformation;
import com.orchestration.instantcard.components.rest.RestClientComponentImpl;
import com.orchestration.instantcard.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class TokenRestServiceImpl<S,R> extends RestClientComponentImpl<S,R> implements TokenRestService<S,R> {

    public TokenRestServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    private static final String CODE_ERR = InstantCardEnumError.BUSINESS_FISERV_TOKEN.getCode();

    private Logger logger = LoggerFactory.getLogger(getClass());

    public Optional<S> getLoginToken(TokenRestInformation tokenInformation, R tokenRequest, Class<S> responseClass, boolean sendError){

        S tokenResponse = null;

        try {
            tokenResponse = responseClass.getDeclaredConstructor().newInstance();
        }catch(InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            throw new BusinessException(CODE_ERR, Utility.buildCustomErrors(InstantCardEnumError.BUSINESS_FISERV_TOKEN, ex.toString()));
        }

        try {
            LoginTokenParams loginTokenParams = new LoginTokenParams();
            loginTokenParams.setPassword(tokenInformation.getPassword());
            loginTokenParams.setUsername(tokenInformation.getApiKey());
            this.setApiKey(tokenInformation.getApiKey());
            tokenResponse = this.getLoginTokenRestClient(loginTokenParams, tokenInformation.getUrl(), tokenRequest, responseClass);
        } catch (Exception ex) {
            logger.info("Error en Obtener el token: {} - Envio de error: {}", ex, sendError);
            if (sendError) {
                throw new BusinessException(CODE_ERR, Utility.buildCustomErrors(InstantCardEnumError.BUSINESS_FISERV_TOKEN, ex.getMessage()));
            }
        }

        return Optional.ofNullable(responseClass.cast(tokenResponse));
    }

}
