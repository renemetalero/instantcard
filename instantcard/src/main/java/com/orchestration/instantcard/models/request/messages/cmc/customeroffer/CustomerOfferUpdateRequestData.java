package com.orchestration.instantcard.models.request.messages.cmc.customeroffer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orchestration.instantcard.models.generals.Header;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOfferUpdateRequestData {

	@Valid
	@ConvertGroup(from = CustomerOfferUpdateRequestData.class, to = Header.class)
	@NotNull(message = "El campo header es requerido")
	private Header header;
	

	@Valid
	@JsonProperty("body")
	@NotNull(message = "El campo body es requerido")
	private CustomerOfferUpdateRequestBody body;
}
