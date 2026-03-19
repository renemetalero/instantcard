package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequest;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CreacionClienteVisionRequestTest {

    @InjectMocks
    private CreatePojos create;

    @Test
    @DisplayName(value = "CreacionClienteVisionRequestTest ==> testCreacionClienteVisionRequest")
    void testCreacionClienteVisionRequest() {
        CreacionClienteVisionRequest creacionClienteVisionRequest = create.createCreacionClienteVisionRequest();
        creacionClienteVisionRequest.getMetadata();
        creacionClienteVisionRequest.getData();
        creacionClienteVisionRequest.getData().getBody();
        creacionClienteVisionRequest.getData().getBody().getCustomerNumber();
        creacionClienteVisionRequest.getData().getBody().getOrganization();
        creacionClienteVisionRequest.getData().getBody().getCustomerOwner();
        creacionClienteVisionRequest.getData().getBody().getVipStatus();
        creacionClienteVisionRequest.getData().getBody().getStatusCustomer();
        creacionClienteVisionRequest.getData().getBody().getCustomerMaritalStatus();
        creacionClienteVisionRequest.getData().getBody().getTypeNameLine1();
        creacionClienteVisionRequest.getData().getBody().getTypeNameLine2();
        creacionClienteVisionRequest.getData().getBody().getTypeNameLine3();
        creacionClienteVisionRequest.getData().getBody().getCountryCode();
        creacionClienteVisionRequest.getData().getBody().getMailingList();
        creacionClienteVisionRequest.getData().getBody().getContactIndicator();
        creacionClienteVisionRequest.getData().getBody().getHomePhoneFlag();
        creacionClienteVisionRequest.getData().getBody().getFaxFlagIndicator();
        creacionClienteVisionRequest.getData().getBody().getMobilePhoneFlag();
        creacionClienteVisionRequest.getData().getBody().getEmployerPhoneFlag();
        creacionClienteVisionRequest.getData().getBody().getEmailFlag();
        creacionClienteVisionRequest.getData().getBody().getShortMessageServiceFlag();
        creacionClienteVisionRequest.getData().getBody().getStatement2Indicator();
        creacionClienteVisionRequest.getData().getBody().getCountryCode2();
        creacionClienteVisionRequest.getData().getBody().getCountryCode3();
        creacionClienteVisionRequest.getData().getBody().getSicCode();
        creacionClienteVisionRequest.getData().getBody().getDriverLicenseNumber();
        creacionClienteVisionRequest.getData().getBody().getDriverLicenseState();
        creacionClienteVisionRequest.getData().getBody().getDriverLicenseCountry();
        creacionClienteVisionRequest.getData().getBody().getIdentificationNumber();
        creacionClienteVisionRequest.getData().getBody().getStatementMessageIndicator();
        creacionClienteVisionRequest.getData().getBody().getEmployerAddress1();
        creacionClienteVisionRequest.getData().getBody().getEmployerAddress2();
        creacionClienteVisionRequest.getData().getBody().getEmployerPhoneNumber();
        creacionClienteVisionRequest.getData().getBody().getEmployerPhoneExtension();
        creacionClienteVisionRequest.getData().getBody().getRelativeName();
        creacionClienteVisionRequest.getData().getBody().getEmailAddress();
        creacionClienteVisionRequest.getData().getBody().getMemo1();
        creacionClienteVisionRequest.getData().getBody().getMemo2();
        creacionClienteVisionRequest.getData().getBody().getUserDefined1();
        creacionClienteVisionRequest.getData().getBody().getUserDefined2();
        creacionClienteVisionRequest.getData().getBody().getUserDefined3();
        creacionClienteVisionRequest.getData().getBody().getUserDefined4();
        creacionClienteVisionRequest.getData().getBody().getUserDefined5();
        creacionClienteVisionRequest.getData().getBody().getUserDefined6();
        creacionClienteVisionRequest.getData().getBody().getUserDefined7();
        creacionClienteVisionRequest.getData().getBody().getUserDefined8();
        creacionClienteVisionRequest.getData().getBody().getUserDefined9();
        creacionClienteVisionRequest.getData().getBody().getUserDefined10();
        creacionClienteVisionRequest.getData().getBody().getUserDefined11();
        creacionClienteVisionRequest.getData().getBody().getUserDefined13();
        creacionClienteVisionRequest.getData().getBody().getUserDefined14();
        creacionClienteVisionRequest.getData().getBody().getUserDefined15();
        creacionClienteVisionRequest.getData().getBody().getUserDefinedDemographicData1();
        creacionClienteVisionRequest.getData().getBody().getUserDefinedDemographicData2();
        creacionClienteVisionRequest.getData().getBody().getUserDefinedDemographicData3();
        creacionClienteVisionRequest.getData().getBody().getAddress2Indicator();
        creacionClienteVisionRequest.getData().getBody().getAddress3Indicator();
        creacionClienteVisionRequest.getData().getBody().getAddress2Line1();
        creacionClienteVisionRequest.getData().getBody().getAddress2Line2();
        creacionClienteVisionRequest.getData().getBody().getAddress2Line3();
        creacionClienteVisionRequest.getData().getBody().getCustomerCity2();
        creacionClienteVisionRequest.getData().getBody().getPostalCode2();
        creacionClienteVisionRequest.getData().getBody().getAddress3Line1();
        creacionClienteVisionRequest.getData().getBody().getAddress3Line2();
        creacionClienteVisionRequest.getData().getBody().getAddress3Line3();
        creacionClienteVisionRequest.getData().getBody().getCustomerCity3();
        creacionClienteVisionRequest.getData().getBody().getPostalCode3();
        creacionClienteVisionRequest.getData().getBody().getUserDate3();
        creacionClienteVisionRequest.getData().getBody().getCustomerIncome();
        creacionClienteVisionRequest.getData().getBody().getComakerStatementMessageIndicator();
        creacionClienteVisionRequest.getData().getBody().getComakerStatement2Indicator();
        creacionClienteVisionRequest.getData().getBody().getNameMagneticStripe();
        creacionClienteVisionRequest.getData().getBody().getLastNameMagneticStripe();
        assertNotNull(creacionClienteVisionRequest.getMetadata());
    }

}
