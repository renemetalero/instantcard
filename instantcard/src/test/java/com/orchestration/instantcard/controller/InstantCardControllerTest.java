package com.orchestration.instantcard.controller;

import com.orchestration.instantcard.service.InstantCardService;
import com.orchestration.instantcard.utils.Authorization;
import com.orchestration.instantcard.context.ApiContext;
import com.orchestration.instantcard.utils.CreatePojos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InstantCardController.class)
@Import(InstantCardControllerTest.MockConfig.class)
class InstantCardControllerTest {

    @Autowired
    private MockMvc mock;

    @TestConfiguration
    static class MockConfig {

        @Bean
        InstantCardService instantCardService() {
            return Mockito.mock(InstantCardService.class);
        }

        @Bean
        Authorization authorization() {
            return Mockito.mock(Authorization.class);
        }

        @Bean
        ApiContext apiContext() {
            return Mockito.mock(ApiContext.class);
        }

        @Bean
        CreatePojos createPojos() {
            return Mockito.mock(CreatePojos.class);
        }
    }

    @Test
    @DisplayName("Validar proceso de modelado automático de las anotaciones")
    void testUnitario_validateModelAutomatic() throws Exception {

        String body = """
                {"metadata":null,"data":null}
                """;

        mock.perform(post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
    }
}