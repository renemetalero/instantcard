package com.orchestration.instantcard.converter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.models.generals.Header;
import com.orchestration.instantcard.models.generals.Metadata;
import com.orchestration.instantcard.models.request.InstantCardRequest;
import com.orchestration.instantcard.models.request.messages.cmc.customeroffer.CustomerOfferUpdateRequest;
import com.orchestration.instantcard.models.response.messages.cmc.customeroffer.*;

class CustomerToProviderConverterTest {

    private ApiContext apiContext;
    private CustomerToProviderConverter converter;

    @BeforeEach
    void setup() {
        apiContext = mock(ApiContext.class);
        converter = new CustomerToProviderConverter(apiContext);
    }

    @Test
    void createRequestOfferUpdate_channelPresent() {

        InstantCardRequest request = mock(InstantCardRequest.class, RETURNS_DEEP_STUBS);
        Metadata metadata = new Metadata();

        when(apiContext.getMetadata()).thenReturn(metadata);

        when(request.getData().getBody().getCustomerNumber())
                .thenReturn("12345");

        when(request.getData().getBody().getOfferCode())
                .thenReturn("OF001");

        when(request.getData().getBody().getChannel())
                .thenReturn("APP");

        when(request.getData().getBody().getRequestNumberMantiz())
                .thenReturn("REQ001");
        request.getData().setHeader(new Header());

        CustomerOfferUpdateRequest response =
                converter.createRequestOfferUpdate(request);

        assertNotNull(response);
        assertEquals(metadata, response.getMetadata());

        assertEquals("12345",
                response.getData().getBody().getCustomerNumber());

        assertEquals("OF001",
                response.getData().getBody().getOfferCode());

        assertEquals("APP",
                response.getData().getBody().getChannel());

        assertEquals(CustomerToProviderConverter.OFFER_FORMALIZADA,
                response.getData().getBody().getStatusOffer());

        assertEquals("REQ001",
                response.getData().getBody().getRequestNumber());

        assertEquals(Header.class,
                response.getData().getHeader().getClass());
    }

    @Test
    void createRequestOfferUpdate_channelNull_shouldUseApplicationId() {

        InstantCardRequest request = mock(InstantCardRequest.class, RETURNS_DEEP_STUBS);
        Metadata metadata = new Metadata();

        when(apiContext.getMetadata()).thenReturn(metadata);

        when(request.getData().getBody().getCustomerNumber())
                .thenReturn("12345");

        when(request.getData().getBody().getOfferCode())
                .thenReturn("OF001");

        when(request.getData().getBody().getChannel())
                .thenReturn(null);

        when(request.getMetadata().getApplicationId())
                .thenReturn("APP-ID");

        when(request.getData().getBody().getRequestNumberMantiz())
                .thenReturn("REQ001");

        CustomerOfferUpdateRequest response =
                converter.createRequestOfferUpdate(request);

        assertEquals("APP-ID",
                response.getData().getBody().getChannel());
    }
}