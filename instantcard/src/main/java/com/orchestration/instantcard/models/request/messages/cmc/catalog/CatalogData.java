package com.orchestration.instantcard.models.request.messages.cmc.catalog;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogData {
	@NotNull(message = "body es requerido")
	private CatalogBody body;
}
