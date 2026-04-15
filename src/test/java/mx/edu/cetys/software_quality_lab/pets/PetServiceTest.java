package mx.edu.cetys.software_quality_lab.pets;

import mx.edu.cetys.software_quality_lab.pets.exceptions.InvalidPetDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    PetRepository  petRepository;

    @InjectMocks
    PetService petService;

    @Test
    void savePet()
    {

        // Arrange

        var petRequest = new PetController.PetRequest("Lu","Negro","Perro",5);

        var mockPetDB = new Pet("Lu", "Negro", "Perro", 5);
        mockPetDB.setId(1L);

        when(petRepository.save(any())).thenReturn(mockPetDB);

        // Act
        var pet = petService.savePet(petRequest);

        // Assert
        Mockito.verify(
                        petRepository, times(1)
                )
                .save(any());

        assertEquals(1L , pet.id());
        assertEquals("Lu", pet.name());
        assertEquals("Perro", pet.race());

        // Recibir la peticion desde el controller (Pet)
        // Verificar los valores del pet:
        // - Edad no sea negativa:
        // - Nombre mas de 2 letras
        // - Salvar a la BD, y la base datos nos regresa el mismo pet pero con ID

    }

    @Test
    void savePet_IvalidName_ExceptionExpected() {

        // Arrange
        var petRequest = new PetController.PetRequest("L","Negro","Perro",5);

        // Assert
        assertThrows(InvalidPetDataException.class, () -> petService.savePet(petRequest));
    }

    @Test
    void getAllPets()
    {
        // Recibir la peticion desde el controller GetAll
        // Query a la BD
        // Mapear los valores del DTO -> Pet Respuesta
        // Si la BD no tiene valores, no fallar request, solo regresar un array vacio

    }
}
