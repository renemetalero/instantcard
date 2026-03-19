package com.orchestration.instantcard.models;

import com.orchestration.instantcard.models.request.messages.cmc.catalog.CatalogReq;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CatalogReqTest {

    @InjectMocks
    private CreatePojos create;

    @Test
    @DisplayName(value = "CatalogTest => testCatalogReq")
    void testCatalogReq() {
        CatalogReq catalogReq = create.createCatalogReq();
        Assertions.assertNotNull(catalogReq.getData().getBody().getCatList());
        Assertions.assertNotNull(catalogReq.getMetadata());
    }

}
