package com.orchestration.instantcard.models.request.messages.cmc.customer;


import com.orchestration.instantcard.models.generals.CreacionCuentaFieldGeneric;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreacionClienteBody extends CreacionCuentaFieldGeneric {
    private String shortName;
}
