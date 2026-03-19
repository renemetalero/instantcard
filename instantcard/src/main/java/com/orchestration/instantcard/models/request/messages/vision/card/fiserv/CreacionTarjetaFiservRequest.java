package com.orchestration.instantcard.models.request.messages.vision.card.fiserv;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreacionTarjetaFiservRequest {
    private String accountCreateFlag;
    private Integer cardActionFlag;
    private String customerNumber;
    private String firstPurchaseFlag;
    private String logo;
    private Integer organizationNumber;
    private Integer sameDayProcessingType;
    private AccountDataFiservRequest accountData;
    private EmbosserDataFiservRequest embosserData;


}
