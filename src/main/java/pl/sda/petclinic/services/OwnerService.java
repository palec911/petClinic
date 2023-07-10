package pl.sda.petclinic.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.sda.petclinic.model.Owner;
import pl.sda.petclinic.repositories.OwnerRepository;

import java.util.List;

@Service
public class OwnerService {

    OwnerRepository ownerRepository;
    PetService petService;

    public OwnerService(OwnerRepository ownerRepository,
                           PetService petService) {
        this.ownerRepository = ownerRepository;
        this.petService = petService;
    }

    @Transactional
    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    public Owner getOwnerById(long id) {
        return ownerRepository.findById(id).get();
    }

    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

}