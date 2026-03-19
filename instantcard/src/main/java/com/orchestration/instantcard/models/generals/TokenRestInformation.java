package com.orchestration.instantcard.models.generals;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenRestInformation {
    private String url;
    private String apiKey;
    private String password;
}
