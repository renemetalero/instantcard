package com.orchestration.instantcard.models.request.messages.cmc.customeroffer;

import com.orchestration.instantcard.models.generals.Metadata;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOfferUpdateRequest {

	@Valid
	@ConvertGroup(from = CustomerOfferUpdateRequest.class, to = Metadata.class)
	@NotNull(message = "El campo metadata es requerido")
	private Metadata metadata;
	
	@Valid
	@ConvertGroup(from = CustomerOfferUpdateRequest.class, to = CustomerOfferUpdateRequestData.class)
	@NotNull(message = "El campo data es requerido")
	private CustomerOfferUpdateRequestData data;
	
}
