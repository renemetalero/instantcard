package com.orchestration.instantcard.mapper;

import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.request.messages.gestiontarjeta.EmbosserUpdateRequestBody;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(imports = InstantCardConstants.class,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmbosserUpdateMapper {
    EmbosserUpdateMapper INSTANCE = Mappers.getMapper(EmbosserUpdateMapper.class);

    @Mapping(target = "cardNumber", source = "creacionTarjetaFiservResponseData.cardNumber")
    @Mapping(target = "maskedCardNumber", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "cardToken", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "organization", source = "body.organization")
    @Mapping(target = "customerNumber", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "principalCardNumber", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "embossedName1", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "embossedName2", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "addressLine1", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "addressLine2", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "city", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "state", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "emailCardholder", source = "body.email")
    @Mapping(target = "cardStatus", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "customFillerInd1", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "customFiller1", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "customFillerInd2", expression = "java(InstantCardConstants.EMPTY_STRING)")
    @Mapping(target = "customFiller2", expression = "java(InstantCardConstants.EMPTY_STRING)")
    EmbosserUpdateRequestBody mapModelToEmbosserUpdateRequestBody(InstantCardRequestBody body, CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData);

}
