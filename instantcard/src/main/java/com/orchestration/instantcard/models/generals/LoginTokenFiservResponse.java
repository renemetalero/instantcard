package com.orchestration.instantcard.models.generals;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginTokenFiservResponse {
    private String token_type;
    private String issued_at;
    private String client_id;
    private String access_token;
    private String expires_in;
    private String status;

}
