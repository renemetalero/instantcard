package com.orchestration.instantcard.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBody {
	
    @Size(min = 19, max = 19, message = "El campo customerNumber debe tener {max} caracteres")
    @NotNull(message = "0505")
    @Pattern(regexp = "\\d+", message = "El campo customerNumber solo puede contener números")
    private String customerNumber;

	@JsonProperty("offerCode")
	@NotBlank(message = "El Campo offerCode no debe ser vacio")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "El campo offerCode no es valido")
	@Size(min = 7, max = 100, message = "El campo offerCode debe ser maximo de 100 caracteres")
	private String offerCode;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("statusOffer")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "El campo statusOffer no es valido")
	@Size(min = 1, max = 2, message = "El campo statusOffer debe ser maximo de 2 caracteres")
	private String statusOffer;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("channel")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "El campo channel no es valido")
	@Size(max = 7, message = "El campo channel debe ser maximo de 7 caracteres")
	private String channel;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("requestNumber")
	@Size(min = 7, max = 100, message = "El campo requestNumber debe ser maximo de 100 caracteres")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "El campo offerCode no es valido")
	private String requestNumber;
	
}
