package pl.sda.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.petclinic.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

}
