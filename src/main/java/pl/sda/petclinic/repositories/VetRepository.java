package pl.sda.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.petclinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long> {
}