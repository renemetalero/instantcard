package com.orchestration.instantcard.utils.constants;

public enum FieldInstantCardConstants {

    TEMPORARY_CREDIT_LIMIT("temporaryCreditLimit", new Integer[]{0,17}),
    CREDIT_LIMIT("creditLimit", new Integer[]{1,17}),
    RELATION_SHIP_NUMBER("relationshipNumber", new Integer[]{0,19}),
    ATM_CASH_AMOUNT("atmCashAmount", new Integer[]{0,17}),
    EMBLEM_ID("emblemId", new Integer[]{5,5}),
    ATM_CASH_SINGLE_TRANSACTION_LIMIT("atmCashSingleTransactionLimit", new Integer[]{0,17}),
    OVER_COUNTER_CASH_AMOUNT("overTheCounterCashAmount", new Integer[]{0,17}),
    OVER_COUNTER_CASH_SINGLE_TRANSACTION_LIMIT("overTheCounterCashSingleTransactionLimit", new Integer[]{0,17}),
    RETAIL_PURCHASE_AMT("retailPurchaseAmt", new Integer[]{0,17}),
    RETAIL_PURCHASE_SINGLE_TRANSACTION_LIMIT("retailPurchaseSingleTransactionLimit", new Integer[]{0,17}),
    INTERNET_PURCHASE_AMOUNT("internetPurchaseAmount", new Integer[]{0,17}),
    INTERNET_PURCHASE_SINGLE_TRANSACTION_LIMIT("internetPurchaseSingleTransactionLimit", new Integer[]{0,13}),
    ORGANIZATION("organization"),
    PRODUCT_TYPE("productType"),
    LOGO("logo"),
    PCT("pct"),
    CYCLE("cycle"),
    CARD_NUMBER("cardNumber");




    private final String field;
    private final Integer[] limit;


    FieldInstantCardConstants(String field) {
        this.field = field;
        this.limit = new Integer[]{};
    }
    FieldInstantCardConstants(String field, Integer[] limit) {
        this.field = field;
        this.limit = limit;
    }

    public String getField() {
        return field;
    }

    public Integer[] getLimit() {
        return limit;
    }
}
