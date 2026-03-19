package com.orchestration.instantcard.validate;

import com.orchestration.instantcard.exception.models.ValidationsException;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidateModelsTest {

    private ValidateModels validateModels;

    @InjectMocks
    private CreatePojos create;
    @Mock
    private ManualValidationProcessImpl manualValidationProcess;

    @BeforeEach
    void setUp() {
        this.validateModels = new ValidateModels(manualValidationProcess);
        ReflectionTestUtils.setField(this.validateModels, "constraintValidatorPayload", "US");
    }

    @ParameterizedTest
    @DisplayName(value = "Test API validateModels")
    @MethodSource("cycleProvider")
    void testValidateModels(Long cycle) {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setCycle(cycle);
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> this.validateModels.validateModel(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    static Stream<Long> cycleProvider() {
        return Stream.of(null, 1000l);
    }
}
