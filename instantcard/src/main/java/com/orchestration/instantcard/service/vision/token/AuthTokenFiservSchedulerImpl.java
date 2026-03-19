package com.orchestration.instantcard.service.vision.token;

import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenFiservSchedulerImpl implements AuthTokenFiservScheduler{

    private final TokenFiservServiceImpl tokenFiservService;
    private final CacheManager cacheManager;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public AuthTokenFiservSchedulerImpl(TokenFiservServiceImpl tokenFiservService, CacheManager cacheManager){
        this.tokenFiservService = tokenFiservService;
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRate = 9 * 60 * 1000)
    public void refreshToken(){
        logger.info("Refrescando token de Fiserv de cada 9 minutos");
        if(cacheManager != null) {
            Cache cache = cacheManager.getCache(InstantCardConstants.FISERV_TOKEN_CACHE_NAME);
            if(cache != null){
                cache.clear();
                tokenFiservService.getAuthToken();
            }
        }
    }
}
