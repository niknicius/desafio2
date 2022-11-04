package tech.dock.paymentapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.dock.paymentapi.controller.PersonController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class PaymentapiApplicationTests {

    @Autowired
    private PersonController personController;
    @Test
    void contextLoads() {
        assertThat(personController).isNotNull();
    }

}
