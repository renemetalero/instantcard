package com.orchestration.instantcard.service.cmc;

import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteRequest;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponse;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;

public interface CmcService {
    ValidacionTarjetaResponse validateDataCmc(InstantCardRequest request);
    CreacionClienteResponse crearClienteCmc(CreacionClienteRequest request, boolean sendError);
    CatalogRes catalogSearch(String nemonicoTable);
    CmcInstantCardResponse insertInstantCardInformation(InstantCardRequest instantCardRequest, CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData);
}
