package com.orchestration.instantcard.exceptions;


import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.UtilThrow;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponse;
import com.orchestration.instantcard.utils.TestsConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

class UtilThrowTest {

    @Test
    void testPrivateConstructor() throws Exception {
        Constructor<UtilThrow> constructor = UtilThrow.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Assertions.assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    @DisplayName("Test when CreacionClienteVisionResponse is null")
    void testCreacionClienteVisionResponse(){
        CreacionClienteVisionResponse clientVisionResponse = null;
        Assertions.assertThrows(BusinessException.class, () -> {
            UtilThrow.throwExc(InstantCardEnumError.BUSINESS_VISION, clientVisionResponse);
        });
    }

    @Test
    @DisplayName("Test when CreacionClienteVisionData is null")
    void testCreacionClienteVisionData(){
        CreacionClienteVisionResponse clientVisionResponse = new CreacionClienteVisionResponse();
        clientVisionResponse.setErrors(List.of(new ErrorModel(TestsConstants.BUSSINESS_ERROR_CODE_ZERO,"Error","Detalles")));
        Assertions.assertThrows(BusinessException.class, () -> {
            UtilThrow.throwExc(InstantCardEnumError.BUSINESS_VISION, clientVisionResponse);
        });
    }
}
