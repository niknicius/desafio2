package tech.dock.paymentapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import tech.dock.paymentapi.core.util.MapperUtil;
import tech.dock.paymentapi.dto.PersonDTO;
import tech.dock.paymentapi.repository.PersonRepository;

import java.time.LocalDate;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class PersonControllerTests {

    private static final String baseUrl = "/persons";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository repository;

    @Test
    void shouldListReturnEmpty() throws Exception {
        this.mockMvc.perform(get(baseUrl).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("numberOfElements").value(0));
    }

    @Test
    void shouldErrorNonexistentPerson() throws Exception {
        this.mockMvc.perform(get(baseUrl.concat("/1000"))).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void shouldCreatePerson() throws Exception {
        this.mockMvc.perform(post(baseUrl)
                .content(MapperUtil.asJsonString(new PersonDTO("Arya Stark", "123.456.789-10", new Date())))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }


}
