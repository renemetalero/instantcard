package com.orchestration.instantcard.models.response.messages.ibs;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaIbsResponseBody {
    private String customerNumber;
    private String customerType;
    private String sicCode;

    @SerializedName(value = "customerNameLine1")
    private String customerNameLine1ResponseIbs;

    @SerializedName(value = "customerNameLine2")
    private String customerNameLine2ResponseIbs;

    @SerializedName(value = "customerNameLine3")
    private String customerNameLine3ResponseIbs;

    @SerializedName(value = "customerAddress1")
    private String customerAddress1ResponseIbs;

    @SerializedName(value = "customerAddress2")
    private String customerAddress2ResponseIbs;

    @SerializedName(value = "customerAddress3")
    private String customerAddress3ResponseIbs;

    @SerializedName(value = "customerAddress4")
    private String customerAddress4ResponseIbs;

    @SerializedName(value = "customerCity")
    private String customerCityResponseIbs;

    @SerializedName(value = "customerState")
    private String customerStateResponseIbs;

    @SerializedName(value = "postalCode")
    private String postalCodeResponseIbs;

    private Integer genderCode;
    private String homePhoneNumber;
    private String faxNumber;

    @SerializedName(value = "mobilePhoneNumber")
    private String mobilePhoneNumberResponseIbs;

    @SerializedName(value = "dateOfBirth")
    private Integer dateOfBirthResponseIbs;

    @SerializedName(value = "driverLicenseNumber")
    private String driverLicenseNumberResponseIbs;

    @SerializedName(value = "driverLicenseState")
    private String driverLicenseStateResponseIbs;

    @SerializedName(value = "driverLicenseCountry")
    private String driverLicenseCountryResponseIbs;

    @SerializedName(value = "flagIdentificationNumber")
    private String flagIdentificationNumberResponseIbs;

    @SerializedName(value = "identificationNumber")
    private String identificationNumberResponseIbs;

    @SerializedName(value = "customerEmployer")
    private String customerEmployerResponseIbs;

    @SerializedName(value = "employerAddress1")
    private String employerAddress1ResponseIbs;

    @SerializedName(value = "employerAddress2")
    private String employerAddress2ResponseIbs;

    @SerializedName(value = "employerPhoneNumber")
    private String employerPhoneNumberResponseIbs;

    @SerializedName(value = "employeePhoneExtension")
    private String employeePhoneExtensionResponseIbs;

    @SerializedName(value = "relativeName")
    private String relativeNameResponseIbs;

    @SerializedName(value = "emailAddress")
    private String emailAddressResponseIbs;

    @SerializedName(value = "memo1")
    private String memo1ResponseIbs;

    @SerializedName(value = "memo2")
    private String memo2ResponseIbs;

    @SerializedName(value = "userDefined1")
    private String userDefined1ResponseIbs;

    @SerializedName(value = "userDefined2")
    private String userDefined2ResponseIbs;

    @SerializedName(value = "userDefined3")
    private String userDefined3ResponseIbs;

    @SerializedName(value = "userDefined4")
    private String userDefined4ResponseIbs;

    @SerializedName(value = "userDefined5")
    private String userDefined5ResponseIbs;

    @SerializedName(value = "userDefined6")
    private String userDefined6ResponseIbs;

    @SerializedName(value = "userDefined7")
    private String userDefined7ResponseIbs;

    @SerializedName(value = "userDefined8")
    private String userDefined8ResponseIbs;

    @SerializedName(value = "userDefined9")
    private String userDefined9ResponseIbs;

    private String userDefined10;
    private String userDefined11;

    @SerializedName(value = "userDefined12")
    private String userDefined12ResponseIbs;

    @SerializedName(value = "userDefined13")
    private String userDefined13ResponseIbs;

    @SerializedName(value = "userDefined14")
    private String userDefined14ResponseIbs;

    @SerializedName(value = "userDefined15")
    private String userDefined15ResponseIbs;

    @SerializedName(value = "userDefinedDemographicData1")
    private String userDefinedDemographicData1ResponseIbs;

    @SerializedName(value = "userDefinedDemographicData2")
    private String userDefinedDemographicData2ResponseIbs;

    @SerializedName(value = "userDefinedDemographicData3")
    private String userDefinedDemographicData3ResponseIbs;

    @SerializedName(value = "address2Indicator")
    private String address2IndicatorResponseIbs;

    @SerializedName(value = "address3Indicator")
    private String address3IndicatorResponseIbs;

    @SerializedName(value = "address2Line1")
    private String address2Line1ResponseIbs;

    @SerializedName(value = "address2Line2")
    private String address2Line2ResponseIbs;

    @SerializedName(value = "address2Line3")
    private String address2Line3ResponseIbs;

    @SerializedName(value = "customerCity2")
    private String customerCity2ResponseIbs;

    @SerializedName(value = "postalCode2")
    private String postalCode2ResponseIbs;

    @SerializedName(value = "address3Line1")
    private String address3Line1ResponseIbs;

    @SerializedName(value = "address3Line2")
    private String address3Line2ResponseIbs;

    @SerializedName(value = "address3Line3")
    private String address3Line3ResponseIbs;

    @SerializedName(value = "customerCity3")
    private String customerCity3ResponseIbs;

    @SerializedName(value = "postalCode3")
    private String postalCode3ResponseIbs;

    //2022-11-174:cambios para cardadd version 5
    private String nameMagneticStripe;
    private String lastNameMagneticStripe;

    private String userDate3;
    private String customerIncome;
    private String customFillerInd1;
    private String customFiller1;
    private String customFiller2;

    private String maritalStatus;
    private String shortName;



}
