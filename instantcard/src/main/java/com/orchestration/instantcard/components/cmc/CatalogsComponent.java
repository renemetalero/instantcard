package com.orchestration.instantcard.components.cmc;

import com.orchestration.instantcard.models.request.InstantCardRequestBody;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogResBody;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogResData;
import com.orchestration.instantcard.service.cmc.CmcService;
import com.orchestration.instantcard.utils.constants.InstantCardConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Optional;

@Component
@RequestScope
public class CatalogsComponent {

    @Value("${catalogo-producto-lms}")
    private String catalogProductLms;

    @Value("${catalogo-cycle}")
    private String catalogCycle;

    private CmcService serviceCmc;

    public CatalogsComponent(CmcService serviceCmc) {
        this.serviceCmc = serviceCmc;
    }

    public void setValueUserCode7(InstantCardRequestBody body) {
        CatalogRes catalog = serviceCmc.catalogSearch(this.catalogProductLms);

        Optional.ofNullable(catalog)
                .map(CatalogRes::getData)
                .map(CatalogResData::getBody)
                .map(CatalogResBody::getCatList)
                .flatMap(catList -> catList.stream().findFirst())
                .ifPresent(details -> body.setUserCode7(details.getCode()));
    }

    public boolean getValidateCatalogCycle(Long cycle){
        CatalogRes catalog = serviceCmc.catalogSearch(this.catalogCycle);
        return Optional.ofNullable(catalog)
            .map(CatalogRes::getData)
            .map(CatalogResData::getBody)
            .map(CatalogResBody::getCatList)
            .stream()
            .flatMap(List::stream)
            .anyMatch(data -> data.getValorInt().compareTo(cycle) == InstantCardConstants.ZERO);
    }

}
