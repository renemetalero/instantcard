package com.orchestration.instantcard.controladvice;

import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.exception.models.ApplicationException;
import com.orchestration.instantcard.exception.models.BusinessException;
import com.orchestration.instantcard.exception.models.ErrorModel;
import com.orchestration.instantcard.exception.models.ValidationsException;
import com.orchestration.instantcard.models.response.InstantCardResponse;
import com.orchestration.instantcard.utils.Authorization;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class RestExceptionHandlerTest {

    private RestExceptionHandler restExceptionHandler;

    @MockBean
    private Authorization authorizations;

    @Mock
    private ApiContext apiContext;

    @InjectMocks
    private CreatePojos create;

    @BeforeEach
    void setUp() {
        this.authorizations = new Authorization(apiContext);
        this.restExceptionHandler = new RestExceptionHandler(authorizations);
    }

    @Test
    @DisplayName(value = "Test handle exception")
    void testHandleBusinesException() {
        when(apiContext.getMetadata()).thenReturn(create.createInstantCard().getMetadata());
        List<ErrorModel> errors = List.of(new ErrorModel("500", "Test message", "Test message"));
        BusinessException bussinessException = new BusinessException("500", "Test message", errors);
        this.restExceptionHandler.handleBusinesException(bussinessException);
        Assertions.assertNotNull(bussinessException);
    }

    @Test
    @DisplayName(value = "Test title not null handle exception")
    void testHandleTitleNotNullBusinesException() {
        when(apiContext.getMetadata()).thenReturn(create.createInstantCard().getMetadata());
        List<ErrorModel> errors = List.of(new ErrorModel("500", "title test", "Test message"));
        ValidationsException validationsException = new ValidationsException(errors, "title test");
        ResponseEntity<InstantCardResponse> validationsExceptionResponse = this.restExceptionHandler.handleValidationException(validationsException);
        Assertions.assertNotNull(validationsExceptionResponse);
    }

    @Test
    @DisplayName(value = "Test Aplication Exception")
    void testAplicationException() {
        when(apiContext.getMetadata()).thenReturn(create.createInstantCard().getMetadata());
        ErrorModel errorModel = new ErrorModel("500", "title test", "Test message");
        List<ErrorModel> errors = List.of(errorModel);
        ApplicationException validationsException = new ApplicationException("Message test", errors);
        ResponseEntity<InstantCardResponse> apllicationExceptionResponse = this.restExceptionHandler.handleApplicationException(validationsException);
        Assertions.assertNotNull(apllicationExceptionResponse);
        Assertions.assertEquals("ErrorModel{code='500', title='title test', detail='Test message'}",errorModel.toString());
    }




}
