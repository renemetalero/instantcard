package com.orchestration.instantcard.models.response.messages.cmc.validations;

public class ValidacionTarjetaDataResponse {
	
	private ValidacionTarjetaBodyResponse body;

	public ValidacionTarjetaBodyResponse getBody() {
		return body;
	}

	public void setBody(ValidacionTarjetaBodyResponse body) {
		this.body = body;
	}

	private Integer clientExist;

	public Integer getClientExist() {
		return clientExist;
	}
	

}
