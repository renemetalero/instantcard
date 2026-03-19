package com.orchestration.instantcard.exception;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponse;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ValidIbsResponse {
    Logger logger = LoggerFactory.getLogger(ValidIbsResponse.class);

    default void checkClassResponseVisionClient(CreacionClienteVisionResponse clientVisionResponse) {
        String codigo = "";
        try {
            codigo = clientVisionResponse.getErrors().get(0).getCode();
            if(!InstantCardConstants.EXIST_CLIENT_VP.equals(codigo)) {
                UtilThrow.throwExc(InstantCardEnumError.BUSINESS_VISION, clientVisionResponse);
            }
        }catch (NullPointerException e){
            logger.info("No se encontró código de error VPL5SNA24S al crear cliente");
        }
    }

    default void checkClassResponseIbs(ConsultaIbsResponse consultaIbsResponse) {
        UtilThrow.throwExc(InstantCardEnumError.BUSINESS_IBS, consultaIbsResponse);
    }
}
