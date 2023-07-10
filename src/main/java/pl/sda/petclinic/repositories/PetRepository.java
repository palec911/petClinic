package pl.sda.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.petclinic.model.Pet;


public interface PetRepository extends JpaRepository<Pet, Long> {

}