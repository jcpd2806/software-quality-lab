package mx.edu.cetys.software_quality_lab.pets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PetControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

    // BeforeAll
    // AfterAll
    // BeforeEach
    // AfterEach

    @BeforeEach
    public void tearDown() {
        petRepository.deleteAll();
    }

    // @Named
    @Test
    void shouldCreatePetAndReturn201() throws Exception {
        String requestBody =
                """
                {
                    "name": "pop",
                    "color": "chihuahua",
                    "race": "azul",
                    "age": 17
                }
                """;

        mockMvc.perform(
                post("/pet")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
        // Assert Mode: ON
        .andExpect(status().isCreated()) // Validar Status 201
        .andExpect(jsonPath("$.info")
                .value("New Pet was added"))
        .andExpect(jsonPath("$.response.name")
                .value("pop"));
//        .andExpect(jsonPath("$.response.race")
//                .value("chihuahua"))
//        .andExpect(jsonPath("$.response.color")
//                .value("azul"))
//        .andExpect(jsonPath("$.response.age")
//                .value(17));
    }



}
