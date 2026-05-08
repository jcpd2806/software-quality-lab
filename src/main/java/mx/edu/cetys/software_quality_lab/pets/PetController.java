package mx.edu.cetys.software_quality_lab.pets;

import mx.edu.cetys.software_quality_lab.commons.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets") // localhost:8080/pets
public class PetController {

    //GET localhost:8080/pets -- todos los pets
    //GET localhost:8080/pets/{id} -- pet by id
    //POST localhost:8080/pets -- nuevo pet con request body
    //PUT localhost:8080/pets/{id} -- Actualizar pet por ID
    //DELETE localhost:8080/pets{id} // Flag available:yes/no


//    record PetRequest(String name, String color, String race){}
//    record PetResponse(Long id, String name){}
//    private record Response(String info, Pet pet){}
//
//    @GetMapping("/hello")
//    public ResponseEntity<String> helo(){
//        return ResponseEntity.ok("HEllo");
//    }
//
    ////    @GetMapping("/help")
    ////    public String help(){
    ////        return "This is the help api";
    ////    }
//
//    @GetMapping("/help")
//    public Response help(){
//        return new
//                Response("This is the help api",null);
//    }

    private final PetService petService;

    public PetController(PetService petService){this.petService = petService;}

    // DTO for request and response
    record PetRequest(String name, String race, String color, Integer age){}
    record PetResponse(Long id, String name, String race, String color, Integer age){}

    // response generic wrapper to include standarized info in all our APIs
    public record  PetWrapper(PetResponse pet){}

    @GetMapping("/help")
    ApiResponse<PetWrapper> help(){
        return new ApiResponse<>("This is the help API", null, null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<PetWrapper> createPet(@RequestBody PetController.PetRequest requestPet){
        return new ApiResponse<>("New pet was added", new PetWrapper(petService.savePet(requestPet)), null);
    }

    @GetMapping("/{petId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ApiResponse<PetWrapper> findPetById(@PathVariable Long petId){
        var pet = petService.getPetById(petId);
        return new ApiResponse<>("Pet found", new PetWrapper(pet), null);
    }
}