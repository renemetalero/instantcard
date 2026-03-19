package com.orchestration.instantcard.utils;

import com.orchestration.instantcard.utils.constants.CommonConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class UtilityTest {
    @Test
    @DisplayName(value = "Test UtilityTest ==> testConstructor <===")
    void testConstructor() throws Exception {
        Constructor<Utility> constructor = Utility.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = Assertions.assertThrows(InvocationTargetException.class, constructor::newInstance);
        Assertions.assertTrue(exception.getCause() instanceof IllegalStateException);
        Assertions.assertEquals(CommonConstants.ILLEGAL_STATE_EXCEPTION_MSG, exception.getCause().getMessage());
    }
}
