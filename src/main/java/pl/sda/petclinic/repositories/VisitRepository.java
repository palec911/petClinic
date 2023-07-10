package pl.sda.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.petclinic.model.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}