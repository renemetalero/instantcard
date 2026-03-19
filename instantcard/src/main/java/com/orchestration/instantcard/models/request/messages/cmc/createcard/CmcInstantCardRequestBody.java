package com.orchestration.instantcard.models.request.messages.cmc.createcard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmcInstantCardRequestBody {
    private String customerNumber;
    private String logo;
    private String cardNumber;
    private String accountNumber;
    private String productType;
    private String cardCycle;
    private String pct;
    private Long fecha;
    private String user;
    private String cardExpiryDate;

    //nuevs campos para guardar en la tabla de tarjetas cmc y Contacto cliente
    private String email;
    private String phoneNumber;
    private String gender; // pending validation
    private String bmUsername;
    private String phoneSource;
    private String emailSource;
}
