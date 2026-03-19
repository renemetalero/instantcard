package com.orchestration.instantcard.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.utils.constants.CommonConstants;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@ExtendWith(MockitoExtension.class)
class LoggerObjectUtilTest {

    @InjectMocks
    private CreatePojos create;

    @Mock
    private ObjectMapper objectMapperMock;

    @Test
    @DisplayName(value = "LoggerObjectUtil ===> testPrint <===")
    void testPrint() {
        InstantCardRequest createInstantCardRequest = create.createRequest();
        String loggerObject = LoggerObjectUtil.print("Title createInstant",createInstantCardRequest);
        Assertions.assertNotEquals(InstantCardConstants.EMPTY_STRING, loggerObject);
    }

    @Test
    @DisplayName(value = "LoggerObjectUtil ===> Exception testPrint <===")
    void testExceptionPrint() throws JsonProcessingException {
        InstantCardRequest createInstantCardRequest = create.createRequest();
        LoggerObjectUtil.setObjectMapper(objectMapperMock);
        Mockito.when(objectMapperMock.writeValueAsString(createInstantCardRequest)).thenThrow(new JsonProcessingException("Error de prueba") {});
        String loggerObject = LoggerObjectUtil.print("Title exception createInstant", createInstantCardRequest);
        Assertions.assertEquals(InstantCardConstants.EMPTY_STRING, loggerObject);
    }


    @Test
    @DisplayName(value = "Test UtilityTest ==> testConstructor <===")
    void testConstructor() throws Exception {
        Constructor<LoggerObjectUtil> constructor = LoggerObjectUtil.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = Assertions.assertThrows(InvocationTargetException.class, constructor::newInstance);
        Assertions.assertTrue(exception.getCause() instanceof IllegalStateException);
        Assertions.assertEquals(CommonConstants.ILLEGAL_STATE_EXCEPTION_MSG, exception.getCause().getMessage());
    }
}
