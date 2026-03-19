package com.orchestration.instantcard.service.vision;


import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.RestClientWithAuthParams;
import com.orchestration.instantcard.components.rest.RestClientComponentImpl;
import com.orchestration.instantcard.utils.Utility;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;


@Service
public class FiservServiceImpl<S,R> extends RestClientComponentImpl<S, R> implements FiservService<S,R> {

    private String resourceUrlFiserv;

    private static final String CODE_ERR = InstantCardEnumError.BUSINESS_VISION.getCode();

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${fiserv-login.grant-type}")
    private String grantType;

    @Value("${Secret-firstdata}")
    private String password;

    @Value("${Apikey-firstdata}")
    private String apiKey;

    @Value("${service-url.fiserv.login}")
    private String urlFirservLogin;

    public FiservServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public S consumeFiservService(R fiservRequest, HttpMethod method, Class<S> responseClass, boolean sendError) {
        logger.info("resourceUrlFiserv{}", resourceUrlFiserv);

        String serviceId = InstantCardEnumError.BUSINESS_VISION_FISERV.getTitle();
        S fiservResponse = null;

        try {
            fiservResponse = responseClass.getDeclaredConstructor().newInstance();
        }catch(InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            throw new BusinessException(CODE_ERR, Utility.buildCustomErrors(InstantCardEnumError.BUSINESS_VISION_FISERV, ex.toString()));
        }

        try {
            RestClientWithAuthParams restClientWithAuthParams = new RestClientWithAuthParams(serviceId, resourceUrlFiserv, method);
            this.setApiKey(this.apiKey);
            fiservResponse = this.sendMessageWithAuth(fiservRequest, responseClass, restClientWithAuthParams);
        } catch (Exception ex) {
            logger.info("Error {}: {} - Envio de error: {}", serviceId, ex, sendError);
            if (sendError) {
                throw new BusinessException(CODE_ERR, Utility.buildCustomErrors(InstantCardEnumError.BUSINESS_VISION_FISERV, ex.getMessage().replaceAll(InstantCardConstants.FISERV_ERROR_500_MSG,"")));
            }
        }
        return responseClass.cast(fiservResponse);
    }

    public void setResourceUrlFiserv(String resourceUrlFiserv) {
        this.resourceUrlFiserv = resourceUrlFiserv;
    }
}
