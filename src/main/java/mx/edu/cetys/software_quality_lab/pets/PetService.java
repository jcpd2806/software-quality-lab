package mx.edu.cetys.software_quality_lab.pets;

import mx.edu.cetys.software_quality_lab.pets.exceptions.InvalidPetDataException;
import mx.edu.cetys.software_quality_lab.pets.exceptions.PetNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private final Logger log = LoggerFactory.getLogger(PetService.class);
    // @Autowired spring has been ave to inject without implicit autowired annotation since 2014
    private final PetRepository petRepository;


    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

//    public record PetDTO(Long petId,
//                         String name,
//                         String color,
//                         String race,
//                         Integer age){}
//
//    public PetDTO savePet(PetRepository.Pet pet){
//        petRepository.
//    }

    PetController.PetResponse savePet(PetController.PetRequest requestPet){
        log.info("Starting Pet Request validation, requestPet={}", requestPet);
        //TODO validation
        //name length > 2
        if(requestPet.name().isEmpty()
                || requestPet.name().isBlank()
                || requestPet.name().length() < 2){
            throw new InvalidPetDataException("Pet name is invalid");
        }
        //TODO regresar un 400 si no se cumple validacion
        //Age > 0
        //TODO regresar un 400 si no se cumple validacion
        //Color is not empty

        return getPetResponseMapper(petRepository.save(
                new Pet(requestPet.name(),
                        requestPet.race(),
                        requestPet.color(),
                        requestPet.age()
                )
        ));
    }

    public PetController.PetResponse getPetById(Long petId){
        log.info("Starting Pet request validation, pet={}",petId);

        //validar si petId es conrrecto (numerico, mayor a o) else return 400

        var petFound = petRepository.findById(petId);
        if(petFound.isEmpty()){
            throw new PetNotFoundException("Pet with id " +petId + " not found ");
            //throw 404 //TODO create 404 exception
        }

        //what if the petFound is null? or empty or not found
        // do we throw an exception or handle it by the controllerAdvice?
        //YES WE THROW EXCEPTION, AND handle it in the advicer

        var realPet = petFound.get();
        return getPetResponseMapper(realPet);
    }

    private static PetController.PetResponse getPetResponseMapper(Pet realPet) {
        return new PetController.PetResponse(
                realPet.getId(),
                realPet.getName(),
                realPet.getRace(),
                realPet.getColor(),
                realPet.getAge());
    }
}