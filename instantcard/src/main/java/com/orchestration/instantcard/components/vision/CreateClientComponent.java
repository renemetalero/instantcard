package com.orchestration.instantcard.components.vision;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.ValidIbsResponse;
import com.orchestration.instantcard.mapper.ClientMapper;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteBody;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteData;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteRequest;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequest;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequestBody;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequestData;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseBody;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponse;
import com.orchestration.instantcard.service.cmc.CmcService;
import com.orchestration.instantcard.service.vision.VisionService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import java.util.Optional;

@Component
@RequestScope
public class CreateClientComponent implements ValidIbsResponse {

    private VisionService<CreacionClienteVisionResponse, CreacionClienteVisionRequest> visionService;
    private CmcService cmcService;
    private ApiContext apiContext;

    public CreateClientComponent(VisionService<CreacionClienteVisionResponse, CreacionClienteVisionRequest> visionService, CmcService cmcService, ApiContext apiContext) {
        this.visionService = visionService;
        this.cmcService = cmcService;
        this.apiContext = apiContext;
    }

    public void createClient(ConsultaIbsResponseBody responseBodyIbs, AccountInformationDto accountInformationDto){

        Optional<CreacionClienteVisionRequest> creacionClienteVisionRequest = Optional.ofNullable(responseBodyIbs)
                .map(responseBody -> ClientMapper.INSTANCE.modelToVisionRequest(responseBody, accountInformationDto))
                .map(this::createInstanceClient);

        Optional<CreacionClienteVisionResponse> responseVisionCreacionCliente = creacionClienteVisionRequest.map(
                value -> visionService.consumeVisionCustomerAdd(value, CreacionClienteVisionResponse.class)
        );

        checkClassResponseVisionClient(responseVisionCreacionCliente.orElse(null));

        if(responseVisionCreacionCliente.isPresent()) {
            CreacionClienteRequest cmcRequest = this.createInstanceClient(ClientMapper.INSTANCE.modelToClienteBodyDto(responseBodyIbs));
            this.cmcService.crearClienteCmc(cmcRequest, true);
        }

    }

    public CreacionClienteVisionRequest createInstanceClient(CreacionClienteVisionRequestBody body){
        CreacionClienteVisionRequest request = new CreacionClienteVisionRequest();
        request.setMetadata(apiContext.getMetadata());
        request.setData(new CreacionClienteVisionRequestData());
        request.getData().setBody(body);
        return request;
    }

    private CreacionClienteRequest createInstanceClient(CreacionClienteBody body){
        CreacionClienteRequest request = new CreacionClienteRequest();
        request.setMetadata(apiContext.getMetadata());
        request.setData(new CreacionClienteData());
        request.getData().setBody(body);
        return request;
    }

}
