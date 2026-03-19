package com.orchestration.instantcard.components.gestiontarjeta;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.mapper.EmbosserUpdateMapper;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequest;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequestBody;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequestData;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.service.gestiontarjeta.EmbosserUpdateService;
import com.orchestration.instantcard.utils.LoggerObjectUtil;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;

@Component
@RequestScope
public class EmbosserUpdateComponent {

    @Value("${embosser-update-url}")
    private String embosserUpdateUrl;

    private EmbosserUpdateService embosserUpdateService;
    private ApiContext apiContext;

    public EmbosserUpdateComponent(EmbosserUpdateService embosserUpdateService, ApiContext apiContext) {
        this.embosserUpdateService = embosserUpdateService;
        this.apiContext = apiContext;
    }

    public void updateEmail(InstantCardRequest instantCardRequest, CreacionTarjetaFiservResponseData creacionTarjetaData){
        EmbosserUpdateRequest embosserUpdateRequest = this.createRequest(instantCardRequest, creacionTarjetaData);
        String cardNumber = Optional.ofNullable(creacionTarjetaData)
                .map(CreacionTarjetaFiservResponseData::getCardNumber)
                .orElse(InstantCardConstants.CARD_NOT_FOUND);
        this.embosserUpdateService.setResourceUrlEmbosserUpdate(this.embosserUpdateUrl);
        this.embosserUpdateService.updateEmail(embosserUpdateRequest)
                .subscribe(response -> LoggerObjectUtil.print(String.format("EmbosserUpdate Response %s",cardNumber), response));
    }

    private EmbosserUpdateRequest createRequest(InstantCardRequest instantCardRequest, CreacionTarjetaFiservResponseData creacionTarjetaData){

        EmbosserUpdateRequestBody embosserUpdateRequestBody = EmbosserUpdateMapper.INSTANCE.mapModelToEmbosserUpdateRequestBody(
                instantCardRequest.getData().getBody(),
                creacionTarjetaData);

        EmbosserUpdateRequestData embosserUpdateRequestData = new EmbosserUpdateRequestData();
        embosserUpdateRequestData.setHeader(instantCardRequest.getData().getHeader());
        embosserUpdateRequestData.setBody(embosserUpdateRequestBody);
        EmbosserUpdateRequest request = new EmbosserUpdateRequest();
        request.setMetadata(apiContext.getMetadata());
        request.setData(embosserUpdateRequestData);
        return request;
    }
}
