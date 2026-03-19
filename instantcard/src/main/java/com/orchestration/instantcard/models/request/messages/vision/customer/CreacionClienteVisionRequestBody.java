package com.orchestration.instantcard.models.request.messages.vision.customer;

import com.orchestration.instantcard.models.generals.CreacionCuentaFieldGeneric;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreacionClienteVisionRequestBody extends CreacionCuentaFieldGeneric implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String customerNumber;
    private Integer organization;
    private Integer customerOwner;
    private Integer vipStatus;
    private Integer statusCustomer;
    private String customerMaritalStatus;
    private Integer typeNameLine1;
    private Integer typeNameLine2;
    private Integer typeNameLine3;
    private String countryCode;
    private String mailingList;
    private Integer contactIndicator;
    private Integer homePhoneFlag;
    private Integer faxFlagIndicator;
    private Integer mobilePhoneFlag;
    private Integer employerPhoneFlag;
    private Integer emailFlag;
    private Integer shortMessageServiceFlag;
    private String statement2Indicator;
    private String countryCode2;
    private String countryCode3;
    private Integer sicCode;
    private String driverLicenseNumber;
    private String driverLicenseState;
    private String driverLicenseCountry;
    private String identificationNumber;
    private Integer statementMessageIndicator;
    private String employerAddress1;
    private String employerAddress2;
    private String employerPhoneNumber;
    private String employerPhoneExtension;

    private String relativeName;

    private String emailAddress;

    private String memo1;

    private String memo2;

    private String userDefined1;

    private String userDefined2;

    private String userDefined3;

    private String userDefined4;

    private String userDefined5;

    private String userDefined6;

    private String userDefined7;

    private String userDefined8;

    private String userDefined9;

    private Integer userDefined10;
    private Integer userDefined11;

    private String userDefined13;

    private String userDefined14;

    private String userDefined15;

    private String userDefinedDemographicData1;

    private String userDefinedDemographicData2;

    private String userDefinedDemographicData3;

    private String address2Indicator;

    private String address3Indicator;

    private String address2Line1;

    private String address2Line2;

    private String address2Line3;

    private String customerCity2;

    private String postalCode2;

    private String address3Line1;

    private String address3Line2;

    private String address3Line3;

    private String customerCity3;

    private String postalCode3;

    private Integer userDate3;
    private Integer customerIncome;
    private Integer comakerStatementMessageIndicator;
    private String comakerStatement2Indicator;

    private String nameMagneticStripe;
    private String lastNameMagneticStripe;

}
