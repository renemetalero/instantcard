package com.orchestration.instantcard.models.generals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldsCardDto {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String province;
    private String customerNumber;
    private String accountNumber;
    private String logo;
    private String shortName;
}
