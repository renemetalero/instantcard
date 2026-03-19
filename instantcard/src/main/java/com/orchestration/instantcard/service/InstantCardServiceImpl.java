package com.orchestration.instantcard.service;

import com.orchestration.instantcard.components.ChecksClassValidationsComponents;
import com.orchestration.instantcard.components.cmc.CatalogsComponent;
import com.orchestration.instantcard.components.cmc.DataValidationComponent;
import com.orchestration.instantcard.components.ibs.DataValidationsIbsComponent;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.validate.ValidateModels;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InstantCardServiceImpl implements InstantCardService {
    private ValidateModels validateModels;
    private DataValidationComponent cmcComponent;
    private DataValidationsIbsComponent ibsComponent;
    private CatalogsComponent catalogsComponent;
    private ChecksClassValidationsComponents checkClassValidationsComponents;
    private ApiContext apiContext;

    public InstantCardServiceImpl(ValidateModels validateModels,
                                  DataValidationComponent cmcComponent,
                                  DataValidationsIbsComponent ibsComponent,
                                  CatalogsComponent catalogsComponent,
                                  ChecksClassValidationsComponents checkClassValidationsComponents,
                                  ApiContext apiContext) {
        this.validateModels = validateModels;
        this.cmcComponent = cmcComponent;
        this.ibsComponent = ibsComponent;
        this.catalogsComponent = catalogsComponent;
        this.checkClassValidationsComponents = checkClassValidationsComponents;
        this.apiContext = apiContext;
    }

    public ResponseEntity<InstantCardResponse> processInstantCard(InstantCardRequest request){

        apiContext.setContextMetadata(request.getMetadata());

        this.validateModels.validateModel(request);

        this.catalogsComponent.setValueUserCode7(request.getData().getBody());

        //aqui se llama con_validacionTarjeta
        ValidacionTarjetaResponse responseCmc = this.cmcComponent.dataValidate(request);


        ConsultaIbsResponse responseIbs = this.ibsComponent.dataValidation(responseCmc,request.getData().getHeader(),request.getData().getBody().getCustomerNumber());

        AccountInformationDto accountInformationDto = this.checkClassValidationsComponents.checksFullNameClient(responseCmc,responseIbs);

        return this.checkClassValidationsComponents.processTypeCard(request, accountInformationDto, responseIbs);

    }
}
