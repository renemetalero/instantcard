package com.orchestration.instantcard.mapper;

import com.orchestration.instantcard.models.response.InstantCardResponseBody;
import com.orchestration.instantcard.models.response.messages.vision.card.fiserv.CreacionTarjetaFiservResponseData;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(imports = InstantCardConstants.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InstantCardResponseMapper {

    InstantCardResponseMapper INSTANCE = Mappers.getMapper(InstantCardResponseMapper.class);

    @Mapping(target= "creditCardAccountNumber", source = "creacionTarjetaFiservResponseData.accountNumber")
    @Mapping(target= "creditCardNumber", source = "creacionTarjetaFiservResponseData.cardNumber")
    @Mapping(target= "customerNumber", source = "creacionTarjetaFiservResponseData.customerNumber")
    InstantCardResponseBody modelToInstantCardResponse(CreacionTarjetaFiservResponseData creacionTarjetaFiservResponseData, String currentDate);

    @AfterMapping
    default void setDefaultValue(@MappingTarget InstantCardResponseBody instantCardResponseBody, String currentDate) {
        instantCardResponseBody.setCreationDate(currentDate);
    }
}
