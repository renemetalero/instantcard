package com.orchestration.instantcard.models.response.messages.cmc.validations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidacionTarjetaBodyResponse {

	@JsonProperty(value = "customerNumber")
	private String customerNumberResponseBody;

	@JsonProperty(value = "customerNameLine1")
	private String customerNameLine1ResponseBody;

	@JsonProperty(value = "customerNameLine2")
	private String customerNameLine2ResponseBody;

	@JsonProperty(value = "customerNameLine3")
	private String customerNameLine3ResponseBody;

	@JsonProperty(value = "customerAddress1")
	private String customerAddress1ResponseBody;

	@JsonProperty(value = "customerAddress2")
	private String customerAddress2ResponseBody;

	@JsonProperty(value = "customerAddress3")
	private String customerAddress3ResponseBody;

	@JsonProperty(value = "customerAddress4")
	private String customerAddress4ResponseBody;

	@JsonProperty(value = "customerCity")
	private String customerCityResponseBody;

	@JsonProperty(value = "customerState")
	private String customerStateResponseBody;

	@JsonProperty(value = "postalCode")
	private String postalCodeResponseBody;

	@JsonProperty(value = "tarjeta")
	private TarjetaBodyResponse tarjetaResponseBody;

	// 2022-11-174:cambios para cardadd version 5
	private String nameMagneticStripe;
	private String lastNameMagneticStripe;
	private String shortName;


}
