package com.orchestration.instantcard.models.request;

import com.orchestration.instantcard.models.generals.Header;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstantCardRequestData {

    @Valid
    @ConvertGroup(from = InstantCardRequestData.class, to = Header.class)
    @NotNull(message = "0503")
    private Header header;

    @Valid
    @ConvertGroup(from = InstantCardRequestData.class, to = InstantCardRequestBody.class)
    @NotNull(message = "0504")
    private InstantCardRequestBody body;

}
