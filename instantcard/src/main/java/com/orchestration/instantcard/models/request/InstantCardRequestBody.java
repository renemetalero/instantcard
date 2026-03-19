package com.orchestration.instantcard.models.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Setter
@Getter
public class InstantCardRequestBody extends RequestBody{

    @Min(value = 100, message = "El campo organization debe ser mayor a {value}")
    @Max(value = 999, message = "El campo organization debe ser menor a {value}")
    @NotNull(message = "0505")
    private Long organization;

    @Size(min = 1, max = 3, message = "El campo productType debe tener entre {min} a {max} caracteres")
    @NotNull(message = "0505")
    @Pattern(regexp = "[a-zA-Z]+", message = "El campo productType solo puede contener letras")
    private String productType;

    @Size(min = 3, max = 3, message = "El campo logo debe tener {max} caracteres")
    @NotNull(message = "0505")
    @Pattern(regexp = "\\d+", message = "El campo logo solo puede contener números")
    private String logo;

    @Min(value = 1, message = "El campo cycle debe ser mayor a {value}")
    @Max(value = 99, message = "El campo cycle debe ser menor a {value}")
    @NotNull(message = "0505")
    private Long cycle;

    @Size(min = 1, max = 3, message = "El campo pct debe tener entre {min} a {max} caracteres")
    @NotNull(message = "0505")
    @Pattern(regexp = "\\d+", message = "El campo pct solo puede contener números")
    private String pct;

    @Min(value = 1, message = "El campo owningBranch debe ser mayor a {value}")
    @Max(value = 999999999, message = "El campo owningBranch debe ser menor a {value}")
    @NotNull(message = "0505")
    private Long owningBranch;

    @Min(value = 1000000, message = "El campo pctOvStartdt debe ser mayor a {value}")
    @Max(value = 9999999, message = "El campo pctOvStartdt debe ser menor a {value}")
    private Long pctOvStartdt;

    @Min(value = 1000000, message = "El campo pctOvEnddt debe ser mayor a {value}")
    @Max(value = 9999999, message = "El campo pctOvEnddt debe ser menor a {value}")
    private Long pctOvEnddt;

    @Min(value = 10000, message = "El campo accountCashPlanNumber debe ser mayor a {value}")
    @Max(value = 99999, message = "El campo accountCashPlanNumber debe ser menor a {value}")
    private Long accountCashPlanNumber;

    @Min(value = 10000, message = "El campo accountRetailPlanNumber debe ser mayor a {value}")
    @Max(value = 99999, message = "El campo accountRetailPlanNumber debe ser menor a {value}")
    private Long accountRetailPlanNumber;

    @Min(value = 10000, message = "El campo accountPromoPlanNumber debe ser mayor a {value}")
    @Max(value = 99999, message = "El campo accountPromoPlanNumber debe ser menor a {value}")
    private Long accountPromoPlanNumber;

    @Size(min = 1, max = 19, message = "El campo directDebitAccount debe tener entre {min} a {max} caracteres")
    private String directDebitAccount;

    @Size(max = 10, message = "El campo ddRoutingNumber debe ser maximo de {max} caracteres")
    private String ddRoutingNumber;

    @Min(value = 10, message = "El campo customerSelectedDueDate debe ser mayor a {value}")
    @Max(value = 99, message = "El campo customerSelectedDueDate debe ser menor a {value}")
    private Long customerSelectedDueDate;

    private BigInteger temporaryCreditLimit;

    @Min(value = 1000000, message = "El campo temporaryCreditLimitExpDt debe ser mayor a {value}")
    @Max(value = 9999999, message = "El campo temporaryCreditLimitExpDt debe ser menor a {value}")
    private Long temporaryCreditLimitExpDt;

    @Min(value = 100, message = "El campo officerCode debe ser mayor a {value}")
    @Max(value = 999, message = "El campo officerCode debe ser menor a {value}")
    private Long officerCode;

    @Min(value = 1, message = "El campo authLimitOverride debe ser mayor a {value}")
    @Max(value = 9, message = "El campo authLimitOverride debe ser menor a {value}")
    private Long authLimitOverride;

    @Size(min = 19, max = 19, message = "El campo cardNumber debe tener {max} caracteres")
    @Pattern(regexp = "\\d+", message = "El campo cardNumber solo puede contener números")
    private String cardNumber;

    private BigDecimal creditLimit;

    private String emblemId;

    @Size(min = 1, max = 26, message = "El campo embossedName1 debe tener entre {min} a {max} caracteres")
    @NotNull(message = "0505")
    private String embossedName1;

    @Size(min = 1, max = 26, message = "El campo embossedName2 debe tener entre {min} a {max} caracteres")
    private String embossedName2;

    @Size(min = 1, max = 10, message = "El campo plasticId debe tener entre {min} a {max} caracteres")
    private String plasticId;

    @Size(min = 1, max = 60, message = "El campo email debe tener entre {min} a {max} caracteres")
    @NotNull(message = "0505")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{1,}$",message = "El formato de email no es válido")
    private String email;

    @Size(min = 1, max = 3, message = "El campo phoneCountryCode debe tener entre {min} a {max} caracteres")
    private String phoneCountryCode;

    @Size(min = 1, max = 3, message = "El campo areaCode debe tener entre {min} a {max} caracteres")
    private String areaCode;

    @Size(min = 1, max = 20, message = "El campo phoneNumber debe tener entre {min} a {max} caracteres")
    private String phoneNumber;

    @Size(min = 1, max = 11, message = "El campo saleExecutive debe tener entre {min} a {max} caracteres")
    private String saleExecutive;

    @Min(value = 1, message = "El campo customerMaritalStatus debe ser mayor a {value}")
    @Max(value = 9, message = "El campo customerMaritalStatus debe ser menor a {value}")
    private Long customerMaritalStatus;

    @Size(min = 1, max = 40, message = "El campo customerAddressLine1 debe tener entre {min} a {max} caracteres")
    @NotNull(message = "0505")
    private String customerAddressLine1;

    @Size(max = 40, message = "El campo customerAddressLine2 debe tener {max} caracteres")
    private String customerAddressLine2;

    @Size(min = 1, max = 30, message = "El campo customerCity debe tener entre {min} a {max} caracteres")
    @NotNull(message = "0505")
    private String customerCity;

    @Size(min = 1, max = 3, message = "El campo customerState debe tener entre {min} a {max} caracteres")
    @NotNull(message = "0505")
    private String customerState;

    @Size(min = 1, max = 20, message = "El campo requestNumberMantiz debe tener entre {min} a {max} caracteres")
    private String requestNumberMantiz;

    @Min(value = 1, message = "El campo billingAccountIndicator debe ser mayor a {value}")
    @Max(value = 9, message = "El campo billingAccountIndicator debe ser menor a {value}")
    private Long billingAccountIndicator;

    @Min(value = 1, message = "El campo relationshipPrimaryAccountFlag debe ser mayor a {value}")
    @Max(value = 9, message = "El campo relationshipPrimaryAccountFlag debe ser menor a {value}")
    private Long relationshipPrimaryAccountFlag;

    private BigInteger relationshipNumber;

    @Size(min = 1, max = 1, message = "El campo dualbillingflag debe tener entre {min} a {max} caracteres")
    private String dualbillingflag;

    @Size(min = 1, max = 1, message = "El campo alternateCustomerNumberFlag debe tener entre {min} a {max} caracteres")
    private String alternateCustomerNumberFlag;

    private String alternateCustomerStartDate;

    private String alternateCustomerExpireDate;

    @Size(min = 1, max = 19, message = "El campo alternateCustomerNumber debe tener entre {min} a {max} caracteres")
    private String alternateCustomerNumber;

    @Size(min = 1, max = 2, message = "El campo customerSelectedDueDay debe tener entre {min} a {max} caracteres")
    private String customerSelectedDueDay;

    @Size(min = 1, max = 1, message = "El campo blockCode1 debe tener entre {min} a {max} caracteres")
    private String blockCode1;

    @Size(min = 1, max = 1, message = "El campo blockCode2 debe tener entre {min} a {max} caracteres")
    private String blockCode2;

    @Size(min = 1, max = 19, message = "El campo processingControlTableControlId debe tener entre {min} a {max} caracteres")
    private String processingControlTableControlId;

    private String processingControlTableLevelStartDate;

    private String processingControlTableLevelExpireDate;

    private Integer savingsRoutingNumber;

    @Size(min = 1, max = 19, message = "El campo savingsAccountNumber debe tener entre {min} a {max} caracteres")
    private String savingsAccountNumber;

    @Size(min = 1, max = 19, message = "El campo userAccountNumber debe tener entre {min} a {max} caracteres")
    private String userAccountNumber;

    private Integer prepaidPlanNumber;

    @Size(min = 1, max = 1, message = "El campo prepaidLoadFrequency debe tener entre {min} a {max} caracteres")
    private String prepaidLoadFrequency;

    private Integer prepaidLoadNumber;

    private Long prepaidLoadAmount;

    private Long minimumSingleLoadAmount;

    private Long maximumSingleLoadAmount;

    private Integer internationalCashPlan;

    private Integer internationalRetailPlan;

    private Integer internationalInstallmentPlan;

    private Integer cashPlanInstallmentBilling;

    private Integer retailPlanInstallmentBilling;

    private Integer acquirerRetailPlan;

    private Integer authorizationControlTable;

    private Integer retailPlanInstallmentPayments;

    private Integer cashPlanInstallmentPayment;

    @Size(min = 1, max = 1, message = "El campo qualification debe tener entre {min} a {max} caracteres")
    private String qualification;

    @Size(max = 10, message = "El campo postalCode debe ser maximo de {max} caracteres")
    private String postalCode;

    @Size(min = 1, max = 3, message = "El campo languageCode debe tener entre {min} a {max} caracteres")
    private String languageCode;

    @Min(value = 100000000, message = "El campo atmCashNumber debe ser mayor a {value}")
    @Max(value = 999999999, message = "El campo atmCashNumber debe ser menor a {value}")
    private Long atmCashNumber;

    private BigInteger atmCashAmount;

    private BigInteger atmCashSingleTransactionLimit;

    @Min(value = 100000000, message = "El campo overTheCounterCashNumber debe ser mayor a {value}")
    @Max(value = 999999999, message = "El campo overTheCounterCashNumber debe ser menor a {value}")
    private Long overTheCounterCashNumber;

    private BigInteger overTheCounterCashAmount;

    private BigInteger overTheCounterCashSingleTransactionLimit;

    @Min(value = 100000000, message = "El campo retailPurchaseNumber debe ser mayor a {value}")
    @Max(value = 999999999, message = "El campo retailPurchaseNumber debe ser menor a {value}")
    private Long retailPurchaseNumber;

    private BigInteger retailPurchaseAmt;

    private BigInteger retailPurchaseSingleTransactionLimit;

    private BigInteger internetPurchaseAmount;

    @Min(value = 100000000, message = "El campo internetPurchaseNumber debe ser mayor a {value}")
    @Max(value = 999999999, message = "El campo internetPurchaseNumber debe ser menor a {value}")
    private Long internetPurchaseNumber;

    private BigInteger internetPurchaseSingleTransactionLimit;

    @Min(value = 1, message = "El campo deliveryOption debe ser igual a {value}")
    @Max(value = 1, message = "El campo deliveryOption debe ser igual a {value}")
    private Integer deliveryOption;

    @Size(min = 1, max = 1, message = "El campo reissueDeliveryOption debe tener entre {min} a {max} caracteres")
    private String reissueDeliveryOption;

    @Size(min = 1, max = 3, message = "El campo authorizationCriteriaTableNumber debe tener entre {min} a {max} caracteres")
    private String authorizationCriteriaTableNumber;

    private Integer programId;

    @Size(min = 1, max = 1, message = "El campo cardActionReasonCode debe tener entre {min} a {max} caracteres")
    private String cardActionReasonCode;

    private Integer icTotalNumberTransactions;

    private Long icAmountLimitSingleTransaction;

    private String userCode7;

    private String dateOpened;

    private String userBM;

}