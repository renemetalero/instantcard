package com.orchestration.instantcard.models.response.messages.cmc.catalog;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.Metadata;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class CatalogRes {
    private Metadata metadata;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CatalogResData data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ErrorModel> errors;


    public Optional<List<ErrorModel>> getErrors() {
        return Optional.ofNullable(errors);
    }

}
