package com.orchestration.instantcard.utils;

import com.orchestration.instantcard.exception.enums.InstantCardEnumError;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.*;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.request.InstantCardRequestData;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogBody;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogData;
import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogReq;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteBody;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteData;
import com.orchestration.instantcard.models.request.messages.cmc.customer.CreacionClienteRequest;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestDTO;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestData;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequest;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequestBody;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequestData;
import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequest;
import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequestBody;
import com.orchestration.instantcard.models.request.messages.ibs.ConsultaIbsRequestData;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.AccountDataFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.EmbosserDataFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.RequestFirservInstantCardDto;
import com.orchestration.instantcard.models.request.messages.vision.customer.AccountInformationDto;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequest;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequestBody;
import com.orchestration.instantcard.models.request.messages.vision.customer.CreacionClienteVisionRequestData;
import com.orchestration.instantcard.models.response.InstantCardResponseBody;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogDetail;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogResBody;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogResData;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponse;
import com.orchestration.instantcard.models.response.messages.cmc.customer.CreacionClienteResponseData;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponse;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponseBody;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponseData;
import com.orchestration.instantcard.models.response.messages.cmc.validations.TarjetaBodyResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaBodyResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaDataResponse;
import com.orchestration.instantcard.models.response.messages.cmc.validations.ValidacionTarjetaResponse;
import com.orchestration.instantcard.models.response.messages.gestiontarjeta.EmbosserUpdateResponse;
import com.orchestration.instantcard.models.response.messages.gestiontarjeta.EmbosserUpdateResponseBody;
import com.orchestration.instantcard.models.response.messages.gestiontarjeta.EmbosserUpdateResponseData;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponse;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseBody;
import com.orchestration.instantcard.models.response.messages.ibs.ConsultaIbsResponseData;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponse;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponse;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponseData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class CreatePojos {
    public InstantCardRequest createRequest() {
        InstantCardRequest request = new InstantCardRequest();

        request.setMetadata(this.createMeta());
        request.setData(new InstantCardRequestData());
        request.getData().setHeader(this.createHeader());
        request.getData().setBody(new InstantCardRequestBody());

        return request;
    }

    public InstantCardRequestBody createRequestBody() {
        InstantCardRequestBody body = new InstantCardRequestBody();
        body.setOrganization(968l);
        body.setProductType("yNl");
        body.setLogo("015");
        body.setCustomerNumber("FdU04XzqBsmvPqaDGre");
        body.setCycle(32l);
        body.setPct("E71");
        body.setOwningBranch(353730468l);
        body.setPctOvStartdt(6242078l);
        body.setPctOvEnddt(8539760l);
        body.setAccountCashPlanNumber(86503l);
        body.setAccountRetailPlanNumber(34052l);
        body.setAccountPromoPlanNumber(81051l);
        body.setDirectDebitAccount("cqcmc8i6iSvFnSb8X26");
        body.setDdRoutingNumber("ksuvu4wQMG");
        body.setCustomerSelectedDueDate(75l);
        body.setTemporaryCreditLimit(new BigInteger("54592020766362470"));
        body.setTemporaryCreditLimitExpDt(9400181l);
        body.setOfficerCode(159l);
        body.setAuthLimitOverride(9l);
        body.setCardNumber("5dR4P0FVQXzdJOaWQxh");
        body.setCreditLimit(new BigDecimal("78684597704157120"));
        body.setEmblemId("63519");
        body.setEmbossedName1("TznqUx8MJPnti1II3ntps5VY2a");
        body.setEmbossedName2("dNJJxGPZeNWBQq3Na8DRTBfKHR");
        body.setPlasticId("qYIHyQYn2N");
        body.setEmail("6M9kAjWBRgsfZXSiwOJU356KHyTUzOZ3MpX9crMiY44Os1eCST3cwSgD71G3");
        body.setPhoneCountryCode("vG9");
        body.setAreaCode("t8G");
        body.setPhoneNumber("4HXdXZABYAlCeBPQKou1");
        body.setSaleExecutive("EtflaZJ0ap6");
        body.setCustomerMaritalStatus(2l);
        body.setCustomerAddressLine1("NaihtYhC0fQcbkv8A8LRq3ISux1D7SRCkTBGqihB");
        body.setCustomerAddressLine2("bOyT0HBTVmvaJh9CYAe6NF6oBTYXW7n3iY8SPTLS");
        body.setCustomerCity("vh1kU1h1C19Fi4WErbSkZGhw54RjW8");
        body.setCustomerState("DUs");
        body.setRequestNumberMantiz("nqCHQxwXTRoDv8r9wSwH");
        body.setBillingAccountIndicator(4l);
        body.setRelationshipPrimaryAccountFlag(4l);
        body.setRelationshipNumber(new BigInteger("7121846950818442000"));
        body.setDualbillingflag("Z");
        body.setAlternateCustomerNumberFlag("7");
        body.setAlternateCustomerStartDate("alternateCustomerStart");
        body.setAlternateCustomerExpireDate("alternateCustomerExpireDate");
        body.setAlternateCustomerNumber("wwOZqWJnpaxVpqaF7F7");
        body.setCustomerSelectedDueDay("v0");
        body.setBlockCode1("i");
        body.setBlockCode2("T");
        body.setProcessingControlTableControlId("fOWWnBpO7MdfZnnhlRp");
        body.setProcessingControlTableLevelStartDate("");
        body.setProcessingControlTableLevelExpireDate("");
        body.setSavingsRoutingNumber(23894);
        body.setSavingsAccountNumber("uSmY6DeNcpstgEBySkb");
        body.setUserAccountNumber("BUUGu7jmFWNsNOGyfJe");
        body.setPrepaidPlanNumber(54345);
        body.setPrepaidLoadFrequency("q");
        body.setPrepaidLoadNumber(456);
        body.setPrepaidLoadAmount(98l);
        body.setMinimumSingleLoadAmount(955l);
        body.setMaximumSingleLoadAmount(5759l);
        body.setInternationalCashPlan(3948);
        body.setInternationalRetailPlan(7654);
        body.setInternationalInstallmentPlan(958);
        body.setCashPlanInstallmentBilling(344);
        body.setRetailPlanInstallmentBilling(667);
        body.setAcquirerRetailPlan(156);
        body.setAuthorizationControlTable(672);
        body.setRetailPlanInstallmentPayments(98677);
        body.setCashPlanInstallmentPayment(2475);
        body.setQualification("v");
        body.setPostalCode("uED0josj7o");
        body.setLanguageCode("DDB");
        body.setAtmCashNumber(849183556l);
        body.setAtmCashAmount(new BigInteger("12205042217218378"));
        body.setAtmCashSingleTransactionLimit(new BigInteger("86704343953252080"));
        body.setOverTheCounterCashNumber(127958044l);
        body.setOverTheCounterCashAmount(new BigInteger("58644063563609224"));
        body.setOverTheCounterCashSingleTransactionLimit(new BigInteger("38480616519591900"));
        body.setRetailPurchaseNumber(874028876l);
        body.setRetailPurchaseAmt(new BigInteger("94734024245174960"));
        body.setRetailPurchaseSingleTransactionLimit(new BigInteger("95584964534996910"));
        body.setInternetPurchaseAmount(new BigInteger("54506639709339200"));
        body.setInternetPurchaseNumber(882393752l);
        body.setInternetPurchaseSingleTransactionLimit(new BigInteger("8612372546074"));
        body.setDeliveryOption(1);
        body.setReissueDeliveryOption("v");
        body.setAuthorizationCriteriaTableNumber("ujc");
        body.setProgramId(345);
        body.setCardActionReasonCode("L");
        body.setIcTotalNumberTransactions(76);
        body.setIcAmountLimitSingleTransaction(865l);
        return body;
    }

    public Metadata createMeta() {
        Metadata meta = new Metadata();
        meta.setMessageType("Request");
        meta.setDatetime("2023-04-01T14:21:33");
        meta.setMessageIdOrg("1687045");
        meta.setApplicationId("OmnicanalEmpresas");
        meta.setServiceId("card-Add");
        meta.setShortMessageId("1687045");
        meta.setMessageId("1687045");
        meta.setMessageId("1687045");
        return meta;
    }

    public Header createHeader() {
        Header header = new Header();
        header.setDate("20230401");
        header.setSystem("sistem");
        header.setInstanceId("01");
        header.setHour("142133");
        header.setLocationUser("127.0.0.1");
        header.setBatch("3740");
        header.setBranch("037");
        header.setSupervisor("0080");
        header.setChannelId("3740");
        header.setUser("samuel");

        return header;
    }

    public CreacionClienteRequest createClient() {
        CreacionClienteRequest request = new CreacionClienteRequest();
        request.setMetadata(this.createMeta());
        request.setData(new CreacionClienteData());
        request.getData().setBody(new CreacionClienteBody());
        return request;
    }

    public ValidacionTarjetaResponse createValidacionTarjetaDataResponse() {

        var tarjetaBodyResponse = new TarjetaBodyResponse();
        tarjetaBodyResponse.setCardNumber("4334545675");
        tarjetaBodyResponse.setProductType("TCD");
        tarjetaBodyResponse.setStatus("200");
        tarjetaBodyResponse.setCustomerNumber("customerNumber");
        tarjetaBodyResponse.setStatusCard("ok");
        tarjetaBodyResponse.setDescription("description");
        tarjetaBodyResponse.setLogo("019");
        tarjetaBodyResponse.setAccountNumber("AcountNumber");

        tarjetaBodyResponse.getCardNumber();
        tarjetaBodyResponse.getProductType();
        tarjetaBodyResponse.getStatus();
        tarjetaBodyResponse.getCustomerNumber();
        tarjetaBodyResponse.getStatusCard();
        tarjetaBodyResponse.getDescription();
        tarjetaBodyResponse.getLogo();
        tarjetaBodyResponse.getAccountNumber();

        var validacionTarjetaBodyResponse = new ValidacionTarjetaBodyResponse();

        validacionTarjetaBodyResponse.setCustomerNumberResponseBody("CustumerNumber");
        validacionTarjetaBodyResponse.setCustomerNameLine1ResponseBody("CustomerNameLine1");
        validacionTarjetaBodyResponse.setCustomerNameLine2ResponseBody("CustomerNameLine2");
        validacionTarjetaBodyResponse.setCustomerNameLine3ResponseBody("CustomerNameLine3");
        validacionTarjetaBodyResponse.setCustomerAddress1ResponseBody("CustomerAddress1");
        validacionTarjetaBodyResponse.setCustomerAddress2ResponseBody("CustomerAddress2");
        validacionTarjetaBodyResponse.setCustomerAddress3ResponseBody("CustomerAddress3");
        validacionTarjetaBodyResponse.setCustomerAddress4ResponseBody("CustomerAddress4");
        validacionTarjetaBodyResponse.setCustomerCityResponseBody("CustomerCity");
        validacionTarjetaBodyResponse.setCustomerStateResponseBody("CustomerState");
        validacionTarjetaBodyResponse.setPostalCodeResponseBody("PostalCode");
        validacionTarjetaBodyResponse.setTarjetaResponseBody(tarjetaBodyResponse);
        validacionTarjetaBodyResponse.setNameMagneticStripe("");
        validacionTarjetaBodyResponse.setLastNameMagneticStripe("LastName");
        validacionTarjetaBodyResponse.setShortName("ShortName");

        validacionTarjetaBodyResponse.getCustomerNumberResponseBody();
        validacionTarjetaBodyResponse.getCustomerNameLine1ResponseBody();
        validacionTarjetaBodyResponse.getCustomerNameLine2ResponseBody();
        validacionTarjetaBodyResponse.getCustomerNameLine3ResponseBody();
        validacionTarjetaBodyResponse.getCustomerAddress1ResponseBody();
        validacionTarjetaBodyResponse.getCustomerAddress2ResponseBody();
        validacionTarjetaBodyResponse.getCustomerAddress3ResponseBody();
        validacionTarjetaBodyResponse.getCustomerAddress4ResponseBody();
        validacionTarjetaBodyResponse.getCustomerCityResponseBody();
        validacionTarjetaBodyResponse.getCustomerStateResponseBody();
        validacionTarjetaBodyResponse.getPostalCodeResponseBody();
        validacionTarjetaBodyResponse.getTarjetaResponseBody();
        validacionTarjetaBodyResponse.getNameMagneticStripe();
        validacionTarjetaBodyResponse.getLastNameMagneticStripe();
        validacionTarjetaBodyResponse.getShortName();


        var validacionTarjetaDataResponse = new ValidacionTarjetaDataResponse();
        validacionTarjetaDataResponse.setBody(validacionTarjetaBodyResponse);
        ValidacionTarjetaResponse validacionTarjetaResponse = new ValidacionTarjetaResponse();
        validacionTarjetaResponse.setData(validacionTarjetaDataResponse);
        validacionTarjetaResponse.getData();

        validacionTarjetaResponse.setMetadata(this.createMeta());
        validacionTarjetaResponse.getMetadata();

        validacionTarjetaResponse.setErrors(Collections.emptyList());
        validacionTarjetaResponse.getErrors();

        return  validacionTarjetaResponse;
    }

    public CatalogRes createCatalogResponse() {
        CatalogRes catalog = new CatalogRes();
        CatalogResData catalogResData = new CatalogResData();
        CatalogResBody catalogResBody = new CatalogResBody();
        CatalogDetail catalogDetail = new CatalogDetail();
        catalogDetail.setCode("206");
        catalogDetail.setDsc("dsc");
        catalogDetail.setIdCatalogo(1);
        catalogDetail.setValorInt(3L);

        catalogResBody.setCatList(List.of(catalogDetail));
        catalogResData.setBody(catalogResBody);
        catalog.setData(catalogResData);
        return catalog;
    }

    public ConsultaIbsRequest createFindIbs() {
        ConsultaIbsRequest request = new ConsultaIbsRequest();
        request.setMetadata(this.createMeta());
        request.setData(new ConsultaIbsRequestData());
        request.getData().setBody(new ConsultaIbsRequestBody());
        return request;
    }

    public CreacionClienteVisionRequest createFindVision() {
        CreacionClienteVisionRequest request = new CreacionClienteVisionRequest();
        request.setMetadata(this.createMeta());
        request.setData(new CreacionClienteVisionRequestData());
        request.getData().setBody(new CreacionClienteVisionRequestBody());
        return request;
    }

    public InstantCardRequestBody createInstantCardBody() {
        InstantCardRequestBody body = new InstantCardRequestBody();
        body.setOrganization(540l);
        body.setProductType("TCD");
        body.setLogo("206");
        body.setCustomerNumber("0000000000004275153");
        body.setCycle(3l);
        body.setPct("066");
        body.setOwningBranch(121l);
        body.setPctOvStartdt(2471840l);
        body.setPctOvEnddt(5030608l);
        body.setAccountCashPlanNumber(73585l);
        body.setAccountRetailPlanNumber(43978l);
        body.setAccountPromoPlanNumber(67398l);
        body.setDirectDebitAccount("XEo4bUQ77qm5cE966Vr");
        body.setDdRoutingNumber("zoEY3qir7q");
        body.setCustomerSelectedDueDate(30l);
        body.setTemporaryCreditLimit(null);
        body.setTemporaryCreditLimitExpDt(3550438l);
        body.setOfficerCode(842l);
        body.setAuthLimitOverride(4l);
        body.setCardNumber("ab8f84jsle03jdwi39j");
        body.setCreditLimit(new BigDecimal("50000"));
        body.setEmblemId("00000");
        body.setEmbossedName1("LUIS G ALVARADO");
        body.setEmbossedName2(null);
        body.setPlasticId("");  // String vacío
        body.setEmail("WgpSqS9WHZLb5QbwpSCHeZL3tLKxCr2P9RqIsQH5MRIk10b2aDZuj5mjgR7y");
        body.setPhoneCountryCode("qaC");
        body.setAreaCode("asp");
        body.setPhoneNumber("7mLNsIReGBv1P1v5l1ZB");
        body.setSaleExecutive("1jkQUF1pda7");
        body.setCustomerMaritalStatus(8l);
        body.setCustomerAddressLine1("69 AV SUR PFE SENDA FLORIDA");
        body.setCustomerAddressLine2("CENTRO DE OPERACIONES BA");
        body.setCustomerCity("SAN SALVADOR");
        body.setCustomerState("SLV");
        body.setRequestNumberMantiz("ki2kOFjXmW76PxdL0YrH");
        body.setBillingAccountIndicator(null);
        body.setRelationshipPrimaryAccountFlag(null);
        body.setRelationshipNumber(null);
        body.setDualbillingflag("f");
        body.setAlternateCustomerNumberFlag(null);
        body.setAlternateCustomerStartDate(null);
        body.setAlternateCustomerExpireDate(null);
        body.setAlternateCustomerNumber(null);
        body.setCustomerSelectedDueDay(null);
        body.setBlockCode1(null);
        body.setBlockCode2(null);
        body.setProcessingControlTableControlId("123");
        body.setProcessingControlTableLevelStartDate(null);
        body.setProcessingControlTableLevelExpireDate(null);
        body.setSavingsRoutingNumber(null);
        body.setSavingsAccountNumber(null);
        body.setUserAccountNumber(null);
        body.setPrepaidPlanNumber(null);
        body.setPrepaidLoadFrequency(null);
        body.setPrepaidLoadNumber(null);
        body.setPrepaidLoadAmount(null);
        body.setMinimumSingleLoadAmount(null);
        body.setMaximumSingleLoadAmount(null);
        body.setInternationalCashPlan(300);
        body.setInternationalRetailPlan(null);
        body.setInternationalInstallmentPlan(null);
        body.setCashPlanInstallmentBilling(null);
        body.setRetailPlanInstallmentBilling(null);
        body.setAcquirerRetailPlan(null);
        body.setAuthorizationControlTable(null);
        body.setRetailPlanInstallmentPayments(null);
        body.setCashPlanInstallmentPayment(null);
        body.setQualification(null);
        body.setPostalCode("PwUc2aHhI6");
        body.setLanguageCode(null);
        body.setAtmCashNumber(null);
        body.setAtmCashAmount(null);
        body.setAtmCashSingleTransactionLimit(null);
        body.setOverTheCounterCashNumber(null);
        body.setOverTheCounterCashAmount(null);
        body.setOverTheCounterCashSingleTransactionLimit(null);
        body.setRetailPurchaseNumber(null);
        body.setRetailPurchaseAmt(null);
        body.setRetailPurchaseSingleTransactionLimit(null);
        body.setInternetPurchaseAmount(null);
        body.setInternetPurchaseNumber(null);
        body.setInternetPurchaseSingleTransactionLimit(null);
        body.setDeliveryOption(null);
        body.setReissueDeliveryOption(null);
        body.setAuthorizationCriteriaTableNumber(null);
        body.setProgramId(null);
        body.setCardActionReasonCode(null);
        body.setIcTotalNumberTransactions(null);
        body.setIcAmountLimitSingleTransaction(null);
        body.setDateOpened(null);
        return body;
    }

    public InstantCardRequest createInstantCard() {
        InstantCardRequestData instantCardRequestData = new InstantCardRequestData();
        instantCardRequestData.setHeader(this.createHeader());
        instantCardRequestData.setBody(this.createInstantCardBody());

        InstantCardRequest request = new InstantCardRequest();
        request.setMetadata(this.createMeta());
        request.setData(instantCardRequestData);

        return request;
    }

    public ConsultaIbsResponse createValidateDataIbsResponse() {
        ConsultaIbsResponse consultaIbsResponse = new ConsultaIbsResponse();
        ConsultaIbsResponseData consultaIbsResponseData = new ConsultaIbsResponseData();
        consultaIbsResponseData.setBody(new ConsultaIbsResponseBody());
        consultaIbsResponseData.setHeader(this.createHeader());
        consultaIbsResponseData.getBody();
        consultaIbsResponseData.getHeader();
        consultaIbsResponse.setData(consultaIbsResponseData);
        return consultaIbsResponse;
    }

    public CreacionClienteVisionResponse createVisionCustomerResponse() {
        CreacionClienteVisionResponse creacionClienteVisionResponse = new CreacionClienteVisionResponse();
        creacionClienteVisionResponse.setData(new CreacionClienteVisionResponseData());
        creacionClienteVisionResponse.setErrors(null);
        return creacionClienteVisionResponse;
    }

    public CreacionClienteResponse creacionClienteResponse() {
        CreacionClienteResponse creacionClienteResponse = new CreacionClienteResponse();
        creacionClienteResponse.setData(new CreacionClienteResponseData());
        creacionClienteResponse.setErrors(null);
        return creacionClienteResponse;
    }

    public CreacionClienteResponse creacionClienteResponseWithError() {
        CreacionClienteResponse creacionClienteResponse = new CreacionClienteResponse();
        creacionClienteResponse.setData(new CreacionClienteResponseData());
        creacionClienteResponse.setErrors(List.of(new ErrorModel("500", "title error", "EL LOGO NO ES VALIDO.")));
        return creacionClienteResponse;
    }

    public CreacionTarjetaFiservResponse createCreacionTarjetaFiservResponse() {
        CreacionTarjetaFiservResponse creacionTarjetaFiservResponse = new CreacionTarjetaFiservResponse();
        CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData = new CreacionTarjetaFiservResponseData();

        creacionTarjetaFiservResponseData.setOrganizationNumber(0l);
        creacionTarjetaFiservResponseData.setAccountNumber("Test mock");
        creacionTarjetaFiservResponseData.setLogo("Logo Mock");
        creacionTarjetaFiservResponseData.setCustomerNumber("Customer Mock");
        creacionTarjetaFiservResponseData.setAccountNumber("Account Number Mock");
        creacionTarjetaFiservResponseData.setCardNumber("card number Mock");
        creacionTarjetaFiservResponseData.setCardSequenceNumber("Sequence Mock");
        creacionTarjetaFiservResponseData.setCreditLimit(0l);
        creacionTarjetaFiservResponseData.setCardExpiryDate("CardExpiryDate Mock");
        creacionTarjetaFiservResponse.setData(creacionTarjetaFiservResponseData);
        return creacionTarjetaFiservResponse;
    }

    public CreacionTarjetaFiservResponse createCreacionTarjetaFiservResponseWithErrors() {
        CreacionTarjetaFiservResponse creacionTarjetaFiservResponse = new CreacionTarjetaFiservResponse();
        creacionTarjetaFiservResponse.setHasError(true);
        creacionTarjetaFiservResponse.setErrors(List.of(new ErrorModel(
                InstantCardEnumError.BUSINESS_VISION_FISERV.getCode(),
                InstantCardEnumError.BUSINESS_VISION_FISERV.getTitle(),
                InstantCardEnumError.BUSINESS_VISION_FISERV.getMessage())));
        return creacionTarjetaFiservResponse;
    }

    public FieldsCardDto createFieldsCardDto() {
        FieldsCardDto fieldsCardDto = new FieldsCardDto();
        fieldsCardDto.setAddressLine1("addressLine1");
        fieldsCardDto.setAddressLine2("addressLine2");
        fieldsCardDto.setCity("city");
        fieldsCardDto.setProvince("province");
        fieldsCardDto.setCustomerNumber("customerNumber");
        fieldsCardDto.setAccountNumber("accountNumber");
        fieldsCardDto.setLogo("logo");
        fieldsCardDto.setShortName("shortName");
        return fieldsCardDto;
    }

    public LoginTokenFiservResponse createLoginFiservResponse() {
        LoginTokenFiservResponse loginTokenFiservResponse = new LoginTokenFiservResponse();
        loginTokenFiservResponse.setToken_type("token_type");
        loginTokenFiservResponse.setIssued_at("issued_at");
        loginTokenFiservResponse.setClient_id("client_id");
        loginTokenFiservResponse.setAccess_token("access_token");
        loginTokenFiservResponse.setExpires_in("expires_in");
        loginTokenFiservResponse.setStatus("status");
        return loginTokenFiservResponse;
    }

    public CreacionCuentaFieldGeneric creacionCuentaFieldGeneric() {
        CreacionCuentaFieldGeneric cuenta = new CreacionCuentaFieldGeneric();
        cuenta.setLogo(1L);
        cuenta.setCurrencyCode("840");
        cuenta.setCustomerNumber("FdU04XzqBsmvPqaDGre");
        cuenta.setCycle(32L);
        cuenta.setPct("009");
        cuenta.setOwningBranch(353730468L);
        cuenta.setDdPaymentType(0L);
        cuenta.setCustomerSelectedDueDate(75L);
        cuenta.setTemporaryCreditLimit(new BigDecimal("54592020766362470"));
        cuenta.setTemporaryCreditLimitExpDt(9400181L);
        cuenta.setAccountCashPlanNumber(86503L);
        cuenta.setAccountRetailPlanNumber(34052L);
        cuenta.setAccountPromoPlanNumber(81051L);
        cuenta.setDirectDebitAccount("cqcmc8i6iSvFnSb8X26");
        cuenta.setDdAccountType("d");
        cuenta.setCustomerNameLine1("NameLine1");
        cuenta.setCustomerNameLine2("NameLine2");
        cuenta.setCustomerNameLine3("NameLine3");
        cuenta.setCustomerAddress1("Address1");
        cuenta.setCustomerAddress2("Address2");
        cuenta.setCustomerAddress3("Address3");
        cuenta.setCustomerAddress4("Address4");
        cuenta.setCustomerCity("City");
        cuenta.setCustomerState("State");
        cuenta.setPostalCode("PostalCode");
        cuenta.setMaritalStatus("MaritalStatus");
        cuenta.setNameMagneticStripe("NameMagneticStripe");
        cuenta.setLastNameMagneticStripe("LastNameMagneticStripe");
        cuenta.setGenderCode(0);
        cuenta.setCustomerType("CustomerType");
        cuenta.setDateOfBirth(0);
        cuenta.setHomePhoneNumber("PhoneNumber");
        cuenta.setFaxNumber("FaxNumber");
        cuenta.setMobilePhoneNumber("MobilePhoneNumber");
        cuenta.setFlagIdentificationNumber(0);
        cuenta.setIdentificationNumber("IdentificationNumber");
        cuenta.setCustomerEmployer("CustomerEmployer");
        cuenta.setUserDefined12("UserDefined12");

        return cuenta;
    }

    public AccountInformationDto createAccountInformationDto() {
        AccountInformationDto.AccountInformationDtoBuilder accountInformationDtoBuilder = AccountInformationDto.builder();
        accountInformationDtoBuilder.nameMagneticStripe("name");
        accountInformationDtoBuilder.lastNameMagneticStripe("lastName");
        accountInformationDtoBuilder.shortName("shortName");
        return accountInformationDtoBuilder.build();
    }

    public RequestFirservInstantCardDto createRequestFiservInstantCardDto() {
        RequestFirservInstantCardDto requestFirservInstantCardDto = new RequestFirservInstantCardDto();
        requestFirservInstantCardDto.setAddressLine1("addressLine1");
        requestFirservInstantCardDto.setAddressLine2("addressLine2");
        requestFirservInstantCardDto.setCity("city");
        requestFirservInstantCardDto.setProvince("province");
        requestFirservInstantCardDto.setCustomerNumber("customerNumber");
        requestFirservInstantCardDto.setAccountNumber("accountNumber");
        requestFirservInstantCardDto.setLogo("logo");
        requestFirservInstantCardDto.setShortName("shortName");
        requestFirservInstantCardDto.setDateOpened("dateOpened");
        return requestFirservInstantCardDto;
    }

    public InstantCardResponseBody createInstantCardResponseBody() {
        InstantCardResponseBody instantCardResponseBody = new InstantCardResponseBody();

        instantCardResponseBody.setCustomerNumber("customerNumber");
        instantCardResponseBody.setCreditCardAccountNumber("creditCardAccountNumber");
        instantCardResponseBody.setCreditCardNumber("creditCardNumber");
        instantCardResponseBody.setCreationDate("creationDate");
        instantCardResponseBody.setCustomFillerInd1("customFillerInd1");
        instantCardResponseBody.setCustomFiller1("customFiller1");
        instantCardResponseBody.setCustomeFillerInd2("customeFillerInd2");
        instantCardResponseBody.setCustomFiller2("customFiller2");

        return instantCardResponseBody;
    }

    public CatalogReq createCatalogRequest(){
        CatalogReq catalogReq = new CatalogReq();
        catalogReq.setMetadata(createMeta());
        catalogReq.setData(new CatalogData());
        catalogReq.getData().setBody(new CatalogBody());
        return catalogReq;
    }

    public AccountDataFiservRequest createAccountDataFiservRequest() {
        AccountDataFiservRequest accountDataFiservRequest = new AccountDataFiservRequest();
        accountDataFiservRequest.setBillingCycle(32);
        accountDataFiservRequest.setAccountNumber("FdU04XzqBsmvPqaDGre");
        accountDataFiservRequest.setBillingAccountIndicator(0);
        accountDataFiservRequest.setRelationshipPrimaryAccountFlag("1");
        accountDataFiservRequest.setRelationshipNumber("34052");
        accountDataFiservRequest.setShortName("ShortName");
        accountDataFiservRequest.setCreditLimit("746");
        accountDataFiservRequest.setBillingCurrency(840);
        accountDataFiservRequest.setRelationshipBillingLevel(0);
        accountDataFiservRequest.setDualBillingFlag(0);
        accountDataFiservRequest.setRelationshipCardScheme(0);
        accountDataFiservRequest.setAlternateCustomerNumberFlag("1");
        accountDataFiservRequest.setAlternateCustomerStartDate("alternateCustomerStart");
        accountDataFiservRequest.setAlternateCustomerExpireDate("alternateCustomerExpireDate");
        accountDataFiservRequest.setAlternateCustomerNumber("wwOZqWJnpaxVpqaF7F7");
        accountDataFiservRequest.setDateOpened("dateOpened");
        accountDataFiservRequest.setCustomerSelectedDueDay("v0");
        accountDataFiservRequest.setStatementFlag(0);
        accountDataFiservRequest.setStatementFrequency(0);
        accountDataFiservRequest.setBlockCode1("i");
        accountDataFiservRequest.setBlockCode2("T");
        accountDataFiservRequest.setProcessingControlTableControlId("fOWWnBpO7MdfZnnhlRp");
        accountDataFiservRequest.setProcessingControlTableStartDate("processingControlTableStartDate");
        accountDataFiservRequest.setProcessingControlTableExpireDate("processingControlTableExpireDate");
        accountDataFiservRequest.setProcessingControlTableLevel("processingControlTableLevel");
        accountDataFiservRequest.setProcessingControlTableLevelStartDate("processingControlTableLevelStartDate");
        accountDataFiservRequest.setProcessingControlTableLevelExpireDate("processingControlTableLevelExpireDate");
        accountDataFiservRequest.setCashPlanNumber(86503);
        accountDataFiservRequest.setRetailPlanNumber(34052);
        accountDataFiservRequest.setPromoPlanNumber(81051);
        accountDataFiservRequest.setDdaRoutingNumber(353730468);
        accountDataFiservRequest.setDdaAccountNumber("cqcmc8i6iSvFnSb8X26");
        accountDataFiservRequest.setSavingsRoutingNumber(23894);
        accountDataFiservRequest.setSavingsAccountNumber("uSmY6DeNcpstgEBySkb");
        accountDataFiservRequest.setUserAccountNumber("BUUGu7jmFWNsNOGyfJe");
        accountDataFiservRequest.setCardTechnology(0);
        accountDataFiservRequest.setAuthorizationLimitOverrides(0L);
        accountDataFiservRequest.setTemporaryCreditLimit(674l);
        accountDataFiservRequest.setDateTemporaryCreditLimit("dateTemporaryCreditLimit");
        accountDataFiservRequest.setStateOfResidenceId("stateOfResidenceId");
        accountDataFiservRequest.setStateOfIssuanceId("stateOfIssuanceId");
        accountDataFiservRequest.setOwningBranchNumber(353730468);
        accountDataFiservRequest.setUserCode1("userCode1");
        accountDataFiservRequest.setUserCode2("userCode2");
        accountDataFiservRequest.setUserCode3("userCode3");
        accountDataFiservRequest.setUserCode4("userCode4");
        accountDataFiservRequest.setUserCode5("userCode5");
        accountDataFiservRequest.setUserCode6("userCode6");
        accountDataFiservRequest.setUserCode7("userCode7");
        accountDataFiservRequest.setUserCode8("userCode8");
        accountDataFiservRequest.setUserCode9("userCode9");
        accountDataFiservRequest.setUserCode10("userCode10");
        accountDataFiservRequest.setUserCode11("userCode11");
        accountDataFiservRequest.setUserCode12("userCode12");
        accountDataFiservRequest.setUserCode13("userCode13");
        accountDataFiservRequest.setUserCode14("userCode14");
        accountDataFiservRequest.setUserDate1("userDate1");
        accountDataFiservRequest.setUserDate2("userDate2");
        accountDataFiservRequest.setUserDate3("userDate3");
        accountDataFiservRequest.setUserDate4("userDate4");
        accountDataFiservRequest.setUserDate5("userDate5");
        accountDataFiservRequest.setUserDate6("userDate6");
        accountDataFiservRequest.setUserDate7("userDate7");
        accountDataFiservRequest.setUserDate8("userDate8");
        accountDataFiservRequest.setUserDate9("userDate9");
        accountDataFiservRequest.setUserDate10("userDate10");
        accountDataFiservRequest.setUserDate11("userDate11");
        accountDataFiservRequest.setUserDate12("userDate12");
        accountDataFiservRequest.setUserDate13("userDate13");
        accountDataFiservRequest.setUserDate14("userDate14");
        accountDataFiservRequest.setUserAmount1(0L);
        accountDataFiservRequest.setUserAmount2(0L);
        accountDataFiservRequest.setUserAmount3(0L);
        accountDataFiservRequest.setUserAmount4(0L);
        accountDataFiservRequest.setUserAmount5(0L);
        accountDataFiservRequest.setUserAmount6(0L);
        accountDataFiservRequest.setUserAmount7(0L);
        accountDataFiservRequest.setUserAmount8(0L);
        accountDataFiservRequest.setUserAmount9(0L);
        accountDataFiservRequest.setUserAmount10(0L);
        accountDataFiservRequest.setUserAmount11(0L);
        accountDataFiservRequest.setUserAmount12(0L);
        accountDataFiservRequest.setUserAmount13(0L);
        accountDataFiservRequest.setUserAmount14(0L);
        accountDataFiservRequest.setMiscellaneousUser1("miscellaneousUser1");
        accountDataFiservRequest.setMiscellaneousUser2("miscellaneousUser2");
        accountDataFiservRequest.setMiscellaneousUser3("miscellaneousUser3");
        accountDataFiservRequest.setMiscellaneousUser4("miscellaneousUser4");
        accountDataFiservRequest.setMiscellaneousUser5("miscellaneousUser5");
        accountDataFiservRequest.setMiscellaneousUser6("miscellaneousUser6");
        accountDataFiservRequest.setMiscellaneousUser7("miscellaneousUser7");
        accountDataFiservRequest.setMiscellaneousUser8("miscellaneousUser8");
        accountDataFiservRequest.setMiscellaneousUser9("miscellaneousUser9");
        accountDataFiservRequest.setMiscellaneousUser10("miscellaneousUser10");
        accountDataFiservRequest.setMiscellaneousUser11("miscellaneousUser11");
        accountDataFiservRequest.setMiscellaneousUser12("miscellaneousUser12");
        accountDataFiservRequest.setPrepaidPlanNumber(54345);
        accountDataFiservRequest.setPrepaidLoadFrequency(0);
        accountDataFiservRequest.setPrepaidLoadNumber(456);
        accountDataFiservRequest.setPrepaidLoadAmount(98l);
        accountDataFiservRequest.setMinimumSingleLoadAmount(955l);
        accountDataFiservRequest.setMaximumSingleLoadAmount(5759l);
        accountDataFiservRequest.setInternationalRetailPlan(7654);
        accountDataFiservRequest.setInternationalInstallmentPlan(958);
        accountDataFiservRequest.setCashPlanInstallmentBilling(344);
        accountDataFiservRequest.setRetailPlanInstallmentBilling(667);
        accountDataFiservRequest.setAcquirerRetailPlan(156);
        accountDataFiservRequest.setAuthorizationControlTable(672);
        accountDataFiservRequest.setRetailPlanInstallmentPayments(98677);
        accountDataFiservRequest.setCashPlanInstallmentPayment(2475);
        accountDataFiservRequest.setQualification("v");
        accountDataFiservRequest.setCardholderAffiliationGroup(345);

        return accountDataFiservRequest;
    }

    public CatalogBody createCatalogBody() {
        CatalogBody catalogBody = new CatalogBody();
        catalogBody.setCatList(Set.of("catalogProductLms"));
        return catalogBody;
    }

    public CatalogData createCatalogData() {
        CatalogData catalogData = new CatalogData();
        catalogData.setBody(createCatalogBody());
        return catalogData;
    }

    public CatalogReq createCatalogReq() {
        CatalogReq catalogReq = new CatalogReq();
        catalogReq.setMetadata(createMeta());
        catalogReq.setData(createCatalogData());
        return catalogReq;
    }

    public CreacionClienteBody creacionClienteBody() {
        CreacionClienteBody creacionClienteBody = new CreacionClienteBody();
        creacionClienteBody.setCustomerNameLine1("Nombre");
        creacionClienteBody.setCustomerNameLine2("Apellido");
        creacionClienteBody.setCustomerNameLine3("Apellido");
        creacionClienteBody.setCustomerAddress1("Calle");
        creacionClienteBody.setCustomerAddress2("Calle");
        creacionClienteBody.setCustomerAddress3("Calle");
        creacionClienteBody.setCustomerAddress4("Calle");
        creacionClienteBody.setCustomerCity("Ciudad");
        creacionClienteBody.setCustomerState("Estado");
        creacionClienteBody.setPostalCode("12345");
        creacionClienteBody.setMaritalStatus("S");
        creacionClienteBody.setNameMagneticStripe("Nombre");
        creacionClienteBody.setLastNameMagneticStripe("Apellido");
        creacionClienteBody.setShortName("Nombre");
        creacionClienteBody.setGenderCode(1);
        creacionClienteBody.setCustomerType("S");
        creacionClienteBody.setDateOfBirth(2023);
        creacionClienteBody.setHomePhoneNumber("123456789");
        creacionClienteBody.setFaxNumber("123456789");
        creacionClienteBody.setMobilePhoneNumber("123456789");
        creacionClienteBody.setFlagIdentificationNumber(1);
        creacionClienteBody.setIdentificationNumber("123456789");
        creacionClienteBody.setCustomerEmployer("Empresa");
        creacionClienteBody.setUserDefined12("123456789");
        return creacionClienteBody;
    }

    public CreacionClienteData creacionClienteData() {
        CreacionClienteData creacionClienteData = new CreacionClienteData();
        CreacionClienteBody creacionClienteBody = creacionClienteBody();
        creacionClienteBody.setShortName("Nombre");
        creacionClienteData.setBody(creacionClienteBody);
        return creacionClienteData;
    }

    public CreacionClienteRequest creacionClienteRequest() {
        CreacionClienteRequest creacionClienteRequest = new CreacionClienteRequest();
        creacionClienteRequest.setMetadata(createMeta());
        creacionClienteRequest.setData(creacionClienteData());
        return creacionClienteRequest;
    }

    public EmbosserDataFiservRequest createEmbosserDataFiservRequest() {
        EmbosserDataFiservRequest embosserDataFiservRequest = new EmbosserDataFiservRequest();
        embosserDataFiservRequest.setDigitalCardFlag(1);
        embosserDataFiservRequest.setPlasticId("123456789");
        embosserDataFiservRequest.setName1TypeIndicator(1);
        embosserDataFiservRequest.setName2TypeIndicator(1);
        embosserDataFiservRequest.setEmbossedName1("123456789");
        embosserDataFiservRequest.setEmbossedName2("123456789");
        embosserDataFiservRequest.setExpirationDate("123456789");
        embosserDataFiservRequest.setCustomerNumber("123456789");
        embosserDataFiservRequest.setName1("123456789");
        embosserDataFiservRequest.setPosServiceCode(1);
        embosserDataFiservRequest.setCardholderFlag("123456789");
        embosserDataFiservRequest.setAddressLine1("123456789");
        embosserDataFiservRequest.setAddressLine2("123456789");
        embosserDataFiservRequest.setCity("123456789");
        embosserDataFiservRequest.setStateOrProvince("123456789");
        embosserDataFiservRequest.setPostalCode("123456789");
        embosserDataFiservRequest.setLanguageCode("123456789");
        embosserDataFiservRequest.setAtmCashNumber(1);
        embosserDataFiservRequest.setAtmCashAmount(1l);
        embosserDataFiservRequest.setAtmCashSingleTransactionLimit(1l);
        embosserDataFiservRequest.setOverTheCounterCashNumber(1);
        embosserDataFiservRequest.setOverTheCounterCashAmount(1l);
        embosserDataFiservRequest.setOverTheCounterCashSingleTransactionLimit(1l);
        embosserDataFiservRequest.setRetailPurchaseAmt(1l);
        embosserDataFiservRequest.setRetailPurchaseSingleTransactionLimit(1l);
        embosserDataFiservRequest.setInternetPurchaseAmount(1l);
        embosserDataFiservRequest.setInternetPurchaseNumber(1l);
        embosserDataFiservRequest.setInternetPurchaseSingleTransactionLimit(1l);
        embosserDataFiservRequest.setUser1("123456789");
        embosserDataFiservRequest.setUser2("123456789");
        embosserDataFiservRequest.setUser3("123456789");
        embosserDataFiservRequest.setUser4(1);
        embosserDataFiservRequest.setUser5("123456789");
        embosserDataFiservRequest.setUser6("123456789");
        embosserDataFiservRequest.setUser7("123456789");
        embosserDataFiservRequest.setUser8("123456789");
        embosserDataFiservRequest.setUserDate1("123456789");
        embosserDataFiservRequest.setUserDate2("123456789");
        embosserDataFiservRequest.setBranchNumber(1);
        embosserDataFiservRequest.setFirstIssueBranch(1);
        embosserDataFiservRequest.setDeliveryOption(1);
        embosserDataFiservRequest.setReissueDeliveryOption(1234);
        embosserDataFiservRequest.setAuthorizationCriteriaTableNumber("123456789");
        embosserDataFiservRequest.setProgramId(1);
        embosserDataFiservRequest.setCardholderAffiliationGroupId(1);
        embosserDataFiservRequest.setCardActionReasonCode("123456789");
        embosserDataFiservRequest.setIcTotalNumberTransactions(1);
        embosserDataFiservRequest.setIcAmountLimitSingleTransaction(1l);
        return embosserDataFiservRequest;
    }

    public ConsultaIbsRequestBody createConsultaIbsRequestBody() {
        ConsultaIbsRequestBody consultaIbsRequestBody = new ConsultaIbsRequestBody();
        consultaIbsRequestBody.setCustomerNumber("123456789");
        return consultaIbsRequestBody;
    }

    public ConsultaIbsRequestData createConsultaIbsRequestData() {
        ConsultaIbsRequestData consultaIbsRequestData = new ConsultaIbsRequestData();
        consultaIbsRequestData.setBody(createConsultaIbsRequestBody());
        return consultaIbsRequestData;
    }

    public ConsultaIbsRequest createConsultaIbsRequest() {
        ConsultaIbsRequest consultaIbsRequest = new ConsultaIbsRequest();
        consultaIbsRequest.setMetadata(createMeta());
        consultaIbsRequest.setData(createConsultaIbsRequestData());
        consultaIbsRequest.getData().setHeader(createHeader());
        return consultaIbsRequest;
    }

    public CreacionClienteVisionRequestBody createCreacionClienteVisionRequestBody() {
        CreacionClienteVisionRequestBody creacionClienteVisionRequestBody = new CreacionClienteVisionRequestBody();

        creacionClienteVisionRequestBody.setCustomerNumber("123456789");
        creacionClienteVisionRequestBody.setOrganization(1);
        creacionClienteVisionRequestBody.setCustomerOwner(1);
        creacionClienteVisionRequestBody.setVipStatus(1);
        creacionClienteVisionRequestBody.setStatusCustomer(1);
        creacionClienteVisionRequestBody.setCustomerMaritalStatus("123456789");
        creacionClienteVisionRequestBody.setTypeNameLine1(1);
        creacionClienteVisionRequestBody.setTypeNameLine2(1);
        creacionClienteVisionRequestBody.setTypeNameLine3(1);
        creacionClienteVisionRequestBody.setCountryCode("123456789");
        creacionClienteVisionRequestBody.setMailingList("123456789");
        creacionClienteVisionRequestBody.setContactIndicator(1);
        creacionClienteVisionRequestBody.setHomePhoneFlag(1);
        creacionClienteVisionRequestBody.setFaxFlagIndicator(1);
        creacionClienteVisionRequestBody.setMobilePhoneFlag(1);
        creacionClienteVisionRequestBody.setEmployerPhoneFlag(1);
        creacionClienteVisionRequestBody.setEmailFlag(1);
        creacionClienteVisionRequestBody.setShortMessageServiceFlag(1);
        creacionClienteVisionRequestBody.setStatement2Indicator("123456789");
        creacionClienteVisionRequestBody.setCountryCode2("123456789");
        creacionClienteVisionRequestBody.setCountryCode3("123456789");
        creacionClienteVisionRequestBody.setSicCode(1);
        creacionClienteVisionRequestBody.setDriverLicenseNumber("123456789");
        creacionClienteVisionRequestBody.setDriverLicenseState("123456789");
        creacionClienteVisionRequestBody.setDriverLicenseCountry("123456789");
        creacionClienteVisionRequestBody.setIdentificationNumber("123456789");
        creacionClienteVisionRequestBody.setStatementMessageIndicator(1);
        creacionClienteVisionRequestBody.setEmployerAddress1("123456789");
        creacionClienteVisionRequestBody.setEmployerAddress2("123456789");
        creacionClienteVisionRequestBody.setEmployerPhoneNumber("123456789");
        creacionClienteVisionRequestBody.setEmployerPhoneExtension("123456789");
        creacionClienteVisionRequestBody.setRelativeName("123456789");
        creacionClienteVisionRequestBody.setEmailAddress("123456789");
        creacionClienteVisionRequestBody.setMemo1("123456789");
        creacionClienteVisionRequestBody.setMemo2("123456789");
        creacionClienteVisionRequestBody.setUserDefined1("123456789");
        creacionClienteVisionRequestBody.setUserDefined2("123456789");
        creacionClienteVisionRequestBody.setUserDefined3("123456789");
        creacionClienteVisionRequestBody.setUserDefined4("123456789");
        creacionClienteVisionRequestBody.setUserDefined5("123456789");
        creacionClienteVisionRequestBody.setUserDefined6("123456789");
        creacionClienteVisionRequestBody.setUserDefined7("123456789");
        creacionClienteVisionRequestBody.setUserDefined8("123456789");
        creacionClienteVisionRequestBody.setUserDefined9("123456789");
        creacionClienteVisionRequestBody.setUserDefined10(1);
        creacionClienteVisionRequestBody.setUserDefined11(1);
        creacionClienteVisionRequestBody.setUserDefined13("123456789");
        creacionClienteVisionRequestBody.setUserDefined14("123456789");
        creacionClienteVisionRequestBody.setUserDefined15("123456789");
        creacionClienteVisionRequestBody.setUserDefinedDemographicData1("123456789");
        creacionClienteVisionRequestBody.setUserDefinedDemographicData2("123456789");
        creacionClienteVisionRequestBody.setUserDefinedDemographicData3("123456789");
        creacionClienteVisionRequestBody.setAddress2Indicator("123456789");
        creacionClienteVisionRequestBody.setAddress3Indicator("123456789");
        creacionClienteVisionRequestBody.setAddress2Line1("123456789");
        creacionClienteVisionRequestBody.setAddress2Line2("123456789");
        creacionClienteVisionRequestBody.setAddress2Line3("123456789");
        creacionClienteVisionRequestBody.setCustomerCity2("123456789");
        creacionClienteVisionRequestBody.setPostalCode2("123456789");
        creacionClienteVisionRequestBody.setAddress3Line1("123456789");
        creacionClienteVisionRequestBody.setAddress3Line2("123456789");
        creacionClienteVisionRequestBody.setAddress3Line3("123456789");
        creacionClienteVisionRequestBody.setCustomerCity3("123456789");
        creacionClienteVisionRequestBody.setPostalCode3("123456789");
        creacionClienteVisionRequestBody.setUserDate3(1);
        creacionClienteVisionRequestBody.setCustomerIncome(1);
        creacionClienteVisionRequestBody.setComakerStatementMessageIndicator(1);
        creacionClienteVisionRequestBody.setComakerStatement2Indicator("123456789");
        creacionClienteVisionRequestBody.setNameMagneticStripe("123456789");
        creacionClienteVisionRequestBody.setLastNameMagneticStripe("123456789");

        return creacionClienteVisionRequestBody;
    }

    public CreacionClienteVisionRequestData createCreacionClienteVisionRequestData() {
        CreacionClienteVisionRequestData creacionClienteVisionRequestData = new CreacionClienteVisionRequestData();
        creacionClienteVisionRequestData.setBody(createCreacionClienteVisionRequestBody());
        return creacionClienteVisionRequestData;
    }

    public CreacionClienteVisionRequest createCreacionClienteVisionRequest() {
        CreacionClienteVisionRequest creacionClienteVisionRequest = new CreacionClienteVisionRequest();
        creacionClienteVisionRequest.setMetadata(createMeta());
        creacionClienteVisionRequest.setData(createCreacionClienteVisionRequestData());
        return creacionClienteVisionRequest;
    }

    public LoginTokenParams createLoginTokenRequest() {
        LoginTokenParams loginTokenParams = new LoginTokenParams();
        loginTokenParams.setPassword("password");
        loginTokenParams.setUsername("username");
        return loginTokenParams;
    }

    public CmcInstantCardResponse createCmcInstantCardResponse() {
        CmcInstantCardResponse cmcInstantCardResponse = new CmcInstantCardResponse();
        CmcInstantCardResponseData cmcInstantCardResponseData =new CmcInstantCardResponseData();
        CmcInstantCardResponseBody cmcInstantCardResponseBody = new CmcInstantCardResponseBody();
        cmcInstantCardResponseBody.setCode("Ok");
        cmcInstantCardResponseData.setBody(cmcInstantCardResponseBody);
        cmcInstantCardResponse.setData(cmcInstantCardResponseData);
        return cmcInstantCardResponse;
    }

    public CmcInstantCardResponse createCmcInstantCardResponseWithErrors() {
        CmcInstantCardResponse cmcInstantCardResponse = new CmcInstantCardResponse();
        cmcInstantCardResponse.setErrors(List.of(new ErrorModel(
                InstantCardEnumError.BUSINESS_CMC.getCode(),
                InstantCardEnumError.BUSINESS_CMC.getTitle(),
                InstantCardEnumError.BUSINESS_CMC.getMessage())));
        return cmcInstantCardResponse;
    }

    public CmcInstantCardResponse createCmcInstantCardResponseWithError() {
        CmcInstantCardResponse cmcInstantCardResponse = new CmcInstantCardResponse();
        cmcInstantCardResponse.setErrors(List.of(new ErrorModel(
                InstantCardEnumError.BUSINESS_CMC.getCode(),
                InstantCardEnumError.BUSINESS_CMC.getTitle(),
                InstantCardEnumError.BUSINESS_CMC.getMessage())));
        return cmcInstantCardResponse;
    }

    public CmcInstantCardRequestDTO cmcInstantCardRequestDTO() {
        CmcInstantCardRequestDTO cmcInstantCardRequestDTO = new CmcInstantCardRequestDTO();
        cmcInstantCardRequestDTO.setCardNumber("cardNumber");
        cmcInstantCardRequestDTO.setCustomerNumber("customerNumber");
        cmcInstantCardRequestDTO.setCreditLimit(new BigDecimal("1"));
        cmcInstantCardRequestDTO.setProductType("productType");
        cmcInstantCardRequestDTO.setLogo("101");
        cmcInstantCardRequestDTO.setFecha(1l);
        cmcInstantCardRequestDTO.setUser("user");
        cmcInstantCardRequestDTO.setChannelId(1l);
        return cmcInstantCardRequestDTO;
    }

    public CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData() {
        CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData = new CreacionTarjetaFiservResponseData();
        creacionTarjetaFiservResponseData.setAccountNumber("accountNumber");
        creacionTarjetaFiservResponseData.setCardNumber("cardNumber");
        creacionTarjetaFiservResponseData.setCustomerNumber("customerNumber");
        creacionTarjetaFiservResponseData.setCardSequenceNumber("cardSequenceNumber");
        creacionTarjetaFiservResponseData.setCreditLimit(1l);
        creacionTarjetaFiservResponseData.setCardExpiryDate("cardExpiryDate");
        return creacionTarjetaFiservResponseData;
    }

    public LoginTokenFiservResponse createLoginTokenFiservResponse() {
        LoginTokenFiservResponse loginTokenFiservResponse = new LoginTokenFiservResponse();
        loginTokenFiservResponse.setToken_type("token_type");
        loginTokenFiservResponse.setIssued_at("issued_at");
        loginTokenFiservResponse.setClient_id("client_id");
        loginTokenFiservResponse.setAccess_token("access_token");
        loginTokenFiservResponse.setExpires_in("expires_in");
        loginTokenFiservResponse.setStatus("status");
        return loginTokenFiservResponse;
    }

    public CmcInstantCardRequestData createCmcInstantCardRequestData(){
        CmcInstantCardRequestData cmcInstantCardRequestData = new CmcInstantCardRequestData();
        CmcInstantCardRequestBody cmcInstantCardRequestBody = new CmcInstantCardRequestBody();
        cmcInstantCardRequestBody.setCustomerNumber("customerNumber");
        cmcInstantCardRequestBody.setLogo("001");
        cmcInstantCardRequestBody.setCardNumber("cardNumber");
        cmcInstantCardRequestBody.setAccountNumber("accountNumber");
        cmcInstantCardRequestBody.setProductType("productType");
        cmcInstantCardRequestBody.setCardCycle("cardCycle");
        cmcInstantCardRequestBody.setPct("pct");
        cmcInstantCardRequestBody.setFecha(1l);
        cmcInstantCardRequestBody.setUser("user");
        cmcInstantCardRequestData.setBody(cmcInstantCardRequestBody);
        return cmcInstantCardRequestData;
    }

    public CreacionTarjetaFiservRequest createCreacionTarjetaFiservRequest() {
        CreacionTarjetaFiservRequest creacionTarjetaFiservRequest = new CreacionTarjetaFiservRequest();
        creacionTarjetaFiservRequest.setAccountCreateFlag("1");
        creacionTarjetaFiservRequest.setCardActionFlag(1);
        creacionTarjetaFiservRequest.setCustomerNumber("customerNumber");
        creacionTarjetaFiservRequest.setFirstPurchaseFlag("firstPurchaseFlag");
        creacionTarjetaFiservRequest.setLogo("logo");
        creacionTarjetaFiservRequest.setOrganizationNumber(1);
        creacionTarjetaFiservRequest.setSameDayProcessingType(1);
        creacionTarjetaFiservRequest.setAccountData(createAccountDataFiservRequest());
        creacionTarjetaFiservRequest.setEmbosserData(createEmbosserDataFiservRequest());
        return creacionTarjetaFiservRequest;
    }

    public EmbosserUpdateResponseData createEmbosserUpdateResponseData() {
        EmbosserUpdateResponseData embosserUpdateResponseData = new EmbosserUpdateResponseData();
        EmbosserUpdateResponseBody embosserUpdateResponseBody = new EmbosserUpdateResponseBody();
        embosserUpdateResponseData.setHeader(this.createHeader());
        embosserUpdateResponseData.getHeader();
        embosserUpdateResponseBody.setAction("Ok");
        embosserUpdateResponseBody.setCustomFillerInd1("");
        embosserUpdateResponseBody.setCustomFiller1("");
        embosserUpdateResponseBody.setCustomFillerInd2("");
        embosserUpdateResponseBody.setCustomFiller2("");
        embosserUpdateResponseData.setBody(embosserUpdateResponseBody);
        embosserUpdateResponseData.getBody();
        embosserUpdateResponseData.getBody().getAction();
        embosserUpdateResponseData.getBody().getCustomFillerInd1();
        embosserUpdateResponseData.getBody().getCustomFillerInd2();
        embosserUpdateResponseData.getBody().getCustomFiller1();
        embosserUpdateResponseData.getBody().getCustomFiller2();
        embosserUpdateResponseData.getHeader();
        return embosserUpdateResponseData;
    }

    public EmbosserUpdateResponse createEmbosserUpdateResponse() {
        EmbosserUpdateResponse embosserUpdateResponse = new EmbosserUpdateResponse();
        embosserUpdateResponse.setMetadata(this.createMeta());
        embosserUpdateResponse.getMetadata();
        EmbosserUpdateResponseData embosserUpdateResponseData = this.createEmbosserUpdateResponseData();
        embosserUpdateResponse.setData(embosserUpdateResponseData);
        embosserUpdateResponse.getData();
        embosserUpdateResponse.setErrors(Collections.emptyList());
        embosserUpdateResponse.getErrors();
        return embosserUpdateResponse;
    }

    public EmbosserUpdateRequest createEmbosserUpdateRequest() {
        EmbosserUpdateRequestBody embosserUpdateRequestBody = new EmbosserUpdateRequestBody();
        embosserUpdateRequestBody.setCardNumber("cardNumber");
        embosserUpdateRequestBody.setMaskedCardNumber("maskedCardNumber");
        embosserUpdateRequestBody.setCardToken("cardToken");
        embosserUpdateRequestBody.setOrganization("organization");
        embosserUpdateRequestBody.setCustomerNumber("customerNumber");
        embosserUpdateRequestBody.setPrincipalCardNumber("principalCardNumber");
        embosserUpdateRequestBody.setEmbossedName1("embossedName1");
        embosserUpdateRequestBody.setEmbossedName2("embossedName2");
        embosserUpdateRequestBody.setAddressLine1("addressLine1");
        embosserUpdateRequestBody.setAddressLine2("addressLine2");
        embosserUpdateRequestBody.setCity("city");
        embosserUpdateRequestBody.setState("state");
        embosserUpdateRequestBody.setEmblemId("emblemId");
        embosserUpdateRequestBody.setEmailCardholder("emailCardholder");
        embosserUpdateRequestBody.setCardStatus("cardStatus");
        embosserUpdateRequestBody.setCustomFillerInd1("customFillerInd1");
        embosserUpdateRequestBody.setCustomFiller1("customFiller1");
        embosserUpdateRequestBody.setCustomFillerInd2("customFillerInd2");
        embosserUpdateRequestBody.setCustomFiller2("customFiller2");

        embosserUpdateRequestBody.getCardNumber();
        embosserUpdateRequestBody.getMaskedCardNumber();
        embosserUpdateRequestBody.getCardToken();
        embosserUpdateRequestBody.getOrganization();
        embosserUpdateRequestBody.getCustomerNumber();
        embosserUpdateRequestBody.getPrincipalCardNumber();
        embosserUpdateRequestBody.getEmbossedName1();
        embosserUpdateRequestBody.getEmbossedName2();
        embosserUpdateRequestBody.getAddressLine1();
        embosserUpdateRequestBody.getAddressLine2();
        embosserUpdateRequestBody.getCity();
        embosserUpdateRequestBody.getState();
        embosserUpdateRequestBody.getEmblemId();
        embosserUpdateRequestBody.getEmailCardholder();
        embosserUpdateRequestBody.getCardStatus();
        embosserUpdateRequestBody.getCustomFillerInd1();
        embosserUpdateRequestBody.getCustomFiller1();
        embosserUpdateRequestBody.getCustomFillerInd2();
        embosserUpdateRequestBody.getCustomFiller2();


        EmbosserUpdateRequestData embosserUpdateRequestData = new EmbosserUpdateRequestData();
        embosserUpdateRequestData.setHeader(this.createHeader());
        embosserUpdateRequestData.getHeader();
        embosserUpdateRequestData.setBody(embosserUpdateRequestBody);
        embosserUpdateRequestData.getBody();

        EmbosserUpdateRequest embosserUpdateRequest = new EmbosserUpdateRequest();
        embosserUpdateRequest.setMetadata(this.createMeta());
        embosserUpdateRequest.getMetadata();
        embosserUpdateRequestData.setBody(embosserUpdateRequestBody);
        embosserUpdateRequest.setData(embosserUpdateRequestData);
        embosserUpdateRequest.getData();

        return embosserUpdateRequest;
    }

    public EmbosserUpdateResponse createEmbosserUpdateRequestWithErrors() {
        EmbosserUpdateResponse embosserUpdateResponse = new EmbosserUpdateResponse();
        embosserUpdateResponse.setData(new EmbosserUpdateResponseData());
        embosserUpdateResponse.setErrors(List.of(new ErrorModel("500", "title error", "EL LOGO NO ES VALIDO.")));
        embosserUpdateResponse.getErrors().get(0);
        return embosserUpdateResponse;
    }

}
