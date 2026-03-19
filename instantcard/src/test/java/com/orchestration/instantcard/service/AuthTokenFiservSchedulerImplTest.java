package com.orchestration.instantcard.service;

import com.orchestration.instantcard.service.vision.token.AuthTokenFiservSchedulerImpl;
import com.orchestration.instantcard.service.vision.token.TokenFiservServiceImpl;
import com.orchestration.instantcard.service.vision.token.TokenRestServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class AuthTokenFiservSchedulerImplTest {

    private AuthTokenFiservSchedulerImpl authTokenFiservScheduler;

    @MockBean
    private TokenFiservServiceImpl tokenFiservService;

    @MockBean
    private TokenRestServiceImpl tokenRestService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    private CreatePojos create;

    @BeforeEach
    void setUp() {
        this.tokenRestService = new TokenRestServiceImpl(restTemplate);
        this.tokenFiservService = new TokenFiservServiceImpl(tokenRestService);

        ReflectionTestUtils.setField(tokenFiservService, "urlFirservLogin", "urlInstantCardInformationCMC");
        ReflectionTestUtils.setField(tokenFiservService, "grantType", "grant_type");
        ReflectionTestUtils.setField(tokenFiservService, "password", "password");
        ReflectionTestUtils.setField(tokenFiservService, "apiKey", "api_key");

        this.authTokenFiservScheduler = new AuthTokenFiservSchedulerImpl(tokenFiservService, this.cacheManager);
    }

    @Test
    @DisplayName(value = "Test AuthTokenFiservScheduler ==> testRefreshToken <===")
    void testRefreshToken(){
        Cache cache = Mockito.mock(Cache.class);

        Mockito.when(cacheManager.getCache(Mockito.anyString())).thenReturn(cache);

        Mockito.when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.any(Class.class)))
                        .thenReturn(new ResponseEntity(create.createLoginTokenFiservResponse(), HttpStatus.OK));

        authTokenFiservScheduler.refreshToken();

        Mockito.verify(cache, Mockito.times(1)).clear();
    }

}
