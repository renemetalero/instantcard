package com.orchestration.instantcard.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class HttpExceptionUtilTest {
    private HttpExceptionUtil httpExceptionUtil;

    @BeforeEach
    void setUp() {
        httpExceptionUtil = new HttpExceptionUtil();
    }

    @Test
    void testCheckExceptionWithValidHttpException() {
        HttpStatusCodeException httpException = mock(HttpStatusCodeException.class);
        String mockResponse = "{\"error\":\"Test error\",\"code\":400}";
        when(httpException.getResponseBodyAsString()).thenReturn(mockResponse);

        Optional<TestErrorResponse> result = httpExceptionUtil.checkException(httpException, TestErrorResponse.class);

        assertTrue(result.isPresent());
        assertEquals("Test error", result.get().getError());
        assertEquals(400, result.get().getCode());
    }

    @Test
    void testCheckExceptionWithNonHttpException() {
        Exception genericException = new RuntimeException("Generic exception");
        Optional<TestErrorResponse> result = httpExceptionUtil.checkException(genericException, TestErrorResponse.class);
        assertFalse(result.isPresent());
    }

    static class TestErrorResponse {
        private String error;
        private int code;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public int getCode() {
            return code;
        }
    }
}
