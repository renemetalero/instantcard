package com.orchestration.instantcard.models.response.messages.vision.customer;


public class CreacionClienteVisionResponseBody {
    private String localAccountOrg;
    private String foreignAssociatedOrg;
    private String localCustomerOrg;
    private String foreignCustomerOrg;
    private String accountNumber;
    private String customerNumber;


    public String getLocalAccountOrg() {
        return localAccountOrg;
    }
    public void setLocalAccountOrg(String localAccountOrg) {
        this.localAccountOrg = localAccountOrg;
    }
    public String getForeignAssociatedOrg() {
        return foreignAssociatedOrg;
    }
    public void setForeignAssociatedOrg(String foreignAssociatedOrg) {
        this.foreignAssociatedOrg = foreignAssociatedOrg;
    }
    public String getLocalCustomerOrg() {
        return localCustomerOrg;
    }
    public void setLocalCustomerOrg(String localCustomerOrg) {
        this.localCustomerOrg = localCustomerOrg;
    }
    public String getForeignCustomerOrg() {
        return foreignCustomerOrg;
    }
    public void setForeignCustomerOrg(String foreignCustomerOrg) {
        this.foreignCustomerOrg = foreignCustomerOrg;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getCustomerNumber() {
        return customerNumber;
    }
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
