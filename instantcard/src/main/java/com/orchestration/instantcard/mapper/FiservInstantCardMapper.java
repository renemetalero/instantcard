package com.orchestration.instantcard.mapper;

import com.orchestration.instantcard.models.generals.FieldsCardDto;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.AccountDataFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.EmbosserDataFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.RequestFirservInstantCardDto;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(imports = InstantCardConstants.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FiservInstantCardMapper {

    FiservInstantCardMapper INSTANCE = Mappers.getMapper(FiservInstantCardMapper.class);

    @Mapping(target = "digitalCardFlag", expression = "java(InstantCardConstants.DIGITAL_CARD_FLAG)")
    @Mapping(target = "name1", source = "requestFirservInstantCardDto.embossedName1")
    @Mapping(target = "posServiceCode", expression = "java(InstantCardConstants.POS_SERVICE_CODE)")
    @Mapping(target = "branchNumber", source = "requestFirservInstantCardDto.owningBranch")
    @Mapping(target = "firstIssueBranch", source = "requestFirservInstantCardDto.owningBranch")
    @Mapping(target = "cardholderAffiliationGroupId", source = "requestFirservInstantCardDto.emblemId")
    @Mapping(target = "stateOrProvince", source = "requestFirservInstantCardDto.province")
    EmbosserDataFiservRequest mapToEmbosserDataFiservRequest(RequestFirservInstantCardDto requestFirservInstantCardDto);

    @Mapping(target = "billingCycle", source = "requestFirservInstantCardDto.cycle")
    @Mapping(target = "shortName", source = "requestFirservInstantCardDto.shortName")
    @Mapping(target = "processingControlTableLevelStartDate", source = "requestFirservInstantCardDto.processingControlTableLevelStartDate")
    @Mapping(target = "processingControlTableLevelExpireDate", source = "requestFirservInstantCardDto.processingControlTableLevelExpireDate")
    @Mapping(target = "cardTechnology", expression = "java(InstantCardConstants.CARD_TECHNOLOGY)")
    @Mapping(target = "authorizationLimitOverrides", expression = "java(InstantCardConstants.AUTHORIZATION_LIMIT_OVERRIDES)")
    @Mapping(target = "stateOfResidenceId", source = "requestFirservInstantCardDto.pct")
    @Mapping(target = "stateOfIssuanceId", source = "requestFirservInstantCardDto.pct")
    @Mapping(target = "owningBranchNumber", source = "requestFirservInstantCardDto.owningBranch")
    @Mapping(target = "userDate1", source = "requestFirservInstantCardDto.deliveryOption")
    @Mapping(target = "miscellaneousUser5", source = "requestFirservInstantCardDto.saleExecutive")
    @Mapping(target = "cardholderAffiliationGroup", source = "requestFirservInstantCardDto.emblemId")
    @Mapping(target = "creditLimit", source = "requestFirservInstantCardDto.creditLimitFormatted")
    AccountDataFiservRequest mapToAccountDataFiservRequest(RequestFirservInstantCardDto requestFirservInstantCardDto);

    @Mapping(target = "accountCreateFlag", expression = "java(InstantCardConstants.Y)")
    @Mapping(target = "cardActionFlag", expression = "java(InstantCardConstants.CARD_ACTION_FLAG)")
    @Mapping(target = "firstPurchaseFlag", expression = "java(InstantCardConstants.FIRST_PURCHASE_FLAG)")
    @Mapping(target = "organizationNumber", expression = "java(InstantCardConstants.ORGANIZATION_NUMBER)")
    @Mapping(target = "sameDayProcessingType", expression = "java(InstantCardConstants.SAME_DAY_PROCESSING_TYPE)")
    @Mapping(target = "embosserData", source = "requestFirservInstantCardDto")
    @Mapping(target = "accountData", source = "requestFirservInstantCardDto")
    CreacionTarjetaFiservRequest mapToFiservRequest(RequestFirservInstantCardDto requestFirservInstantCardDto, String currentDate);

    @Mapping(target = "logo", source = "fieldsCardDto.logo")
    @Mapping(target = "customerNumber", source = "fieldsCardDto.customerNumber")
    RequestFirservInstantCardDto mapInstantRequestBodyToFieldsCardDto(InstantCardRequestBody instantCardRequestBody, FieldsCardDto fieldsCardDto);

    @AfterMapping
    default void setDefaultValue(@MappingTarget CreacionTarjetaFiservRequest requestFirservInstantCardDto, String currentDate) {
        requestFirservInstantCardDto.getAccountData().setDateOpened(currentDate);
    }
}
