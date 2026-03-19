package com.orchestration.instantcard.components;

import com.orchestration.instantcard.components.vision.CreateClientComponent;
import com.orchestration.instantcard.components.vision.CreateInstantCardComponent;
import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.ClientRequestInformationDto;
import com.orchestration.instantcard.models.generals.FieldsCardDto;
import com.orchestration.instantcard.models.generals.Header;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.request.InstantCardRequestData;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import com.orchestration.instantcard.models.response.InstantCardResponseBody;
import com.orchestration.instantcard.models.response.InstantCardResponseData;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaBodyResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaDataResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseBody;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseData;
import com.orchestration.instantcard.utils.Authorization;
import com.orchestration.instantcard.utils.Utility;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import com.orchestration.instantcard.validate.ReplaceNoASCII;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecksClassValidationsComponents {

    private Authorization authorizations;
    private CreateClientComponent createClientComponent;
    private CreateInstantCardComponent createInstantCardComponent;

    public ChecksClassValidationsComponents(Authorization authorizations, CreateClientComponent createClientComponent, CreateInstantCardComponent createInstantCardComponent) {
        this.authorizations = authorizations;
        this.createClientComponent = createClientComponent;
        this.createInstantCardComponent = createInstantCardComponent;
    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    public AccountInformationDto checksFullNameClient(ValidacionTarjetaResponse responseCmc, ConsultaIbsResponse responseDataIbs){
        AccountInformationDto.AccountInformationDtoBuilder accountInformationDtoBuilder = AccountInformationDto.builder();

        Optional<ValidacionTarjetaBodyResponse> validacionTarjetaBodyResponseOptional = Optional.ofNullable(responseCmc)
                .map(ValidacionTarjetaResponse::getData)
                .map(ValidacionTarjetaDataResponse::getBody);

        Optional<ConsultaIbsResponseBody> consultaIbsResponseBodyOptional = Optional.ofNullable(responseDataIbs)
                .map(ConsultaIbsResponse::getData)
                .map(ConsultaIbsResponseData::getBody);

        if(validacionTarjetaBodyResponseOptional.isEmpty() && consultaIbsResponseBodyOptional.isEmpty()){
            throw new BusinessException(
                    InstantCardEnumError.BUSINESS_IBS.getCode(),
                    List.of(new ErrorModel(
                            InstantCardEnumError.BUSINESS_IBS.getCode(),
                            InstantCardConstants.IBS_DATA_NOT_FOUND,
                            InstantCardConstants.IBS_DATA_NOT_FOUND
                    ))
            );
        }

        consultaIbsResponseBodyOptional.ifPresent(body -> accountInformationDtoBuilder
                .nameMagneticStripe(Utility.validateString(body.getNameMagneticStripe(),null))
                .lastNameMagneticStripe(Utility.validateString(body.getLastNameMagneticStripe(), null))
                .shortName(Utility.validateString(body.getShortName(), ""))
        );

        validacionTarjetaBodyResponseOptional.ifPresent(body -> accountInformationDtoBuilder
                        .nameMagneticStripe(Utility.validateString(body.getNameMagneticStripe(), null))
                        .lastNameMagneticStripe(Utility.validateString(body.getLastNameMagneticStripe(), null))
                        .shortName(Utility.validateString(body.getShortName(), ""))
                );

        return accountInformationDtoBuilder.build();
    }

    public ResponseEntity<InstantCardResponse> processTypeCard(InstantCardRequest request, AccountInformationDto accountInformationDto,
                                                               ConsultaIbsResponse responseIbs){

        Optional<ConsultaIbsResponseBody> ibsResponseBody = Optional.ofNullable(responseIbs)
                .map(ConsultaIbsResponse::getData)
                .map(ConsultaIbsResponseData::getBody);

        Optional<InstantCardRequestBody> instantCardRequestBody = Optional.ofNullable(request)
                .map(InstantCardRequest::getData)
                .map(InstantCardRequestData::getBody);

        Optional<String> productType = instantCardRequestBody
                .map(InstantCardRequestBody::getProductType)
                .filter(InstantCardConstants.LIST_PRODUCT_TYPE::contains);

        Optional<String> customerNumber = Optional.ofNullable(request)
                .map(InstantCardRequest::getData)
                .map(InstantCardRequestData::getBody)
                .map(InstantCardRequestBody::getCustomerNumber);

        if(productType.isPresent() && ibsResponseBody.isPresent())
            this.createClientComponent.createClient(ibsResponseBody.get(), accountInformationDto);

        ClientRequestInformationDto baseRequestInformation = new ClientRequestInformationDto();
        baseRequestInformation.setShortName(accountInformationDto.getShortName());
        baseRequestInformation.setCustomerNumber(customerNumber.orElse(""));

        InstantCardRequestBody requestBody = instantCardRequestBody
                .orElseThrow(() -> new BusinessException(
                        InstantCardConstants.INSTANT_CARD_ERROR,
                        InstantCardConstants.INSTANT_CARD_SERVICE_ERROR_MESSAGE,
                        List.of(new ErrorModel(
                                InstantCardEnumError.BUSINESS_INSTANT_CARD,
                                InstantCardConstants.INSTANT_CARD_SERVICE_ERROR_MESSAGE
                        ))
                ));

        FieldsCardDto fieldsCardDto = this.checkedClassValidationsResponses(requestBody,baseRequestInformation);
        InstantCardResponseBody instantCardResponseBody = this.createInstantCardComponent.createInstantCard(request, fieldsCardDto);

        Header header = Optional.ofNullable(request)
                .map(InstantCardRequest::getData)
                .map(InstantCardRequestData::getHeader)
                .orElse(new Header());

        return this.authorizations.getSuccessResponse(this.createInstance(instantCardResponseBody, header));
    }

    public InstantCardResponseData createInstance(InstantCardResponseBody responseBody, Header header) {
        InstantCardResponseData dataResponse = new InstantCardResponseData();
        dataResponse.setHeader(new Header((header)));
        dataResponse.setBody(responseBody);
        return dataResponse;
    }

    public FieldsCardDto checkedClassValidationsResponses(InstantCardRequestBody body, ClientRequestInformationDto clientRequestInformationDto) {
        FieldsCardDto dto = new FieldsCardDto();
        dto.setAddressLine1(ReplaceNoASCII.normalizarString(body.getCustomerAddressLine1()));
        dto.setAddressLine2(ReplaceNoASCII.normalizarString(body.getCustomerAddressLine2()));
        dto.setCity(ReplaceNoASCII.normalizarString(body.getCustomerCity()));
        dto.setProvince(ReplaceNoASCII.normalizarString(body.getCustomerState()));
        dto.setCustomerNumber(ReplaceNoASCII.normalizarString(clientRequestInformationDto.getCustomerNumber()));
        dto.setLogo(ReplaceNoASCII.normalizarString(body.getLogo().toString()));
        dto.setShortName(clientRequestInformationDto.getShortName());

        logger.info("accountnumber cmc {} " , dto.getAccountNumber());
        logger.info("customerNumber cmc {}" , dto.getCustomerNumber());
        return dto;
    }
}
