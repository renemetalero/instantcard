package com.orchestration.instantcard.components.ibs;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.ValidIbsResponse;
import com.orchestration.instantcard.models.generals.Header;
import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequest;
import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequestBody;
import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequestData;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaDataResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.service.ibs.IbsService;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
public class DataValidationsIbsComponent implements ValidIbsResponse {

    private IbsService<ConsultaIbsResponse, ConsultaIbsRequest> serviceIbs;
    private ApiContext apiContext;

    public DataValidationsIbsComponent(IbsService<ConsultaIbsResponse, ConsultaIbsRequest> serviceIbs, ApiContext apiContext) {
        this.serviceIbs = serviceIbs;
        this.apiContext = apiContext;
    }

    private ConsultaIbsRequest createInstance(Header header, String customerNumber) {
        ConsultaIbsRequest requestIbs = new ConsultaIbsRequest();

        requestIbs.setData(new ConsultaIbsRequestData());
        requestIbs.getData().setBody(new ConsultaIbsRequestBody());
        requestIbs.getData().getBody().setCustomerNumber(customerNumber);
        requestIbs.setMetadata(apiContext.getMetadata());

        requestIbs.getData().setHeader(header);

        return requestIbs;
    }

    public ConsultaIbsResponse dataValidation(ValidacionTarjetaResponse responseCmc, Header header, String customerNumber) {
        ConsultaIbsResponse responseIbs = null;
        Integer cmcErrorOrClientNotExists = checkCmcResponse(responseCmc);

        if(cmcErrorOrClientNotExists == 0) {
            responseIbs = serviceIbs.validateDataIbs((this.createInstance(header, customerNumber)), ConsultaIbsResponse.class);
            checkClassResponseIbs(responseIbs);
        }

        return responseIbs;
    }

    private static Integer checkCmcResponse(ValidacionTarjetaResponse responseCmc) {
        return Optional.ofNullable(responseCmc).map(ValidacionTarjetaResponse::getData)
                .map(ValidacionTarjetaDataResponse::getClientExist).orElse(0);
    }

}
