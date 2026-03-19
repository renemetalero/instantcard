package com.orchestration.instantcard.utils.validations;

import com.orchestration.instantcard.exception.models.ValidationsException;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.request.InstantCardRequestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class ValidationsModelsTest {

    private ValidationsModels validationsModels;

    @BeforeEach
    void setUp() {
        this.validationsModels = new ValidationsModels();
        ReflectionTestUtils.setField(this.validationsModels, "constraintValidatorPayload", "US");
    }
    @Test
    @DisplayName(value = "Test API validateModels")
    void testValidateModels() {
        assertDoesNotThrow(() -> validationsModels.validateModel(new Object()));
    }

    @Test
    @DisplayName(value = "Test API validateModels")
    void testGenerateExceptionValidateModels() {
        InstantCardRequest instantCardRequest = new InstantCardRequest();
        instantCardRequest.setData(new InstantCardRequestData());
        instantCardRequest.getData().setBody(new InstantCardRequestBody());
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> validationsModels.validateModel(instantCardRequest));
        assertEquals("", validationsException.getMessage());
    }

}
