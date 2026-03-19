package com.orchestration.instantcard.mapper;

import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.AccountDataFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.CreacionTarjetaFiservRequest;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.EmbosserDataFiservRequest;
import com.orchestration.instantcard.utils.CreatePojos;
import com.orchestration.instantcard.utils.Utility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FiservInstantCardMapperTest {
    @InjectMocks
    private CreatePojos create;

    @Spy
    private FiservInstantCardMapper mapper = Mappers.getMapper(FiservInstantCardMapper.class);

    @Test
    @DisplayName(value = "Validar proceso de mapToFiservRequest")
    void mapToFiservRequest() {
        String dateCurrent = Utility.getLocalDateNowISO();
        CreacionTarjetaFiservRequest clientObj = mapper.mapToFiservRequest(create.createRequestFiservInstantCardDto(), dateCurrent);
        Assertions.assertNotNull(clientObj);
    }

    @Test
    @DisplayName(value = "Validar proceso de mapToFiservRequest")
    void mapToAccountDataFiservRequest() {
        AccountDataFiservRequest accountDataFiservRequest = mapper.mapToAccountDataFiservRequest(create.createRequestFiservInstantCardDto());
        Assertions.assertNotNull(accountDataFiservRequest);
    }

    @Test
    @DisplayName(value = "Validar proceso de mapToEmbosserDataFiservRequest")
    void mapToEmbosserDataFiservRequest() {
        EmbosserDataFiservRequest emboserDataFiservRequest = mapper.mapToEmbosserDataFiservRequest(create.createRequestFiservInstantCardDto());
        Assertions.assertNotNull(emboserDataFiservRequest);
    }


}
