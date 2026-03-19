package com.orchestration.instantcard.models.request.messages.vision.customer;

import lombok.*;

@Getter
@NoArgsConstructor
public class AccountInformationDto {
    private String name;
    private String lastName;
    private String shortName;


    private AccountInformationDto(AccountInformationDtoBuilder builder) {
        this.name = builder.nameMagneticStripe;
        this.lastName = builder.lastNameMagneticStripe;
        this.shortName = builder.shortName;
    }

    public static AccountInformationDtoBuilder builder() {
        return new AccountInformationDtoBuilder();
    }

    public static class AccountInformationDtoBuilder {
        private String nameMagneticStripe;
        private String lastNameMagneticStripe;
        private String shortName;

        public AccountInformationDtoBuilder nameMagneticStripe(String nameMagneticStripe) {
            this.nameMagneticStripe = nameMagneticStripe;
            return this;
        }

        public AccountInformationDtoBuilder lastNameMagneticStripe(String lastNameMagneticStripe) {
            this.lastNameMagneticStripe = lastNameMagneticStripe;
            return this;
        }

        public AccountInformationDtoBuilder shortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public AccountInformationDto build() {
            return new AccountInformationDto(this);
        }
    }

}
