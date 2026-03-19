package com.orchestration.instantcard.models.request.messages.vision.card.fiserv;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EmbosserDataFiservRequest {
        private Integer digitalCardFlag;
        private String plasticId;
        private Integer name1TypeIndicator;
        private Integer name2TypeIndicator;
        private String embossedName1;
        private String embossedName2;
        private String expirationDate;
        private String customerNumber;
        private String name1;
        private Integer posServiceCode;
        private String cardholderFlag;
        private String addressLine1;
        private String addressLine2;
        private String city;
        private String stateOrProvince;
        private String postalCode;
        private String languageCode;
        private Integer atmCashNumber;
        private Long atmCashAmount;
        private Long atmCashSingleTransactionLimit;
        private Integer overTheCounterCashNumber;
        private Long overTheCounterCashAmount;
        private Long overTheCounterCashSingleTransactionLimit;
        private Long retailPurchaseAmt;
        private Long retailPurchaseSingleTransactionLimit;
        private Long internetPurchaseAmount;
        private Long internetPurchaseNumber;
        private Long internetPurchaseSingleTransactionLimit;
        private String user1;
        private String user2;
        private String user3;
        private Integer user4;
        private String user5;
        private String user6;
        private String user7;
        private String user8;
        private String userDate1;
        private String userDate2;
        private Integer branchNumber;
        private Integer firstIssueBranch;
        private Integer deliveryOption;
        private Integer reissueDeliveryOption;
        private String authorizationCriteriaTableNumber;
        private Integer programId;
        private Integer cardholderAffiliationGroupId;
        private String cardActionReasonCode;
        private Integer icTotalNumberTransactions;
        private Long icAmountLimitSingleTransaction;


}
