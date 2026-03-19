package com.orchestration.instantcard.models.request.messages.cmc.customer;


import com.orchestration.instantcard.models.generals.Metadata;

public class CreacionClienteRequest {

	private Metadata metadata;
	private CreacionClienteData data;
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public CreacionClienteData getData() {
		return data;
	}
	public void setData(CreacionClienteData data) {
		this.data = data;
	}
		
}
