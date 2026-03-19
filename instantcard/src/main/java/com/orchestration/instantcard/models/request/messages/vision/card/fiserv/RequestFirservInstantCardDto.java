package com.orchestration.instantcard.models.request.messages.vision.card.fiserv;

import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFirservInstantCardDto extends InstantCardRequestBody {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String customerNumber;
    private String accountNumber;
    private String logo;
    private String shortName;
    private String dateOpened;
    private String creditLimitFormatted;
}
