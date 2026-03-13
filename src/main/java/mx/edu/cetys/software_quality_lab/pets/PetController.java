package mx.edu.cetys.software_quality_lab.pets;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pet") // localhost:8080/pets <- base url
public class PetController {

    // HTTP Verbs: POST, GET, PUT, PATCH, DELETE

    // GET localhost:8080/pets --TODO los pets, TODO pagination
    // GET localhost:8080/pets{id} -- Pet by ID

    // POST localhost:8080/pets -- Nuevo Pet - RequestBody {json body}

    // PUT localhost:8080/pets{id} -- Actualizar PET por ID

    // DELETE localhost:8080/pets{id} -- Flag available: yes / no

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // DTOs (Data Transfer Object)
    record PetRequest(String name, String color, String race, int age){}
    record PetResponse(Long id, String name, String color, String race, int age){}

    // Response Generic Wrapper to include standarize info in all our APIs
    record ApiResponse<T>(String info, T response, String error){}

    @GetMapping("/help")
    ApiResponse<PetResponse> help(){
        return new ApiResponse<>("Hola I'am an API Response for the help API", null, null);
    }

    @PostMapping
    ApiResponse<PetResponse> createPet(@RequestBody PetController.PetRequest requestPet) {
        return new ApiResponse<>("New Pet was added", petService.savePet(requestPet), null);
    }



}
