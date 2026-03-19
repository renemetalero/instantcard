package com.orchestration.instantcard.service.customeroffer;

import java.util.concurrent.CompletableFuture;

import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequest;

public interface CustomerOfferUpdateService {
	CompletableFuture<Void> updateOffer(CustomerOfferUpdateRequest offerUpdateRequest);
}
