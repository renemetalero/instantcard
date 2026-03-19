package com.orchestration.instantcard.validate;

import com.orchestration.instantcard.components.cmc.CatalogsComponent;
import com.orchestration.instantcard.exception.models.ValidationsException;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ManualValidationProcessImplTest {

    @Mock
    private CatalogsComponent catalogsComponents;

    private ManualValidationProcessImpl manualValidationProcess;

    @InjectMocks
    private CreatePojos create;

    @BeforeEach
    void setUp() {
        this.manualValidationProcess = new ManualValidationProcessImpl(catalogsComponents);
    }

    @Test
    void testExceptionManualValidationProcess() {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setProductType("TCM");
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @Test
    void testManualValidationProcessValidateProductTypeExists() {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setProductType("TCD");
        body.setCardNumber(" ");
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @Test
    void testManualValidateInListError() {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setProductType("TCD");
        body.setOrganization(500l);
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @Test
    void testRangeValidationBigNumberError() {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setCreditLimit(new BigDecimal("500000000000000000.00"));
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @Test
    void testValidateTcdValidateLogo() {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setLogo(" ");
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @Test
    void testValidateTcdValidatePct() {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setPct(" ");
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @Test
    void testValidateTcdValidateCreditLimit() {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setCreditLimit(new BigDecimal("-1.0"));
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @ParameterizedTest
    @ValueSource(longs = {0l,31l})
    void testInvalidValidateCycle(Long cycle) {
        InstantCardRequestBody body = create.createInstantCardBody();
        body.setCycle(cycle == 0l ? null : cycle);
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @ParameterizedTest
    @ValueSource(strings = {"12","null"})
    void testInvalidValidateEmblemId(String emblemId) {
        InstantCardRequestBody body = create.createInstantCardBody();
        String value = emblemId.equals("null") ? null : emblemId;
        body.setEmblemId(value);
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @Test
    void testInvalidValidateCycleCatalog() {
        InstantCardRequestBody body = create.createInstantCardBody();
        Mockito.when(catalogsComponents.getValidateCatalogCycle(Mockito.anyLong())).thenReturn(false);
        ValidationsException validationsException = assertThrows(ValidationsException.class, () -> manualValidationProcess.validateProcessManual(body));
        assertEquals(ValidationsException.class, validationsException.getClass());
    }

    @Test
    void testValidateProcessManual(){
        InstantCardRequestBody body = create.createInstantCardBody();
        Mockito.when(catalogsComponents.getValidateCatalogCycle(Mockito.anyLong())).thenReturn(true);
        assertDoesNotThrow(() -> manualValidationProcess.validateProcessManual(body));
    }

}
