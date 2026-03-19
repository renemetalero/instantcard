package com.orchestration.instantcard.components.vision;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.orchestration.instantcard.components.gestiontarjeta.EmbosserUpdateComponent;
import com.orchestration.instantcard.converter.CustomerToProviderConverter;
import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.mapper.FiservInstantCardMapper;
import com.orchestration.instantcard.mapper.InstantCardResponseMapper;
import com.orchestration.instantcard.models.generals.FieldsCardDto;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.request.InstantCardRequestData;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.RequestFirservInstantCardDto;
import com.orchestration.instantcard.models.response.InstantCardResponseBody;
import com.orchestration.instantcard.models.response.InstantCardResponseData;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponse;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponseBody;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponseData;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.service.cmc.CmcService;
import com.orchestration.instantcard.service.customeroffer.CustomerOfferUpdateService;
import com.orchestration.instantcard.service.vision.FiservService;
import com.orchestration.instantcard.service.vision.token.AuthTokenFiservScheduler;
import com.orchestration.instantcard.service.vision.token.TokenFiservService;
import com.orchestration.instantcard.utils.LoggerObjectUtil;
import com.orchestration.instantcard.utils.Utility;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;

@Component
public class CreateInstantCardComponent {

    private FiservService<CreacionTarjetaFiservResponse,CreacionTarjetaFiservRequest> fiservService;
    private CmcService cmcService;

    @Value("${service-url.fiserv.create-instant-card}")
    private String createInstantCardFiserv;

    private TokenFiservService tokenFiservService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private AuthTokenFiservScheduler authTokenFiservScheduler;

    private EmbosserUpdateComponent embosserUpdateComponent;
    
    private CustomerOfferUpdateService customerOfferUpdateService;
    
    private CustomerToProviderConverter converter;

    public CreateInstantCardComponent(FiservService<CreacionTarjetaFiservResponse,CreacionTarjetaFiservRequest> fiservService,
                                      CmcService cmcService, 
                                      TokenFiservService tokenFiservService, 
                                      AuthTokenFiservScheduler authTokenFiservScheduler,
                                      EmbosserUpdateComponent embosserUpdateComponent,
                                      CustomerOfferUpdateService customerOfferUpdateService,
                                      CustomerToProviderConverter converter) {
        this.fiservService = fiservService;
        this.cmcService = cmcService;
        this.tokenFiservService = tokenFiservService;
        this.authTokenFiservScheduler = authTokenFiservScheduler;
        this.embosserUpdateComponent = embosserUpdateComponent;
        this.customerOfferUpdateService = customerOfferUpdateService;
        this.converter = converter; 
    }

    public InstantCardResponseBody createInstantCard(InstantCardRequest instantCardRequest, FieldsCardDto fieldsCardDto) {
        RequestFirservInstantCardDto fiservRequest = FiservInstantCardMapper.INSTANCE.mapInstantRequestBodyToFieldsCardDto(instantCardRequest.getData().getBody(), fieldsCardDto);

        String user = instantCardRequest.getData().getHeader().getUser();

        Optional<InstantCardRequestBody> instantCardRequestBodyOptional = Optional.ofNullable(instantCardRequest)
                .map(InstantCardRequest::getData)
                .map(InstantCardRequestData::getBody);

        instantCardRequestBodyOptional
                .map(InstantCardRequestBody::getCreditLimit)
                .ifPresent(value-> fiservRequest.setCreditLimitFormatted(Utility.formatCreditLimit(value, 2)));

        String dateCurrent = Utility.getLocalDateNowISO();

        CreacionTarjetaFiservRequest requestCreacionTarjeta = FiservInstantCardMapper.INSTANCE.mapToFiservRequest(fiservRequest, dateCurrent);

        instantCardRequestBodyOptional
                .map(InstantCardRequestBody::getDateOpened)
                .ifPresent(requestCreacionTarjeta.getAccountData()::setDateOpened);

        logger.info("Date Opened: {}", requestCreacionTarjeta.getAccountData().getDateOpened());

        String token = this.tokenFiservService.getAuthToken();
        if(Objects.isNull(token)){
            logger.info("El token es null se obtendrá nuevamente");
            authTokenFiservScheduler.refreshToken();
            token = this.tokenFiservService.getAuthToken();
        }
        fiservService.setToken(token);

        this.fiservService.setResourceUrlFiserv(this.createInstantCardFiserv);

        LoggerObjectUtil.print("Fiserv request", requestCreacionTarjeta);

        CreacionTarjetaFiservResponse creacionTarjetaResponse = this.fiservService.consumeFiservService(requestCreacionTarjeta, HttpMethod.POST, CreacionTarjetaFiservResponse.class, true);

        LoggerObjectUtil.print("Fiserv response", creacionTarjetaResponse);

        Optional.ofNullable(creacionTarjetaResponse)
                .filter(response -> Optional.ofNullable(response.getHasError()).orElse(false))
                .ifPresent(response -> {
                    throw new BusinessException(
                            InstantCardConstants.INSTANT_CARD_ERROR_FISERV,
                            InstantCardConstants.CREATE_INSTANT_CARD_ERROR_MESSAGE,
                            response.getErrors());
                });

        CreacionTarjetaFiservResponseData creacionTarjetaData = Optional.ofNullable(creacionTarjetaResponse)
                .map(CreacionTarjetaFiservResponse::getData)
                .orElseThrow(() -> new BusinessException(
                        InstantCardConstants.INSTANT_CARD_ERROR_FISERV,
                        InstantCardConstants.CREATE_INSTANT_CARD_ERROR_MESSAGE,
                        List.of(new ErrorModel(
                                InstantCardEnumError.BUSINESS_VISION_FISERV,
                                InstantCardConstants.INSERT_INSTANT_CARD_ERROR_SERVICE_MESSAGE
                        ))
                ));

        instantCardRequest.getData().getBody().setUserBM(user);

        // aqui se llama la CMC
        Optional<CmcInstantCardResponse> cmcInstantCardResponse =
                Optional.ofNullable(this.cmcService.insertInstantCardInformation(
                        instantCardRequest,
                        creacionTarjetaData));
        
        Optional<CmcInstantCardResponseData> cmcInstantCardResponseData = cmcInstantCardResponse
                .map(CmcInstantCardResponse::getData);

        Optional<String> codeOptional = cmcInstantCardResponseData
                .map(CmcInstantCardResponseData::getBody)
                .map(CmcInstantCardResponseBody::getCode);

        if(!codeOptional.filter(InstantCardConstants.CMC_RESPONSE_CODE_OK::equals).isPresent()){
            List<ErrorModel> errorModelList = cmcInstantCardResponse
                            .map(CmcInstantCardResponse::getErrorsList)
                            .orElseGet(() -> List.of(new ErrorModel(
                                            InstantCardEnumError.BUSINESS_VISION_FISERV,
                                            InstantCardConstants.INSERT_INSTANT_CARD_ERROR_SERVICE_MESSAGE))
                            );

            throw new BusinessException(
                    InstantCardConstants.INSTANT_CARD_ERROR_CMC,
                    InstantCardConstants.INSERT_INSTANT_CARD_ERROR_MESSAGE,
                    errorModelList);
        }

        this.embosserUpdateComponent.updateEmail(instantCardRequest, creacionTarjetaData);

        this.customerOfferUpdateService.updateOffer(
        		this.converter.createRequestOfferUpdate(instantCardRequest));
        
        InstantCardResponseBody instantCardResponseBody = InstantCardResponseMapper.INSTANCE.modelToInstantCardResponse(creacionTarjetaData, dateCurrent);
        InstantCardResponseData instantCardResponseData = new InstantCardResponseData();
        instantCardResponseData.setBody(instantCardResponseBody);

        return instantCardResponseBody;

    }

}
