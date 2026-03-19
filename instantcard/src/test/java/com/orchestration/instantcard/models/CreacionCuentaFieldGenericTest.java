package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.generals.CreacionCuentaFieldGeneric;
import com.orchestration.instantcard.utils.CreatePojos;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CreacionCuentaFieldGenericTest {

    @InjectMocks
    private CreatePojos create;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreacionCuentaFieldGenericModel() {
        CreacionCuentaFieldGeneric cuenta = this.create.creacionCuentaFieldGeneric();
        cuenta.getLogo();
        cuenta.getCurrencyCode();
        cuenta.getCustomerNumber();
        cuenta.getCycle();
        cuenta.getPct();
        cuenta.getOwningBranch();
        cuenta.getDdPaymentType();
        cuenta.getCustomerSelectedDueDate();
        cuenta.getTemporaryCreditLimit();
        cuenta.getTemporaryCreditLimitExpDt();
        cuenta.getAccountCashPlanNumber();
        cuenta.getAccountRetailPlanNumber();
        cuenta.getAccountPromoPlanNumber();
        cuenta.getDirectDebitAccount();
        cuenta.getDdAccountType();
        cuenta.getCustomerNameLine1();
        cuenta.getCustomerNameLine2();
        cuenta.getCustomerNameLine3();
        cuenta.getCustomerAddress1();
        cuenta.getCustomerAddress2();
        cuenta.getCustomerAddress3();
        cuenta.getCustomerAddress4();
        cuenta.getCustomerCity();
        cuenta.getCustomerState();
        cuenta.getPostalCode();
        cuenta.getMaritalStatus();
        cuenta.getNameMagneticStripe();
        cuenta.getLastNameMagneticStripe();
        cuenta.getGenderCode();
        cuenta.getCustomerType();
        cuenta.getDateOfBirth();
        cuenta.getHomePhoneNumber();
        cuenta.getFaxNumber();
        cuenta.getMobilePhoneNumber();
        cuenta.getFlagIdentificationNumber();
        cuenta.getIdentificationNumber();
        cuenta.getCustomerEmployer();
        cuenta.getUserDefined12();
        assertNotNull(cuenta.getLogo());
    }

}
