package com.orchestration.instantcard.components.cmc;


import com.orchestration.instantcard.exception.ValidIbsResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.service.cmc.CmcService;
import org.springframework.stereotype.Component;

@Component
public class DataValidationComponent implements ValidIbsResponse {

    private CmcService serviceCmc;
    public DataValidationComponent( CmcService serviceCmc){
        this.serviceCmc = serviceCmc;
    }
    public ValidacionTarjetaResponse dataValidate(InstantCardRequest request){
        return this.serviceCmc.validateDataCmc(request);
    }


}
