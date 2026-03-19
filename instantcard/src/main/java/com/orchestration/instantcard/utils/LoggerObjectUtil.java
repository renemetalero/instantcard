package com.orchestration.instantcard.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestration.instantcard.utils.constants.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoggerObjectUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    private LoggerObjectUtil() {
        throw new IllegalStateException(CommonConstants.ILLEGAL_STATE_EXCEPTION_MSG);
    }

    private static final Logger logger = LoggerFactory.getLogger(LoggerObjectUtil.class);
    public static String print(String title,Object object) {
        String value = CommonConstants.EMPTY_STRING;
        try {
            value = objectMapper.writeValueAsString(object);
            logger.info("[LoggerObjectUtil inicio] {}", title);
            logger.info(value);
            logger.info("[LoggerObjectUtil fin] {}", title);
        }catch(JsonProcessingException jsonEx) {
            logger.error("Error LoggerObjectUtil: {}", jsonEx.getMessage());
        }
        return value;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        LoggerObjectUtil.objectMapper = objectMapper;
    }

}
