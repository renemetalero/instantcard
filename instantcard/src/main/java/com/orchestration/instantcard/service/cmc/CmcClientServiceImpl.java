package com.orchestration.instantcard.service.cmc;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponse;
import com.orchestration.instantcard.components.rest.RestClientComponentImpl;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import com.orchestration.instantcard.utils.HttpExceptionUtil;
import com.orchestration.instantcard.utils.Utility;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;


@Service
public class CmcClientServiceImpl<S,R> extends RestClientComponentImpl<S, R> implements CmcClientService<S, R> {

    @Getter
    @Setter
    private String resourceUrlCmc;

    private HttpExceptionUtil httpExceptionUtil;

    private static final String CODE_ERR = InstantCardEnumError.BUSINESS_CMC.getCode();

    private Logger logger = LoggerFactory.getLogger(getClass());

    public CmcClientServiceImpl(RestTemplate restTemplate, HttpExceptionUtil httpExceptionUtil) {
        super(restTemplate);
        this.httpExceptionUtil = httpExceptionUtil;
    }

    public S comsumeCmcService(R cmcReq, HttpMethod method, Class<S> responseClass, boolean sendError) {
        logger.info("resourceUrlCmc{} ", resourceUrlCmc);

        String serviceId = InstantCardEnumError.BUSINESS_CMC.getTitle();
        S cmcRes = null;
        try {
            cmcRes = responseClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException |NoSuchMethodException ex) {
            throw new BusinessException(CODE_ERR, Utility.buildCustomErrors (InstantCardEnumError.BUSINESS_CMC, ex.getMessage()));
        }
        try {
            cmcRes = this.sendMessage(cmcReq, serviceId, resourceUrlCmc, responseClass, method);
        } catch (Exception ex) {
            Optional<CreacionClienteResponse> responseError = httpExceptionUtil.checkException(ex, CreacionClienteResponse.class);
            logger.error("Error  {}: {}", serviceId, ex.getMessage() + " - Envio de error : " + sendError);
            if(sendError) {
                validateError(responseError,ex);
            }
        }
        return responseClass.cast(cmcRes); //  (S)
    }

    private void validateError(Optional<CreacionClienteResponse> responseError, Exception ex) {
        if (responseError.isPresent() ) {
            Optional<List<ErrorModel>> repcmc=responseError.get().getErrors();
            if(repcmc.isPresent()) {
                boolean isError = true;
                for(ErrorModel value : repcmc.get()){
                    if(InstantCardConstants.LIST_NOT_ERROR_CMC.contains(value.getCode())
                            || InstantCardConstants.LIST_NOT_ERROR_CMC.contains(value.getDetail())){
                        isError = false;
                        break;
                    }
                }

                if(isError)
                    throw new BusinessException(CODE_ERR, repcmc.get());
            }
        }else{
            throw new BusinessException(CODE_ERR, Utility.buildCustomErrors (InstantCardEnumError.BUSINESS_CMC, ex.getMessage()));
        }
    }

}
