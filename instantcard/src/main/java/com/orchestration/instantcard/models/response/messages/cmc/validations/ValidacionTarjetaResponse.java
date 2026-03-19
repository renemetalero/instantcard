package com.orchestration.instantcard.models.response.messages.cmc.validations;


import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.Metadata;

import java.util.List;

public class ValidacionTarjetaResponse {

	Metadata metadata;
	ValidacionTarjetaDataResponse data;
	List<ErrorModel> errors;
	
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public ValidacionTarjetaDataResponse getData() {
		return data;
	}
	public void setData(ValidacionTarjetaDataResponse data) {
		this.data = data;
	}
	public List<ErrorModel> getErrors() {
		return errors;
	}
	public void setErrors(List<ErrorModel> errors) {
		this.errors = errors;
	}
	
	
}
