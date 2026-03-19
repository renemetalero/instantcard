package com.orchestration.instantcard.mapper;


import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestDTO;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CmcInstantCardMapperTest {
    @InjectMocks
    private CreatePojos create;

    @Spy
    private CmcInstantCardMapper mapper = Mappers.getMapper(CmcInstantCardMapper.class);

    @Test
    @DisplayName(value = "Validar proceso de mapToFiservRequest")
    void mapToFiservRequest() {
        CmcInstantCardRequestBody clientObj = mapper.mapModelToCmcInstantCardRequestBody(create.cmcInstantCardRequestDTO());
        Assertions.assertNotNull(clientObj);
    }

    @Test
    @DisplayName(value = "Validar proceso de mapModelToCmcInstantCardRequestDTO")
    void mapModelToCmcInstantCardRequestDTO() {
        CmcInstantCardRequestDTO cmcInstantCardRequestDTO = mapper.mapModelToCmcInstantCardRequestDTO(create.createInstantCardBody(),create.creacionTarjetaFiservResponseData());
        Assertions.assertNotNull(cmcInstantCardRequestDTO);
    }
}
