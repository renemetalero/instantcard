package com.orchestration.instantcard.models.request.messages.cmc.createcard;

import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CmcInstantCardRequestDTO extends InstantCardRequestBody {
    private String accountNumber;
    private String cardCycle;
    private BigDecimal cardSequence;
    private Long fecha;
    private String user;
    private Long channelId;
    private String cardExpiryDate;
}
