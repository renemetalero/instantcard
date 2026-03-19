package com.orchestration.instantcard.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

public class RangeValidation {
    String field;
    BigInteger value;
    Integer[] limit;
    BigDecimal valueDecimal;

    public RangeValidation(String field, BigInteger value, Integer[] limit) {
        this.field = field;
        this.value = value;
        this.limit = limit;
    }

    public RangeValidation(String field, BigDecimal valueDecimal, Integer[] limit) {
        this.field = field;
        this.valueDecimal = valueDecimal;
        this.limit = limit;
    }

    public String getField() {
        return field;
    }

    public BigDecimal getValueDecimal() {
        return valueDecimal;
    }

    public BigInteger getValue() {
        return value;
    }

    public Integer[] getLimit() {
        return limit;
    }
}
