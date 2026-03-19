package com.orchestration.instantcard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = {
                "Secret-firstdata=test-secret",
                "Apikey-firstdata=test-apikey",
                "customeroffer-update-url=http://localhost/test"
        }
)
class InstantcardApplicationTest {


    @Test
    void contextLoads() {
        // Si el contexto levanta correctamente, el test pasa.
    }
}