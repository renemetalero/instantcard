package com.orchestration.instantcard.mapper;

import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteBody;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequestBody;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseBody;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

@Mapper(imports = InstantCardConstants.class,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    String IBS_SUFFIX = "ResponseIbs";
    String BODY_IBS = "bodyIbs.";
    String STRING_ONE = "1";


    @Mapping(target = "customerNameLine1", source = "customerNameLine1" + IBS_SUFFIX)
    @Mapping(target = "customerNameLine2", source = "customerNameLine2" + IBS_SUFFIX)
    @Mapping(target = "customerNameLine3", source = "customerNameLine3" + IBS_SUFFIX)
    @Mapping(target = "customerAddress1", source = "customerAddress1" + IBS_SUFFIX)
    @Mapping(target = "customerAddress2", source = "customerAddress2" + IBS_SUFFIX)
    @Mapping(target = "customerAddress3", source = "customerAddress3" + IBS_SUFFIX)
    @Mapping(target = "customerAddress4", source = "customerAddress4" + IBS_SUFFIX)
    @Mapping(target = "customerCity", source = "customerCity" + IBS_SUFFIX)
    @Mapping(target = "customerState", source = "customerState" + IBS_SUFFIX)
    @Mapping(target = "postalCode", source = "postalCode" + IBS_SUFFIX)

    //Campos agregados por gtejada el 11-Oct-2023 para poder crear mas tarjetas a cliente que no este en vision+.
    @Mapping(target = "maritalStatus", source = "maritalStatus")
    @Mapping(target = "nameMagneticStripe", source = "nameMagneticStripe")
    @Mapping(target = "lastNameMagneticStripe", source = "lastNameMagneticStripe")
    @Mapping(target = "shortName", source = "shortName")
    @Mapping(target = "genderCode", source = "genderCode")
    @Mapping(target = "customerType", source = "customerType")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth" + IBS_SUFFIX)
    @Mapping(target = "homePhoneNumber", source = "homePhoneNumber" )
    @Mapping(target = "faxNumber", source = "faxNumber")
    @Mapping(target = "mobilePhoneNumber", source = "mobilePhoneNumber" + IBS_SUFFIX)
    @Mapping(target = "flagIdentificationNumber", source = "flagIdentificationNumber" + IBS_SUFFIX)
    @Mapping(target = "identificationNumber", source = "identificationNumber" + IBS_SUFFIX)
    @Mapping(target = "customerEmployer", source = "customerEmployer" + IBS_SUFFIX)
    @Mapping(target = "userDefined12", source = "userDefined12" + IBS_SUFFIX)
    CreacionClienteBody modelToClienteBodyDto(ConsultaIbsResponseBody clienteIbs);


    @Mapping(target = "customerNameLine1", source = BODY_IBS + "customerNameLine1" + IBS_SUFFIX)
    @Mapping(target = "customerNameLine2", source = BODY_IBS + "customerNameLine2" + IBS_SUFFIX)
    @Mapping(target = "customerNameLine3", source = BODY_IBS + "customerNameLine3" + IBS_SUFFIX)
    @Mapping(target = "customerAddress1", source = BODY_IBS + "customerAddress1" + IBS_SUFFIX)
    @Mapping(target = "customerAddress2", source = BODY_IBS + "customerAddress2" + IBS_SUFFIX)
    @Mapping(target = "customerAddress3", source = BODY_IBS + "customerAddress3" + IBS_SUFFIX)
    @Mapping(target = "customerAddress4", source = BODY_IBS + "customerAddress4" + IBS_SUFFIX)
    @Mapping(target = "customerCity", source = BODY_IBS + "customerCity" + IBS_SUFFIX)
    @Mapping(target = "customerState", source = BODY_IBS + "customerState" + IBS_SUFFIX)
    @Mapping(target = "postalCode", source = BODY_IBS + "postalCode" + IBS_SUFFIX)
    @Mapping(target = "mobilePhoneNumber", source = BODY_IBS + "mobilePhoneNumber" + IBS_SUFFIX)
    @Mapping(target = "dateOfBirth", source = BODY_IBS + "dateOfBirth" + IBS_SUFFIX)
    @Mapping(target = "driverLicenseNumber", source = BODY_IBS + "driverLicenseNumber" + IBS_SUFFIX)
    @Mapping(target = "driverLicenseState", source = BODY_IBS + "driverLicenseState" + IBS_SUFFIX)
    @Mapping(target = "driverLicenseCountry", source = BODY_IBS + "driverLicenseCountry" + IBS_SUFFIX)
    @Mapping(target = "identificationNumber", source = BODY_IBS + "identificationNumber" + IBS_SUFFIX)
    @Mapping(target = "statementMessageIndicator", expression = "java(1)")

    @Mapping(target = "customerEmployer", source = BODY_IBS + "customerEmployer" + IBS_SUFFIX)
    @Mapping(target = "employerAddress1", source = BODY_IBS + "employerAddress1" + IBS_SUFFIX)
    @Mapping(target = "employerAddress2", source = BODY_IBS + "employerAddress2" + IBS_SUFFIX)
    @Mapping(target = "employerPhoneNumber", source = BODY_IBS + "employerPhoneNumber" + IBS_SUFFIX)
    @Mapping(target = "employerPhoneExtension", source = BODY_IBS + "employeePhoneExtension" + IBS_SUFFIX)
    @Mapping(target = "relativeName", source = BODY_IBS + "relativeName" + IBS_SUFFIX)
    @Mapping(target = "emailAddress", source = BODY_IBS + "emailAddress" + IBS_SUFFIX)
    @Mapping(target = "memo1", source = BODY_IBS + "memo1" + IBS_SUFFIX)
    @Mapping(target = "memo2", source = BODY_IBS + "memo2" + IBS_SUFFIX)
    @Mapping(target = "userDefined1", source = BODY_IBS + "userDefined1" + IBS_SUFFIX)
    @Mapping(target = "userDefined2", source = BODY_IBS + "userDefined2" + IBS_SUFFIX)
    @Mapping(target = "userDefined3", source = BODY_IBS + "userDefined3" + IBS_SUFFIX)
    @Mapping(target = "userDefined4", source = BODY_IBS + "userDefined4" + IBS_SUFFIX)
    @Mapping(target = "userDefined5", source = BODY_IBS + "userDefined5" + IBS_SUFFIX)
    @Mapping(target = "userDefined6", source = BODY_IBS + "userDefined6" + IBS_SUFFIX)
    @Mapping(target = "userDefined7", source = BODY_IBS + "userDefined7" + IBS_SUFFIX)
    @Mapping(target = "userDefined8", source = BODY_IBS + "userDefined8" + IBS_SUFFIX)
    @Mapping(target = "userDefined9", source = BODY_IBS + "userDefined9" + IBS_SUFFIX)
    @Mapping(target = "userDefined12", source = BODY_IBS + "userDefined12" + IBS_SUFFIX)
    @Mapping(target = "userDefined13", source = BODY_IBS + "userDefined13" + IBS_SUFFIX)
    @Mapping(target = "userDefined14", source = BODY_IBS + "userDefined14" + IBS_SUFFIX)
    @Mapping(target = "userDefined15", source = BODY_IBS + "userDefined15" + IBS_SUFFIX)
    @Mapping(target = "userDefinedDemographicData1", source = BODY_IBS + "userDefinedDemographicData1" + IBS_SUFFIX)
    @Mapping(target = "userDefinedDemographicData2", source = BODY_IBS + "userDefinedDemographicData2" + IBS_SUFFIX)
    @Mapping(target = "userDefinedDemographicData3", source = BODY_IBS + "userDefinedDemographicData3" + IBS_SUFFIX)
    @Mapping(target = "address2Indicator", source = BODY_IBS + "address2Indicator" + IBS_SUFFIX)
    @Mapping(target = "address3Indicator", source = BODY_IBS + "address3Indicator" + IBS_SUFFIX)
    @Mapping(target = "address2Line1", source = BODY_IBS + "address2Line1" + IBS_SUFFIX)
    @Mapping(target = "address2Line2", source = BODY_IBS + "address2Line2" + IBS_SUFFIX)
    @Mapping(target = "address2Line3", source = BODY_IBS + "address2Line3" + IBS_SUFFIX)
    @Mapping(target = "customerCity2", source = BODY_IBS + "customerCity2" + IBS_SUFFIX)
    @Mapping(target = "postalCode2", source = BODY_IBS + "postalCode2" + IBS_SUFFIX)
    @Mapping(target = "address3Line1", source = BODY_IBS + "address3Line1" + IBS_SUFFIX)
    @Mapping(target = "address3Line2", source = BODY_IBS + "address3Line2" + IBS_SUFFIX)
    @Mapping(target = "address3Line3", source = BODY_IBS + "address3Line3" + IBS_SUFFIX)
    @Mapping(target = "customerCity3", source = BODY_IBS + "customerCity3" + IBS_SUFFIX)
    @Mapping(target = "postalCode3", source = BODY_IBS + "postalCode3" + IBS_SUFFIX)
    @Mapping(target = "comakerStatementMessageIndicator", expression = "java(1)")
    @Mapping(target = "comakerStatement2Indicator", expression = "java(STRING_ONE)")
    @Mapping(target = "customerMaritalStatus", source = BODY_IBS + "maritalStatus")
    @Mapping(target = "organization", expression = "java(InstantCardConstants.ORGANIZATION)")
    @Mapping(target = "customerOwner", expression = "java(1)")
    @Mapping(target = "vipStatus", expression = "java(0)")
    @Mapping(target = "statusCustomer", expression = "java(0)")
    @Mapping(target = "typeNameLine1", source = BODY_IBS + "customerType", qualifiedByName = "mapCustomerType")
    @Mapping(target = "countryCode", expression = "java(InstantCardConstants.SLV)")
    @Mapping(target = "mailingList", expression = "java(InstantCardConstants.Y)")
    @Mapping(target = "contactIndicator", expression = "java(0)")
    @Mapping(target = "homePhoneFlag", source = BODY_IBS + "homePhoneNumber", qualifiedByName = "mapFlagVision")
    @Mapping(target = "faxFlagIndicator", source = BODY_IBS + "faxNumber", qualifiedByName = "mapFlagVision")
    @Mapping(target = "mobilePhoneFlag", source = BODY_IBS + "mobilePhoneNumber" + IBS_SUFFIX, qualifiedByName = "mapFlagVision")
    @Mapping(target = "flagIdentificationNumber", expression = "java(1)")
    @Mapping(target = "employerPhoneFlag", source = BODY_IBS + "employerPhoneNumber" + IBS_SUFFIX, qualifiedByName = "mapFlagVision")
    @Mapping(target = "emailFlag", source = BODY_IBS + "emailAddress" + IBS_SUFFIX, qualifiedByName = "mapFlagVision")
    @Mapping(target = "shortMessageServiceFlag", source = BODY_IBS + "mobilePhoneNumber" + IBS_SUFFIX, qualifiedByName = "mapFlagVision")
    @Mapping(target = "statement2Indicator", expression = "java(STRING_ONE)")
    @Mapping(target = "countryCode2", expression = "java(InstantCardConstants.SLV)")
    @Mapping(target = "countryCode3", expression = "java(InstantCardConstants.SLV)")
    @Mapping(target = "nameMagneticStripe", source = "accountInformationDto.name")
    @Mapping(target = "lastNameMagneticStripe", source = "accountInformationDto.lastName")
    CreacionClienteVisionRequestBody modelToVisionRequest(ConsultaIbsResponseBody bodyIbs, AccountInformationDto accountInformationDto);
    @Named("mapCustomerType")
    default Integer customerType(String value) {
        if (value != null) {
            return value.equalsIgnoreCase("1") ? 1 : 0;
        } else {
            return 0;
        }
    }

    @Named("mapFlagVision")
    default Integer flagVision(String value) {
        return StringUtils.hasText(value)? 1 : 0;
    }

    @AfterMapping
    default void afterMappingClient(
            @MappingTarget CreacionClienteVisionRequestBody visionReqDto) {

        String customerLine2 = visionReqDto.getCustomerNameLine2();
        Integer customerTypeInt = visionReqDto.getTypeNameLine1();
        visionReqDto.setTypeNameLine2(validateNameLine(customerLine2, customerTypeInt
        ));
        String customerLine3 = visionReqDto.getCustomerNameLine3();
        visionReqDto.setTypeNameLine3(validateNameLine(customerLine3, customerTypeInt
        ));

    }

    default Integer formatInteger(String value) {
        return StringUtils.hasText(value) ? Integer.parseInt(value) :0;
    }

    default Integer validateNameLine(String customerNameLine, Integer customerTypeInt) {
        if (StringUtils.hasText(customerNameLine)) {
            return customerTypeInt;
        } else
            return 0;
    }
}