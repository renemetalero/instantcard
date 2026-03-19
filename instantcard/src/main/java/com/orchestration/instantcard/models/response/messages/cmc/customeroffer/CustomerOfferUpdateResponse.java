package com.orchestration.instantcard.models.response.messages.cmc.customeroffer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.gson.annotations.SerializedName;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.models.generals.Metadata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOfferUpdateResponse {

	@JsonIgnore
    private Integer status;
    @JsonIgnore
    private String message;
	
	private Metadata metadata;
	
    @JsonInclude(Include.NON_NULL)
	private CustomerOfferUpdateResponseData data;
	
	@JsonInclude(Include.NON_NULL)
	@SerializedName("errors")
	private List<ErrorModel> errors;
}
