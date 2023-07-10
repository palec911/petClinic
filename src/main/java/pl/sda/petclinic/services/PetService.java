package pl.sda.petclinic.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.petclinic.model.Pet;
import pl.sda.petclinic.repositories.OwnerRepository;
import pl.sda.petclinic.repositories.PetRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public Optional<Pet> getPetById(Long id) {
        return Optional.of(petRepository.findById(id)).orElse(null);
    }

    public Optional<Pet> createPet(Pet pet) {
        return Optional.of(petRepository.save(pet));
    }

    public Set<Pet> getPetsByOwnerId(Long ownerId) {
        if(ownerRepository.findById(ownerId).isPresent()) {
            return ownerRepository.findById(ownerId).get().getPets();
        }
        return new HashSet<>();
    }

    public List<Pet> getAll() {
        return petRepository.findAll();
    }

    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }
}