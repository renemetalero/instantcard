package com.orchestration.instantcard.models.response.messages.cmc.customeroffer;


import com.orchestration.instantcard.models.generals.Header;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOfferUpdateResponseData {

	private Header header;
	
	private CustomerOfferUpdateResponseBody body;
}
