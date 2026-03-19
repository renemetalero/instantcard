package com.orchestration.instantcard.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.orchestration.instantcard.models.generals.Header;
import com.orchestration.instantcard.models.generals.Metadata;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequest;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequestBody;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequestData;
import com.orchestration.instantcard.service.customeroffer.CustomerOfferUpdateServiceImpl;
import com.orchestration.instantcard.utils.LoggerObjectUtil;

import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class CustomerOfferUpdateServiceImplTest {

	@Mock
	private WebClient webClient;

	@Mock
	private WebClient.RequestBodyUriSpec requestBodyUriSpec;

	@Mock
	private WebClient.RequestBodySpec requestBodySpec;

	@Mock
	@SuppressWarnings("rawtypes")
	private WebClient.RequestHeadersSpec requestHeadersSpec;

	@InjectMocks
	private CustomerOfferUpdateServiceImpl service;

	private CustomerOfferUpdateRequest request;

	@BeforeEach
	void setup() {

		ReflectionTestUtils.setField(service, "resourceUrlCustomerofferUpdate", "http://localhost/test");

		when(webClient.post()).thenReturn(requestBodyUriSpec);
		when(requestBodyUriSpec.uri(anyString())).thenReturn(requestBodySpec);
		when(requestBodySpec.bodyValue(any())).thenReturn(requestHeadersSpec);

		// MOCK CLAVE (evita NPE)
		when(requestHeadersSpec.exchangeToMono(any()))
				.thenReturn(Mono.just("OK"));

		request = buildInstantCardRequest();
	}

	@Test
	void updateOffer_success() {

		try (MockedStatic<LoggerObjectUtil> mockedLogger = mockStatic(LoggerObjectUtil.class)) {

			CompletableFuture<Void> result = service.updateOffer(request);
			result.join();

			assertNotNull(result);
		}
	}

	@Test
	void shouldUpdateOfferSuccessfullyVerify() {

		CompletableFuture<Void> result = service.updateOffer(request);
		result.join();

		verify(webClient).post();
	}

	@Test
	void updateOffer_success2() {

		CompletableFuture<Void> result = service.updateOffer(request);
		result.join();

		verify(webClient).post();
		verify(requestBodyUriSpec).uri(anyString());
		verify(requestBodySpec).bodyValue(any());
		verify(requestHeadersSpec).exchangeToMono(any());

		assertNotNull(result);
	}

	@Test
	void updateOffer_channelNull_shouldUseApplicationId() {

		request.getData().getBody().setChannel(null);

		CompletableFuture<Void> future = service.updateOffer(request);
		future.join();

		verify(webClient).post();
	}

	@Test
	void updateOffer_errorInWebClient() {

		// Simular error real
		when(requestHeadersSpec.exchangeToMono(any()))
				.thenReturn(Mono.error(new RuntimeException("API ERROR")));

		CompletableFuture<Void> future = service.updateOffer(request);

		assertThrows(Exception.class, future::join);

		verify(webClient).post();
	}

	// =========================
	// BUILDER REQUEST
	// =========================
	public CustomerOfferUpdateRequest buildInstantCardRequest() {

		CustomerOfferUpdateRequest offerUpdateRequest = new CustomerOfferUpdateRequest();
		offerUpdateRequest.setMetadata(new Metadata());
		offerUpdateRequest.setData(new CustomerOfferUpdateRequestData());
		offerUpdateRequest.getData().setHeader(new Header());
		offerUpdateRequest.getData().setBody(new CustomerOfferUpdateRequestBody());

		offerUpdateRequest.getData().getBody().setCustomerNumber("00032002300340");
		offerUpdateRequest.getData().getBody().setOfferCode("0030402304002304034");
		offerUpdateRequest.getData().getBody().setChannel("BM");
		offerUpdateRequest.getData().getBody().setRequestNumber("0000012026");

		return offerUpdateRequest;
	}

	@Test
	void updateOffer_shouldExecuteExchangeToMonoMapping() {

		// Mock de ClientResponse
		var clientResponse = org.mockito.Mockito.mock(org.springframework.web.reactive.function.client.ClientResponse.class);

		when(clientResponse.statusCode())
				.thenReturn(org.springframework.http.HttpStatus.OK);

		when(clientResponse.bodyToMono(String.class))
				.thenReturn(Mono.just("response-body"));

		// Ejecutar el lambda real
		when(requestHeadersSpec.exchangeToMono(any()))
				.thenAnswer(invocation -> {

					java.util.function.Function<
							org.springframework.web.reactive.function.client.ClientResponse,
							Mono<String>
							> function = invocation.getArgument(0);

					return function.apply(clientResponse);
				});

		CompletableFuture<Void> result = service.updateOffer(request);
		result.join();

		verify(webClient).post();
	}

	@Test
	void updateOffer_shouldHandleEmptyBody() {

		ClientResponse clientResponse = org.mockito.Mockito.mock(ClientResponse.class);

		when(clientResponse.statusCode())
				.thenReturn(HttpStatus.OK);

		when(clientResponse.bodyToMono(String.class))
				.thenReturn(Mono.empty());

		when(requestHeadersSpec.exchangeToMono(any()))
				.thenAnswer(invocation -> {
					@SuppressWarnings("unchecked")
					java.util.function.Function<ClientResponse, Mono<String>> function =
							(java.util.function.Function<ClientResponse, Mono<String>>) invocation.getArgument(0);

					return function.apply(clientResponse);
				});

		CompletableFuture<Void> result = service.updateOffer(request);
		result.join();

		verify(webClient).post();
	}
}