package mx.edu.cetys.software_quality_lab.pets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PetService {
    private final Logger log = LoggerFactory.getLogger(PetService.class);

    // @Autowired Spring since 2014 is able to Inject without explicit Autowired aspect/annotatio
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    PetController.PetResponse savePet(PetController.PetRequest requestPet) {
        log.info("Starting Pet Request Validation, requestPet={}", requestPet);

        // TODO validation
        // Name length > 2 char
        // Age > 0
        // Color is not empty (extra validation)

        var savePet = petRepository.save(new Pet(requestPet.name(), requestPet.race(), requestPet.color(), requestPet.age()));

        return new PetController.PetResponse(savePet.getId(), savePet.getName(), savePet.getRace(), savePet.getColor(), savePet.getAge());
    }
}
