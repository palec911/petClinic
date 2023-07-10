package pl.sda.petclinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long visitId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate visitDate;
    private String description;
    @Column(name = "pet_id")
    Long petId;

    public Visit(LocalDate visitDate, String description, Long petId) {
        this.visitDate = visitDate;
        this.description = description;
        this.petId = petId;
    }
}