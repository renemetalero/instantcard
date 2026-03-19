package com.orchestration.instantcard.service.customeroffer;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequest;
import com.orchestration.instantcard.utils.LoggerObjectUtil;


@Service
public class CustomerOfferUpdateServiceImpl implements CustomerOfferUpdateService {

	@Value("${customeroffer-update-url}")
	private String resourceUrlCustomerofferUpdate;

	private final WebClient webClient;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public static final String OFFER_FORMALIZADA = "F";

	public CustomerOfferUpdateServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	public CompletableFuture<Void> updateOffer(CustomerOfferUpdateRequest offerUpdateRequest) {

		return CompletableFuture.runAsync(() -> {

			logger.info("resourceUrlCustomerOffer {}", resourceUrlCustomerofferUpdate);

			LoggerObjectUtil.print("Request updateOffer: ", offerUpdateRequest);

			webClient
					.post()
					.uri(resourceUrlCustomerofferUpdate)
					.bodyValue(offerUpdateRequest)
					.exchangeToMono(response -> {
						HttpStatusCode status = response.statusCode();
						return response.bodyToMono(String.class)
								.defaultIfEmpty("")
								.map(body -> {
									logger.info("========================================");
									logger.info("RESPONSE customerOfferUpdate");
									logger.info("STATUS: {}", status.value());
									logger.info("BODY: {}", body);
									logger.info("========================================");
									return body;
								});
					})
					.timeout(Duration.ofSeconds(8))
					.doOnError(error ->
							logger.error("Error consumiendo API customerofferUpdate: {}", error.getMessage())
					)
					.block();
			logger.info("Proceso finalizado");
		});
	}
}