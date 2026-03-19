package com.orchestration.instantcard.models;

import org.junit.jupiter.api.Test;

import com.orchestration.instantcard.models.request.RequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.createcard.CmcInstantCardRequestDTO;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequest;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequestData;
import com.orchestration.instantcard.models.request.messages.vision.card.fiserv.RequestFirservInstantCardDto;
import com.orchestration.instantcard.models.response.InstantCardResponseBody;
import com.orchestration.instantcard.models.response.InstantCardResponseData;
import com.orchestration.instantcard.models.response.messages.cmc.catalog.CatalogRes;
import com.orchestration.instantcard.models.response.messages.cmc.createcard.CmcInstantCardResponse;
import com.orchestration.instantcard.models.response.messages.cmc.customeroffer.CustomerOfferUpdateResponse;
import com.orchestration.instantcard.models.response.messages.cmc.customeroffer.CustomerOfferUpdateResponseBody;
import com.orchestration.instantcard.models.response.messages.cmc.customeroffer.CustomerOfferUpdateResponseData;
import com.orchestration.instantcard.models.response.messages.vision.customer.CreacionClienteVisionResponseBody;


class DomainObjTest {

	@Test
	void testAllClassGetterSetter_CustomerOffer() {
		/** Begin test new entities */
		GetterSetterVerifier.forClass(CustomerOfferUpdateRequest.class).verify();
		GetterSetterVerifier.forClass(CustomerOfferUpdateRequestData.class).verify();
		GetterSetterVerifier.forClass(CustomerOfferUpdateRequestBody.class).verify();
		
		GetterSetterVerifier.forClass(CustomerOfferUpdateResponse.class).verify();
		GetterSetterVerifier.forClass(CustomerOfferUpdateResponseData.class).verify();
		GetterSetterVerifier.forClass(CustomerOfferUpdateResponseBody.class).verify();
		
		GetterSetterVerifier.forClass(RequestBody.class).verify();
	}
	
	@Test
	void testAllClassGetterSetter() {
		/** Begin test new entities */
		GetterSetterVerifier.forClass(InstantCardResponseBody.class).verify();
		GetterSetterVerifier.forClass(InstantCardResponseData.class).verify();
		
		GetterSetterVerifier.forClass(RequestFirservInstantCardDto.class).verify();
		
		GetterSetterVerifier.forClass(CmcInstantCardRequestDTO.class).verify();
		
		GetterSetterVerifier.forClass(CatalogRes.class).verify();
		
		GetterSetterVerifier.forClass(CmcInstantCardResponse.class).verify();
		
		GetterSetterVerifier.forClass(CreacionClienteVisionResponseBody.class).verify();
	}
}