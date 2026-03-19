package com.orchestration.instantcard.models.response.messages.vision.card.fiserv;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreacionTarjetaFiservResponseData {
    private Long organizationNumber;
    private String logo;
    private String customerNumber;
    private String accountNumber;
    private String cardNumber;
    private String cardSequenceNumber;
    private Long creditLimit;
    private String cardExpiryDate;
}
