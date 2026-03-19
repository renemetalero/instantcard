package com.orchestration.instantcard.service;



import com.orchestration.instantcard.components.ChecksClassValidationsComponents;
import com.orchestration.instantcard.components.cmc.CatalogsComponent;
import com.orchestration.instantcard.components.cmc.DataValidationComponent;
import com.orchestration.instantcard.components.ibs.DataValidationsIbsComponent;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.models.generals.Header;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogReq;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponse;
import com.orchestration.instantcard.service.cmc.CmcClientServiceImpl;
import com.orchestration.instantcard.service.vision.FiservServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import com.orchestration.instantcard.validate.ValidateModels;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class InstantCardServiceImplTest {

    private InstantCardServiceImpl instantCardService;

    @InjectMocks
    private CreatePojos create;

    @MockBean
    private CmcClientServiceImpl<CatalogRes, CatalogReq> cmcCatalogoService;

    @MockBean
    private CmcClientServiceImpl<ValidacionTarjetaResponse, InstantCardRequest> cmcValidationTarjeta;

    @MockBean
    private CmcClientServiceImpl<CreacionClienteResponse, CreacionClienteRequest> cmcCreacionCliente;

    @MockBean
    private FiservServiceImpl<CreacionTarjetaFiservResponse, CreacionTarjetaFiservRequest> fiservService;

    @Mock
    private ValidateModels validateModels;
    @Mock
    private DataValidationComponent cmcComponent;
    @Mock
    private DataValidationsIbsComponent ibsComponent;
    @Mock
    private CatalogsComponent catalogsComponent;
    @Mock
    private ChecksClassValidationsComponents checkClassValidationsComponents;
    @Mock
    private ApiContext apiContext;

    @BeforeEach
    void setUp() {
        instantCardService = new InstantCardServiceImpl(
                validateModels,
                cmcComponent,
                ibsComponent,
                catalogsComponent,
                checkClassValidationsComponents,
                apiContext);
    }

    @Test
    void testInstantCardCreationProcess() {

        ConsultaIbsResponse validateDataIbsResponse = create.createValidateDataIbsResponse();

        ValidacionTarjetaResponse validacionTarjetaResponse = create.createValidacionTarjetaDataResponse();
        Mockito.when(this.cmcComponent.dataValidate(Mockito.any(InstantCardRequest.class)))
                .thenReturn(validacionTarjetaResponse);

        Mockito.when(this.ibsComponent.dataValidation(Mockito.any(ValidacionTarjetaResponse.class),Mockito.any(Header.class), Mockito.anyString()))
                .thenReturn(validateDataIbsResponse);

        AccountInformationDto accountInformationDto = create.createAccountInformationDto();
        Mockito.when(this.checkClassValidationsComponents.checksFullNameClient(Mockito.any(ValidacionTarjetaResponse.class), Mockito.any(ConsultaIbsResponse.class)))
                .thenReturn(accountInformationDto);

        ResponseEntity<InstantCardResponse> responseEntity = new ResponseEntity<>(new InstantCardResponse(), HttpStatus.OK);
        Mockito.when(this.checkClassValidationsComponents.processTypeCard(Mockito.any(InstantCardRequest.class), Mockito.any(AccountInformationDto.class),Mockito.any(ConsultaIbsResponse.class)))
                .thenReturn(responseEntity);

        this.instantCardService.processInstantCard(create.createInstantCard());

        Mockito.verify(this.checkClassValidationsComponents, Mockito.times(1)).processTypeCard(Mockito.any(), Mockito.any(), Mockito.any());

    }
}
