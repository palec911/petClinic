package pl.sda.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.petclinic.model.VetSpeciality;

public interface VetSpecialityRepository extends JpaRepository<VetSpeciality, Long> {
}