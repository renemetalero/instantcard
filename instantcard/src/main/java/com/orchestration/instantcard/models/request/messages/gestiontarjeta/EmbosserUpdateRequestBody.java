package com.orchestration.instantcard.models.request.messages.gestiontarjeta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmbosserUpdateRequestBody {
    private String cardNumber;
    private String maskedCardNumber;
    private String cardToken;
    private String organization;
    private String customerNumber;
    private String principalCardNumber;
    private String embossedName1;
    private String embossedName2;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String emblemId;
    private String emailCardholder;
    private String cardStatus;
    private String customFillerInd1;
    private String customFiller1;
    private String customFillerInd2;
    private String customFiller2;

}
