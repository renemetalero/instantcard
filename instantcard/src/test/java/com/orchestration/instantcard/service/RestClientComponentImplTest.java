package com.orchestration.instantcard.service;

import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.LoginTokenFiservResponse;
import com.orchestration.instantcard.models.generals.LoginTokenParams;
import com.orchestration.instantcard.models.generals.RestClientWithAuthParams;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogReq;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.components.rest.RestClientComponentImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class RestClientComponentImplTest {
    @InjectMocks
    private CreatePojos create;

    @Mock
    private RestTemplate restTemplate;

    private RestClientComponentImpl<CatalogRes, CatalogReq> restClientService;

    private RestClientComponentImpl<LoginTokenFiservResponse, CreacionTarjetaFiservRequest> restTokenService;

    @BeforeEach
    void setUp() {
        this.restClientService = new RestClientComponentImpl<>(restTemplate);
        this.restTokenService = new RestClientComponentImpl<>(restTemplate);
    }

    @Test
    @DisplayName(value = "Test RestClientService ==> testSendMessage <===")
    void testSendMessage() {
        ResponseEntity<CatalogRes> catalogResResponseEntity = new ResponseEntity<>(create.createCatalogResponse(), HttpStatus.OK);
        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class)))
                .thenReturn(catalogResResponseEntity);
        CatalogReq catalogReq = create.createCatalogReq();
        CatalogRes catalogRes = this.restClientService.sendMessage(catalogReq, "catalogo", "resourceUrlSem", CatalogRes.class, HttpMethod.GET);
        Mockito.verify(restTemplate, Mockito.times(1)).exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class));
        Assertions.assertEquals(catalogResResponseEntity.getBody(), catalogRes);
    }

   @Test
   @DisplayName(value = "Test RestClientService ==> testSendMessage <===")
    void sendMessageWithAuthWithError() {
        CreacionTarjetaFiservRequest requestCreacionTarjeta = create.createCreacionTarjetaFiservRequest();
        RestClientWithAuthParams restClientWithAuthParams = new RestClientWithAuthParams("serviceId", "resourceUrlSem", HttpMethod.POST);
        Assertions.assertThrows(BusinessException.class, ()->{
            this.restTokenService.sendMessageWithAuth(requestCreacionTarjeta, LoginTokenFiservResponse.class, restClientWithAuthParams);
        });
    }

    @Test
    @DisplayName(value = "Test RestClientService ==> testGetLoginTokenRestClient <===")
    void testGetLoginTokenRestClient() {
        CreacionTarjetaFiservRequest requestCreacionTarjeta = create.createCreacionTarjetaFiservRequest();

        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class)))
                .thenReturn(new ResponseEntity<>(create.createLoginTokenFiservResponse(), HttpStatus.OK));

        RestClientWithAuthParams restClientWithAuthParams = new RestClientWithAuthParams("serviceId", "resourceUrlSem", HttpMethod.POST);
        LoginTokenParams loginTokenParams = new LoginTokenParams();
        loginTokenParams.setPassword("password");
        loginTokenParams.setUsername("username");

        LoginTokenFiservResponse loginTokenFiservResponse = this.restTokenService.getLoginTokenRestClient(
                loginTokenParams,
                restClientWithAuthParams.getResourceUrlSem(),
                requestCreacionTarjeta,
                LoginTokenFiservResponse.class);

        Assertions.assertEquals("access_token",loginTokenFiservResponse.getAccess_token());
        Assertions.assertEquals("token_type",loginTokenFiservResponse.getToken_type());
        Assertions.assertEquals("issued_at",loginTokenFiservResponse.getIssued_at());
        Assertions.assertEquals("client_id",loginTokenFiservResponse.getClient_id());
        Assertions.assertEquals("expires_in",loginTokenFiservResponse.getExpires_in());
        Assertions.assertEquals("status",loginTokenFiservResponse.getStatus());

    }



}
