package pl.sda.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.petclinic.model.Note;

public interface NotesRepository extends JpaRepository<Note, Long> {
}
