package com.orchestration.instantcard.utils;

import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.utils.constants.CommonConstants;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


@ExtendWith(MockitoExtension.class)
class InjectionValidatorTest {

    @InjectMocks
    private CreatePojos create;

    @Test
    @DisplayName("Validando error de entrada")
    void validateRequestInjection(){
        InstantCardRequestBody bodyRequest = create.createRequestBody();
        bodyRequest.setCardNumber(";;SELECT * FROM");
        Assertions.assertThrows(BusinessException.class,() ->
                InjectionValidator.validateStringsForInjection(bodyRequest, InstantCardConstants.FIELD_ACCESIBLE));
    }

    @Test
    @DisplayName("Validando error null de entrada")
    void validateNullRequestInjection(){
        InstantCardRequestBody bodyRequest = null;
        Assertions.assertDoesNotThrow(() ->
                InjectionValidator.validateStringsForInjection(bodyRequest, InstantCardConstants.FIELD_ACCESIBLE));
    }

    @Test
    void testValidateStringsForInjectionWithIllegalAccessException() {
        InstantCardRequestBody bodyRequest = create.createRequestBody();
        Assertions.assertDoesNotThrow(() ->
                InjectionValidator.validateStringsForInjection(bodyRequest, InstantCardConstants.FIELD_NOT_ACCESIBLE)
        );
    }

    @Test
    @DisplayName(value = "Test InjectionValidator ==> testConstructor <===")
    void testConstructor() throws Exception {
        Constructor<InjectionValidator> constructor = InjectionValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = Assertions.assertThrows(InvocationTargetException.class, constructor::newInstance);
        Assertions.assertTrue(exception.getCause() instanceof IllegalStateException);
        Assertions.assertEquals(CommonConstants.ILLEGAL_STATE_EXCEPTION_MSG, exception.getCause().getMessage());
    }

}
