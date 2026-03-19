package com.orchestration.instantcard.models.request.messages.cmc.catalog;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CatalogBody {
	private Set<String> catList;
}
