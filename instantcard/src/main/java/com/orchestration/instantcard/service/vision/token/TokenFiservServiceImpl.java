package com.orchestration.instantcard.service.vision.token;

import com.orchestration.instantcard.models.generals.LoginTokenFiservResponse;
import com.orchestration.instantcard.models.generals.TokenRestInformation;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Optional;

@Service
public class TokenFiservServiceImpl implements TokenFiservService{

    @Value("${service-url.fiserv.login}")
    private String urlFirservLogin;
    @Value("${fiserv-login.grant-type}")
    private String grantType;

    @Value("${Secret-firstdata}")
    private String password;

    @Value("${Apikey-firstdata}")
    private String apiKey;

    private final TokenRestServiceImpl<LoginTokenFiservResponse,MultiValueMap<String, String>> tokenRestService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public TokenFiservServiceImpl(TokenRestServiceImpl<LoginTokenFiservResponse,MultiValueMap<String, String>> tokenRestService){
        this.tokenRestService = tokenRestService;
    }

    @Cacheable(value = InstantCardConstants.FISERV_TOKEN_CACHE_NAME)
    public String getAuthToken() {
        TokenRestInformation tokenRestInformation = new TokenRestInformation(this.urlFirservLogin,this.apiKey,this.password);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", this.grantType);
        logger.info("Obteniendo token...");
        Optional<LoginTokenFiservResponse> fiservToken = this.tokenRestService.getLoginToken(tokenRestInformation, body, LoginTokenFiservResponse.class,false);
        return fiservToken
            .map(LoginTokenFiservResponse::getAccess_token)
            .orElse(null);
    }
}
