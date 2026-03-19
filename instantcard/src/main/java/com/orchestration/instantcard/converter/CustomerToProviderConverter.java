package com.orchestration.instantcard.converter;

import org.springframework.stereotype.Component;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequest;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequestData;

@Component
public class CustomerToProviderConverter {

	private ApiContext apiContext;

	public static final String OFFER_FORMALIZADA = "F";

	public CustomerToProviderConverter(ApiContext apiContext) {
		this.apiContext = apiContext;
	}

	public CustomerOfferUpdateRequest createRequestOfferUpdate(InstantCardRequest request) {
		CustomerOfferUpdateRequest offerUpdateRequest = new CustomerOfferUpdateRequest();
		offerUpdateRequest.setMetadata(apiContext.getMetadata());
		offerUpdateRequest.setData(new CustomerOfferUpdateRequestData());
		offerUpdateRequest.getData().setHeader(request.getData().getHeader());
		offerUpdateRequest.getData().setBody(new CustomerOfferUpdateRequestBody());
		offerUpdateRequest.getData().getBody().setCustomerNumber(
				request.getData().getBody().getCustomerNumber());
		offerUpdateRequest.getData().getBody().setOfferCode(
				request.getData().getBody().getOfferCode());
		offerUpdateRequest.getData().getBody().setStatusOffer(OFFER_FORMALIZADA);
		offerUpdateRequest.getData().getBody()
				.setChannel(request.getData().getBody().getChannel() != null 
				? request.getData().getBody().getChannel()
				: request.getMetadata().getApplicationId());
		offerUpdateRequest.getData().getBody().setRequestNumber(
				request.getData().getBody().getRequestNumberMantiz());

		return offerUpdateRequest;
	}
}
