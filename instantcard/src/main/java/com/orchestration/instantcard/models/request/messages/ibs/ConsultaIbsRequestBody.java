package com.orchestration.instantcard.models.request.messages.ibs;

public class ConsultaIbsRequestBody {

    private static final long serialVersionUID = -2880066304279926916L;

    private String customerNumber;

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
