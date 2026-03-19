package com.orchestration.instantcard.service;


import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.generals.LoginTokenFiservResponse;
import com.orchestration.instantcard.models.generals.TokenRestInformation;
import com.orchestration.instantcard.service.vision.token.TokenRestServiceImpl;
import com.orchestration.instantcard.utils.ResponseExceptionMockModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenRestServiceTest {

    private TokenRestServiceImpl<ResponseExceptionMockModel, Object> tokenRestService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TokenRestServiceImpl<LoginTokenFiservResponse,MultiValueMap<String, String>> tokenRestFiservService;

    @BeforeEach
    void setUp() {
        this.tokenRestService = new TokenRestServiceImpl(restTemplate);
        this.tokenRestFiservService = new TokenRestServiceImpl<>(restTemplate);
    }

    @Test
    @DisplayName(value = "Test TokenRestService ==> testGetLoginExceptionToken <===")
    void testGetLoginExceptionToken() {
        TokenRestInformation tokenRestInformation = new TokenRestInformation("urlFirservLogin","apiKey","password");

        Class<ResponseExceptionMockModel> responseClass = ResponseExceptionMockModel.class;

        Object object = new Object();

        BusinessException thrownException = assertThrows(BusinessException.class,
                ()-> tokenRestService.getLoginToken(tokenRestInformation, object, responseClass,true));

        Assertions.assertEquals(InstantCardEnumError.BUSINESS_FISERV_TOKEN.getCode(), thrownException.getCode());
    }

    @Test
    @DisplayName(value = "Test TokenRestService ==> testGetLoginTokenRestClient <===")
    void testGetLoginTokenRestClient() {
        TokenRestInformation tokenRestInformation = new TokenRestInformation("urlFirservLogin","apiKey","password");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "grantType");

        BusinessException thrownException = assertThrows(BusinessException.class,
                ()-> tokenRestFiservService.getLoginToken(tokenRestInformation, body, LoginTokenFiservResponse.class,true));

        Assertions.assertEquals(InstantCardEnumError.BUSINESS_FISERV_TOKEN.getCode(), thrownException.getCode());
    }
}
