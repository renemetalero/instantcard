package com.orchestration.instantcard.models.response;

public class InstantCardResponseBody {
    private String creditCardAccountNumber;
    private String creditCardNumber;
    private String customerNumber;

    private String creationDate;
    private String customFillerInd1;
    private String customFiller1;
    private String customeFillerInd2;
    private String customFiller2;

    public String getCreditCardAccountNumber() {
        return creditCardAccountNumber;
    }

    public void setCreditCardAccountNumber(String creditCardAccountNumber) {
        this.creditCardAccountNumber = creditCardAccountNumber;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomFillerInd1() {
        return customFillerInd1;
    }

    public void setCustomFillerInd1(String customFillerInd1) {
        this.customFillerInd1 = customFillerInd1;
    }

    public String getCustomFiller1() {
        return customFiller1;
    }

    public void setCustomFiller1(String customFiller1) {
        this.customFiller1 = customFiller1;
    }

    public String getCustomeFillerInd2() {
        return customeFillerInd2;
    }

    public void setCustomeFillerInd2(String customeFillerInd2) {
        this.customeFillerInd2 = customeFillerInd2;
    }

    public String getCustomFiller2() {
        return customFiller2;
    }

    public void setCustomFiller2(String customFiller2) {
        this.customFiller2 = customFiller2;
    }
}
