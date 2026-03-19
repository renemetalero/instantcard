package com.orchestration.instantcard.models.request.messages.cmc.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orchestration.instantcard.models.generals.Metadata;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CatalogReq {
	@NotNull(message = "El campo metadata es requerido")
	@JsonProperty("metadata")
	private Metadata metadata;
	@NotNull(message = "El campo data es requerido")
	private CatalogData data;
}
