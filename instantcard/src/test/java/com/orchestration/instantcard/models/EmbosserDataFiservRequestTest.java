package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.EmbosserDataFiservRequest;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmbosserDataFiservRequestTest {

    @InjectMocks
    private CreatePojos create;

    @Test
    @DisplayName(value = "EmbosserDataFiservRequestTest ==> testEmbosserDataFiservRequest")
    void testEmbosserDataFiservRequest() {
        EmbosserDataFiservRequest embosserDataFiservRequest = create.createEmbosserDataFiservRequest();
        embosserDataFiservRequest.getDigitalCardFlag();
        embosserDataFiservRequest.getPlasticId();
        embosserDataFiservRequest.getName1TypeIndicator();
        embosserDataFiservRequest.getName2TypeIndicator();
        embosserDataFiservRequest.getEmbossedName1();
        embosserDataFiservRequest.getEmbossedName2();
        embosserDataFiservRequest.getExpirationDate();
        embosserDataFiservRequest.getCustomerNumber();
        embosserDataFiservRequest.getName1();
        embosserDataFiservRequest.getPosServiceCode();
        embosserDataFiservRequest.getCardholderFlag();
        embosserDataFiservRequest.getAddressLine1();
        embosserDataFiservRequest.getAddressLine2();
        embosserDataFiservRequest.getCity();
        embosserDataFiservRequest.getStateOrProvince();
        embosserDataFiservRequest.getPostalCode();
        embosserDataFiservRequest.getLanguageCode();
        embosserDataFiservRequest.getAtmCashNumber();
        embosserDataFiservRequest.getAtmCashAmount();
        embosserDataFiservRequest.getAtmCashSingleTransactionLimit();
        embosserDataFiservRequest.getOverTheCounterCashNumber();
        embosserDataFiservRequest.getOverTheCounterCashAmount();
        embosserDataFiservRequest.getOverTheCounterCashSingleTransactionLimit();
        embosserDataFiservRequest.getRetailPurchaseAmt();
        embosserDataFiservRequest.getRetailPurchaseSingleTransactionLimit();
        embosserDataFiservRequest.getInternetPurchaseAmount();
        embosserDataFiservRequest.getInternetPurchaseNumber();
        embosserDataFiservRequest.getInternetPurchaseSingleTransactionLimit();
        embosserDataFiservRequest.getUser1();
        embosserDataFiservRequest.getUser2();
        embosserDataFiservRequest.getUser3();
        embosserDataFiservRequest.getUser4();
        embosserDataFiservRequest.getUser5();
        embosserDataFiservRequest.getUser6();
        embosserDataFiservRequest.getUser7();
        embosserDataFiservRequest.getUser8();
        embosserDataFiservRequest.getUserDate1();
        embosserDataFiservRequest.getUserDate2();
        embosserDataFiservRequest.getBranchNumber();
        embosserDataFiservRequest.getFirstIssueBranch();
        embosserDataFiservRequest.getDeliveryOption();
        embosserDataFiservRequest.getReissueDeliveryOption();
        embosserDataFiservRequest.getAuthorizationCriteriaTableNumber();
        embosserDataFiservRequest.getProgramId();
        embosserDataFiservRequest.getCardholderAffiliationGroupId();
        embosserDataFiservRequest.getCardActionReasonCode();
        embosserDataFiservRequest.getIcTotalNumberTransactions();
        embosserDataFiservRequest.getIcAmountLimitSingleTransaction();
        Assertions.assertNotNull(embosserDataFiservRequest.getDigitalCardFlag());
    }
}
