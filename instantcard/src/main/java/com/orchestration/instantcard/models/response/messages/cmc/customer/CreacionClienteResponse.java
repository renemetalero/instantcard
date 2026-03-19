package com.orchestration.instantcard.models.response.messages.cmc.customer;

import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.Metadata;

import java.util.List;
import java.util.Optional;

public class CreacionClienteResponse {

	Metadata metadata;
	CreacionClienteResponseData data;

	private List<ErrorModel> errors;
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public CreacionClienteResponseData getData() {
		return data;
	}
	public void setData(CreacionClienteResponseData data) {
		this.data = data;
	}

	public Optional<List<ErrorModel>> getErrors() {
		return Optional.ofNullable(errors);
	}

	public void setErrors(List<ErrorModel> errors) {
		this.errors = errors;
	}
}
