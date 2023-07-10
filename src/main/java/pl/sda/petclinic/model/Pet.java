package pl.sda.petclinic.model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notes_id")
    private Note note;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_id")
    private Set<Visit> visits = new HashSet<>();

    public Pet(String name, LocalDate date, PetType petType, Note note, Set<Visit> visits) {
        this.name = name;
        this.date = date;
        this.petType = petType;
        this.note = note;
        this.visits = visits;
    }
    public Pet(String name, LocalDate date, PetType petType, Note note) {
        this.name = name;
        this.date = date;
        this.petType = petType;
        this.note = note;
    }
}