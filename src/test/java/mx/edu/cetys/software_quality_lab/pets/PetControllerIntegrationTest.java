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
        String requestBody = """
                        {
                            "name": "pop",
                            "race": "dalmate",
                            "color": "blanco y negro",
                            "age": 1
                        }""";

        mockMvc.perform(
                        //request setup
                        post("/pets")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                // assertmode on
                .andExpect(status().isCreated()) //validar status 201
                .andExpect(jsonPath("$.info").value("New pet was added"))
                .andExpect(jsonPath("$.response.pet.color").value("blanco y negro"))
                .andExpect(jsonPath("$.response.pet.race").value("dalmate"))
                .andExpect(jsonPath("$.response.pet.name").value("pop"))
        ;

    }
}
