package com.orchestration.instantcard.models.response.messages.cmc.catalog;

import java.util.List;

public class CatalogResBody {

	private List<CatalogDetail> catList;

	public List<CatalogDetail> getCatList() {
		return catList;
	}

	public void setCatList(List<CatalogDetail> catList) {
		this.catList = catList;
	}
	
}
