package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.AccountDataFiservRequest;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AccountDataFiservRequestTest {

    private AccountDataFiservRequest accountDataFiservRequest;

    @InjectMocks
    private CreatePojos create;

    @Test
    void testAccountDataFiservRequest() {
        this.accountDataFiservRequest = create.createAccountDataFiservRequest();

        accountDataFiservRequest.getBillingCycle();
        accountDataFiservRequest.getAccountNumber();
        accountDataFiservRequest.getBillingAccountIndicator();
        accountDataFiservRequest.getRelationshipPrimaryAccountFlag();
        accountDataFiservRequest.getRelationshipNumber();
        accountDataFiservRequest.getShortName();
        accountDataFiservRequest.getCreditLimit();
        accountDataFiservRequest.getBillingCurrency();
        accountDataFiservRequest.getRelationshipBillingLevel();
        accountDataFiservRequest.getDualBillingFlag();
        accountDataFiservRequest.getRelationshipCardScheme();
        accountDataFiservRequest.getAlternateCustomerNumberFlag();
        accountDataFiservRequest.getAlternateCustomerStartDate();
        accountDataFiservRequest.getAlternateCustomerExpireDate();
        accountDataFiservRequest.getAlternateCustomerNumber();
        accountDataFiservRequest.getDateOpened();
        accountDataFiservRequest.getCustomerSelectedDueDay();
        accountDataFiservRequest.getStatementFlag();
        accountDataFiservRequest.getStatementFrequency();
        accountDataFiservRequest.getBlockCode1();
        accountDataFiservRequest.getBlockCode2();
        accountDataFiservRequest.getProcessingControlTableControlId();
        accountDataFiservRequest.getProcessingControlTableStartDate();
        accountDataFiservRequest.getProcessingControlTableExpireDate();
        accountDataFiservRequest.getProcessingControlTableLevel();
        accountDataFiservRequest.getProcessingControlTableLevelStartDate();
        accountDataFiservRequest.getProcessingControlTableLevelExpireDate();
        accountDataFiservRequest.getCashPlanNumber();
        accountDataFiservRequest.getRetailPlanNumber();
        accountDataFiservRequest.getPromoPlanNumber();
        accountDataFiservRequest.getDdaRoutingNumber();
        accountDataFiservRequest.getDdaAccountNumber();
        accountDataFiservRequest.getSavingsRoutingNumber();
        accountDataFiservRequest.getSavingsAccountNumber();
        accountDataFiservRequest.getUserAccountNumber();
        accountDataFiservRequest.getCardTechnology();
        accountDataFiservRequest.getAuthorizationLimitOverrides();
        accountDataFiservRequest.getTemporaryCreditLimit();
        accountDataFiservRequest.getDateTemporaryCreditLimit();
        accountDataFiservRequest.getStateOfResidenceId();
        accountDataFiservRequest.getStateOfIssuanceId();
        accountDataFiservRequest.getOwningBranchNumber();
        accountDataFiservRequest.getUserCode1();
        accountDataFiservRequest.getUserCode2();
        accountDataFiservRequest.getUserCode3();
        accountDataFiservRequest.getUserCode4();
        accountDataFiservRequest.getUserCode5();
        accountDataFiservRequest.getUserCode6();
        accountDataFiservRequest.getUserCode7();
        accountDataFiservRequest.getUserCode8();
        accountDataFiservRequest.getUserCode9();
        accountDataFiservRequest.getUserCode10();
        accountDataFiservRequest.getUserCode11();
        accountDataFiservRequest.getUserCode12();
        accountDataFiservRequest.getUserCode13();
        accountDataFiservRequest.getUserCode14();
        accountDataFiservRequest.getUserDate1();
        accountDataFiservRequest.getUserDate2();
        accountDataFiservRequest.getUserDate3();
        accountDataFiservRequest.getUserDate4();
        accountDataFiservRequest.getUserDate5();
        accountDataFiservRequest.getUserDate6();
        accountDataFiservRequest.getUserDate7();
        accountDataFiservRequest.getUserDate8();
        accountDataFiservRequest.getUserDate9();
        accountDataFiservRequest.getUserDate10();
        accountDataFiservRequest.getUserDate11();
        accountDataFiservRequest.getUserDate12();
        accountDataFiservRequest.getUserDate13();
        accountDataFiservRequest.getUserDate14();
        accountDataFiservRequest.getUserAmount1();
        accountDataFiservRequest.getUserAmount2();
        accountDataFiservRequest.getUserAmount3();
        accountDataFiservRequest.getUserAmount4();
        accountDataFiservRequest.getUserAmount5();
        accountDataFiservRequest.getUserAmount6();
        accountDataFiservRequest.getUserAmount7();
        accountDataFiservRequest.getUserAmount8();
        accountDataFiservRequest.getUserAmount9();
        accountDataFiservRequest.getUserAmount10();
        accountDataFiservRequest.getUserAmount11();
        accountDataFiservRequest.getUserAmount12();
        accountDataFiservRequest.getUserAmount13();
        accountDataFiservRequest.getUserAmount14();
        accountDataFiservRequest.getMiscellaneousUser1();
        accountDataFiservRequest.getMiscellaneousUser2();
        accountDataFiservRequest.getMiscellaneousUser3();
        accountDataFiservRequest.getMiscellaneousUser4();
        accountDataFiservRequest.getMiscellaneousUser5();
        accountDataFiservRequest.getMiscellaneousUser6();
        accountDataFiservRequest.getMiscellaneousUser7();
        accountDataFiservRequest.getMiscellaneousUser8();
        accountDataFiservRequest.getMiscellaneousUser9();
        accountDataFiservRequest.getMiscellaneousUser10();
        accountDataFiservRequest.getMiscellaneousUser11();
        accountDataFiservRequest.getMiscellaneousUser12();
        accountDataFiservRequest.getPrepaidPlanNumber();
        accountDataFiservRequest.getPrepaidLoadFrequency();
        accountDataFiservRequest.getPrepaidLoadNumber();
        accountDataFiservRequest.getPrepaidLoadAmount();
        accountDataFiservRequest.getMinimumSingleLoadAmount();
        accountDataFiservRequest.getMaximumSingleLoadAmount();
        accountDataFiservRequest.getInternationalRetailPlan();
        accountDataFiservRequest.getInternationalInstallmentPlan();
        accountDataFiservRequest.getCashPlanInstallmentBilling();
        accountDataFiservRequest.getRetailPlanInstallmentBilling();
        accountDataFiservRequest.getAcquirerRetailPlan();
        accountDataFiservRequest.getAuthorizationControlTable();
        accountDataFiservRequest.getRetailPlanInstallmentPayments();
        accountDataFiservRequest.getCashPlanInstallmentPayment();
        accountDataFiservRequest.getQualification();
        accountDataFiservRequest.getCardholderAffiliationGroup();

        assertNotNull(accountDataFiservRequest.getBillingCycle());
    }
}
