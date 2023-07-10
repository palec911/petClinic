package pl.sda.petclinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private Set<Pet> pets = new HashSet<>();

    public Owner(String firstName, String lastName, String address, String city, Set<Pet> pets) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.pets = pets;
    }

    public Owner(String firstName, String lastName, String address, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
    }
}