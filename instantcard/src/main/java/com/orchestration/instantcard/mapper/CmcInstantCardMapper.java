package com.orchestration.instantcard.mapper;

import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestDTO;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(imports = InstantCardConstants.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CmcInstantCardMapper {
    CmcInstantCardMapper INSTANCE = Mappers.getMapper(CmcInstantCardMapper.class);

    CmcInstantCardRequestBody mapModelToCmcInstantCardRequestBody(CmcInstantCardRequestDTO cmcInstantCardRequestDTO);

    @Mapping(target = "cardCycle", source = "instantCardRequestBody.cycle")
    @Mapping(target = "cardSequence", expression = "java(InstantCardConstants.CARD_SEQUENCE)")
    @Mapping(target = "productType", expression = "java(InstantCardConstants.PRODUCT_TYPE_TCD)")
    @Mapping(target = "logo", source = "instantCardRequestBody.logo")
    @Mapping(target = "customerNumber",source = "creacionTarjetaFiservResponseData.customerNumber")
    @Mapping(target = "cardNumber",source = "creacionTarjetaFiservResponseData.cardNumber")
    @Mapping(target = "creditLimit",source = "creacionTarjetaFiservResponseData.creditLimit")
    @Mapping(target = "user", expression = "java(InstantCardConstants.USER_CREATE_CMC_INSTANT_CARD)")
    @Mapping(target = "channelId", expression = "java(InstantCardConstants.CMC_INSTANT_CARD_CHANNEL_ID)")
    @Mapping(target = "cardExpiryDate", source = "creacionTarjetaFiservResponseData.cardExpiryDate")
    CmcInstantCardRequestDTO mapModelToCmcInstantCardRequestDTO(InstantCardRequestBody instantCardRequestBody, CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData);

    @AfterMapping
    default void setDefaultValue(@MappingTarget CmcInstantCardRequestDTO cmcInstantCardRequestDTO) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(InstantCardConstants.DATE_FORMAT_ISO_8601);
        Long formattedDate = Long.parseLong(today.format(formatter));
        cmcInstantCardRequestDTO.setFecha(formattedDate);
        String cardExpiryDate = cmcInstantCardRequestDTO.getCardExpiryDate().replace("-","");
        cmcInstantCardRequestDTO.setCardExpiryDate(cardExpiryDate);
    }
}
