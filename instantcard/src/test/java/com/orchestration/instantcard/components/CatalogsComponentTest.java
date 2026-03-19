package com.orchestration.instantcard.components;

import com.orchestration.instantcard.components.cmc.CatalogsComponent;
import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.service.cmc.CmcServiceImpl;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


@ExtendWith(MockitoExtension.class)
class CatalogsComponentTest {

    private CatalogsComponent catalogsComponent;

    @InjectMocks
    private CreatePojos create;

    @Mock
    private CmcServiceImpl serviceCmc;

    @BeforeEach
    void setUp() {
        catalogsComponent = new CatalogsComponent(serviceCmc);
    }

    @Test
    @DisplayName(value = "Test API setValueUserCode7")
    void testSetValueUserCode7() {

        String catalogValue = "catalogProductLms";
        ReflectionTestUtils.setField(catalogsComponent, "catalogProductLms", catalogValue);

        InstantCardRequestBody body = create.createInstantCardBody();
        CatalogRes catalog = create.createCatalogResponse();
        Mockito.when(serviceCmc.catalogSearch(Mockito.anyString())).thenReturn(catalog);
        catalogsComponent.setValueUserCode7(body);
        Mockito.verify(serviceCmc, Mockito.times(1)).catalogSearch(catalogValue);
    }

    @Test
    @DisplayName(value = "Test API getValidateCatalogCycle")
    void testGetValidateCatalogCycle() {
        CatalogsComponent catalogsComponent = new CatalogsComponent(serviceCmc);

        Long cycleValue = 3l;
        String catalogCycleValue = "CICLOS";
        ReflectionTestUtils.setField(catalogsComponent, "catalogCycle", "CICLOS");

        CatalogRes catalog = create.createCatalogResponse();

        Mockito.when(serviceCmc.catalogSearch(Mockito.anyString())).thenReturn(catalog);

        Boolean validateCatalogLogo =catalogsComponent.getValidateCatalogCycle(cycleValue);

        Mockito.verify(serviceCmc, Mockito.times(1)).catalogSearch(catalogCycleValue);
        Assertions.assertTrue(validateCatalogLogo);
    }
}
